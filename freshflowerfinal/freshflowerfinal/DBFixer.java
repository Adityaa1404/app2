package freshflowerfinal;

import freshflowerfinal.database.KoneksiDB;
import java.sql.Connection;
import java.sql.Statement;

public class DBFixer {
    public static void main(String[] args) {
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement()) {
            
            try {
                stmt.execute("ALTER TABLE customer ADD COLUMN is_member BOOLEAN DEFAULT FALSE");
                System.out.println("Berhasil menambahkan kolom is_member.");
            } catch (Exception e) { 
                System.out.println("Kolom is_member mungkin sudah ada."); 
            }

            try {
                stmt.execute("ALTER TABLE customer ADD COLUMN password VARCHAR(50) NULL");
                System.out.println("Berhasil menambahkan kolom password.");
            } catch (Exception e) { 
                System.out.println("Kolom password mungkin sudah ada."); 
            }

            try {
                stmt.executeUpdate("INSERT IGNORE INTO customer (id_customer, nama, alamat, nomor_telepon, is_member, password) VALUES ('2024-001', 'Budi', 'Jakarta', '08111', TRUE, 'member1')");
                stmt.executeUpdate("INSERT IGNORE INTO customer (id_customer, nama, alamat, nomor_telepon, is_member, password) VALUES ('2024-002', 'Wati', 'Surabaya', '08222', TRUE, 'member2')");
                stmt.executeUpdate("INSERT IGNORE INTO customer (id_customer, nama, alamat, nomor_telepon, is_member, password) VALUES ('2024-003', 'Iwan', 'Semarang', '08333', TRUE, 'member3')");
                System.out.println("Berhasil memasukkan data akun Member (2024-001, 2024-002, 2024-003).");
            } catch (Exception e) {
                System.out.println("Gagal memasukkan data member.");
                e.printStackTrace();
            }
            
            System.out.println("Perbaikan Database Selesai!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
