/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author alanbokchoy
 */
public class CreateBookingPage {
    
    JFrame frame;
    JLabel createBookingLabel;
    
    public CreateBookingPage() {
        frame = new JFrame();
        components();
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
        createBookingLabel = new JLabel("CREATE BOOKING");
        createBookingLabel.setFont(new Font(null, Font.BOLD, 45));
        createBookingLabel.setBounds(95, 30, 800, 100);
        
        frame.add(createBookingLabel);
    }
}
