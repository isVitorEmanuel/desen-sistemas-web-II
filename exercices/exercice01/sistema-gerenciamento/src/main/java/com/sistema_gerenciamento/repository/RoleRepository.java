package main.java.com.sistema_gerenciamento.repository;

import main.java.com.sistema_gerenciamento.domain.Role;

import java.util.ArrayList;

public class RoleRepository {
    private Role role;

    public RoleRepository(Role role) {
        this.role = role;
    }

    public void setPermission(ArrayList<String> permission) {
        this.role.setPermissions(permission);
    }

    public void addPermission(String permission) {
        this.role.getPermissions().add(permission);
    }
}
