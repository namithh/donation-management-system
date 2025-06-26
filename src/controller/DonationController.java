package controller;

import model.Donation;
import service.DonationService;
import java.util.List;

public class DonationController {
    private DonationService donationService;

    public DonationController() {
        donationService = new DonationService();
    }

    public boolean addDonation(Donation donation) {
        donationService.addDonation(donation);
        return false;
    }

    public void updateDonation(Donation donation) {
        donationService.updateDonation(donation);
    }

    public void deleteDonation(int donationId) {
        donationService.deleteDonation(donationId);
    }

    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    public Donation getDonationById(int donationId) {
        return donationService.getDonationById(donationId);
    }
}
