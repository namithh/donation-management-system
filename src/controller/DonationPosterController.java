package controller;

import model.DonationPoster;
import service.DonationPosterService;

import java.util.List;

public class DonationPosterController {
    private DonationPosterService service = new DonationPosterService();

    public boolean addDonationPoster(DonationPoster poster) {
        return service.addDonationPoster(poster);
    }

    public List<DonationPoster> getAllDonationPosters() {
        return service.getAllDonationPosters();
    }


    public boolean updateDonationPoster(DonationPoster poster) {
        return service.updateDonationPoster(poster);
    }

    public boolean deleteDonationPoster(int id) {
        return service.deleteDonationPoster(id);
    }

    public DonationPoster getDonationPosterById(int id) {
        return service.getDonationPosterById(id);
    }
}
