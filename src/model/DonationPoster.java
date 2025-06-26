package model;

import java.util.Date;

public class DonationPoster {
    private int id;
    private String title;
    private String description;
    private Date date;

    // Default constructor
    public DonationPoster() {}

    // Constructor without ID (for insert)
    public DonationPoster(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    // Constructor with ID (for select/update)
    public DonationPoster(int id, String title, String description, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public DonationPoster(String title, String description) {
    }

    public DonationPoster(int id, String text, String text1) {
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
