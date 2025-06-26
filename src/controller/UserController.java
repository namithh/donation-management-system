package controller;

import model.User;
import service.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public User authenticateUser(String email, String password) {
        return userService.authenticate(email, password);
    }

    public boolean registerUser(User user) {
        return userService.register(user);
    }

    public User getUserById(int id) {
        return userService.getUserById(id);
    }
    public boolean addUser(User user) {
        return userService.register(user);  // Calls the service layer to register the user
    }
    public boolean updateUser(User user) {
        return userService.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return userService.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
