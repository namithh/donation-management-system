package view;

import controller.UserController;
import model.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;


public class UserDashboard extends JFrame {
    private User currentUser;

    private JTextField searchField;
    private JButton searchBtn;
    private JTable userInfoTable;
    private DefaultTableModel userInfoModel;
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    public UserDashboard(User user) {
        this.currentUser = user;
        setTitle("User Dashboard - Welcome " + user.getName());
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 200));
        setLayout(new BorderLayout(15, 15));

        // North Panel with Welcome and Sign Out
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getName());
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(45, 60, 90));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton signOutBtn = new JButton("\uD83D\uDD13 Sign Out");
        signOutBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        signOutBtn.setForeground(Color.BLACK);
        signOutBtn.setBackground(new Color(255, 69, 0));
        signOutBtn.setFocusPainted(false);
        signOutBtn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        signOutBtn.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });

        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.setBackground(new Color(245, 245, 250));
        topRightPanel.add(signOutBtn);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(new Color(245, 245, 250));
        northPanel.add(welcomeLabel, BorderLayout.WEST);
        northPanel.add(topRightPanel, BorderLayout.EAST);
        add(northPanel, BorderLayout.NORTH);

        // Button Panel (Center)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 20, 20));
        buttonPanel.setBackground(new Color(245, 245, 250));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        searchField = new JTextField(15);
        searchBtn = new JButton("Search Posters");
        styleButton(searchBtn);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        searchPanel.setBackground(new Color(245, 245, 250));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        buttonPanel.add(searchPanel);

        JButton profileBtn = new JButton("Update Profile");
        styleButton(profileBtn);
        profileBtn.addActionListener((ActionEvent e) -> openUpdateProfileDialog());

        JButton postersBtn = new JButton("View Donation Posters");
        styleButton(postersBtn);
        postersBtn.addActionListener(e -> new DonationPosterListView(currentUser));

        buttonPanel.add(profileBtn);
        buttonPanel.add(postersBtn);
        add(buttonPanel, BorderLayout.CENTER);

        searchBtn.addActionListener(e -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                new DonationPosterListView(currentUser);
            } else {
                JOptionPane.showMessageDialog(this, "Enter search text!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // User Info Table (South)
        String[] userCols = {"Name", "Email", "Contact"};
        String[][] userData = {
                {currentUser.getName(), currentUser.getEmail(), currentUser.getContact()}
        };

        userInfoModel = new DefaultTableModel(userData, userCols);
        userInfoTable = new JTable(userInfoModel);
        userInfoTable.setEnabled(false);
        userInfoTable.setRowHeight(30);
        userInfoTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userInfoTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));

        JScrollPane tableScroll = new JScrollPane(userInfoTable);
        tableScroll.setPreferredSize(new Dimension(500, 70));

        JPanel userInfoPanel = new JPanel(new BorderLayout());
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("Your Information"));
        userInfoPanel.add(tableScroll, BorderLayout.CENTER);

        // Create a panel to hold both the table and the footer
        JPanel southCombinedPanel = new JPanel(new BorderLayout());

// Add the table panel to the top part of the south region
        southCombinedPanel.add(userInfoPanel, BorderLayout.CENTER);

// Footer label
        JLabel footerLabel = new JLabel("Stay connected with us!", JLabel.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        footerLabel.setForeground(new Color(120, 120, 130));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

// Add footer below the table
        southCombinedPanel.add(footerLabel, BorderLayout.SOUTH);

// Finally, add the combined panel to the SOUTH of the main frame
        add(southCombinedPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to open the update profile dialog
    private void openUpdateProfileDialog() {
        JTextField nameField = new JTextField(currentUser.getName(), 20);
        JTextField emailField = new JTextField(currentUser.getEmail(), 20);
        JTextField contactField = new JTextField(currentUser.getContact(), 20);
        JPasswordField passwordField = new JPasswordField(20);

        JPanel panel = new JPanel(new GridLayout(10, 4));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Contact:"));
        panel.add(contactField);
        panel.add(new JLabel("New Password:"));
        panel.add(passwordField);

        JButton deleteBtn = new JButton("Delete Profile");
        deleteBtn.setBackground(new Color(255, 69, 0));
        deleteBtn.setForeground(Color.BLACK);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 14));
        deleteBtn.addActionListener(e -> deleteProfile());

        panel.add(new JLabel());
        panel.add(deleteBtn);

        int option = JOptionPane.showConfirmDialog(this, panel, "Update Profile", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String updatedName = nameField.getText().trim();
            String updatedEmail = emailField.getText().trim();
            String updatedContact = contactField.getText().trim();
            String updatedPassword = new String(passwordField.getPassword()).trim();

            if (updatedName.isEmpty() || updatedEmail.isEmpty() || updatedContact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            // Basic email format validation
            if (!isValidEmail(updatedEmail)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address (e.g., example@mail.com).");
                return;
            }

            currentUser.setName(updatedName);
            currentUser.setEmail(updatedEmail);
            currentUser.setContact(updatedContact);
            if (!updatedPassword.isEmpty()) {
                currentUser.setPassword(updatedPassword);
            }

            UserController userController = new UserController();
            boolean success = userController.updateUser(currentUser);
            if (success) {
                JOptionPane.showMessageDialog(this, "Profile updated successfully!");
                updateUserInfoTable(); // Update the table with new info
            } else {
                JOptionPane.showMessageDialog(this, "Error updating profile!");
            }
        }
    }

    // Method to delete the profile
    private void deleteProfile() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete your profile?", "Delete Profile", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            UserController userController = new UserController();
            boolean success = userController.deleteUser(currentUser.getId());
            if (success) {
                JOptionPane.showMessageDialog(this, "Profile deleted successfully!");
                dispose();
                new LoginForm().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting profile!");
            }
        }
    }

    // Update the table with current user info
    private void updateUserInfoTable() {
        userInfoModel.setValueAt(currentUser.getName(), 0, 0);
        userInfoModel.setValueAt(currentUser.getEmail(), 0, 1);
        userInfoModel.setValueAt(currentUser.getContact(), 0, 2);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(173, 216, 230));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);

    }
}


