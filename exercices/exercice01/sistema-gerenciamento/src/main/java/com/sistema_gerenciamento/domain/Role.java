package main.java.com.sistema_gerenciamento.domain;

import java.util.ArrayList;

public class Role {
    private int ID;
    private String name;
    private ArrayList<String> permissions;

    public Role(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.permissions = new ArrayList<>();
    }

    public int getID() { return this.ID; }
    public String getName() { return this.name; }
    public ArrayList<String> getPermissions() { return this.permissions; }

    public void setID(int ID) { this.ID = ID; }
    public void setName(String name) { this.name = name; }
    public void setPermissions(ArrayList<String> permissions) { this.permissions = permissions; }
}
