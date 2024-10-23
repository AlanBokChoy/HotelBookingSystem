/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author alanbokchoy
 */
public class InformationPage {
    
    JFrame frame;
    JLabel hotelLabel;
    JLabel informationLabel1;
    JLabel informationLabel2;
    JLabel informationLabel3;
    JLabel informationLabel4;
    JLabel informationLabel5;
    JLabel informationLabel6;
    JLabel informationLabel7;
    JLabel hotelPhonenumberLabel;
    JLabel hotelEmailLabel;
    JLabel hotelAddressLabel;
    JButton returnButton;
    
    public InformationPage() {
        frame = new JFrame("Information Page");
        components();
        returnButton();
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
        hotelLabel = new JLabel("ROYAL OAK HOTEL");
        hotelLabel.setFont(new Font(null, Font.BOLD, 45));
        hotelLabel.setBounds(80, 30, 800, 100);
        
        informationLabel1 = new JLabel("Located in the heart of Auckland, the Royal Oak Hotel combines modern luxury");
        informationLabel1.setFont(new Font(null, Font.BOLD, 12));
        informationLabel1.setBounds(55, 110, 600, 100);
        
        informationLabel2 = new JLabel("with historical charm. Situated on 21 Queen Street, this elegant hotel offers ");
        informationLabel2.setFont(new Font(null, Font.BOLD, 12));
        informationLabel2.setBounds(55, 130, 600, 100);
        
        informationLabel3 = new JLabel("easy access to the city’s attractions while providing a peaceful retreat. Guests");
        informationLabel3.setFont(new Font(null, Font.BOLD, 12));
        informationLabel3.setBounds(55, 150, 600, 100);
        
        informationLabel4 = new JLabel("can enjoy delicious can enjoy delicious meals throughout the day, crafted to");
        informationLabel4.setFont(new Font(null, Font.BOLD, 12));
        informationLabel4.setBounds(55, 170, 600, 100);
        
        informationLabel5 = new JLabel("showcase the best of local flavors. showcase the best of local flavors. With its");
        informationLabel5.setFont(new Font(null, Font.BOLD, 12));
        informationLabel5.setBounds(55, 190, 600, 100);
        
        informationLabel6 = new JLabel("comfortable amenities and dedicated service, the Auckland Royal Oak Hotel is a");
        informationLabel6.setFont(new Font(null, Font.BOLD, 12));
        informationLabel6.setBounds(55, 210, 600, 100);
        
        informationLabel7 = new JLabel("top choice for travelers seeking a memorable stay a memorable stay in the city.");
        informationLabel7.setFont(new Font(null, Font.BOLD, 12));
        informationLabel7.setBounds(55, 230, 600, 100);
        
        hotelPhonenumberLabel = new JLabel("Phone : 09-246-8224");
        hotelPhonenumberLabel.setFont(new Font(null, Font.BOLD, 12));
        hotelPhonenumberLabel.setBounds(110, 280, 200, 100);
        
        hotelEmailLabel = new JLabel("Email : info@royalOakHotel.com");
        hotelEmailLabel.setFont(new Font(null, Font.BOLD, 12));
        hotelEmailLabel.setBounds(110, 300, 200, 100);
        
        hotelAddressLabel = new JLabel("Address : 21 Queen Street, Auckland 2012");
        hotelAddressLabel.setFont(new Font(null, Font.BOLD, 12));
        hotelAddressLabel.setBounds(110, 320, 300, 100);
        
        frame.add(hotelLabel);
        frame.add(informationLabel1);
        frame.add(informationLabel2);
        frame.add(informationLabel3);
        frame.add(informationLabel4);
        frame.add(informationLabel5);
        frame.add(informationLabel6);
        frame.add(informationLabel7);
        frame.add(hotelPhonenumberLabel);
        frame.add(hotelEmailLabel);
        frame.add(hotelAddressLabel);
    }
    
    private void returnButton() {
        returnButton = new JButton("Return To Main Menu");
        returnButton.setBounds(380, 420, 150, 35);
        
        frame.add(returnButton); 
        
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenuPage mainMenu = new MainMenuPage();   
            }
        });
    }
}
