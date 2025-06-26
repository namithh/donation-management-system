package view;

import controller.DonationController;
import controller.DonationPosterController;
import model.DonationPoster;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DonationPosterListView extends JFrame {
    private DonationPosterController posterController;
    private DonationController donationController;
    private User currentUser;  // Store the current user to associate the donation
    private JPanel panel;


    public DonationPosterListView(User user) {
        this.currentUser = user;
        setTitle("Donation Posters");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize controllers
        posterController = new DonationPosterController();
        donationController = new DonationController();

        // Layout for displaying donation posters
        setLayout(new BorderLayout(10, 10));
        panel=new JPanel();

        // Get all donation posters from the controller
        List<DonationPoster> posters = posterController.getAllDonationPosters();

        // Panel to hold the posters and donate buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(posters.size(), 1, 10, 10));  // Create rows for each poster

        // Loop through each donation poster and create a button
        for (DonationPoster poster : posters) {
            JPanel posterPanel = new JPanel();
            posterPanel.setLayout(new FlowLayout());

            JLabel posterLabel = new JLabel(poster.getTitle() + " - " + poster.getDescription());
            JButton donateButton = new JButton("Donate");

            donateButton.addActionListener((ActionEvent e) -> {
                // When Donate button is clicked, open the DonationView
                // Pass the donor information (currentUser) and the selected donation poster details

                DonationView donationView = new DonationView(currentUser, poster);
                donationView.setVisible(true);
                dispose();  // Close DonationPosterListView
            });

            posterPanel.add(posterLabel);
            posterPanel.add(donateButton);
            panel.add(posterPanel);
        }


        add(new JScrollPane(panel), BorderLayout.CENTER);  // Add to the center with scrolling

        // Show the frame
        setVisible(true);
    }
}
