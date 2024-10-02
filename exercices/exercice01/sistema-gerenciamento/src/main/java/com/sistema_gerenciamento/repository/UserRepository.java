package main.java.com.sistema_gerenciamento.repository;

import main.java.com.sistema_gerenciamento.domain.Role;
import main.java.com.sistema_gerenciamento.domain.User;
import main.java.com.sistema_gerenciamento.service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public void addUser(User user, List<User> users) {
        users.add(user);
    }

    public void updateUser(User user, List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID() == user.getID()) {
                users.set(i, user);
                return;
            }
        }
    }

    public void deleteUser(int userID, List<User> users) {
        users.removeIf(user -> user.getID() == userID);
    }

    public User getUserByID(int userID, List<User> users) {
        for (User user : users) {
            if (user.getID() == userID) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username, List<User> users) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers(List<User> users) {
        return users;
    }

    public void setUserPermissions(User user, ArrayList<String> permissions) {
        Role role = user.getRole();
        RoleRepository roleRepository = new RoleRepository(role);
        RoleService roleService = new RoleService(roleRepository);

        roleService.setPermissions(permissions);
    }

    public void addUserPermission(User user, String permission) {
        Role role = user.getRole();
        RoleRepository roleRepository = new RoleRepository(role);
        RoleService roleService = new RoleService(roleRepository);

        roleService.addPermission(permission);
    }
}
