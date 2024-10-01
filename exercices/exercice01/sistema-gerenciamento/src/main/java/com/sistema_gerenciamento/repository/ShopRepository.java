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
     * This function register a product in storage.
     * @param product Product that will be added to stock
     */
    public void registerProduct(Product product) {
        this.shop.getProducts().add(product);
    }

    /**
     * This function unsubscribe a product in storage.
     * @param ID ID product that will be removed to stock.
     */
    public void unsubscribeProduct(int ID) {
        this.shop.getProducts().removeIf(product -> { return product.getID() == ID; });
    }

    /**
     * Return all products in stock.
     * @return Product that will be returned.
     */
    public ArrayList<Product> allProducts() { return this.shop.getProducts(); }

    // Methods to Operations.
    /**
     * // Add an operation in stock.
     * @param operation Operation that will be saved in stock.
     */
    public void addOperation(Operation operation) { this.shop.getOperations().add(operation); }

    /**
     * Return all operations in stock.
     * @return Operations that will be returned.
     */
    public ArrayList<Operation> allOperations() { return this.shop.getOperations(); }
}
