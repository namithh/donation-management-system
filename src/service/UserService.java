package service;

import dao.UserDAO;
import model.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User authenticate(String email, String password) {
        return userDAO.authenticate(email, password); // Calls the authenticate method in UserDAO
    }

    public boolean register(User user) {
        return userDAO.register(user); // Calls the register method in UserDAO
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id); // Calls the getUserById method in UserDAO
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user); // Calls the updateUser method in UserDAO
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id); // Calls the deleteUser method in UserDAO
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers(); // Calls the getAllUsers method in UserDAO
    }
}
