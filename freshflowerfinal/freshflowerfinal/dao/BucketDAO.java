package freshflowerfinal.dao;

import freshflowerfinal.database.KoneksiDB;
import freshflowerfinal.model.Bucket;
import freshflowerfinal.model.Bunga;
import freshflowerfinal.model.Boneka;
import freshflowerfinal.model.Jajan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDAO {
    
    public void tambahBucket(Bucket bucket, String jenis) {
        String query = "INSERT INTO bucket (nama, jenis, harga, stok) VALUES (?, ?, ?, ?)";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bucket.getNama());
            stmt.setString(2, jenis);
            stmt.setDouble(3, bucket.getHarga());
            stmt.setInt(4, bucket.getStok());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bucket> getAllBucket() {
        List<Bucket> buckets = new ArrayList<>();
        String query = "SELECT * FROM bucket";
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int idBucket = rs.getInt("id_bucket");
                String nama = rs.getString("nama");
                String jenis = rs.getString("jenis");
                double harga = rs.getDouble("harga");
                int stok = rs.getInt("stok");

                Bucket bucket = null;
                if ("Bunga".equals(jenis)) {
                    bucket = new Bunga(harga, stok, nama);
                } else if ("Boneka".equals(jenis)) {
                    bucket = new Boneka(harga, stok, nama);
                } else if ("Jajan".equals(jenis)) {
                    bucket = new Jajan(harga, stok, nama);
                }

                if (bucket != null) {
                    bucket.setIdBucket(idBucket);
                    buckets.add(bucket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buckets;
    }
    
    public void ubahBucket(Bucket bucket) {
        String query = "UPDATE bucket SET nama = ?, harga = ?, stok = ? WHERE id_bucket = ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bucket.getNama());
            stmt.setDouble(2, bucket.getHarga());
            stmt.setInt(3, bucket.getStok());
            stmt.setInt(4, bucket.getIdBucket());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapusBucket(int idBucket) {
        String query = "DELETE FROM bucket WHERE id_bucket = ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idBucket);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kurangiStok(int idBucket, int jumlahKurang) {
        String query = "UPDATE bucket SET stok = stok - ? WHERE id_bucket = ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, jumlahKurang);
            stmt.setInt(2, idBucket);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
