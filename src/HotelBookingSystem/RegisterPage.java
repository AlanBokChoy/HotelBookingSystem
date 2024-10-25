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
public class RegisterPage {

    private JFrame frame;
    private JLabel registerLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phonenumberLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phonenumberField;
    private JButton registerButton;
    private JButton resetButton;
    private JButton loginButton;

    private GuestDBManager guestDBManager;

    public RegisterPage() {
        guestDBManager = new GuestDBManager();
        guestDBManager.createGuestDatabase();
        
        frame = new JFrame("Register Page");
        components();
        registerButton();
        resetButton();
        loginButton();
        frame();
    }

    private void frame() {
        frame.setLayout(null);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void components() {
        registerLabel = new JLabel("REGISTER");
        registerLabel.setFont(new Font(null, Font.BOLD, 45));
        registerLabel.setBounds(145, 30, 300, 100);

        usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(75, 160, 75, 35);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(77, 200, 75, 35);

        loginLabel = new JLabel("Have an account?");
        loginLabel.setBounds(140, 440, 200, 35);

        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(100, 240, 75, 35);

        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(100, 280, 75, 35);

        phonenumberLabel = new JLabel("Phone: ");
        phonenumberLabel.setBounds(95, 320, 75, 35);

        usernameField = new JTextField();
        usernameField.setBounds(145, 160, 230, 35);

        passwordField = new JPasswordField();
        passwordField.setBounds(145, 200, 230, 35);

        nameField = new JTextField();
        nameField.setBounds(145, 240, 230, 35);

        emailField = new JTextField();
        emailField.setBounds(145, 280, 230, 35);

        phonenumberField = new JTextField();
        phonenumberField.setBounds(145, 320, 230, 35);

        frame.add(registerLabel);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(loginLabel);
        frame.add(nameLabel);
        frame.add(emailLabel);
        frame.add(phonenumberLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(nameField);
        frame.add(emailField);
        frame.add(phonenumberField);
    }

    private void registerButton() {
        registerButton = new JButton("Register");
        registerButton.setBounds(145, 380, 100, 35);

        frame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phonenumberField.getText();

                if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Incorrect Details.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(frame, "Incorrect Email.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else if (!phone.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Incorrect Phone Number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    guestDBManager.registerGuest(username, password, name, email, phone);

                    frame.setVisible(false);
                    MainMenuPage mainMenu = new MainMenuPage();
                }
            }
        });
    }

    private void resetButton() {
        resetButton = new JButton("Reset");
        resetButton.setBounds(275, 380, 100, 35);

        frame.add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
                nameField.setText("");
                emailField.setText("");
                phonenumberField.setText("");
            }
        });
    }

    private void loginButton() {
        loginButton = new JButton("Login");
        loginButton.setBounds(275, 440, 100, 35);

        frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                LoginPage login = new LoginPage();
            }
        });
    }
}
