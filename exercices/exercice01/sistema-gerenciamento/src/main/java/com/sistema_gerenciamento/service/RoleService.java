package main.java.com.sistema_gerenciamento.service;

import main.java.com.sistema_gerenciamento.repository.RoleRepository;

import java.util.ArrayList;

public class RoleService {
    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.roleRepository.setPermission(permissions);
    }

    public void addPermission(String permission) {
        this.roleRepository.addPermission(permission);
    }
}
