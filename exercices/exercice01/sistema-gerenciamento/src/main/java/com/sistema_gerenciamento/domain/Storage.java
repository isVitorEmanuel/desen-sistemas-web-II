package main.java.com.sistema_gerenciamento.domain;

import java.util.ArrayList;

public class Storage {
    ArrayList<Product> products;
    ArrayList<Operation> operations;

    /*
     * Constructors.
     */
    public Storage() { /* Empty. */ }
    public Storage(ArrayList<Product> products, ArrayList<Operation> operations) {
        this.products = products;
        this.operations = operations;
    }

    /*
     * Getters.
     */
    public ArrayList<Product> getProducts() { return this.products; }
    public ArrayList<Operation> getOperations() { return this.operations; }

    /*
     * Setters.
     */
    public void setProducts(ArrayList<Product> products) { this.products = products; }
    public void setOperations(ArrayList<Operation> operations) { this.operations = operations; }
}
