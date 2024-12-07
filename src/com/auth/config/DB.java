package com.auth.config;

import com.view.PrintDelay;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gamerpg";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName(JDBC_DRIVER); 
        } catch (ClassNotFoundException e) {
            PrintDelay.print("Driver MySQL tidak ditemukan!");
            e.printStackTrace();
        }
    }

    // Membuat koneksi ke database
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Mencari akun berdasarkan email
    public static int cariData(String eml, String pwd) {
        String query = "SELECT ID FROM akun WHERE email = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, eml);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }

    // Login akun
    public static String authAkun(int id, String eml, String password) {
        String query = "SELECT EMAIL, PASSWORD FROM akun WHERE ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String dbEmail = rs.getString("EMAIL");
                    String dbPassword = rs.getString("PASSWORD");

                    if (dbEmail.equals(eml) && dbPassword.equals(password)) {
                        return "Login Success";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Incorrect email or password";
    }

    // Register akun
    public static boolean register(String email, String password) {
        String query = "INSERT INTO akun (EMAIL, PASSWORD) VALUES (?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
}
