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
// Class represents the main menu interface of the application
public class MainMenuPage {

    private JFrame frame;
    private JLabel mainMenuLabel;
    private JButton createBookingButton;
    private JButton cancelBookingButton;
    private JButton checkBookingButton;
    private JButton informationButton;
    private JButton signoutButton;

    // Constructor to initialize the main menu page
    public MainMenuPage() {
        frame();
    }

    // Method to setup the frame properties
    private void frame() {
        frame = new JFrame("Main Menu Page");
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        components();
        frame.setVisible(true);
    }

    // Method to initialize and add components to the frame
    private void components() {
        mainMenuLabel = new JLabel("MAIN MENU");
        mainMenuLabel.setFont(new Font(null, Font.BOLD, 45));
        mainMenuLabel.setBounds(165, 30, 300, 100);

        frame.add(mainMenuLabel);

        createButtons();
    }

    // Method to create all buttons and add them to the frame
    private void createButtons() {
        createBookingButton = new JButton("Create Booking");
        createBookingButton.setBounds(185, 150, 230, 50);

        frame.add(createBookingButton);

        createBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CreateBookingPage createBooking = new CreateBookingPage();
            }
        });

        cancelBookingButton = new JButton("Cancel Booking");
        cancelBookingButton.setBounds(185, 220, 230, 50);

        frame.add(cancelBookingButton);

        cancelBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CancelBookingPage cancelBooking = new CancelBookingPage();
            }
        });

        checkBookingButton = new JButton("Check Booking");
        checkBookingButton.setBounds(185, 290, 230, 50);

        frame.add(checkBookingButton);

        checkBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CheckBookingPage checkBooking = new CheckBookingPage();
            }
        });

        informationButton = new JButton("Information");
        informationButton.setBounds(185, 360, 230, 50);

        frame.add(informationButton);

        informationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                InformationPage information = new InformationPage();
            }
        });

        signoutButton = new JButton("Sign Out");
        signoutButton.setBounds(185, 430, 230, 50);

        frame.add(signoutButton);

        signoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                LoginPage login = new LoginPage();
            }
        });
    }
}
