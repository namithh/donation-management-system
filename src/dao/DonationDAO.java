package dao;

import model.Donation;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonationDAO {

    // Method to insert a donation
    public void insertDonation(Donation donation) {
        String sql = "INSERT INTO donations (donor_name, amount, date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, donation.getDonorName());
            stmt.setDouble(2, donation.getAmount());
            stmt.setDate(3, new java.sql.Date(donation.getDate().getTime())); // Convert util.Date to sql.Date
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all donations
    public List<Donation> getAllDonations() {
        List<Donation> donations = new ArrayList<>();
        String sql = "SELECT * FROM donations";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Donation donation = new Donation();
                donation.setId(rs.getInt("id"));
                donation.setDonorName(rs.getString("donor_name"));
                donation.setAmount(rs.getDouble("amount"));
                donation.setDate(rs.getDate("date"));
                donations.add(donation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donations;
    }

    // Method to get a donation by ID
    public Donation getDonationById(int id) {
        Donation donation = null;
        String sql = "SELECT * FROM donations WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                donation = new Donation();
                donation.setId(rs.getInt("id"));
                donation.setDonorName(rs.getString("donor_name"));
                donation.setAmount(rs.getDouble("amount"));
                donation.setDate(rs.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donation;
    }

    // Method to update a donation
    public void updateDonation(Donation donation) {
        String sql = "UPDATE donations SET donor_name = ?, amount = ?, date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, donation.getDonorName());
            stmt.setDouble(2, donation.getAmount());
            stmt.setDate(3, new java.sql.Date(donation.getDate().getTime())); // Convert util.Date to sql.Date
            stmt.setInt(4, donation.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a donation by ID
    public void deleteDonation(int id) {
        String sql = "DELETE FROM donations WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
