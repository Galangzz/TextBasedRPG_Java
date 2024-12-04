package com.auth.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gamerpg";
    private static final String USER = "root";  
    private static final String PASSWORD = ""; 

    public static Connection conn;
    public static Statement statement;
    private static ResultSet resultData;

    //connect ke mysql
    public static void connect(){
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            if(conn != null){
                // System.out.println("Koneksi Berhasil");
            }

            
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL tidak ditemukan!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    //disconnect ke mysql
    public static void disconnect(){
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                // System.out.println("Koneksi database telah ditutup.");
            }else{
                // System.out.println("Koneksi database sudah tidak aktif.");
            }
        } catch (SQLException e) {
            // System.out.println("Gagal menutup koneksi database!");
            e.printStackTrace();
        }
    }


    //cari akun by email
    public static int cariData( String eml, String pwd )
    {
        DB.connect();
        int id = 0;  
        
        try {
        statement = conn.createStatement();
        String query = "SELECT * FROM akun WHERE email = '" + eml + "'";
        resultData = statement.executeQuery(query);

        int count = 0;
        while( resultData.next() ){
        id = resultData.getInt("ID");
        count++;
        }

        if( count == 0 ){
            id = 0;
        }else{
            id = id;
        }
        
        // close statement dan koneksinya
        statement.close();
        disconnect();   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    //login akun
    public static String authAkun(int id, String eml, String password){
        String data = "";
        String email = "";
        String pass = "";
        connect();
        try {

            statement = conn.createStatement();
            String query = "SELECT * FROM akun WHERE ID = " + id ;
            resultData = statement.executeQuery(query);
            while(resultData.next()){
                email = resultData.getString("EMAIL");
                pass = resultData.getString("PASSWORD");
            }
            if (email.equals(eml) && pass.equals(password)){
                data = "Login Success";
            }else{
                data = "Incorrect email or password";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        disconnect();
        return data;
    }

    //register akun
    public static boolean  register(String email, String password){
        connect();
        boolean data = false;
        try {
            statement = conn.createStatement();
            String query = "INSERT INTO akun (EMAIL, PASSWORD) VALUES (" + "'" + email + "', " + "'" + password + "'" + ")";
            
            if(conn == null){
                System.out.println("Connection is null");
            } else if(!statement.execute(query)){
                data = true;
            }
            statement.close();
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
