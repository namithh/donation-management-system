package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String contact;
    private String role;
    private String status;  // Assuming "Active" status

    // Constructor with id, name, email, password, contact, role, and status
    public User(int id, String name, String email, String password, String contact, String role, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.role = role;
        this.status = status;
    }

    // Constructor without id (for adding a new user)
    public User(String name, String email, String password, String contact, String role, String status) {
        this(0, name, email, password, contact, role, status);  // Calls the constructor with id = 0
    }

    // No-argument constructor
    public User() {
        // Default constructor
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
