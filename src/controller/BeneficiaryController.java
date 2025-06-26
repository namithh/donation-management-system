package controller;

import model.Beneficiary;
import service.BeneficiaryService;
import java.util.List;

public class BeneficiaryController {
    private BeneficiaryService beneficiaryService;

    public BeneficiaryController() {
        this.beneficiaryService = new BeneficiaryService();
    }

    public boolean addBeneficiary(Beneficiary beneficiary) {
        return beneficiaryService.addBeneficiary(beneficiary);
    }

    public Beneficiary getBeneficiaryById(int id) {
        return beneficiaryService.getBeneficiaryById(id);
    }

    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryService.getAllBeneficiaries();
    }

    public boolean updateBeneficiary(Beneficiary beneficiary) {
        return beneficiaryService.updateBeneficiary(beneficiary);
    }

    public boolean deleteBeneficiary(int id) {
        return beneficiaryService.deleteBeneficiary(id);
    }
}
