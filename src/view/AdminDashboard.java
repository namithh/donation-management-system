package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import model.User;
import view.UserView;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for content
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel welcomeLabel = new JLabel("Welcome, Admin!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton userButton = new JButton("Manage Users");
        JButton donationPosterButton = new JButton("Manage Donation Posters");
        JButton beneficiaryButton = new JButton("Manage Beneficiaries");


        // Add action listeners
        userButton.addActionListener((ActionEvent e) -> {
            new UserView(); // Replace with actual class
        });

        donationPosterButton.addActionListener((ActionEvent e) -> {
            System.out.println("Manage Donation Posters button clicked.");
            new DonationPosterView(); // Your working GUI
        });

        beneficiaryButton.addActionListener((ActionEvent e) -> {
            new BeneficiaryView(); // Replace with actual class
        });



        // Add to panel
        panel.add(welcomeLabel);
        panel.add(userButton);
        panel.add(donationPosterButton);
        panel.add(beneficiaryButton);


        add(panel);
        setVisible(true);
    }
}
