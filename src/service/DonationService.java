package service;

import dao.DonationDAO;
import model.Donation;
import java.util.List;

public class DonationService {
    private DonationDAO donationDAO = new DonationDAO();

    public void addDonation(Donation donation) {
        donationDAO.insertDonation(donation);
    }

    public List<Donation> getAllDonations() {
        return donationDAO.getAllDonations();
    }

    public Donation getDonationById(int id) {
        return donationDAO.getDonationById(id);
    }

    public void updateDonation(Donation donation) {
        donationDAO.updateDonation(donation);
    }

    public void deleteDonation(int id) {
        donationDAO.deleteDonation(id);
    }
}
