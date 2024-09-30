package main.java.com.sistema_gerenciamento.repository;

import main.java.com.sistema_gerenciamento.domain.Operation;
import main.java.com.sistema_gerenciamento.domain.Product;
import main.java.com.sistema_gerenciamento.domain.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageRepository {
    private Storage storage;

    /**
     * Constructor.
     */
    public StorageRepository(Storage storage) {
        this.storage = storage;

        if (this.storage.getOperations() == null ||
            this.storage.getProducts() == null) {
            this.storage.setOperations(new ArrayList<Operation>());
            this.storage.setProducts(new ArrayList<Product>());
        }
    }

    // Methods to Products.
    /**
     * This function register a product in storage.
     * @param product Product that will be added to stock
     */
    public void registerProduct(Product product) {
        this.storage.getProducts().add(product);
    }

    /**
     * This function unsubscribe a product in storage.
     * @param ID ID product that will be removed to stock.
     */
    public void unsubscribeProduct(int ID) {
        this.storage.getProducts().removeIf(product -> { return product.getID() == ID; });
    }

    /**
     * Return all products in stock.
     * @return Product that will be returned.
     */
    public ArrayList<Product> allProducts() {
        return this.storage.getProducts();
    }

    // Methods to Operations.
    /**
     * // Add an operation in stock.
     * @param operation Operation that will be saved in stock.
     */
    public void addOperation(Operation operation) {
        this.storage.getOperations().add(operation);
    }

    /**
     * Return all operations in stock.
     * @return Operations that will be returned.
     */
    public ArrayList<Operation> allOperations() { return storage.getOperations(); }
}
