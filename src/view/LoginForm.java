package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginForm() {
        setTitle("Login Form");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(4, 2,10,10));
        getContentPane().setBackground(new Color(245, 245, 245));  // Set light gray background

        emailField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Set font and color for the text fields and buttons
        Font font = new Font("Arial", Font.PLAIN, 14);
        emailField.setFont(font);
        passwordField.setFont(font);
        loginButton.setFont(font);
        registerButton.setFont(font);

        // Set border style for the text fields
        emailField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));

        // Set button colors
        loginButton.setBackground(new Color(220, 220, 220));
        loginButton.setForeground(Color.BLACK);
        registerButton.setBackground(new Color(220, 220, 220));
        registerButton.setForeground(new Color(0, 123, 255));
        loginButton.setFocusPainted(false);
        registerButton.setFocusPainted(false);

        // Set button hover effects (using ActionListener)
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 103, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 123, 255));
            }
        });

        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(180, 180, 180));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(220, 220, 220));
            }
        });

        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel());
        add(loginButton);
        add(new JLabel());
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate user from UserController
                User user = new UserController().authenticateUser(email, password);

                if (user != null) {
                    if (user.getRole().equals("Admin")) {
                        // If the user is an admin, open AdminDashboard
                        JOptionPane.showMessageDialog(LoginForm.this, "Admin login successful!");
                        new AdminDashboard().setVisible(true); // Open Admin Dashboard
                    } else {
                        // If the user is a regular user, open UserDashboard
                        JOptionPane.showMessageDialog(LoginForm.this, "User login successful!");
                        new UserDashboard(user).setVisible(true); // Open User Dashboard and pass the user
                    }
                    dispose(); // Close the login form after successful login
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid credentials!");
                }
            }
        });

        // Register Button ActionListener
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the registration form
                new RegisterForm().setVisible(true);  // Open the Register Form
                dispose(); // Close the login form
            }
        });
    }
}