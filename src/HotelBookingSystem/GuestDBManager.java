/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author alanbokchoy
 */
public class GuestDBManager {

    private static Connection conn;

    private static String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static final String URL = "jdbc:derby://localhost:1527/GuestDB;create=true";

    public GuestDBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createGuestDatabase() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (conn != null) {
                String checkTableSql = "SELECT * FROM GuestDB";
                try {
                    conn.createStatement().execute(checkTableSql);
                    System.out.println("Table already exists.");
                } catch (SQLException e) {
                    String sql = "CREATE TABLE GuestDB ("
                            + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                            + "username VARCHAR(50), "
                            + "password VARCHAR(50), "
                            + "name VARCHAR(50), "
                            + "email VARCHAR(50), "
                            + "phone INTEGER)";
                    conn.createStatement().execute(sql);
                    System.out.println("Table created successfully.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void registerGuest(String username, String password, String name, String email, String phone) {
        String sql = "INSERT INTO GuestDB (username, password, name, email, phone) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);

            pstmt.executeUpdate();
            System.out.println("Guest registered successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean verifyCredentials(String username, String password) {
        String sql = "SELECT * FROM GuestDB WHERE username = ? AND password = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
