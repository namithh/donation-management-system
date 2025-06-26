package service;

import dao.DonationPosterDAO;
import model.DonationPoster;
import java.util.List;

public class DonationPosterService {
    private DonationPosterDAO dao = new DonationPosterDAO();

    public boolean addDonationPoster(DonationPoster poster) {
        return dao.createDonationPoster(poster);
    }

    public List<DonationPoster> getAllDonationPosters() {
        return dao.getAllDonationPosters();
    }

    public boolean updateDonationPoster(DonationPoster poster) {
        return dao.updateDonationPoster(poster);
    }

    public boolean deleteDonationPoster(int id) {
        return dao.deleteDonationPoster(id);
    }

    public DonationPoster getDonationPosterById(int id) {
        return dao.getDonationPosterById(id);
    }


}