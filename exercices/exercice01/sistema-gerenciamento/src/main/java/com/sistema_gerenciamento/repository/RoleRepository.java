package main.java.com.sistema_gerenciamento.repository;

import main.java.com.sistema_gerenciamento.domain.Role;

import java.util.ArrayList;

public class RoleRepository {
    private Role role;

    /**
     * Constructor that initializes the RoleRepository with a specific role.
     * @param role The role to be managed by this repository.
     */
    public RoleRepository(Role role) {
        this.role = role;
    }

    /**
     * Sets a list of permissions for the role.
     * @param permissions The list of permissions to be assigned to the role.
     */
    public void setPermission(ArrayList<String> permissions) {
        this.role.setPermissions(permissions);
    }

    /**
     * Adds a single permission to the role.
     * @param permission The permission to be added to the role.
     */
    public void addPermission(String permission) {
        this.role.getPermissions().add(permission);
    }

    /**
     * Retrieves the role being managed by this repository.
     * @return The role object.
     */
    public Role getRole() {
        return this.role;
    }
}
