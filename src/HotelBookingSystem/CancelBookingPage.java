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
public class CancelBookingPage {
    
    private JFrame frame;
    private JLabel cancelBookingLabel;
    private JComboBox<String> bookingComboBox;
    private JButton cancelButton;
    
    private DBManager guestDBManager;
    
    public CancelBookingPage() {
        guestDBManager = new DBManager();
        frame = new JFrame("Cancel Booking Page");
        components();
        loadBookings();
        frame();
    }
    
    private void frame() {
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private void components() {
        cancelBookingLabel = new JLabel("CANCEL BOOKING");
        cancelBookingLabel.setFont(new Font(null, Font.BOLD, 45));
        cancelBookingLabel.setBounds(95, 30, 800, 100);
        
        bookingComboBox = new JComboBox<>();
        bookingComboBox.setBounds(150, 150, 300, 35);

        cancelButton = new JButton("Cancel Booking");
        cancelButton.setBounds(200, 250, 200, 35);

        frame.add(cancelBookingLabel);
        frame.add(bookingComboBox);
        frame.add(cancelButton);
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBooking = (String) bookingComboBox.getSelectedItem();
                if (selectedBooking != null) {
                    int bookingId = Integer.parseInt(selectedBooking.split(" - ")[0]); 
                    guestDBManager.cancelRoomBooking(bookingId);
                    JOptionPane.showMessageDialog(frame, "Booking cancelled successfully!");
                    loadBookings();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a booking to cancel.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadBookings() {
        bookingComboBox.removeAllItems();
        int guestId = UserSession.getGuestId();
        List<String> bookings = guestDBManager.getGuestBookings(guestId);

        for (String booking : bookings) {
            bookingComboBox.addItem(booking);
        }
    }
}
  
