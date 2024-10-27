/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author alanbokchoy
 */
// Class represents the create booking of the application
public class CreateBookingPage {

    private JFrame frame;
    private JLabel createBookingLabel;
    private JLabel roomPriceLabel;
    private JLabel standardRoomPrice;
    private JLabel deluxeRoomPrice;
    private JLabel oakspecialRoomPrice;
    private JComboBox roomTypeBox;
    private JComboBox roomNumberBox;
    private JLabel checkinLabel;
    private JLabel checkoutLabel;
    private JTextField checkinField;
    private JTextField checkoutField;
    private JButton confirmButton;
    private JButton returnButton;
    private DBManager dbManager;

    // Constructor to initialize the create booking page
    public CreateBookingPage() {
        dbManager = new DBManager();
        dbManager.createHotelRooms();
        setupFrame();
        initializeComponents();
        roomType();
        roomNumber();
        checkinDate();
        checkoutDate();
        frame.setVisible(true);
    }

    // Method to setup the frame properties
    private void setupFrame() {
        frame = new JFrame("Create Booking Page");
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to initialize and add components to the frame
    private void initializeComponents() {
        createBookingLabel = new JLabel("CREATE BOOKING");
        createBookingLabel.setFont(new Font(null, Font.BOLD, 45));
        createBookingLabel.setBounds(95, 30, 800, 100);

        roomPriceLabel = new JLabel("Room Prices:");
        roomPriceLabel.setFont(new Font(null, Font.BOLD, 16));
        roomPriceLabel.setBounds(250, 125, 250, 60);

        roomPrice();

        frame.add(createBookingLabel);
        frame.add(roomPriceLabel);

        confirmButton();
        returnButton();
    }

    // Method to display the room prices
    private void roomPrice() {
        standardRoomPrice = new JLabel("Standard Room - $100 per night");
        standardRoomPrice.setBounds(195, 155, 250, 60);

        deluxeRoomPrice = new JLabel("Deluxe Room - $150 per night");
        deluxeRoomPrice.setBounds(195, 175, 250, 60);

        oakspecialRoomPrice = new JLabel("OakSpecial Room - $200 per night");
        oakspecialRoomPrice.setBounds(195, 195, 250, 60);

        frame.add(standardRoomPrice);
        frame.add(deluxeRoomPrice);
        frame.add(oakspecialRoomPrice);
    }

    // Method to set up room type selection dropdown
    private void roomType() {
        roomTypeBox = new JComboBox();
        roomTypeBox.setBounds(95, 260, 200, 60);

        roomTypeBox.addItem("Select Room Type");
        roomTypeBox.addItem("Standard Room");
        roomTypeBox.addItem("Deluxe Room");
        roomTypeBox.addItem("OakSpecial Room");

        frame.add(roomTypeBox);
    }

    // Method to set up room number selection dropdown
    private void roomNumber() {
        roomNumberBox = new JComboBox();
        roomNumberBox.setBounds(305, 260, 200, 60);

        roomNumberBox.addItem("Select Room Number");

        for (int i = 100; i <= 110; i++) {
            roomNumberBox.addItem("R" + i);
        }

        frame.add(roomNumberBox);
    }

    // Method to set up check-in date input
    private void checkinDate() {
        checkinLabel = new JLabel("Checkin Date:");
        checkinLabel.setFont(new Font(null, Font.BOLD, 13));
        checkinLabel.setBounds(150, 315, 250, 60);

        checkinField = new JTextField("DD/MM/YYYY");
        checkinField.setBounds(145, 360, 130, 35);

        frame.add(checkinLabel);
        frame.add(checkinField);
    }

    // Method to set up check-out date input
    private void checkoutDate() {
        checkoutLabel = new JLabel("Checkout Date:");
        checkoutLabel.setFont(new Font(null, Font.BOLD, 13));
        checkoutLabel.setBounds(325, 315, 250, 60);

        checkoutField = new JTextField("DD/MM/YYYY");
        checkoutField.setBounds(325, 360, 130, 35);

        frame.add(checkoutLabel);
        frame.add(checkoutField);
    }

    // Method to setup the confirm button and its action
    private void confirmButton() {
        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(225, 430, 150, 40);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBooking();
            }
        });

        frame.add(confirmButton);
    }

    // Method to setup the return button and its action
    private void returnButton() {
        returnButton = new JButton("Return");
        returnButton.setBounds(440, 495, 120, 45);

        frame.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenuPage mainMenu = new MainMenuPage();
            }
        });
    }

    // Method to get the price of a room based on its type
    private String getRoomPrice(String roomType) {
        switch (roomType) {
            case "Standard Room":
                return "$100";
            case "Deluxe Room":
                return "$150";
            case "OakSpecial Room":
                return "$200";
            default:
                return "$0";
        }
    }

    // Method to validate the check-in and check-out dates
    private String validateDates(String checkinDate, String checkoutDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date checkin = sdf.parse(checkinDate);
            Date checkout = sdf.parse(checkoutDate);

            if (checkout.before(checkin)) {
                return "Invalid Date Try Again.";
            }
        } catch (ParseException ex) {
            return "Please enter valid dates in the format DD/MM/YYYY.";
        }
        return null;
    }

    // Method to validate room booking
    private void handleBooking() {
        String selectedRoomType = (String) roomTypeBox.getSelectedItem();
        String selectedRoomNumber = (String) roomNumberBox.getSelectedItem();
        String checkinDate = checkinField.getText();
        String checkoutDate = checkoutField.getText();

        // Validate that a room type and number are selected
        if (selectedRoomType.equals("Select Room Type") || selectedRoomNumber.equals("Select Room Number")) {
            JOptionPane.showMessageDialog(frame, "Please select a valid room type and number.");
            return;
        }

        // Validate the check-in and check-out dates
        String validationMessage = validateDates(checkinDate, checkoutDate);
        if (validationMessage != null) {
            JOptionPane.showMessageDialog(frame, validationMessage);
            return;
        }

        // Check if the selected room is available for the specified dates
        boolean isAvailable = dbManager.isRoomAvailable(selectedRoomNumber, selectedRoomType, checkinDate, checkoutDate);
        if (!isAvailable) {
            JOptionPane.showMessageDialog(frame, "Room not available for the selected dates!");
            return;
        }

        // Add the room booking to the database
        int guestId = UserSession.getGuestId();
        dbManager.addRoom(selectedRoomType, selectedRoomNumber, getRoomPrice(selectedRoomType), checkinDate, checkoutDate, "Booked", guestId);
        JOptionPane.showMessageDialog(frame, "Room booked successfully!");

        frame.setVisible(false);
        new MainMenuPage();
    }
}
