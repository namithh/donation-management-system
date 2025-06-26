package model;

public class Beneficiary {
    private int id;
    private String name;
    private String contact;
    private String description;

    public Beneficiary() {}

    public Beneficiary(int id, String name, String contact, String description) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

