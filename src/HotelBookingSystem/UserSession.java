/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

/**
 *
 * @author alanbokchoy
 */

// Class to manage user session data, using username and guestID
public class UserSession {

    private static String username;
    private static int guestId;

    // Method to get the username
    public static String getUsername() {
        return username;
    }

    // Method to set the username
    public static void setUsername(String username) {
        UserSession.username = username;
    }

    // Method to get the current guestID
    public static int getGuestId() {
        return guestId;
    }

    //Method to set the guestID
    public static void setGuestId(int guestId) {
        UserSession.guestId = guestId;
    }
}
