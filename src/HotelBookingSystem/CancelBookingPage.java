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
        frame();
    }

    // Method to setup the frame properties
    public void frame() {
        frame = new JFrame("Cancel Booking Page");
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        components();
        frame.setVisible(true);
    }

    // Method to initialize and add components to the frame
    public void components() {
        cancelBookingLabel = new JLabel("CANCEL BOOKING");
        cancelBookingLabel.setFont(new Font(null, Font.BOLD, 45));
        cancelBookingLabel.setBounds(95, 30, 800, 100);

        bookingComboBox = new JComboBox<>();
        bookingComboBox.setBounds(150, 150, 300, 100);

        frame.add(cancelBookingLabel);
        frame.add(bookingComboBox);

        loadBookings();
        cancelButton();
        returnButton();
    }

    // Method to setup the cancel button and its action
    public void cancelButton() {
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

    // Method to load existing bookings into the combo box
    public void loadBookings() {
        bookingComboBox.removeAllItems();
        int guestId = UserSession.getGuestId();
        List<String> bookings = dbManager.getGuestBookings(guestId);

        // Add each booking to the combo box
        for (String booking : bookings) {
            bookingComboBox.addItem(booking);
        }
    }

    // Method to handle the cancellation of a selected booking
    public void handleCancelBooking() {
        String selectedBooking = (String) bookingComboBox.getSelectedItem();
        if (selectedBooking != null) {
            int bookingId = Integer.parseInt(selectedBooking.split(" - ")[0]);
            dbManager.cancelRoomBooking(bookingId);
            JOptionPane.showMessageDialog(frame, "Booking cancelled successfully!");
            loadBookings();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a booking to cancel.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(false);
        MainMenuPage mainMenu = new MainMenuPage();
    }
}
