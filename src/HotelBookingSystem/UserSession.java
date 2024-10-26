/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

/**
 *
 * @author alanbokchoy
 */
public class UserSession {
    private static String username;
    private static int guestId;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserSession.username = username;
    }

    public static int getGuestId() {
        return guestId;
    }

    public static void setGuestId(int guestId) {
        UserSession.guestId = guestId;
    }
}
