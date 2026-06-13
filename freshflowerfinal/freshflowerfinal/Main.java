package freshflowerfinal;

import freshflowerfinal.dao.*;
import freshflowerfinal.model.*;
import freshflowerfinal.datastructure.NodeKeranjang;
import java.util.Scanner;
import java.util.List;

public class Main {
    // DAOs
    private static final BucketDAO bucketDAO = new BucketDAO();
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final TransaksiDAO transaksiDAO = new TransaksiDAO();

    // Authenticated credentials / active session state
    private static final Admin admin = new Admin();
    private static final Owner owner = new Owner();
    private static Customer activeCustomer;
    private static Transaksi activeTransaksi;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Cek koneksi database MySQL
        try (java.sql.Connection conn = freshflowerfinal.database.KoneksiDB.getConnection()) {
            if (conn == null) {
                System.out.println("Gagal menghubungkan ke Database MySQL! Harap pastikan MySQL telah di-Start.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Gagal menghubungkan ke Database!");
            System.exit(0);
        }

        while (true) {
            System.out.println("\n==============================================");
            System.out.println("          ✿ FRESH FLOWER SHOP Menu ✿          ");
            System.out.println("==============================================");
            System.out.println("Pilih Role Anda:");
            System.out.println("1. Pembeli (Guest)");
            System.out.println("2. Member");
            System.out.println("3. Admin");
            System.out.println("4. Owner (Pemilik)");
            System.out.println("5. Keluar");
            System.out.print("Masukkan Pilihan [1-5]: ");
            String pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1":
                    runPembeliGuestFlow();
                    break;
                case "2":
                    runMemberFlow();
                    break;
                case "3":
                    runAdminFlow();
                    break;
                case "4":
                    runOwnerFlow();
                    break;
                case "5":
                    System.out.println("Terima kasih telah berkunjung ke toko kami!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid! Masukkan angka antara 1 sampai 5.");
            }
        }
    }

    private static void runPembeliGuestFlow() {
        System.out.print("Masukkan Nama Anda: ");
        String nama = scanner.nextLine().trim();
        if (nama.isEmpty()) {
            System.out.println("Nama tidak boleh kosong!");
            return;
        }
        
        // Register Guest
        String idCustomer = "GST" + (System.currentTimeMillis() % 10000);
        activeCustomer = new Customer(idCustomer, nama, "-", "-", false, null);
        customerDAO.tambahCustomer(activeCustomer);
        
        int newId = (int) (System.currentTimeMillis() / 1000);
        activeTransaksi = new Transaksi(newId, activeCustomer, 99);

        System.out.println("\nSelamat Datang Guest: " + activeCustomer.getNama());
        runShopFlow();
    }

    private static void runMemberFlow() {
        System.out.print("Masukkan ID Member: ");
        String id = scanner.nextLine().trim();
        System.out.print("Masukkan Password: ");
        String pass = scanner.nextLine().trim();

        Customer member = customerDAO.loginMember(id, pass);
        if (member == null) {
            System.out.println("ID Member atau Password salah!");
            return;
        }
        activeCustomer = member;
        int newId = (int) (System.currentTimeMillis() / 1000);
        activeTransaksi = new Transaksi(newId, activeCustomer, 99);

        while (true) {
            System.out.println("\n----------------------------------------------");
            System.out.println("Dashboard Member: " + member.getNama() + " (Diskon 5% Aktif)");
            System.out.println("----------------------------------------------");
            System.out.println("1. Mulai Belanja");
            System.out.println("2. Ubah Password");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih menu [1-3]: ");
            String pilihan = scanner.nextLine().trim();

            if (pilihan.equals("1")) {
                runShopFlow();
            } else if (pilihan.equals("2")) {
                System.out.print("Masukkan password baru: ");
                String newPass = scanner.nextLine().trim();
                if (!newPass.isEmpty()) {
                    customerDAO.ubahPassword(activeCustomer.getIdCustomer(), newPass);
                    activeCustomer.setPassword(newPass);
                    System.out.println("Password berhasil diubah!");
                } else {
                    System.out.println("Gagal mengubah password! Password tidak boleh kosong.");
                }
            } else if (pilihan.equals("3")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void runShopFlow() {
        while (true) {
            System.out.println("\n----------------------------------------------");
            System.out.println("                KATALOG BARANG                ");
            System.out.println("----------------------------------------------");
            List<Bucket> catalog = bucketDAO.getAllBucket();
            System.out.printf("%-5s | %-25s | %-10s | %-12s | %-6s\n", "ID", "Nama Barang", "Jenis", "Harga", "Stok");
            System.out.println("------------------------------------------------------------------------");
            for (Bucket b : catalog) {
                String jenis = b instanceof Bunga ? "Bunga" : (b instanceof Boneka ? "Boneka" : "Jajan");
                System.out.printf("%-5d | %-25s | %-10s | Rp %-10.0f | %-6d\n", 
                    b.getIdBucket(), b.getNama(), jenis, b.getHarga(), b.getStok());
            }
            System.out.println("------------------------------------------------------------------------");

            System.out.println("1. Tambah Barang ke Keranjang");
            System.out.println("2. Undo (Hapus Barang Terakhir)");
            System.out.println("3. Lihat Keranjang & Total Bayar");
            System.out.println("4. Selesai & Bayar (Kirim ke Antrean)");
            System.out.println("5. Batal & Kembali");
            System.out.print("Pilih Menu [1-5]: ");
            String menu = scanner.nextLine().trim();

            if (menu.equals("1")) {
                System.out.print("Masukkan ID Barang: ");
                try {
                    int idBucket = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Masukkan Jumlah: ");
                    int qty = Integer.parseInt(scanner.nextLine().trim());
                    if (qty <= 0) {
                        System.out.println("Jumlah harus lebih dari 0!");
                        continue;
                    }
                    
                    // Add to cart directly
                    List<Bucket> buckets = bucketDAO.getAllBucket();
                    Bucket target = null;
                    for (Bucket b : buckets) {
                        if (b.getIdBucket() == idBucket) {
                            target = b;
                            break;
                        }
                    }
                    
                    if (target != null && qty <= target.getStok()) {
                        activeTransaksi.tambahKeKeranjang(target, qty);
                        bucketDAO.kurangiStok(idBucket, qty);
                        System.out.println("Berhasil menambahkan ke keranjang.");
                    } else {
                        System.out.println("Gagal menambahkan! Stok tidak mencukupi atau ID salah.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka!");
                }
            } else if (menu.equals("2")) {
                if (activeTransaksi != null) {
                    NodeKeranjang popped = activeTransaksi.getKeranjang().pop();
                    if (popped != null) {
                        Bucket b = popped.getBucket();
                        bucketDAO.kurangiStok(b.getIdBucket(), -popped.getJumlah());
                        
                        double totalSem = activeTransaksi.getKeranjang().hitungTotalBayar();
                        activeTransaksi.setTotalBayar(totalSem * (activeCustomer.isMember() ? 0.95 : 1.0));
                        System.out.println("Berhasil menghapus: " + b.getNama() + " (" + popped.getJumlah() + " pcs)");
                    } else {
                        System.out.println("Keranjang kosong!");
                    }
                } else {
                    System.out.println("Keranjang kosong!");
                }
            } else if (menu.equals("3")) {
                tampilkanKeranjang();
            } else if (menu.equals("4")) {
                if (activeTransaksi != null && activeTransaksi.getUkuranKeranjang() > 0) {
                    double total = activeTransaksi.getTotalBayar();
                    int status = transaksiDAO.simpanTransaksi(activeTransaksi, activeTransaksi.getKeranjang());
                    if (status == 1) {
                        System.out.println("Transaksi berhasil disimpan! Total Bayar: Rp " + String.format("%,.0f", total));
                        System.out.println("Silakan lakukan pembayaran di kasir (Admin).");
                        activeTransaksi = null;
                        activeCustomer = null;
                        break;
                    } else {
                        System.out.println("Gagal menyimpan transaksi!");
                    }
                } else {
                    System.out.println("Keranjang belanja masih kosong!");
                }
            } else if (menu.equals("5")) {
                if (activeTransaksi != null) {
                    NodeKeranjang curr = activeTransaksi.getKeranjang().peek();
                    while (curr != null) {
                        bucketDAO.kurangiStok(curr.getBucket().getIdBucket(), -curr.getJumlah());
                        curr = curr.getNext();
                    }
                    activeTransaksi = null;
                    activeCustomer = null;
                }
                System.out.println("Pembelian dibatalkan.");
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tampilkanKeranjang() {
        if (activeTransaksi == null) {
            System.out.println("Belum ada transaksi aktif!");
            return;
        }
        System.out.println("\n----------------------------------------------");
        System.out.println("               KERANJANG BELANJA              ");
        System.out.println("----------------------------------------------");
        NodeKeranjang curr = activeTransaksi.getKeranjang().peek();
        if (curr == null) {
            System.out.println("(Keranjang Kosong)");
        } else {
            System.out.printf("%-25s | %-8s | %-12s\n", "Nama Barang", "Jumlah", "Total Harga");
            System.out.println("--------------------------------------------------");
            while (curr != null) {
                System.out.printf("%-25s | %-8d | Rp %-10.0f\n", 
                    curr.getBucket().getNama(), curr.getJumlah(), curr.getTotalHarga());
                curr = curr.getNext();
            }
            System.out.println("--------------------------------------------------");
            System.out.println("Total Bayar: Rp " + String.format("%,.0f", activeTransaksi.getTotalBayar()));
        }
    }

    private static void runAdminFlow() {
        System.out.print("Masukkan Username Admin: ");
        String user = scanner.nextLine().trim();
        System.out.print("Masukkan Password Admin: ");
        String pass = scanner.nextLine().trim();

        if (!admin.login(user, pass)) {
            System.out.println("Username atau Password Admin salah!");
            return;
        }

        while (true) {
            System.out.println("\n----------------------------------------------");
            System.out.println("               KASIR / ADMIN PANEL            ");
            System.out.println("----------------------------------------------");
            System.out.println("1. Lihat Antrean Transaksi (FIFO)");
            System.out.println("2. Proses Transaksi Pertama");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih Menu [1-3]: ");
            String menu = scanner.nextLine().trim();

            if (menu.equals("1")) {
                List<Transaksi> antrean = transaksiDAO.getAntreanPesanan();
                if (antrean.isEmpty()) {
                    System.out.println("(Antrean Kosong)");
                } else {
                    System.out.printf("%-15s | %-20s | %-10s | %-12s\n", "ID Transaksi", "Nama Pelanggan", "Status", "Total Bayar");
                    System.out.println("------------------------------------------------------------------------");
                    for (Transaksi t : antrean) {
                        String statusPelanggan = t.getCustomer().isMember() ? "Member" : "Guest";
                        System.out.printf("%-15d | %-20s | %-10s | Rp %-10.0f\n", 
                            t.getIdTransaksi(), t.getCustomer().getNama(), statusPelanggan, t.getTotalBayar());
                    }
                }
            } else if (menu.equals("2")) {
                List<Transaksi> antrean = transaksiDAO.getAntreanPesanan();
                if (!antrean.isEmpty()) {
                    Transaksi diproses = antrean.get(0);
                    transaksiDAO.prosesPesanan(diproses.getIdTransaksi());
                    System.out.println("Berhasil memproses Transaksi ID " + diproses.getIdTransaksi() + 
                                       " atas nama " + diproses.getCustomer().getNama());
                } else {
                    System.out.println("Tidak ada antrean transaksi!");
                }
            } else if (menu.equals("3")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void runOwnerFlow() {
        System.out.print("Masukkan Username Owner: ");
        String user = scanner.nextLine().trim();
        System.out.print("Masukkan Password Owner: ");
        String pass = scanner.nextLine().trim();

        if (!owner.login(user, pass)) {
            System.out.println("Username atau Password Owner salah!");
            return;
        }

        while (true) {
            System.out.println("\n----------------------------------------------");
            System.out.println("                   OWNER PANEL                ");
            System.out.println("----------------------------------------------");
            System.out.println("1. Kelola/Update Harga Barang");
            System.out.println("2. Laporan Akumulasi Pendapatan Barang");
            System.out.println("3. Laporan Akumulasi Belanja Member");
            System.out.println("4. Cetak Grafik Penjualan");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih Menu [1-5]: ");
            String menu = scanner.nextLine().trim();

            if (menu.equals("1")) {
                List<Bucket> items = bucketDAO.getAllBucket();
                System.out.printf("%-5s | %-25s | %-12s | %-6s\n", "ID", "Nama Barang", "Harga", "Stok");
                System.out.println("------------------------------------------------------------------------");
                for (Bucket b : items) {
                    System.out.printf("%-5d | %-25s | Rp %-10.0f | %-6d\n", b.getIdBucket(), b.getNama(), b.getHarga(), b.getStok());
                }
                System.out.println("------------------------------------------------------------------------");
                System.out.print("Masukkan ID Barang yang ingin diubah harganya: ");
                try {
                    int idBucket = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Masukkan Harga Baru (Rp): ");
                    double newPrice = Double.parseDouble(scanner.nextLine().trim());
                    if (newPrice < 0) {
                        System.out.println("Harga tidak valid!");
                        continue;
                    }
                    
                    List<Bucket> buckets = bucketDAO.getAllBucket();
                    Bucket target = null;
                    for (Bucket b : buckets) {
                        if (b.getIdBucket() == idBucket) {
                            target = b;
                            break;
                        }
                    }
                    if (target != null) {
                        target.setHarga(newPrice);
                        bucketDAO.ubahBucket(target);
                        System.out.println("Harga berhasil diperbarui.");
                    } else {
                        System.out.println("Gagal memperbarui! ID tidak ditemukan.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka!");
                }
            } else if (menu.equals("2")) {
                System.out.println("\n--- Laporan Pendapatan per Barang ---");
                List<TransaksiDAO.LaporanBarang> listBarang = transaksiDAO.getLaporanPendapatanBarangList();
                if (listBarang.isEmpty()) {
                    System.out.println("Belum ada transaksi.");
                } else {
                    int i = 1;
                    for (TransaksiDAO.LaporanBarang lb : listBarang) {
                        System.out.println(i + ". " + lb.getNama() + " : Rp " + String.format("%,.0f", lb.getTotal()));
                        i++;
                    }
                }
            } else if (menu.equals("3")) {
                System.out.println("\n--- Laporan Belanja Member ---");
                List<TransaksiDAO.LaporanMember> listMember = transaksiDAO.getLaporanBelanjaMemberList();
                if (listMember.isEmpty()) {
                    System.out.println("Belum ada transaksi member.");
                } else {
                    int i = 1;
                    for (TransaksiDAO.LaporanMember lm : listMember) {
                        System.out.println(i + ". " + lm.getNama() + " : Rp " + String.format("%,.0f", lm.getTotal()));
                        i++;
                    }
                }
            } else if (menu.equals("4")) {
                System.out.println("\n--- Grafik Penjualan (Setiap X mewakili Rp 10.000) ---");
                List<TransaksiDAO.LaporanBarang> listBarang = transaksiDAO.getLaporanPendapatanBarangList();
                if (listBarang.isEmpty()) {
                    System.out.println("Belum ada data penjualan.");
                } else {
                    for (TransaksiDAO.LaporanBarang lb : listBarang) {
                        int xCount = (int) (lb.getTotal() / 10000);
                        StringBuilder sb = new StringBuilder();
                        for (int k = 0; k < xCount; k++) {
                            sb.append("X");
                        }
                        System.out.printf("%-25s : %s (Rp %,.0f)\n", lb.getNama(), sb.toString(), lb.getTotal());
                    }
                }
            } else if (menu.equals("5")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }
}