package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private JTextField nameField, emailField;
    private JComboBox<String> roleComboBox;
    private JPasswordField passwordField;
    private JButton addButton;
    private UserController controller;

    public UserView() {
        setTitle("User Management");
        setSize(450, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new UserController();

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 60, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(120, 60, 200, 25);
        add(emailField);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 100, 100, 25);  // New label for password
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 100, 200, 25);  // Password field for input
        add(passwordField);

        // Role Label and ComboBox
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(20, 140, 100, 25);
        add(roleLabel);

        roleComboBox = new JComboBox<>(new String[]{"Admin", "User"});  // Options for role
        roleComboBox.setBounds(120, 140, 200, 25);
        add(roleComboBox);

        addButton = new JButton("Add User");
        addButton.setBounds(120, 180, 120, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setPassword(new String(passwordField.getPassword()));
                user.setRole((String) roleComboBox.getSelectedItem());

                // Check if any field is empty
                if (user.getPassword().isEmpty() || user.getRole() == null) {
                    JOptionPane.showMessageDialog(null, "Password and Role cannot be empty!");
                    return;
                }

                boolean success = controller.addUser(user);
                if (success) {
                    JOptionPane.showMessageDialog(null, "User added successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add user.");
                }
            }
        });

        setVisible(true);
    }
}
