import main.java.com.sistema_gerenciamento.domain.Role;
import main.java.com.sistema_gerenciamento.domain.User;
import main.java.com.sistema_gerenciamento.repository.UserRepository;
import main.java.com.sistema_gerenciamento.repository.RoleRepository;
import main.java.com.sistema_gerenciamento.service.UserService;
import main.java.com.sistema_gerenciamento.service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class UserTests {

    public static void main(String[] args) {
        // Create a list of users
        List<User> users = new ArrayList<>();

        // Tests for Role and RoleService
        testRoleAndRoleService();

        // Tests for User and UserService
        testUserAndUserService(users);
    }

    public static void testRoleAndRoleService() {
        System.out.println("== Starting test for Role and RoleService ==");

        // Create a role with permissions
        Role adminRole = new Role(0, "Admin");
        RoleRepository roleRepository = new RoleRepository(adminRole);
        RoleService roleService = new RoleService(roleRepository);

        // Add permissions to the role
        roleService.addPermission("READ_PRIVILEGES");
        roleService.addPermission("WRITE_PRIVILEGES");

        // Verify if the permissions were added correctly
        if (adminRole.getPermissions().contains("READ_PRIVILEGES") &&
                adminRole.getPermissions().contains("WRITE_PRIVILEGES")) {
            System.out.println("Role permission test: Passed");
        } else {
            System.out.println("Role permission test: Failed");
        }

        // Check permission existence using RoleService
        if (roleService.hasPermission("READ_PRIVILEGES")) {
            System.out.println("RoleService permission check test: Passed");
        } else {
            System.out.println("RoleService permission check test: Failed");
        }
    }

    public static void testUserAndUserService(List<User> users) {
        System.out.println("== Starting test for User and UserService ==");

        // Create a new role and user
        Role userRole = new Role(1, "User");
        User user = new User(1, "John", "john@gmail","password123", userRole);
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        // Add the user to the list
        userService.createUser(user, users);

        // Verify if the user was added
        if (users.contains(user)) {
            System.out.println("User creation test: Passed");
        } else {
            System.out.println("User creation test: Failed");
        }

        // Authenticate the user
        if (userService.authenticateUser("John", "password123", users)) {
            System.out.println("User authentication test: Passed");
        } else {
            System.out.println("User authentication test: Failed");
        }

        // Add a permission to the user's role
        userService.addUserPermission(user, "EDIT_PROFILE");

        // Verify if the permission was added
        if (user.getRole().getPermissions().contains("EDIT_PROFILE")) {
            System.out.println("Add user permission test: Passed");
        } else {
            System.out.println("Add user permission test: Failed");
        }

        // Delete the user
        userService.deleteUser(1, users);

        // Verify if the user was deleted
        if (!users.contains(user)) {
            System.out.println("User deletion test: Passed");
        } else {
            System.out.println("User deletion test: Failed");
        }
    }
}
