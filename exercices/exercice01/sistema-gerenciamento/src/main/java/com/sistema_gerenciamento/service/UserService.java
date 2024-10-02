package main.java.com.sistema_gerenciamento.service;

import main.java.com.sistema_gerenciamento.domain.User;
import main.java.com.sistema_gerenciamento.repository.RoleRepository;
import main.java.com.sistema_gerenciamento.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserRepository userRepository;

    /**
     * Constructor that initializes the UserService with a UserRepository.
     * @param userRepository The repository responsible for handling user-related data.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user if it doesn't already exist.
     * @param user The user to be created.
     * @param users List of existing users.
     */
    public void createUser(User user, List<User> users) {
        if (userRepository.getUserByUsername(user.getName(), users) == null) {
            userRepository.addUser(user, users);
        } else {
            System.out.println("User already exists.");
        }
    }

    /**
     * Deletes a user by their ID if found.
     * @param userId The ID of the user to be deleted.
     * @param users List of existing users.
     */
    public void deleteUser(int userId, List<User> users) {
        User user = userRepository.getUserByID(userId, users);
        if (user != null) {
            userRepository.deleteUser(userId, users);
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Retrieves a list of all users.
     * @param users List of existing users.
     * @return List of users.
     */
    public List<User> getAllUsers(List<User> users) {
        return userRepository.getAllUsers(users);
    }

    /**
     * Authenticates a user by username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param users List of existing users.
     * @return true if the user is authenticated, false otherwise.
     */
    public boolean authenticateUser(String username, String password, List<User> users) {
        User user = userRepository.getUserByUsername(username, users);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    /**
     * Sets a list of permissions for a user.
     * @param user The user whose permissions will be set.
     * @param permissions The list of permissions to assign to the user.
     */
    public void setUserPermissions(User user, ArrayList<String> permissions) {
        this.userRepository.setUserPermissions(user, permissions);
    }

    /**
     * Adds a single permission to a user.
     * @param user The user to whom the permission will be added.
     * @param permission The permission to be added.
     */
    public void addUserPermission(User user, String permission) {
        this.userRepository.addUserPermission(user, permission);
    }

    /**
     * Checks if a user has a specific permission.
     * @param user The user to check.
     * @param permission The permission to check for.
     * @return true if the user has the permission, false otherwise.
     */
    public boolean checkPermission(User user, String permission) {
        RoleRepository roleRepository = new RoleRepository(user.getRole());
        RoleService roleService = new RoleService(roleRepository);
        return roleService.hasPermission(permission);
    }
}
