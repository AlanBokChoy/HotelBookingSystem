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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author alanbokchoy
 */
// Class represents the cancel booking of the application
public class CancelBookingPage {

    private JFrame frame;
    private JLabel cancelBookingLabel;
    private JComboBox<String> bookingComboBox;
    private JButton cancelButton;
    private JButton returnButton;
    private DBManager dbManager;

    // Constructor to initialize the cancel booking page
    public CancelBookingPage() {
        dbManager = new DBManager();
        setupFrame();
        initializeComponents();
        loadBookings();
        frame.setVisible(true);
    }

    // Method to setup the frame properties
    private void setupFrame() {
        frame = new JFrame("Cancel Booking Page");
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to initialize and add components to the frame
    private void initializeComponents() {
        cancelBookingLabel = new JLabel("CANCEL BOOKING");
        cancelBookingLabel.setFont(new Font(null, Font.BOLD, 45));
        cancelBookingLabel.setBounds(95, 30, 800, 100);

        bookingComboBox = new JComboBox<>();
        bookingComboBox.setBounds(150, 150, 300, 100);

        frame.add(cancelBookingLabel);
        frame.add(bookingComboBox);

        cancelButton();
        returnButton();
    }

    // Method to setup the cancel button and its action
    private void cancelButton() {
        cancelButton = new JButton("Cancel Booking");
        cancelButton.setBounds(200, 250, 200, 40);

        frame.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCancelBooking();
            }
        });
    }

    // Method to setup the return button and its action
    private void returnButton() {
        returnButton = new JButton("Return");
        returnButton.setBounds(440, 495, 120, 45);

        frame.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToMainMenu();
            }
        });
    }

    // Method to load existing bookings into the combo box
    private void loadBookings() {
        bookingComboBox.removeAllItems();
        int guestId = UserSession.getGuestId();
        List<String> bookings = dbManager.getGuestBookings(guestId);

        // Add each booking to the combo box
        for (String booking : bookings) {
            bookingComboBox.addItem(booking);
        }
    }

    // Method to handle the cancellation of a selected booking
    private void handleCancelBooking() {
        String selectedBooking = (String) bookingComboBox.getSelectedItem();
        if (selectedBooking != null) {
            int bookingId = Integer.parseInt(selectedBooking.split(" - ")[0]);
            dbManager.cancelRoomBooking(bookingId);
            JOptionPane.showMessageDialog(frame, "Booking cancelled successfully!");
            loadBookings();
            navigateToMainMenu();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a booking to cancel.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to navigate back to the main menu
    private void navigateToMainMenu() {
        frame.setVisible(false);
        new MainMenuPage();
    }
}
