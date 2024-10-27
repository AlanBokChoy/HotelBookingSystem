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
// Class represents the database management of the application
public class DBManager {

    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static final String URL = "jdbc:derby://localhost:1527/GuestDB;create=true";
    private Connection conn;

    private static final String CREATE_GUEST_TABLE = "CREATE TABLE GuestDB ("
            + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1), "
            + "username VARCHAR(50), "
            + "password VARCHAR(50), "
            + "name VARCHAR(50), "
            + "email VARCHAR(50), "
            + "phone INTEGER)";

    private static final String CREATE_ROOM_TABLE = "CREATE TABLE RoomDB ("
            + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1), "
            + "room_type VARCHAR(50), "
            + "room_number VARCHAR(10), "
            + "room_price VARCHAR(10), "
            + "checkin_date VARCHAR(10), "
            + "checkout_date VARCHAR(10), "
            + "status VARCHAR(20), "
            + "guest_id INT)";

    // Constructor that establishes a connection to the database upon creation
    public DBManager() {
        establishConnection();
    }

    // Getter method to access the connection
    public Connection getConnection() {
        return this.conn;
    }

    // Method to establish a connection to the database
    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Method to create the Guest database table if it doesn't already exist
    public void createGuestDatabase() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (conn != null) {
                // Checks if the table already exists
                String checkTableSql = "SELECT * FROM GuestDB";
                try {
                    conn.createStatement().execute(checkTableSql);
                    System.out.println("Guest database already exists.");
                } catch (SQLException e) {
                    String sql = CREATE_GUEST_TABLE;
                    conn.createStatement().execute(sql);
                    System.out.println("Guest database created successfully.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to register a new guest
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

    // Method to verify guest credentials
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

    // Method to create the Room database table if it doesn't already exist
    public void createHotelRooms() {
        try {
            if (conn != null) {
                String checkTableSql = "SELECT * FROM Rooms";
                try {
                    conn.createStatement().execute(checkTableSql);
                    System.out.println("Room database already exists.");
                } catch (SQLException e) {
                    String sql = CREATE_ROOM_TABLE;
                    conn.createStatement().execute(sql);
                    System.out.println("Room database created successfully.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to add a room to the database
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

    // Method to check if a room is available
    public boolean isRoomAvailable(String roomNumber, String roomType, String checkinDate, String checkoutDate) {
        String sql = "SELECT * FROM RoomDB WHERE room_number = ? AND room_type = ? AND status = 'Booked' "
                + "AND ((checkin_date <= ? AND checkout_date > ?) "
                + "OR (checkin_date < ? AND checkout_date >= ?))";

        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomNumber);
            pstmt.setString(2, roomType);
            pstmt.setString(3, checkinDate);
            pstmt.setString(4, checkinDate);
            pstmt.setString(5, checkoutDate);
            pstmt.setString(6, checkoutDate);

            ResultSet rs = pstmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Method to get the guestID based on the username
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

    // Method to retrieve guest bookings
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

    // Method to cancel a room booking
    public void cancelRoomBooking(int bookingId) {
        String sql = "UPDATE RoomDB SET status = 'Available', guest_id = NULL WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingId);
            pstmt.executeUpdate();
            System.out.println("Booking cancelled successfully, room is now available.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to get the guest name based on guestID
    public String getGuestName(int guestId) {
        String sql = "SELECT name FROM GuestDB WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, guestId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Method to get the guest email based on guestID
    public String getGuestEmail(int guestId) {
        String sql = "SELECT email FROM GuestDB WHERE id = ?";
        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, guestId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
