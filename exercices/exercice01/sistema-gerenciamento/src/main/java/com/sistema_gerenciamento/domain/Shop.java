package main.java.com.sistema_gerenciamento.domain;

import java.util.ArrayList;

public class Shop {
    int ID;
    String name;
    String location;
    ArrayList<Product> products;
    ArrayList<Operation> operations;

    public Shop(int ID, String name, String location) {
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.products = new ArrayList<>();
        this.operations = new ArrayList<>();
    }

    /*
     * Getters.
     */
    public int getID() { return this.ID; }
    public String getName() { return this.name; }
    public String getLocation() { return this.location; }
    public ArrayList<Product> getProducts() { return this.products; }
    public ArrayList<Operation> getOperations() { return this.operations; }

    /*
     * Setters.
     */
    public void setID(int ID) { this.ID = ID; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setProducts(ArrayList<Product> products) { this.products = products; }
    public void setOperations(ArrayList<Operation> operations) { this.operations = operations; }
}
