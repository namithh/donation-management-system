package dao;

import model.Beneficiary;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeneficiaryDAO {

    private Connection connection;

    public BeneficiaryDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Add Beneficiary
    public boolean addBeneficiary(Beneficiary beneficiary) {
        String sql = "INSERT INTO beneficiaries (name, contact, description) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, beneficiary.getName());
            stmt.setString(2, beneficiary.getContact());
            stmt.setString(3, beneficiary.getDescription());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all Beneficiaries
    public List<Beneficiary> getAllBeneficiaries() {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        String sql = "SELECT * FROM beneficiaries";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                beneficiaries.add(mapResultSetToBeneficiary(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beneficiaries;
    }

    // Update Beneficiary
    public boolean updateBeneficiary(Beneficiary beneficiary) {
        String sql = "UPDATE beneficiaries SET name = ?, contact = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, beneficiary.getName());
            stmt.setString(2, beneficiary.getContact());
            stmt.setString(3, beneficiary.getDescription());
            stmt.setInt(4, beneficiary.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Beneficiary
    public boolean deleteBeneficiary(int id) {
        String sql = "DELETE FROM beneficiaries WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Beneficiary by ID
    public Beneficiary getBeneficiaryById(int id) {
        String sql = "SELECT * FROM beneficiaries WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBeneficiary(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to convert ResultSet to Beneficiary object
    private Beneficiary mapResultSetToBeneficiary(ResultSet rs) throws SQLException {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(rs.getInt("id"));
        beneficiary.setName(rs.getString("name"));
        beneficiary.setContact(rs.getString("contact"));
        beneficiary.setDescription(rs.getString("description"));
        return beneficiary;
    }
}
