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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author alanbokchoy
 */
// Class represents the login page of the application
public class LoginPage {

    private JFrame frame;
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel registerLabel;
    private JLabel messageLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JButton registerButton;
    private DBManager dbManager;

    // Constructor to initialize the login page
    public LoginPage() {
        dbManager = new DBManager();
        frame();
    }

    // Method to setup the frame properties
    private void frame() {
        frame = new JFrame("Login Page");
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        components();
        frame.setVisible(true);
    }

    // Method to initialize and add components to the frame
    private void components() {
        loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font(null, Font.BOLD, 45));
        loginLabel.setBounds(180, 30, 150, 100);

        usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(75, 160, 75, 35);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(77, 200, 75, 35);

        registerLabel = new JLabel("Don't have an account?");
        registerLabel.setBounds(100, 320, 200, 35);

        messageLabel = new JLabel();
        messageLabel.setBounds(150, 250, 200, 35);

        usernameField = new JTextField();
        usernameField.setBounds(145, 160, 230, 35);

        passwordField = new JPasswordField();
        passwordField.setBounds(145, 200, 230, 35);

        frame.add(loginLabel);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(registerLabel);
        frame.add(messageLabel);
        frame.add(usernameField);
        frame.add(passwordField);

        loginButton();
        resetButton();
        registerButton();
    }

    // Method to setup the login button and its action
    private void loginButton() {
        loginButton = new JButton("Login");
        loginButton.setBounds(145, 260, 100, 35);

        frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (dbManager.verifyCredentials(username, password)) {
                    int guestId = dbManager.getGuestId(username);
                    UserSession.setUsername(username);
                    UserSession.setGuestId(guestId);

                    frame.setVisible(false);
                    MainMenuPage mainMenu = new MainMenuPage();
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect Details.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to setup the reset button and its action
    private void resetButton() {
        resetButton = new JButton("Reset");
        resetButton.setBounds(275, 260, 100, 35);

        frame.add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }

    // Method to setup the register button and its action
    private void registerButton() {
        registerButton = new JButton("Register");
        registerButton.setBounds(270, 320, 100, 35);

        frame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                RegisterPage register = new RegisterPage();
            }
        });
    }
}
