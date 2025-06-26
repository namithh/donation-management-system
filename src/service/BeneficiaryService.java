package service;

import model.Beneficiary;
import dao.BeneficiaryDAO;  // Assuming BeneficiaryDAO handles database access
import java.util.List;

public class BeneficiaryService {

    private BeneficiaryDAO beneficiaryDAO;

    public BeneficiaryService() {
        this.beneficiaryDAO = new BeneficiaryDAO();
    }

    // Add Beneficiary
    public boolean addBeneficiary(Beneficiary beneficiary) {
        return beneficiaryDAO.addBeneficiary(beneficiary);
    }

    // Get Beneficiary by ID
    public Beneficiary getBeneficiaryById(int id) {
        return beneficiaryDAO.getBeneficiaryById(id);  // Ensure this method exists in BeneficiaryDAO
    }

    // Get all Beneficiaries
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryDAO.getAllBeneficiaries();
    }

    // Update Beneficiary
    public boolean updateBeneficiary(Beneficiary beneficiary) {
        return beneficiaryDAO.updateBeneficiary(beneficiary);
    }

    // Delete Beneficiary
    public boolean deleteBeneficiary(int id) {
        return beneficiaryDAO.deleteBeneficiary(id);
    }
}
