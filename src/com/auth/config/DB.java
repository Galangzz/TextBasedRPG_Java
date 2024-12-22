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

//Characterrrrr
    //tampilkan weapon role
    public static List<String[]> showAllWeaponRole(String used) {
        String query = "SELECT * FROM weapon WHERE KEGUNAAN_WEAPON = ? ORDER BY NAMA_WEAPON";
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); 
        PreparedStatement stmt = conn.prepareStatement(query);) {
                stmt.setString(1, used);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String name = rs.getString("NAMA_WEAPON");
                        int id = rs.getInt("ID_WEAPON");
                        // Simpan dalam array
                        resultList.add(new String[]{name, String.valueOf(id)});
                    }
                }
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    //count weapon role
    public static int countWeaponRole(String table) {
        String query = "SELECT COUNT(*) AS JUMLAH FROM weapon WHERE KEGUNAAN_WEAPON = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query);) {

            stmt.setString(1, table);
            try(ResultSet rs = stmt.executeQuery()){

                if (rs.next()) {
                    return rs.getInt("JUMLAH");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //tampilkan armor role
    public static List<String[]> showAllArmorRole(String used) {
        String query = "SELECT * FROM armor WHERE KEGUNAAN_ARMOR = ? ORDER BY NAMA_ARMOR";
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); 
        PreparedStatement stmt = conn.prepareStatement(query);) {
                stmt.setString(1, used);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String name = rs.getString("NAMA_ARMOR");
                        int id = rs.getInt("ID_ARMOR");
                        // Simpan dalam array
                        resultList.add(new String[]{name, String.valueOf(id)});
                    }
                }
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    //count armor role
    public static int countArmorRole(String table) {
        String query = "SELECT COUNT(*) AS JUMLAH FROM armor WHERE KEGUNAAN_ARMOR = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query);) {

            stmt.setString(1, table);
            try(ResultSet rs = stmt.executeQuery()){

                if (rs.next()) {
                    return rs.getInt("JUMLAH");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //menambahkan karakter
    public static boolean addCharacter(int idAkun, int idWeapon, int idArmor, String name, int damage, int defense, int heal_amount, String role) {
        String query = "INSERT INTO chara(ID_AKUN, ID_WEAPON, ID_ARMOR, NAMA_CHARA, DAMAGE_CHARA, DEFENSE_CHARA, HEAL_AMOUNT, ROLE_CHARA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAkun);
            stmt.setInt(2, idWeapon);
            stmt.setInt(3, idArmor);
            stmt.setString(4, name);
            stmt.setInt(5, damage);
            stmt.setInt(6, defense);
            stmt.setInt(7, heal_amount);
            stmt.setString(8, role);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//ARMOR
    //menampilkan semua armor
    public static List<String[]> showAllArmor() {
        String query = "SELECT * FROM armor ORDER BY KEGUNAAN_armor, DEFENSE_ARMOR";
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("NAMA_ARMOR");
                int damage = rs.getInt("DEFENSE_ARMOR");
                String used = rs.getString("KEGUNAAN_ARMOR");
                // Simpan dalam array
                resultList.add(new String[]{name, String.valueOf(damage), used});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    //Menambahkan Armor
    public static boolean addArmor(String name, int defense, String used) {
        String query = "INSERT INTO armor(NAMA_ARMOR, DEFENSE_ARMOR, KEGUNAAN_ARMOR) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setInt(2, defense);
            stmt.setString(3, used);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Mencari nama armor
    public static boolean cariArmor(String nama) {
        String query = "SELECT * FROM armor WHERE NAMA_ARMOR = ?";
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

    //delete armor
    public static boolean deleteArmor(String nama) {
        String query = "DELETE FROM armor WHERE NAMA_ARMOR = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //cari weapon by id return damage weapon untuk dimasukin ke chara
    public static int cariWeaponByID(int id) {
        String query = "SELECT DAMAGE_WEAPON FROM weapon WHERE ID_WEAPON = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    return rs.getInt("DAMAGE_WEAPON");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //cari armor by id return defense armor untuk dimasukin ke chara
    public static int cariArmorByID(int id) {
        String query = "SELECT DEFENSE_ARMOR FROM armor WHERE ID_ARMOR = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    return rs.getInt("DEFENSE_ARMOR");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    //tampilkan nama character
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
        String query = "SELECT c.NAMA_CHARA as NAMA, w.NAMA_WEAPON as WEAPON, w.DAMAGE_WEAPON, a.NAMA_ARMOR as ARMOR, a.DEFENSE_ARMOR, c.HP_CHARA as HP, c.DAMAGE_CHARA as DAMAGE, c.DEFENSE_CHARA as DEFENSE, c.HEAL_AMOUNT as HEAL, c.ROLE_CHARA as ROLE, c.LEVEL_CHARA as LEVEL, m.ID_MONSTER FROM chara c JOIN weapon w ON c.ID_WEAPON = w.ID_WEAPON JOIN armor a ON c.ID_ARMOR = a.ID_ARMOR LEFT JOIN monster m ON c.ID_MONSTER = m.ID_MONSTER WHERE ID_AKUN = ? ORDER BY c.NAMA_CHARA";
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("NAMA");
                    String weapon = rs.getString("WEAPON");
                    int damage = rs.getInt("DAMAGE_WEAPON");
                    String armor = rs.getString("ARMOR");
                    int defense = rs.getInt("DEFENSE_ARMOR");
                    int hp = rs.getInt("HP");
                    int damageChara = rs.getInt("DAMAGE");
                    int defenseChara = rs.getInt("DEFENSE");
                    int heal = rs.getInt("HEAL");
                    String role = rs.getString("ROLE");
                    int level = rs.getInt("LEVEL");
                    int idMonster = rs.getInt("ID_MONSTER");
                    // Simpan dalam array
                    resultList.add(new String[]{name, weapon, String.valueOf(damage), armor, String.valueOf(defense), String.valueOf(hp), String.valueOf(damageChara), String.valueOf(defenseChara), String.valueOf(heal), role, String.valueOf(level), String.valueOf(idMonster)});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    //get data monster(id, nama, hp, damage, level) by level player
    public static List<String[]> getMonsterData(int level) {
        String query = "SELECT ID_MONSTER, NAMA_MONSTER, HP_MONSTER, DAMAGE_MONSTER, LEVEL_MONSTER FROM monster WHERE LEVEL_MONSTER = ? ";
        List<String[]> resultList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, level);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID_MONSTER");
                    String name = rs.getString("NAMA_MONSTER");
                    int hp = rs.getInt("HP_MONSTER");
                    int damage = rs.getInt("DAMAGE_MONSTER");
                    int levelMonster = rs.getInt("LEVEL_MONSTER");
                    // Simpan dalam array
                    resultList.add(new String[]{String.valueOf(id), name, String.valueOf(hp), String.valueOf(damage), String.valueOf(levelMonster)});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    //get data monster(id, nama, hp, damage, level) by id monster
    public static String[] getMonsterDataID(int id) {
        String query = "SELECT ID_MONSTER, NAMA_MONSTER, HP_MONSTER, DAMAGE_MONSTER, LEVEL_MONSTER FROM monster WHERE ID_MONSTER = ? ";
        String[] resultList = null;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("ID_MONSTER");
                    String name = rs.getString("NAMA_MONSTER");
                    int hp = rs.getInt("HP_MONSTER");
                    int damage = rs.getInt("DAMAGE_MONSTER");
                    int levelMonster = rs.getInt("LEVEL_MONSTER");
                    // Simpan dalam array
                    resultList = new String[]{String.valueOf(id), name, String.valueOf(hp), String.valueOf(damage), String.valueOf(levelMonster)};
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }


    public static boolean updateChara(String name, int level, int damage, int defense, int monsterId) {
        String query = "UPDATE chara SET LEVEL_CHARA = ?, DAMAGE_CHARA = ?, DEFENSE_CHARA = ?, ID_MONSTER = ? WHERE NAMA_CHARA = ?;";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, level);
            stmt.setInt(2, damage);
            stmt.setInt(3, defense);
            stmt.setInt(4, monsterId);
            stmt.setString(5, name);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
