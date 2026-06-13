package freshflowerfinal.dao;

import freshflowerfinal.database.KoneksiDB;
import freshflowerfinal.model.Transaksi;
import freshflowerfinal.model.Customer;
import freshflowerfinal.datastructure.StackKeranjang;
import freshflowerfinal.datastructure.NodeKeranjang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {
    
    public int simpanTransaksi(Transaksi transaksi, StackKeranjang keranjang) {
        String queryTransaksi = "INSERT INTO transaksi (id_transaksi, id_customer, total_bayar, sudah_dibayar, status_pesanan) VALUES (?, ?, ?, ?, ?)";
        String queryDetail = "INSERT INTO detail_transaksi (id_transaksi, id_bucket, jumlah, total_harga) VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        try {
            conn = KoneksiDB.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Insert Transaksi
            try (PreparedStatement stmt = conn.prepareStatement(queryTransaksi)) {
                stmt.setInt(1, transaksi.getIdTransaksi());
                stmt.setString(2, transaksi.getCustomer().getIdCustomer());
                stmt.setDouble(3, transaksi.getTotalBayar());
                stmt.setBoolean(4, transaksi.isSudahDibayar());
                stmt.setString(5, "Belum Diproses");
                stmt.executeUpdate();
            }

            // Insert Detail Transaksi
            try (PreparedStatement stmtDetail = conn.prepareStatement(queryDetail)) {
                NodeKeranjang current = keranjang.peek();
                while (current != null) {
                    stmtDetail.setInt(1, transaksi.getIdTransaksi());
                    stmtDetail.setInt(2, current.getBucket().getIdBucket());
                    stmtDetail.setInt(3, current.getJumlah());
                    stmtDetail.setDouble(4, current.getTotalHarga());
                    stmtDetail.addBatch();
                    current = current.getNext();
                }
                stmtDetail.executeBatch();
            }

            conn.commit();
            return 1;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return 0;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public List<Transaksi> getAntreanPesanan() {
        return getTransaksiByStatus("Belum Diproses");
    }

    public List<Transaksi> getAllTransaksi() {
        return getTransaksiByStatus(null);
    }

    public void prosesPesanan(int idTransaksi) {
        String query = "UPDATE transaksi SET status_pesanan = 'Selesai' WHERE id_transaksi = ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idTransaksi);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Transaksi> getTransaksiByStatus(String statusFilter) {
        List<Transaksi> list = new ArrayList<>();
        String query = "SELECT t.*, c.nama as cust_nama, c.alamat, c.nomor_telepon, c.is_member, c.password " +
                       "FROM transaksi t " +
                       "JOIN customer c ON t.id_customer = c.id_customer ";
        if (statusFilter != null) {
            query += "WHERE t.status_pesanan = ?";
        }

        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            if (statusFilter != null) {
                stmt.setString(1, statusFilter);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idTransaksi = rs.getInt("id_transaksi");
                    String idCustomer = rs.getString("id_customer");
                    String custNama = rs.getString("cust_nama");
                    String alamat = rs.getString("alamat");
                    String noTelp = rs.getString("nomor_telepon");
                    boolean isMember = rs.getBoolean("is_member");
                    String password = rs.getString("password");
                    
                    Customer customer = new Customer(idCustomer, custNama, alamat, noTelp, isMember, password);
                    Transaksi t = new Transaksi(idTransaksi, customer, 99);
                    t.setTotalBayar(rs.getDouble("total_bayar"));
                    t.setSudahDibayar(rs.getBoolean("sudah_dibayar"));
                    list.add(t);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cetakLaporanPendapatanBarang() {
        String query = "SELECT b.nama, SUM(d.total_harga) as total " +
                       "FROM detail_transaksi d " +
                       "JOIN bucket b ON d.id_bucket = b.id_bucket " +
                       "JOIN transaksi t ON t.id_transaksi = d.id_transaksi " +
                       "WHERE t.status_pesanan = 'Selesai' " +
                       "GROUP BY b.id_bucket";
        
        String queryTotal = "SELECT SUM(total_bayar) as total_pendapatan FROM transaksi WHERE status_pesanan = 'Selesai'";

        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement()) {
            
            double totalPendapatan = 0;
            ResultSet rsTotal = stmt.executeQuery(queryTotal);
            if (rsTotal.next()) {
                totalPendapatan = rsTotal.getDouble("total_pendapatan");
            }
            System.out.println("Total pendapatan : " + totalPendapatan);

            ResultSet rs = stmt.executeQuery(query);
            int i = 1;
            while (rs.next()) {
                String namaBarang = rs.getString("nama");
                double totalSales = rs.getDouble("total");
                System.out.println(i + ". " + namaBarang + " : " + totalSales);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cetakGrafikPenjualanBarang() {
        String query = "SELECT b.nama, SUM(d.total_harga) as total " +
                       "FROM detail_transaksi d " +
                       "JOIN bucket b ON d.id_bucket = b.id_bucket " +
                       "JOIN transaksi t ON t.id_transaksi = d.id_transaksi " +
                       "WHERE t.status_pesanan = 'Selesai' " +
                       "GROUP BY b.id_bucket";

        System.out.println("== Grafik Penjualan (Puluhan Ribu) ==");
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                String namaBarang = rs.getString("nama");
                double totalSales = rs.getDouble("total");
                
                int numX = (int) (totalSales / 10000);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < numX; j++) {
                    sb.append("X");
                }
                
                System.out.printf("%-10s : %s %.1f\n", namaBarang, sb.toString(), totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cetakLaporanBelanjaMember() {
        String query = "SELECT c.nama, SUM(t.total_bayar) as total " +
                       "FROM transaksi t " +
                       "JOIN customer c ON t.id_customer = c.id_customer " +
                       "WHERE c.is_member = TRUE AND t.status_pesanan = 'Selesai' " +
                       "GROUP BY c.id_customer";

        System.out.println("== Total Belanja per Member ==");
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            int i = 1;
            boolean found = false;
            while (rs.next()) {
                found = true;
                String namaMember = rs.getString("nama");
                double totalBelanja = rs.getDouble("total");
                System.out.println(i + ". " + namaMember + " : " + totalBelanja);
                i++;
            }
            if (!found) {
                System.out.println("Belum ada data transaksi member.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getTotalPendapatanSelesai() {
        String queryTotal = "SELECT SUM(total_bayar) as total_pendapatan FROM transaksi WHERE status_pesanan = 'Selesai'";
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryTotal)) {
            if (rs.next()) {
                return rs.getDouble("total_pendapatan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static class LaporanBarang {
        private String nama;
        private double total;
        public LaporanBarang(String nama, double total) {
            this.nama = nama;
            this.total = total;
        }
        public String getNama() { return nama; }
        public double getTotal() { return total; }
    }

    public static class LaporanMember {
        private String nama;
        private double total;
        public LaporanMember(String nama, double total) {
            this.nama = nama;
            this.total = total;
        }
        public String getNama() { return nama; }
        public double getTotal() { return total; }
    }

    public List<LaporanBarang> getLaporanPendapatanBarangList() {
        List<LaporanBarang> list = new ArrayList<>();
        String query = "SELECT b.nama, SUM(d.total_harga) as total " +
                       "FROM detail_transaksi d " +
                       "JOIN bucket b ON d.id_bucket = b.id_bucket " +
                       "JOIN transaksi t ON t.id_transaksi = d.id_transaksi " +
                       "WHERE t.status_pesanan = 'Selesai' " +
                       "GROUP BY b.id_bucket";
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new LaporanBarang(rs.getString("nama"), rs.getDouble("total")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<LaporanMember> getLaporanBelanjaMemberList() {
        List<LaporanMember> list = new ArrayList<>();
        String query = "SELECT c.nama, SUM(t.total_bayar) as total " +
                       "FROM transaksi t " +
                       "JOIN customer c ON t.id_customer = c.id_customer " +
                       "WHERE c.is_member = TRUE AND t.status_pesanan = 'Selesai' " +
                       "GROUP BY c.id_customer";
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new LaporanMember(rs.getString("nama"), rs.getDouble("total")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
