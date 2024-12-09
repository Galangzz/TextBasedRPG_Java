package com.auth.config;

import com.view.PrintDelay;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static int cariData(String eml) {
        String query = "SELECT ID_AKUN FROM akun WHERE EMAIL_AKUN = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, eml);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_AKUN");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }

    // Login akun
    public static String authAkun(int id, String eml, String password) {
        String query = "SELECT EMAIL_AKUN, PASSWORD_AKUN, ROLE_AKUN FROM akun WHERE ID_AKUN = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String dbEmail = rs.getString("EMAIL_AKUN");
                    String dbPassword = rs.getString("PASSWORD_AKUN");
                    String dbRole = rs.getString("ROLE_AKUN");

                    if (dbEmail.equals(eml) && dbPassword.equals(password)) {
                        // return player atau admin
                        return dbRole;
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
        String query = "INSERT INTO AKUN (EMAIL_AKUN, PASSWORD_AKUN) VALUES (?, ?)";
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

    //menghitung banyak data dalam table
    public static int countData(String table){
        String query = "SELECT COUNT(*) AS JUMLAH FROM " + table;
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("JUMLAH");
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //tampilkan semua akun(admin)
    public static List<String[]> showAllAccount(){
        String query = "SELECT * FROM akun WHERE ROLE_AKUN = 'player' ORDER BY EMAIL_AKUN";
        List<String[]> resultList = new ArrayList<>();

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                String email = rs.getString("EMAIL_AKUN");

                // Simpan dalam array
                resultList.add(new String[]{email});

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }




}
