package com.auth.config;

import com.view.PrintDelay;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    private static final Logger logger = Logger.getLogger(DB.class.getName());
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gamerpg";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            PrintDelay.print("Driver MySQL tidak ditemukan!");
            logger.log(Level.SEVERE, "MySQL Driver not found", e);
        }
    }

    // Membuat koneksi ke database
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private static void logSQLException(String message, SQLException e) {
        logger.log(Level.SEVERE, message, e);
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
            logSQLException("Error finding account by email: " + eml, e);
        }
        return 0;
    }

    //Mencari character berdasarkan id AKUN
    public static boolean cariChara(int id) {
        String query = "SELECT C.NAMA_CHARA AS NAMA FROM CHARA C WHERE C.ID_AKUN = ?";
        return executeExistsQuery(query, id);
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
                        return dbRole;
                    }
                }
            }
        } catch (SQLException e) {
            logSQLException("Authentication failed for email: " + eml, e);
        }
        return "Incorrect email or password";
    }

    // Register akun
    public static boolean register(String email, String password) {
        String query = "INSERT INTO AKUN (EMAIL_AKUN, PASSWORD_AKUN) VALUES (?, ?)";
        return executeUpdate(query, email, password);
    }

    //menghitung banyak data dalam table
    public static int countData(String table) {
        String query = "SELECT COUNT(*) AS JUMLAH FROM " + table;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("JUMLAH");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error counting data in table: " + table, e);
        }
        return 0;
    }

//EMAIL_ACCOUNT
    //tampilkan semua akun(admin)
    public static List<String[]> showAllAccount() {
        String query = "SELECT EMAIL_AKUN FROM akun WHERE ROLE_AKUN = 'player' ORDER BY EMAIL_AKUN";
        return executeSelectQuery(query, "EMAIL_AKUN");
    }

    public static boolean deleteAccount(int id) {
        return executeDeleteQuery("DELETE FROM akun WHERE ID_AKUN = ?", id);
    }

    public static boolean deleteChara(int id) {
        return executeDeleteQuery("DELETE FROM chara WHERE ID_AKUN = ?", id);
    }

//OPPONENT
    public static boolean addOpponent(String name, int hp, int damage, int level) {
        String query = "INSERT INTO monster(NAMA_MONSTER, HP_MONSTER, DAMAGE_MONSTER, LEVEL_MONSTER) VALUES (?, ?, ?, ?)";
        return executeUpdate(query, name, hp, damage, level);
    }

    public static List<String[]> showAllOpponent() {
        String query = "SELECT * FROM monster ORDER BY LEVEL_MONSTER, NAMA_MONSTER;";
        return executeSelectQuery(query, "NAMA_MONSTER", "HP_MONSTER", "DAMAGE_MONSTER", "LEVEL_MONSTER");
    }

    public static boolean deleteOpponent(String name) {
        return executeDeleteQuery("DELETE FROM monster WHERE NAMA_MONSTER = ?", name);
    }

    public static boolean cariOpponent(String name) {
        String query = "SELECT * FROM monster WHERE NAMA_MONSTER = ?";
        return executeExistsQuery(query, name);
    }

//WEAPON
    public static List<String[]> showAllWeapon() {
        String query = "SELECT * FROM weapon ORDER BY KEGUNAAN_WEAPON, DAMAGE_WEAPON";
        return executeSelectQuery(query, "NAMA_WEAPON", "DAMAGE_WEAPON", "KEGUNAAN_WEAPON");
    }

    public static boolean addWeapon(String name, int damage, String used) {
        String query = "INSERT INTO weapon(NAMA_WEAPON, DAMAGE_WEAPON, KEGUNAAN_WEAPON) VALUES (?, ?, ?)";
        return executeUpdate(query, name, damage, used);
    }

    public static boolean cariWeapon(String name) {
        String query = "SELECT * FROM weapon WHERE NAMA_WEAPON = ?";
        return executeExistsQuery(query, name);
    }

    public static boolean deleteWeapon(String name) {
        return executeDeleteQuery("DELETE FROM weapon WHERE NAMA_WEAPON = ?", name);
    }

