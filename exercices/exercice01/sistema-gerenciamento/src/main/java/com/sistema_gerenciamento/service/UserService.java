package main.java.com.sistema_gerenciamento.service;

import main.java.com.sistema_gerenciamento.domain.User;
import main.java.com.sistema_gerenciamento.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user, List<User> users) {
        if (userRepository.getUserByUsername(user.getName(), users) == null) {
            userRepository.addUser(user, users);
        } else {
            System.out.println("User already exist.");
        }
    }

    public void updateUser(User user, List<User> users) {
        User existingUser = userRepository.getUserByID(user.getID(), users);
        if (existingUser != null) {
            userRepository.updateUser(user, users);
        } else {
            System.out.println("User not found.");
        }
    }

    public void deleteUser(int userId, List<User> users) {
        User user = userRepository.getUserByID(userId, users);
        if (user != null) {
            userRepository.deleteUser(userId, users);
        } else {
            System.out.println("User not found.");
        }
    }

    public List<User> getAllUsers(List<User> users) {
        return userRepository.getAllUsers(users);
    }

    public boolean authenticateUser(String username, String password, List<User> users) {
        User user = userRepository.getUserByUsername(username, users);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public void setUserPermissions(User user, ArrayList<String> permissions) {
        // TODO: Check if the permissions are valid.
        this.userRepository.setUserPermissions(user, permissions);
    }

    public void addUserPermission(User user, String permission) {
        // TODO: Check if the permission is valid.
        this.userRepository.addUserPermission(user, permission);
    }
}
