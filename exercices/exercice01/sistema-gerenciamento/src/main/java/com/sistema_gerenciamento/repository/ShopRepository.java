package main.java.com.sistema_gerenciamento.repository;

import main.java.com.sistema_gerenciamento.domain.Operation;
import main.java.com.sistema_gerenciamento.domain.Product;
import main.java.com.sistema_gerenciamento.domain.Shop;

import java.util.ArrayList;

public class ShopRepository {

    Shop shop;

    /**
     * Constructor.
     */
    public ShopRepository(Shop shop) {
        this.shop = shop;
    }

    // Methods to Products.
    /**
     * This function registers a product in storage.
     * @param product Product that will be added to stock
     */
    public void registerProduct(Product product) {
        this.shop.getProducts().add(product);
    }

    /**
     * This function unsubscribes a product in storage.
     * @param ID ID product that will be removed from stock.
     */
    public void unsubscribeProduct(int ID) {
        this.shop.getProducts().removeIf(product -> { return product.getID() == ID; });
    }

    /**
     * Returns all products in stock.
     * @return List of all products in stock.
     */
    public ArrayList<Product> allProducts() {
        return this.shop.getProducts();
    }

    // Methods to Operations.
    /**
     * This function adds an operation to the list of operations in stock.
     * @param operation Operation that will be saved in stock.
     */
    public void addOperation(Operation operation) {
        this.shop.getOperations().add(operation);
    }

    /**
     * Returns all operations performed on the stock.
     * @return List of all operations in stock.
     */
    public ArrayList<Operation> allOperations() {
        return this.shop.getOperations();
    }
}
