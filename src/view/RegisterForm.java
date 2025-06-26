package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame {
    private JTextField nameField, emailField, contactField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterForm() {
        setTitle("User Registration");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        nameField = new JTextField();
        emailField = new JTextField();
        contactField = new JTextField();
        passwordField = new JPasswordField();
        registerButton = new JButton("Register");

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Contact:"));
        add(contactField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel());
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setContact(contactField.getText());
                user.setPassword(new String(passwordField.getPassword()));

                user.setRole("User");

                boolean success = new UserController().registerUser(user);
                if (success) {
                    JOptionPane.showMessageDialog(RegisterForm.this, "Registration successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(RegisterForm.this, "Registration failed.");
                }
            }
        });
    }
}