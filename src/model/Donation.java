package model;

import java.util.Date;


public class Donation {
    private int id;
    private int userId;
    private int beneficiaryId;
    private String donorName;
    private double amount;
    private Date date;

    // Constructors
    public Donation() {}

    public Donation(int id, int userId, int beneficiaryId, String donorName, double amount, Date date) {
        this.id = id;
        this.userId = userId;
        this.beneficiaryId = beneficiaryId;
        this.donorName = donorName;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDonorId(int id) {
    }
}
