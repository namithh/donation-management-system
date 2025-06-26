
package view;

import controller.BeneficiaryController;
import model.Beneficiary;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BeneficiaryView extends JFrame {
    private JTextField nameField, contactField, descriptionField;
    private JButton saveButton, updateButton, deleteButton, clearButton;
    private JTable beneficiaryTable;
    private BeneficiaryController controller;
    private int selectedBeneficiaryId = -1;

    public BeneficiaryView() {
        setTitle("Beneficiary Management");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controller = new BeneficiaryController();

        nameField = new JTextField();
        contactField = new JTextField();
        descriptionField = new JTextField();
        saveButton = new JButton("Create Beneficiary");
        updateButton = new JButton("Update Beneficiary");
        deleteButton = new JButton("Delete Beneficiary");
        clearButton = new JButton("Clear Fields");

        // JTable to display beneficiaries
        beneficiaryTable = new JTable();
        beneficiaryTable.setModel(new DefaultTableModel(new Object[]{"ID", "Name", "Contact", "Description"}, 0));
        JScrollPane scrollPane = new JScrollPane(beneficiaryTable);

        // Layout and styling
        getContentPane().setBackground(Color.decode("#f4f4f9")); // Light background
        setLayout(new BorderLayout(15, 15));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Beneficiary Details"));
        inputPanel.setBackground(Color.decode("#f4f4f9"));

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 13);
        Font btnFont = new Font("Arial", Font.BOLD, 13);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(new Color(33, 37, 41));
        inputPanel.add(nameLabel);
        nameField.setFont(fieldFont);
        inputPanel.add(nameField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setFont(labelFont);
        contactLabel.setForeground(new Color(33, 37, 41));
        inputPanel.add(contactLabel);
        contactField.setFont(fieldFont);
        inputPanel.add(contactField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(labelFont);
        descLabel.setForeground(new Color(33, 37, 41));
        inputPanel.add(descLabel);
        descriptionField.setFont(fieldFont);
        inputPanel.add(descriptionField);

        // Button colors and font
        saveButton.setBackground(Color.decode("#4CAF50"));
        saveButton.setForeground(Color.BLACK);
        saveButton.setFont(btnFont);

        updateButton.setBackground(Color.decode("#2196F3"));
        updateButton.setForeground(Color.BLACK);
        updateButton.setFont(btnFont);

        deleteButton.setBackground(Color.decode("#f44336"));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFont(btnFont);

        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.BLACK);
        clearButton.setFont(btnFont);

        inputPanel.add(saveButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Table styling
        beneficiaryTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        beneficiaryTable.getTableHeader().setBackground(new Color(70, 130, 180));
        beneficiaryTable.getTableHeader().setForeground(Color.BLACK);
        beneficiaryTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        beneficiaryTable.setRowHeight(22);

        // Focus effect
        addFocusHighlight(nameField);
        addFocusHighlight(contactField);
        addFocusHighlight(descriptionField);

        // Load table selection
        beneficiaryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = beneficiaryTable.getSelectedRow();
                    if (row >= 0) {
                        selectedBeneficiaryId = (int) beneficiaryTable.getValueAt(row, 0);
                        nameField.setText((String) beneficiaryTable.getValueAt(row, 1));
                        contactField.setText((String) beneficiaryTable.getValueAt(row, 2));
                        descriptionField.setText((String) beneficiaryTable.getValueAt(row, 3));
                    }
                }
            }
        });

        // Event listeners
        saveButton.addActionListener(this::createBeneficiary);
        updateButton.addActionListener(this::updateBeneficiary);
        deleteButton.addActionListener(this::deleteBeneficiary);
        clearButton.addActionListener(this::clearFields);

        loadBeneficiaries();
        setVisible(true);
    }

    private void createBeneficiary(ActionEvent e) {
        String name = nameField.getText();
        String contact = contactField.getText();
        String description = descriptionField.getText();

        if (!validateInput(name, contact, description)) return;

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName(name);
        beneficiary.setContact(contact);
        beneficiary.setDescription(description);

        if (controller.addBeneficiary(beneficiary)) {
            JOptionPane.showMessageDialog(this, "Beneficiary created successfully!");
            loadBeneficiaries();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create beneficiary.");
        }
    }

    private void updateBeneficiary(ActionEvent e) {
        int row = beneficiaryTable.getSelectedRow();
        if (row >= 0) {
            int id = (int) beneficiaryTable.getValueAt(row, 0);
            String updatedName = nameField.getText();
            String updatedContact = contactField.getText();
            String updatedDescription = descriptionField.getText();

            if (!validateInput(updatedName, updatedContact, updatedDescription)) return;

            Beneficiary updatedBeneficiary = new Beneficiary(id, updatedName, updatedContact, updatedDescription);
            if (controller.updateBeneficiary(updatedBeneficiary)) {
                JOptionPane.showMessageDialog(this, "Beneficiary updated successfully!");
                loadBeneficiaries();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update beneficiary.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a beneficiary to update.");
        }
    }

    private void deleteBeneficiary(ActionEvent e) {
        int row = beneficiaryTable.getSelectedRow();
        if (row >= 0) {
            int id = (int) beneficiaryTable.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this beneficiary?", "Delete Beneficiary", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (controller.deleteBeneficiary(id)) {
                    JOptionPane.showMessageDialog(this, "Beneficiary deleted successfully!");
                    loadBeneficiaries();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete beneficiary.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a beneficiary to delete.");
        }
    }

    private void loadBeneficiaries() {
        List<Beneficiary> beneficiaries = controller.getAllBeneficiaries();
        DefaultTableModel model = (DefaultTableModel) beneficiaryTable.getModel();
        model.setRowCount(0); // Clear table

        for (Beneficiary beneficiary : beneficiaries) {
            model.addRow(new Object[]{beneficiary.getId(), beneficiary.getName(), beneficiary.getContact(), beneficiary.getDescription()});
        }
    }

    private void clearFields(ActionEvent e) {
        nameField.setText("");
        contactField.setText("");
        descriptionField.setText("");
    }

    private void addFocusHighlight(JTextField field) {
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBackground(new Color(255, 255, 204)); // Light yellow
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBackground(Color.WHITE);
            }
        });
    }
       private boolean validateInput(String name, String contact, String description) {
        if (name.isEmpty() || contact.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!name.matches("^[A-Za-z ]+$")) {
            JOptionPane.showMessageDialog(this, "Name must contain only letters and spaces.");
            return false;
        }

        if (!contact.matches("^\\d{10}$")) {
            JOptionPane.showMessageDialog(this, "Contact must be exactly 10 digits.");
            return false;
        }

        return true;
    }
}

