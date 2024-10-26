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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alanbokchoy
 */
public class DBManager {

    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static final String URL = "jdbc:derby://localhost:1527/GuestDB;create=true";

    private static Connection conn;

    public DBManager() {
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
                    System.out.println("Guest database already exists.");
                } catch (SQLException e) {
                    String sql = "CREATE TABLE GuestDB ("
                            + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1), "
                            + "username VARCHAR(50), "
                            + "password VARCHAR(50), "
                            + "name VARCHAR(50), "
                            + "email VARCHAR(50), "
                            + "phone INTEGER)";
                    conn.createStatement().execute(sql);
                    System.out.println("Guest database created successfully.");
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

    public void createHotelRooms() {
        try {
            if (conn != null) {
                String checkTableSql = "SELECT * FROM Rooms";
                try {
                    conn.createStatement().execute(checkTableSql);
                    System.out.println("Room database already exists.");
                } catch (SQLException e) {
                    String sql = "CREATE TABLE RoomDB ("
                            + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1), "
                            + "room_type VARCHAR(50), "
                            + "room_number VARCHAR(10), "
                            + "room_price VARCHAR(10), "
                            + "checkin_date VARCHAR(10), "
                            + "checkout_date VARCHAR(10), "
                            + "status VARCHAR(20),"
                            + "guest_id INT)";
                    conn.createStatement().execute(sql);
                    System.out.println("Room database created successfully.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addRoom(String roomType, String roomNumber, String roomPrice, String checkinDate, String checkoutDate, String status, int guestId) {
        String sql = "INSERT INTO RoomDB (room_type, room_number, room_price, checkin_date, checkout_date, status, guest_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomType);
            pstmt.setString(2, roomNumber);
            pstmt.setString(3, roomPrice);
            pstmt.setString(4, checkinDate);
            pstmt.setString(5, checkoutDate);
            pstmt.setString(6, status);
            pstmt.setInt(7, guestId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isRoomAvailable(String roomNumber, String checkinDate, String checkoutDate) {
        String sql = "SELECT * FROM RoomDB WHERE room_number = ? AND status = 'Booked' "
                + "AND ((checkin_date <= ? AND checkout_date >= ?) "
                + "OR (checkin_date <= ? AND checkout_date >= ?))";

        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomNumber);
            pstmt.setString(2, checkoutDate);
            pstmt.setString(3, checkinDate);
            pstmt.setString(4, checkoutDate);
            pstmt.setString(5, checkinDate);

            ResultSet rs = pstmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int getGuestId(String username) {
        String sql = "SELECT id FROM GuestDB WHERE username = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public List<String> getGuestBookings(int guestId) {
        List<String> bookings = new ArrayList<>();
        String sql = "SELECT id, room_type, room_number, checkin_date, checkout_date FROM RoomDB WHERE guest_id = ?";

        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, guestId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String roomType = rs.getString("room_type");
                String roomNumber = rs.getString("room_number");
                String checkinDate = rs.getString("checkin_date");
                String checkoutDate = rs.getString("checkout_date");
                bookings.add(id + " - " + roomType + " (" + roomNumber + "), Check-in: " + checkinDate + ", Check-out: " + checkoutDate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookings;
    }

    public void cancelRoomBooking(int bookingId) {
        String sql = "DELETE FROM RoomDB WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingId);
            pstmt.executeUpdate();
            System.out.println("Booking cancelled successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
