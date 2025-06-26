
package view;

import controller.DonationController;
import model.Donation;
import model.DonationPoster;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class DonationView extends JFrame {
    private DonationPoster poster;
    private User currentUser;
    private JTextField donorNameField, amountField, dateField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTable donationTable;
    private DonationController controller;

    // Constructor to initialize DonationView with user and poster
    public DonationView(User currentUser, DonationPoster poster) {
        this.currentUser = currentUser;  // Set the current user (donor)
        this.poster = poster;            // Set the selected donation poster (beneficiary)

        setTitle("Donation Form");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controller = new DonationController();

        donorNameField = new JTextField(currentUser.getName());  // Prepopulate with donor's name
        amountField = new JTextField();
        dateField = new JTextField();
        addButton = new JButton("Add Donation");
        updateButton = new JButton("Update Donation");
        deleteButton = new JButton("Delete Donation");
        clearButton = new JButton("Clear Fields");

        // 🌟 Style text fields
        donorNameField.setForeground(Color.BLACK);
        amountField.setForeground(Color.BLACK);
        dateField.setForeground(Color.BLACK);
        donorNameField.setBackground(Color.WHITE);
        amountField.setBackground(Color.WHITE);
        dateField.setBackground(Color.WHITE);

        // 🌟 Style buttons
        addButton.setForeground(Color.BLACK);
        updateButton.setForeground(Color.BLACK);
        deleteButton.setForeground(Color.BLACK);
        clearButton.setForeground(Color.BLACK);
        addButton.setBackground(Color.LIGHT_GRAY);
        updateButton.setBackground(Color.LIGHT_GRAY);
        deleteButton.setBackground(Color.LIGHT_GRAY);
        clearButton.setBackground(Color.LIGHT_GRAY);

        // 🌟 Optional font
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        donorNameField.setFont(font);
        amountField.setFont(font);
        dateField.setFont(font);
        addButton.setFont(font);
        updateButton.setFont(font);
        deleteButton.setFont(font);
        clearButton.setFont(font);

        donationTable = new JTable();
        donationTable.setModel(new DefaultTableModel(new Object[]{"ID", "Donor Name", "Amount", "Date"}, 0));
        JScrollPane scrollPane = new JScrollPane(donationTable);

        // Layout
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Donor Name:"));
        panel.add(donorNameField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("Date (yyyy-mm-dd):"));
        panel.add(dateField);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountText = amountField.getText().trim();
                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(DonationView.this, "Amount cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double amount = Double.parseDouble(amountText);
                    Donation donation = new Donation();
                    donation.setDonorName(donorNameField.getText());
                    donation.setAmount(amount);
                    donation.setDate(Date.valueOf(dateField.getText()));
                    donation.setDonorId(currentUser.getId());
                    donation.setBeneficiaryId(poster.getId());
                    controller.addDonation(donation);
                    loadDonationTable();
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DonationView.this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = donationTable.getSelectedRow();
                if (row >= 0) {
                    int donationId = (int) donationTable.getValueAt(row, 0);
                    Donation donation = controller.getDonationById(donationId);

                    String amountText = amountField.getText().trim();
                    if (amountText.isEmpty()) {
                        JOptionPane.showMessageDialog(DonationView.this, "Amount cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        double amount = Double.parseDouble(amountText);
                        donation.setDonorName(donorNameField.getText());
                        donation.setAmount(amount);
                        donation.setDate(Date.valueOf(dateField.getText()));
                        controller.updateDonation(donation);
                        loadDonationTable();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(DonationView.this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = donationTable.getSelectedRow();
                if (row >= 0) {
                    int donationId = (int) donationTable.getValueAt(row, 0);
                    controller.deleteDonation(donationId);
                    loadDonationTable();
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        loadDonationTable();
    }

    public DonationView() {
        // Empty constructor (optional use)
    }

    private void loadDonationTable() {
        List<Donation> donations = controller.getAllDonations();
        DefaultTableModel model = (DefaultTableModel) donationTable.getModel();
        model.setRowCount(0);
        for (Donation donation : donations) {
            model.addRow(new Object[]{donation.getId(), donation.getDonorName(), donation.getAmount(), donation.getDate()});
        }
    }

    private void clearFields() {
        donorNameField.setText("");
        amountField.setText("");
        dateField.setText("");
    }
}
