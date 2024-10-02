package main.java.com.sistema_gerenciamento.service;

import main.java.com.sistema_gerenciamento.domain.Operation;
import main.java.com.sistema_gerenciamento.domain.Product;
import main.java.com.sistema_gerenciamento.enums.TypeOperation;
import main.java.com.sistema_gerenciamento.repository.ShopRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShopService {

    private ShopRepository shopRepository;

    /**
     * Constructor that receives the repository.
     * @param shopRepository Repository to interact with the shop data.
     */
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    // Methods to manage Products
    /**
     * Registers a product, ensuring no duplicate IDs exist.
     * @param product Product to be added.
     * @return true if product was added, false if the product already exists.
     */
    public boolean registerProduct(Product product) {
        // Check if product with the same ID already exists
        Optional<Product> existingProduct = findProductById(product.getID());
        if (existingProduct.isPresent()) {
            return false; // Product already exists
        }
        shopRepository.registerProduct(product);
        return true;
    }

    /**
     * Unsubscribe a product by ID.
     * @param ID ID of the product to be removed.
     * @return true if the product was removed, false if it doesn't exist.
     */
    public boolean unsubscribeProduct(int ID) {
        Optional<Product> product = findProductById(ID);
        if (product.isPresent()) {
            shopRepository.unsubscribeProduct(ID);
            return true;
        }
        return false; // Product not found
    }

    /**
     * Return all products in the shop.
     * @return List of all products.
     */
    public ArrayList<Product> getAllProducts() {
        return shopRepository.allProducts();
    }

    /**
     * Finds a product by ID.
     * @param ID Product ID.
     * @return Optional of product if found, otherwise empty.
     */
    public Optional<Product> findProductById(int ID) {
        return shopRepository.allProducts().stream()
                .filter(product -> product.getID() == ID)
                .findFirst();
    }

    /**
     * Finds products by name.
     * @param name Product name.
     * @return List of products matching the name.
     */
    public List<Product> findProductsByName(String name) {
        return shopRepository.allProducts().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /**
     * Finds products by stock quantity.
     * @param quantity Minimum stock quantity.
     * @return List of products with stock greater than or equal to the specified quantity.
     */
    public List<Product> findProductsByStockQuantity(int quantity) {
        return shopRepository.allProducts().stream()
                .filter(product -> product.getStockQuantity() >= quantity)
                .collect(Collectors.toList());
    }

    /**
     * Finds products with stock below the specified limit.
     * @param limit Stock quantity limit.
     * @return List of products below the specified stock limit.
     */
    public List<Product> findLowStockProducts(int limit) {
        return shopRepository.allProducts().stream()
                .filter(product -> product.getStockQuantity() < limit)
                .collect(Collectors.toList());
    }

    /**
     * Registers a purchase operation and updates the stock for a product.
     * @param ID ID of the product to be purchased.
     * @param quantity Quantity to be added to the stock.
     */
    public void buyProduct(int ID, int quantity) {
        Optional<Product> productOptional = this.findProductById(ID);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStockQuantity(product.getStockQuantity() + quantity);
            this.addOperation(new Operation(TypeOperation.PURCHASE, Calendar.getInstance(), ID));
        } else {
            System.out.println("ERROR: Product is not valid!");
        }
    }

    /**
     * Registers a sale operation and updates the stock for a product.
     * @param ID ID of the product to be sold.
     * @param quantity Quantity to be removed from the stock.
     */
    public void saleProduct(int ID, int quantity) {
        Optional<Product> productOptional = this.findProductById(ID);

        if (productOptional.isPresent() && (productOptional.get().getStockQuantity() > quantity)) {
            Product product = productOptional.get();
            product.setStockQuantity(product.getStockQuantity() - quantity);
            this.addOperation(new Operation(TypeOperation.SALE, Calendar.getInstance(), ID));
        } else {
            System.out.println("ERROR: ID or quantity is not valid!");
        }
    }

    // Methods to manage Operations
    /**
     * Add an operation to the shop.
     * @param operation Operation to be added.
     */
    public void addOperation(Operation operation) {
        shopRepository.addOperation(operation);
    }

    /**
     * Get all operations of the shop.
     * @return List of all operations.
     */
    public ArrayList<Operation> getAllOperations() {
        return shopRepository.allOperations();
    }
}
