/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

/**
 *
 * @author alanbokchoy
 */
// Class to handle validation when user is registering an account
public class RegisterValidate {

    private DBManager dbManager;

    // Constructor to initialize the DBManager
    public RegisterValidate(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    // Method to register a user with given details
    public boolean registerUser(String username, String password, String name, String email, String phone) {
        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            return false;
        }
        // Checks that the email contains an '@' character
        if (!email.contains("@")) {
            return false;
        }
        // Checks that the phone number contains only digits
        if (!phone.matches("\\d+")) {
            return false;
        }

        // Register the user in the guest database
        dbManager.registerGuest(username, password, name, email, phone);
        return true;
    }
}