//Characterrrrr
    //tampilkan weapon role
    public static List<String[]> showAllWeaponRole(String used) {
        String query = "SELECT * FROM weapon WHERE KEGUNAAN_WEAPON = ? ORDER BY NAMA_WEAPON";
        return executeShowSelectQuery(query, used, "NAMA_WEAPON", "ID_WEAPON");
    }

    public static int countWeaponRole(String table) {
        String query = "SELECT COUNT(*) AS JUMLAH FROM weapon WHERE KEGUNAAN_WEAPON = ?";
        return executeCountQuery(query, table);
    }

    //tampilkan armor role
    public static List<String[]> showAllArmorRole(String used) {
        String query = "SELECT * FROM armor WHERE KEGUNAAN_ARMOR = ? ORDER BY NAMA_ARMOR";
        return executeShowSelectQuery(query, used, "NAMA_ARMOR", "ID_ARMOR");
    }

    public static int countArmorRole(String table) {
        String query = "SELECT COUNT(*) AS JUMLAH FROM armor WHERE KEGUNAAN_ARMOR = ?";
        return executeCountQuery(query, table);
    }

     //menambahkan karakter
    public static boolean addCharacter(int idAkun, int idWeapon, int idArmor, String name, int damage, int defense, int heal_amount, String role) {
        String query = "INSERT INTO chara(ID_AKUN, ID_WEAPON, ID_ARMOR, NAMA_CHARA, DAMAGE_CHARA, DEFENSE_CHARA, HEAL_AMOUNT, ROLE_CHARA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return executeUpdate(query, idAkun, idWeapon, idArmor, name, damage, defense, heal_amount, role);
    }

