package view;

import controller.DonationPosterController;
import model.DonationPoster;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DonationPosterView extends JFrame {
    private JTextField titleField, descriptionField;
    private JButton saveButton, updateButton, deleteButton, clearButton;
    private DonationPosterController controller;
    private JTable posterTable;
    private DonationPoster selectedPoster;

    public DonationPosterView() {
        setTitle("Donation Poster Management");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controller = new DonationPosterController();

        titleField = new JTextField();
        descriptionField = new JTextField();
        saveButton = new JButton("Create Poster");
        updateButton = new JButton("Update Poster");
        deleteButton = new JButton("Delete Poster");
        clearButton = new JButton("Clear Fields");

        // JTable to display posters
        posterTable = new JTable();
        posterTable.setModel(new DefaultTableModel(new Object[]{"ID", "Title", "Description", "Date"}, 0));
        JScrollPane scrollPane = new JScrollPane(posterTable);

        setLayout(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(saveButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event listeners for buttons
        saveButton.addActionListener(this::createDonationPoster);
        updateButton.addActionListener(this::updateDonationPoster);
        deleteButton.addActionListener(this::deleteDonationPoster);
        clearButton.addActionListener(this::clearFields);

        // Load all donation posters into the table
        loadDonationPosters();

        setVisible(true);
    }

    private void createDonationPoster(ActionEvent e) {
        String title = titleField.getText().trim();
        String description = descriptionField.getText().trim();

        if (title.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title and Description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DonationPoster poster = new DonationPoster(title, description);
        if (controller.addDonationPoster(poster)) {
            JOptionPane.showMessageDialog(this, "Poster created!");
            loadDonationPosters();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create poster.");
        }
    }

    private void updateDonationPoster(ActionEvent e) {
        int row = posterTable.getSelectedRow();
        if (row >= 0) {
            int id = (int) posterTable.getValueAt(row, 0);
            selectedPoster = controller.getDonationPosterById(id);
            titleField.setText(selectedPoster.getTitle());
            descriptionField.setText(selectedPoster.getDescription());

            // Now when the user presses the "Update" button, we will update the poster
            DonationPoster updatedPoster = new DonationPoster(id, titleField.getText(), descriptionField.getText());
            if (controller.updateDonationPoster(updatedPoster)) {
                JOptionPane.showMessageDialog(this, "Poster updated successfully!");
                loadDonationPosters();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update poster.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a poster to update.");
        }
    }

    private void deleteDonationPoster(ActionEvent e) {
        int row = posterTable.getSelectedRow();
        if (row >= 0) {
            int id = (int) posterTable.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this poster?", "Delete Poster", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (controller.deleteDonationPoster(id)) {
                    JOptionPane.showMessageDialog(this, "Poster deleted successfully!");
                    loadDonationPosters();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete poster.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a poster to delete.");
        }
    }

    private void loadDonationPosters() {
        List<DonationPoster> posters = controller.getAllDonationPosters();
        DefaultTableModel model = (DefaultTableModel) posterTable.getModel();
        model.setRowCount(0); // Clear the table before reloading
        for (DonationPoster poster : posters) {
            model.addRow(new Object[]{poster.getId(), poster.getTitle(), poster.getDescription(), poster.getDate()});
        }
    }

    private void clearFields(ActionEvent e) {
        titleField.setText("");
        descriptionField.setText("");
    }
}
