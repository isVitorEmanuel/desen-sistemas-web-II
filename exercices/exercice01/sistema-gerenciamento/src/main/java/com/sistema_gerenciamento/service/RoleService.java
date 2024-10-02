package main.java.com.sistema_gerenciamento.service;

import main.java.com.sistema_gerenciamento.repository.RoleRepository;

import java.util.ArrayList;

public class RoleService {
    RoleRepository roleRepository;

    /**
     * Constructor that initializes the RoleService with a specific RoleRepository.
     * @param roleRepository The repository responsible for handling role-related data.
     */
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Sets a list of permissions for the role.
     * @param permissions The list of permissions to be assigned to the role.
     */
    public void setPermissions(ArrayList<String> permissions) {
        this.roleRepository.setPermission(permissions);
    }

    /**
     * Adds a single permission to the role.
     * @param permission The permission to be added to the role.
     */
    public void addPermission(String permission) {
        this.roleRepository.addPermission(permission);
    }

    /**
     * Checks if the role has a specific permission.
     * @param permission The permission to check for.
     * @return True if the role has the permission, false otherwise.
     */
    public boolean hasPermission(String permission) {
        ArrayList<String> permissionsUser = roleRepository.getRole().getPermissions();

        return permissionsUser.contains(permission);
    }
}
