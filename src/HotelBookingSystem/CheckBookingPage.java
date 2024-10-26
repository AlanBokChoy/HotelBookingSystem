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
public class CheckBookingPage {

    JFrame frame;
    JLabel checkBookingLabel;
    JLabel userDetailsLabel;
    JTextArea bookingDetailsArea;
    JButton returnButton;

    private DBManager dbManager;

    public CheckBookingPage() {
        dbManager = new DBManager();
        frame = new JFrame();
        components();
        returnButton();
        frame();
        displayUserDetails();
        displayBookingDetails();
    }

    private void frame() {
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void components() {
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
    }

    private void returnButton() {
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

    private void displayUserDetails() {
        int guestId = UserSession.getGuestId(); 
        String username = dbManager.getGuestUsername(guestId); 
        String name = dbManager.getGuestName(guestId); 
        String email = dbManager.getGuestEmail(guestId); 

        String userDetails = String.format("                   Name: %s       \nEmail: %s", name, email);
        userDetailsLabel.setText(userDetails);
    }

    private void displayBookingDetails() {
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
