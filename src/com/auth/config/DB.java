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
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

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

    //Mencari character berdasarkan id AKUN
    public static boolean cariChara(int id) {
        String query = "SELECT C.NAMA_CHARA AS NAMA, A.EMAIL_AKUN AS EMAIL FROM CHARA C JOIN AKUN A ON C.ID_AKUN = A.ID_AKUN WHERE C.ID_AKUN = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Login akun
    public static String authAkun(int id, String eml, String password) {
        String query = "SELECT EMAIL_AKUN, PASSWORD_AKUN, ROLE_AKUN FROM akun WHERE ID_AKUN = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

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
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

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
    public static int countData(String table) {
        String query = "SELECT COUNT(*) AS JUMLAH FROM " + table;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("JUMLAH");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

//EMAIL_ACCOUNT
    //tampilkan semua akun(admin)
    public static List<String[]> showAllAccount() {
        String query = "SELECT * FROM akun WHERE ROLE_AKUN = 'player' ORDER BY EMAIL_AKUN";
        List<String[]> resultList = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                String email = rs.getString("EMAIL_AKUN");

                // Simpan dalam array
                resultList.add(new String[]{email});

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    //Delete Akun
    public static boolean deleteAccount(int id) {
        String query = "DELETE FROM akun WHERE ID_AKUN = " + id;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //delete chara by id akun
    public static boolean deleteChara(int id) {
        String query = "DELETE FROM chara WHERE ID_AKUN = " + id;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//OPPONENT
    public static boolean addOpponent(String name, int hp, int damage, int level) {
        String query = "INSERT INTO `monster`(`NAMA_MONSTER`, `HP_MONSTER`, `DAMAGE_MONSTER`, `LEVEL_MONSTER`) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setInt(2, hp);
            stmt.setInt(3, damage);
            stmt.setInt(4, level);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //tampilkan semua opponenet(admin)
    public static List<String[]> showAllOpponent() {
        String query = "SELECT * FROM monster ORDER BY LEVEL_MONSTER, NAMA_MONSTER;";
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("NAMA_MONSTER");
                int hp = rs.getInt("HP_MONSTER");
                int damage = rs.getInt("DAMAGE_MONSTER");
                int level = rs.getInt("LEVEL_MONSTER");
                // Simpan dalam array
                resultList.add(new String[]{name, String.valueOf(hp), String.valueOf(damage), String.valueOf(level)});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    //delete opponent
    public static boolean deleteOpponent(String nama) {
        String query = "DELETE FROM monster WHERE NAMA_MONSTER = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //cari Opponent
    public static boolean cariOpponent(String nama) {
        String query = "SELECT * FROM monster WHERE NAMA_MONSTER = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//WEAPON
    //tampilkan semua weapon(admin)
    public static List<String[]> showAllWeapon() {
        String query = "SELECT * FROM weapon ORDER BY KEGUNAAN_WEAPON, DAMAGE_WEAPON";
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("NAMA_WEAPON");
                int damage = rs.getInt("DAMAGE_WEAPON");
                String used = rs.getString("KEGUNAAN_WEAPON");
                // Simpan dalam array
                resultList.add(new String[]{name, String.valueOf(damage), used});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    //Menambahkan Weapon
    public static boolean addWeapon(String name, int damage, String used) {
        String query = "INSERT INTO weapon(NAMA_WEAPON, DAMAGE_WEAPON, KEGUNAAN_WEAPON) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setInt(2, damage);
            stmt.setString(3, used);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Mencari Weapon di DB
    public static boolean cariWeapon(String nama) {
        String query = "SELECT * FROM weapon WHERE NAMA_WEAPON = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //delete weapon
    public static boolean deleteWeapon(String nama) {
        String query = "DELETE FROM weapon WHERE NAMA_WEAPON = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
