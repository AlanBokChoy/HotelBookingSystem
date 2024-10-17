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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author alanbokchoy
 */
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
    
    public LoginPage() {
        components();
        buttonComponents();
        actionlisteners();
        frame();
    }
    
    private void frame() {
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private void components() {
        frame = new JFrame("Login Page");
        loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font(null, Font.BOLD, 45));
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        registerLabel = new JLabel("Don't have an account?");
        messageLabel = new JLabel();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        
        loginLabel.setBounds(190, 30, 150, 100);
        usernameLabel.setBounds(100, 160, 75, 35);
        passwordLabel.setBounds(100, 200, 75, 35);
        registerLabel.setBounds(125, 320, 200, 35);
        messageLabel.setBounds(150, 250, 250, 35);
        usernameField.setBounds(175, 160, 200, 35);
        passwordField.setBounds(175, 200, 200, 35);

        frame.add(loginLabel);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(registerLabel);
        frame.add(messageLabel);
        frame.add(usernameField);
        frame.add(passwordField);
    }
    
    private void buttonComponents() {
        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");
        registerButton = new JButton("Register");
        
        loginButton.setBounds(140, 260, 100, 35);
        resetButton.setBounds(260, 260, 100, 35);
        registerButton.setBounds(280, 320, 100, 35);
        
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(registerButton);
    }
    
    private void actionlisteners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login button clicked");
                
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Register button clicked");
                
            }
        });
    }
}
