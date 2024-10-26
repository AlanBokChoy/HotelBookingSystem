/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 *
 * @author alanbokchoy
 */
public class CancelBookingPage {
    
    private JFrame frame;
    private JLabel cancelBookingLabel;
    private JList<String> bookingList;
    private DefaultListModel<String> listModel;
    private JButton cancelButton;
    private DBManager roomDBManager;

    public CancelBookingPage() {
        roomDBManager = new DBManager();
        frame = new JFrame("Cancel Booking");
        listModel = new DefaultListModel<>();
        loadBookings();
        components();
        frame();
    }

    private void frame() {
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void components() {
        cancelBookingLabel = new JLabel("CANCEL BOOKING");
        cancelBookingLabel.setFont(new Font(null, Font.BOLD, 25));
        cancelBookingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        bookingList = new JList<>(listModel);
        bookingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        cancelButton = new JButton("Cancel Booking");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBooking = bookingList.getSelectedValue();
                if (selectedBooking != null) {
                    int bookingId = Integer.parseInt(selectedBooking.split(" - ")[0]); // Extract booking ID
                    cancelBooking(bookingId);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a booking to cancel.");
                }
            }
        });

        frame.add(cancelBookingLabel, BorderLayout.NORTH);
        frame.add(new JScrollPane(bookingList), BorderLayout.CENTER);
        frame.add(cancelButton, BorderLayout.SOUTH);
    }

    private void loadBookings() {
        int guestId = UserSession.getGuestId();
        List<String> bookings = roomDBManager.getGuestBookings(guestId);
        for (String booking : bookings) {
            listModel.addElement(booking);
        }
    }

    private void cancelBooking(int bookingId) {
        int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to cancel this booking?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            roomDBManager.cancelRoomBooking(bookingId);
            JOptionPane.showMessageDialog(frame, "Booking cancelled successfully.");
            listModel.removeElement(bookingId + " - " + "Your Booking Details");
        }
    }
}
