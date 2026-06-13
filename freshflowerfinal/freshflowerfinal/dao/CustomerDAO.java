package freshflowerfinal.dao;

import freshflowerfinal.database.KoneksiDB;
import freshflowerfinal.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    
    public void tambahCustomer(Customer customer) {
        String query = "INSERT INTO customer (id_customer, nama, alamat, nomor_telepon, is_member, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getIdCustomer());
            stmt.setString(2, customer.getNama());
            stmt.setString(3, customer.getAlamat());
            stmt.setString(4, customer.getNomorTelepon());
            stmt.setBoolean(5, customer.isMember());
            stmt.setString(6, customer.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                customers.add(new Customer(
                    rs.getString("id_customer"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("nomor_telepon"),
                    rs.getBoolean("is_member"),
                    rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer loginMember(String idCustomer, String password) {
        String query = "SELECT * FROM customer WHERE id_customer = ? AND password = ? AND is_member = TRUE";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, idCustomer);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getString("id_customer"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("nomor_telepon"),
                        rs.getBoolean("is_member"),
                        rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ubahPassword(String idCustomer, String newPassword) {
        String query = "UPDATE customer SET password = ? WHERE id_customer = ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, idCustomer);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void ubahCustomer(Customer customer) {
        String query = "UPDATE customer SET nama = ?, alamat = ?, nomor_telepon = ? WHERE id_customer = ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getNama());
            stmt.setString(2, customer.getAlamat());
            stmt.setString(3, customer.getNomorTelepon());
            stmt.setString(4, customer.getIdCustomer());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapusCustomer(String idCustomer) {
        String query = "DELETE FROM customer WHERE id_customer = ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, idCustomer);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
