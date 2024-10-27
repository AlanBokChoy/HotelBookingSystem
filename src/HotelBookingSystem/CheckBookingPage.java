/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author alanbokchoy
 */
// Class represents the check booking of the application
public class CheckBookingPage {

    private JFrame frame;
    private JLabel checkBookingLabel;
    private JLabel userDetailsLabel;
    private JTextArea bookingDetailsArea;
    private JButton returnButton;
    private DBManager dbManager;

    // Constructor to initialize the check booking page
    public CheckBookingPage() {
        dbManager = new DBManager();
        frame();
    }

    // Method to setup the frame properties
    public void frame() {
        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        components();
        displayUserDetails();
        displayBookingDetails();
        frame.setVisible(true);
    }

    // Method to initialize and add components to the frame
    public void components() {
        checkBookingLabel = new JLabel("CHECK BOOKING");
        checkBookingLabel.setFont(new Font(null, Font.BOLD, 45));
        checkBookingLabel.setBounds(100, 30, 800, 100);

        userDetailsLabel = new JLabel();
        userDetailsLabel.setFont(new Font(null, Font.PLAIN, 16));
        userDetailsLabel.setBounds(50, 120, 500, 60);

        bookingDetailsArea = new JTextArea();
        bookingDetailsArea.setEditable(false);
        bookingDetailsArea.setBounds(50, 180, 500, 300);
        bookingDetailsArea.setLineWrap(true);
        bookingDetailsArea.setWrapStyleWord(true);

        frame.add(checkBookingLabel);
        frame.add(userDetailsLabel);
        frame.add(bookingDetailsArea);

        returnButton();
    }

    // Method to setup the return button and its action
    public void returnButton() {
        returnButton = new JButton("Return");
        returnButton.setBounds(440, 500, 120, 35);

        frame.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenuPage mainMenu = new MainMenuPage();
            }
        });
    }

    // Method to display user details (name and email)
    public void displayUserDetails() {
        int guestId = UserSession.getGuestId();
        String name = dbManager.getGuestName(guestId);
        String email = dbManager.getGuestEmail(guestId);

        String userDetails = String.format("                   Name: %s       \nEmail: %s", name, email);
        userDetailsLabel.setText(userDetails);
    }

    // Method to display booking details for the user
    public void displayBookingDetails() {
        int guestId = UserSession.getGuestId();
        List<String> bookings = dbManager.getGuestBookings(guestId);

        if (bookings.isEmpty()) {
            bookingDetailsArea.setText("No bookings found for this user.");
        } else {
            StringBuilder details = new StringBuilder("Your Bookings:\n\n");
            for (String booking : bookings) {
                details.append(booking).append("\n");
            }
            bookingDetailsArea.setText(details.toString());
        }
    }
}
