package dao;

import model.DonationPoster;
import util.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class DonationPosterDAO {
    public boolean createDonationPoster(DonationPoster p) {
        // Set the current date if the date is null
        if (p.getDate() == null) {
            p.setDate(new java.util.Date()); // Set the current date
        }
        if (p.getTitle() == null || p.getTitle().trim().isEmpty()) {
            System.out.println("Title cannot be null or empty.");
            return false;
        }
        String sql = "INSERT INTO donation_posters (title, description, date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getTitle().trim());
            stmt.setString(2, p.getDescription());
            stmt.setDate(3, new java.sql.Date(p.getDate().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DonationPoster> getAllDonationPosters() {
        List<DonationPoster> list = new ArrayList<>();
        String sql = "SELECT * FROM donation_posters";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DonationPoster p = new DonationPoster(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("date")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateDonationPoster(DonationPoster poster) {
        // Set the current date if the date is null
        if (poster.getDate() == null) {
            poster.setDate(new java.util.Date()); // Set the current date
        }
        // Check for null or empty title and description
        if (poster.getTitle() == null || poster.getTitle().trim().isEmpty()) {
            System.out.println("Title cannot be null or empty.");
            return false;
        }
        String sql = "UPDATE donation_posters SET title = ?, description = ?, date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, poster.getTitle().trim());
            stmt.setString(2, poster.getDescription());
            stmt.setDate(3, new java.sql.Date(poster.getDate().getTime()));
            stmt.setInt(4, poster.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDonationPoster(int id) {
        String sql = "DELETE FROM donation_posters WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public DonationPoster getDonationPosterById(int id) {
        String sql = "SELECT * FROM donation_posters WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DonationPoster(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getDate("date")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
