package main.java.com.sistema_gerenciamento.repository;

import main.java.com.sistema_gerenciamento.domain.Role;
import main.java.com.sistema_gerenciamento.domain.User;
import main.java.com.sistema_gerenciamento.service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    /**
     * Adds a user to the user list.
     * @param user The user to be added.
     * @param users The list of existing users.
     */
    public void addUser(User user, List<User> users) {
        users.add(user);
    }

    /**
     * Deletes a user from the user list based on the user ID.
     * @param userID The ID of the user to be deleted.
     * @param users The list of existing users.
     */
    public void deleteUser(int userID, List<User> users) {
        users.removeIf(user -> user.getID() == userID);
    }

    /**
     * Retrieves a user by their ID.
     * @param userID The ID of the user to be retrieved.
     * @param users The list of existing users.
     * @return The user with the specified ID, or null if not found.
     */
    public User getUserByID(int userID, List<User> users) {
        for (User user : users) {
            if (user.getID() == userID) {
                return user;
            }
        }
        return null;
    }

    /**
     * Retrieves a user by their username.
     * @param username The username of the user to be retrieved.
     * @param users The list of existing users.
     * @return The user with the specified username, or null if not found.
     */
    public User getUserByUsername(String username, List<User> users) {
        for (User user : users) {
            if (user.getName().equals(username)) { return user; }
        }
        return null;
    }

    /**
     * Returns the list of all users.
     * @param users The list of users.
     * @return The complete list of users.
     */
    public List<User> getAllUsers(List<User> users) { return users; }

    /**
     * Sets the permissions for a given user.
     * @param user The user for whom permissions will be set.
     * @param permissions The list of permissions to be assigned.
     */
    public void setUserPermissions(User user, ArrayList<String> permissions) {
        Role role = user.getRole();
        RoleRepository roleRepository = new RoleRepository(role);
        RoleService roleService = new RoleService(roleRepository);

        roleService.setPermissions(permissions);
    }

    /**
     * Adds a specific permission to a user's role.
     * @param user The user to whom the permission will be added.
     * @param permission The permission to be added.
     */
    public void addUserPermission(User user, String permission) {
        Role role = user.getRole();
        RoleRepository roleRepository = new RoleRepository(role);
        RoleService roleService = new RoleService(roleRepository);

        roleService.addPermission(permission);
    }

    /**
     * Retrieves the role and permissions of a given user.
     * @param user The user whose role and permissions are to be retrieved.
     * @return The role of the user.
     */
    public Role permissions(User user) {
        return user.getRole();
    }
}
