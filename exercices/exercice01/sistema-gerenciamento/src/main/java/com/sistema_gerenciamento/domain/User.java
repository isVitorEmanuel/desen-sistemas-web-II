package main.java.com.sistema_gerenciamento.domain;

public class User {
    private int ID;
    private String name;
    private String email;
    private String password;
    private Role role;

    public User(int ID, String name, String email, String password, Role role) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getID() { return this.ID; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public String getPassword() { return this.password; }
    public Role getRole() { return this.role; }

    public void setID(int ID) { this.ID = ID; }
    public void setName(String name) { this.name = name;}
    public void setEmail(String email) { this.email = email;}
    public void setPassword(String password) { this.password = password;}
    public void setRole(Role role) { this.role = role;}
}
