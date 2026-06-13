package freshflowerfinal.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class KoneksiDB {
    private static final String URL = "jdbc:mysql://localhost:3306/freshflower_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Gagal terhubung ke database!");
            e.printStackTrace();
            return null;
        }
    }
}
