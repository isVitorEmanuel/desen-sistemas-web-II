package main.java.com.sistema_gerenciamento.service;

import main.java.com.sistema_gerenciamento.domain.Operation;
import main.java.com.sistema_gerenciamento.domain.Product;
import main.java.com.sistema_gerenciamento.enums.TypeOperation;
import main.java.com.sistema_gerenciamento.repository.StorageRepository;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class StorageService {
    private StorageRepository storageRepository;

    /**
     * Constructor.
     *
     * @param storageRepository The repository to interact with storage.
     */
    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    /**
     * Registers a new product in the storage.
     *
     * @param product The product to be added to stock.
     */
    public void registerProduct(Product product) {
        this.storageRepository.registerProduct(product);

        if (product.getStockQuantity() > 0) {
            Operation operation = new Operation(
                    TypeOperation.PURCHASE,
                    Calendar.getInstance(),
                    product.getID()
            );

            this.storageRepository.addOperation(operation);
        }
    }

    /**
     * Unsubscribes (removes) a product from storage by its ID.
     *
     * @param ID The ID of the product to be removed from stock.
     */
    public void unsubscribeProduct(int ID) {
        this.storageRepository.unsubscribeProduct(ID);
    }

    /**
     * Adds a specific quantity of a product to the stock.
     *
     * @param ID       The ID of the product to be updated.
     * @param quantity The amount to be added to the product's stock.
     */
    public void addProduct(int ID, int quantity) {
        Product product = this.findProductByID(ID);
        product.setStockQuantity(product.getStockQuantity() + quantity);

        Operation operation = new Operation(
            TypeOperation.PURCHASE,
            Calendar.getInstance(),
            ID
        );

        storageRepository.addOperation(operation);
    }

    /**
     * Removes a specific quantity of a product from the stock.
     *
     * @param ID       The ID of the product to be updated.
     * @param quantity The amount to be removed from the product's stock.
     */
    public void removeProduct(int ID, int quantity) {
        Product product = this.findProductByID(ID);

        // Checks if there is enough stock to remove the specified quantity
        if (product.getStockQuantity() >= quantity) {
            product.setStockQuantity(product.getStockQuantity() - quantity);

            Operation operation = new Operation(
                    TypeOperation.SALE,
                    Calendar.getInstance(),
                    ID
            );

            storageRepository.addOperation(operation);

            return;
        }

        System.out.println("The quantity is greater than available!");
    }

    /**
     * Finds a product by its ID.
     *
     * @param ID The ID of the product to be found.
     * @return The product with the specified ID.
     * @throws RuntimeException if the product with the given ID is not found.
     */
    public Product findProductByID(int ID) {
        return this.storageRepository.allProducts().stream()
                .filter(product -> product.getID() == ID)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product with ID \"" + ID + "\" not found!"));
    }

    /**
     * Finds a product by its name.
     *
     * @param name The name of the product to be found.
     * @return The product with the specified name.
     * @throws RuntimeException if the product with the given name is not found.
     */
    public Product findProductByName(String name) {
        return this.storageRepository.allProducts().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product with name \"" + name + "\" not found!"));
    }

    /**
     * Finds all products with a specified quantity.
     *
     * @param quantity The quantity to search for.
     * @return A list of products that have the specified quantity.
     */
    public List<Product> findProductsByQuantity(int quantity) {
        return this.storageRepository.allProducts().stream()
                .filter(product -> product.getStockQuantity() == quantity)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all operations from the storage repository.
     *
     * @return A list of all operations currently stored in the repository.
     */
    public List<Operation> findAllOperations() {
        return storageRepository.allOperations();
    }

    /**
     * This method finds operations within a given date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of operations within the specified date range.
     */
    public List<Operation> findOperationsByDateRange(Calendar startDate, Calendar endDate) {
        return this.storageRepository.allOperations().stream()
                .filter(operation -> !operation.getDateOperation().before(startDate) &&
                        !operation.getDateOperation().after(endDate))
                .collect(Collectors.toList());
    }
}