//ARMOR
    //menampilkan semua armor
    public static List<String[]> showAllArmor() {
        String query = "SELECT * FROM armor ORDER BY KEGUNAAN_ARMOR, DEFENSE_ARMOR";
        return executeSelectQuery(query, "NAMA_ARMOR", "DEFENSE_ARMOR", "KEGUNAAN_ARMOR");
    }

    public static boolean addArmor(String name, int defense, String used) {
        String query = "INSERT INTO armor(NAMA_ARMOR, DEFENSE_ARMOR, KEGUNAAN_ARMOR) VALUES (?, ?, ?)";
        return executeUpdate(query, name, defense, used);
    }

    public static boolean cariArmor(String name) {
        String query = "SELECT * FROM armor WHERE NAMA_ARMOR = ?";
        return executeExistsQuery(query, name);
    }

    public static boolean deleteArmor(String name) {
        return executeDeleteQuery("DELETE FROM armor WHERE NAMA_ARMOR = ?", name);
    }

    //cari weapon by id return damage weapon untuk dimasukin ke chara
    public static int cariWeaponByID(int id) {
        String query = "SELECT DAMAGE_WEAPON FROM weapon WHERE ID_WEAPON = ?";
        return executeSingleIntQuery(query, id);
    }

    //cari armor by id return defense armor untuk dimasukin ke chara
    public static int cariArmorByID(int id) {
        String query = "SELECT DEFENSE_ARMOR FROM armor WHERE ID_ARMOR = ?";
        return executeSingleIntQuery(query, id);
    }

    @Deprecated
    @SuppressWarnings("Kuno Bro")
    public static List<String[]> showCharacterNameCreated(int id) {
        String query = "SELECT NAMA_CHARA as NAMA FROM chara WHERE ID_AKUN = ? ORDER BY NAMA_CHARA";
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("NAMA");

                    // Simpan dalam array
                    resultList.add(new String[]{name});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    //tampilkan stats character yang sudah dibuat
    public static List<String[]> showCharacterStatsCreated(int id) {
        String query = "SELECT c.NAMA_CHARA as NAMA, w.NAMA_WEAPON as WEAPON, w.DAMAGE_WEAPON, a.NAMA_ARMOR as ARMOR, a.DEFENSE_ARMOR, c.HP_CHARA as HP, c.DAMAGE_CHARA as DAMAGE, c.DEFENSE_CHARA as DEFENSE, c.HEAL_AMOUNT as HEAL, c.ROLE_CHARA as ROLE, c .LEVEL_CHARA as LEVEL, m.ID_MONSTER FROM chara c JOIN weapon w ON c.ID_WEAPON = w.ID_WEAPON JOIN armor a ON c.ID_ARMOR = a.ID_ARMOR LEFT JOIN monster m ON c.ID_MONSTER = m.ID_MONSTER WHERE ID_AKUN = ? ORDER BY c.NAMA_CHARA";
        return executeShowSelectQuery(query, id, "NAMA", "WEAPON", "DAMAGE_WEAPON", "ARMOR", "DEFENSE_ARMOR", "HP", "DAMAGE", "DEFENSE", "HEAL", "ROLE", "LEVEL", "ID_MONSTER");
    }

    //get data monster(id, nama, hp, damage, level) by level player
    public static List<String[]> getMonsterData(int level) {
        String query = "SELECT ID_MONSTER, NAMA_MONSTER, HP_MONSTER, DAMAGE_MONSTER, LEVEL_MONSTER FROM monster WHERE LEVEL_MONSTER = ?";
        return executeShowSelectQuery(query, level, "ID_MONSTER", "NAMA_MONSTER", "HP_MONSTER", "DAMAGE_MONSTER", "LEVEL_MONSTER");
    }

    //get data monster(id, nama, hp, damage, level) by id monster
    public static String[] getMonsterDataID(int id) {
        String query = "SELECT ID_MONSTER, NAMA_MONSTER, HP_MONSTER, DAMAGE_MONSTER, LEVEL_MONSTER FROM monster WHERE ID_MONSTER = ?";
        return executeSingleSelectQuery(query, id, "ID_MONSTER", "NAMA_MONSTER", "HP_MONSTER", "DAMAGE_MONSTER", "LEVEL_MONSTER");
    }

    public static boolean updateChara(String name, int level, int damage, int defense, int monsterId) {
        String query = "UPDATE chara SET LEVEL_CHARA = ?, DAMAGE_CHARA = ?, DEFENSE_CHARA = ?, ID_MONSTER = ? WHERE NAMA_CHARA = ?";
        return executeUpdate(query, level, damage, defense, monsterId, name);
    }

    private static boolean executeUpdate(String query, Object... params) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logSQLException("Error executing update: " + query, e);
        }
        return false;
    }

    private static boolean executeExistsQuery(String query, Object param) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, param);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logSQLException("Error executing exists query: " + query, e);
        }
        return false;
    }

    private static boolean executeDeleteQuery(String query, Object param) {
        return executeUpdate(query, param);
    }

    private static List<String[]> executeShowSelectQuery(String query, Object param, String... columnNames) {
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, param);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String[] row = new String[columnNames.length];
                    for (int i = 0; i < columnNames.length; i++) {
                        row[i] = rs.getString(columnNames[i]);
                    }
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            logSQLException("Error executing select query: " + query, e);
        }
        return resultList;
    }

    private static List<String[]> executeSelectQuery(String query, String... columnNames) {
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String[] row = new String[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    row[i] = rs.getString(columnNames[i]);
                }
                resultList.add(row);
            }
        } catch (SQLException e) {
            logSQLException("Error executing select query: " + query, e);
        }
        return resultList;
    }

    private static String[] executeSingleSelectQuery(String query, Object param, String... columnNames) {
        String[] result = null;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, param);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = new String[columnNames.length];
                    for (int i = 0; i < columnNames.length; i++) {
                        result[i] = rs.getString(columnNames[i]);
                    }
                }
            }
        } catch (SQLException e) {
            logSQLException("Error executing single select query: " + query, e);
        }
        return result;
    }

    private static int executeSingleIntQuery(String query, Object param) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, param);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logSQLException("Error executing single int query: " + query, e);
        }
        return 0;
    }

    private static int executeCountQuery(String query, Object param) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, param);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("JUMLAH");
                }
            }
        } catch (SQLException e) {
            logSQLException("Error executing count query: " + query, e);
        }
        return 0;
    }
}