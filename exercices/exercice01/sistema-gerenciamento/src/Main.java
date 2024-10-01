import main.java.com.sistema_gerenciamento.domain.*;
import main.java.com.sistema_gerenciamento.repository.ShopRepository;
import main.java.com.sistema_gerenciamento.service.ShopService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // - - - Set fixed data. - - -
        ArrayList<Shop> shops = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean end = false;

        // Create the shops.
        Shop midway = new Shop(0, "Midway", "New York - USA");
        ShopRepository midwayRepository = new ShopRepository(midway);
        ShopService midwayService = new ShopService(midwayRepository);

        // Register products.
        midwayService.registerProduct(new Product(0, "Banana", "Yellow", 0.8, 1));
        midwayService.registerProduct(new Product(1, "Apple", "Red", 1, 2));
        midwayService.registerProduct(new Product(2, "Water", "Mineral", 0.5, 0.7));

        midwayService.buyProduct(0, 6);

        Shop noWay = new Shop(4, "NoWay", "São Paulo - BR");
        ShopRepository noWayRepository = new ShopRepository(noWay);
        ShopService noWayService = new ShopService(noWayRepository);

        // Register products.
        noWayService.registerProduct(new Product(2, "Banana", "Yellow", 1, 1.5));
        noWayService.registerProduct(new Product(0, "Apple", "Red", 2, 4));
        noWayService.registerProduct(new Product(1, "Water", "Mineral", 0.2, 0.5));

        noWayService.buyProduct(1, 10);

        shops.add(midway);
        shops.add(noWay);

        // - - - - - - - - - - - - -

        // Tests!
        while (!end) {
            System.out.println("- - - - MY STOCK - - - -");
            System.out.println("Options:");
            System.out.println("1. Select store");
            System.out.println("2. Create new store");
            System.out.println("3. Exit");

            System.out.print(">> Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    selectShop(shops, scanner);
                    break;
                case "2":
                    createNewShop(shops, scanner);
                    break;
                case "3":
                    end = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Exiting the system...");
        scanner.close();
    }

    private static void selectShop(ArrayList<Shop> shops, Scanner scanner) {
        System.out.println("Select a store by ID:");

        for (Shop shop : shops) {
            System.out.println("+ " + shop.getName() + " (ID: " + shop.getID() + ")");
        }

        System.out.print(">> Enter the store ID: ");
        String input = scanner.nextLine();

        try {
            int shopID = Integer.parseInt(input);
            Shop selectedShop = shops.stream().filter(shop -> shop.getID() == shopID).findFirst().orElse(null);

            if (selectedShop == null) {
                System.out.println("Store not found. Please try again.");
            } else {
                ShopRepository shopRepo = new ShopRepository(selectedShop);
                ShopService shopService = new ShopService(shopRepo);

                boolean shopMenu = true;
                while (shopMenu) {

                    System.out.println("\nManaging Store: " + selectedShop.getName());
                    System.out.println("1. View Products");
                    System.out.println("2. Buy Product");
                    System.out.println("3. Register Product");
                    System.out.println("4. Delete Product");
                    System.out.println("5. View Operations");
                    System.out.println("6. Sell Product");
                    System.out.println("7. Search Product");
                    System.out.println("8. Low Stock Report");
                    System.out.println("9. Back");

                    System.out.print(">> Choose an option: ");
                    String option = scanner.nextLine();

                    switch (option) {
                        case "1":
                            ArrayList<Product> products = shopService.getAllProducts();
                            if (products != null && !products.isEmpty()) {
                                for (Product product : products) {
                                    System.out.println("Product ID: " + product.getID() +
                                            ", Name: " + product.getName() +
                                            ", Quantity: " + product.getStockQuantity() +
                                            ", Description: " + product.getDescription() +
                                            ", Cost Price: " + product.getCostPrice() +
                                            ", Sale Price: " + product.getCostSale()
                                    );
                                }
                            } else {
                                System.out.println("No products available.");
                            }
                            break;

                        case "2": // Buy Product
                            System.out.print("Enter the Product ID to buy: ");
                            int productID = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter the quantity: ");
                            int quantity = Integer.parseInt(scanner.nextLine());
                            shopService.buyProduct(productID, quantity);
                            break;

                        case "3":
                            System.out.print("Enter the Product ID: ");
                            int newProductID = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter the Product Name: ");
                            String productName = scanner.nextLine();
                            System.out.print("Enter the Product Description: ");
                            String productDesc = scanner.nextLine();
                            System.out.print("Enter the Product Cost Price: ");
                            int costPrice = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter the Product Sale Price: ");
                            double salePrice = Double.parseDouble(scanner.nextLine());

                            shopService.registerProduct(new Product(newProductID, productName, productDesc, costPrice, salePrice));
                            System.out.println("Product registered successfully.");
                            break;

                        case "4":
                            System.out.print("Enter the ID of the Product to remove: ");
                            int removeProductID = Integer.parseInt(scanner.nextLine());
                            if (shopService.unsubscribeProduct(removeProductID)) {
                                System.out.println("Product removed successfully.");
                            } else {
                                System.out.println("Product not found.");
                            }
                            break;

                        case "5": // View Operations
                            ArrayList<Operation> operations = shopService.getAllOperations();
                            if (operations != null && !operations.isEmpty()) {
                                for (Operation operation : operations) {
                                    System.out.println("Operation: " + operation.getTypeOperation() + ", Date: " + operation.getDateOperation().getTime() + ", Product ID: " + operation.getIdProduct());
                                }
                            } else {
                                System.out.println("No operations recorded.");
                            }
                            break;

                        case "6": // Sell Product
                            System.out.print("Enter the ID of the Product to sell: ");
                            int sellProductID = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter the quantity to sell: ");
                            int sellQuantity = Integer.parseInt(scanner.nextLine());

                            shopService.buyProduct(sellProductID, sellQuantity);
                            break;
                        case "7":
                            System.out.println("How would you like to search?");
                            System.out.println("1. By ID");
                            System.out.println("2. By Name");
                            System.out.println("3. By Stock Quantity");
                            String searchOption = scanner.nextLine();

                            switch (searchOption) {
                                case "1":
                                    System.out.print("Enter the Product ID: ");
                                    int searchID = Integer.parseInt(scanner.nextLine());
                                    Optional<Product> foundProduct = shopService.findProductById(searchID);
                                    if (foundProduct.isPresent()) {
                                        System.out.println("Product found: " + foundProduct.get().getName() + ", Quantity: " + foundProduct.get().getStockQuantity());
                                    } else {
                                        System.out.println("Product not found.");
                                    }
                                    break;
                                case "2":
                                    System.out.print("Enter the product name: ");
                                    String searchName = scanner.nextLine();
                                    List<Product> foundProductsByName = shopService.findProductsByName(searchName);
                                    if (!foundProductsByName.isEmpty()) {
                                        foundProductsByName.forEach(product -> System.out.println("Product found: ID: " + product.getID() + ", Name: " + product.getName() + ", Quantity: " + product.getStockQuantity()));
                                    } else {
                                        System.out.println("No products found with that name.");
                                    }
                                    break;
                                case "3":
                                    System.out.print("Enter the minimum stock quantity: ");
                                    int minStock = Integer.parseInt(scanner.nextLine());
                                    List<Product> foundProductsByStock = shopService.findProductsByStockQuantity(minStock);
                                    if (!foundProductsByStock.isEmpty()) {
                                        foundProductsByStock.forEach(product -> System.out.println("Product found: ID: " + product.getID() + ", Name: " + product.getName() + ", Quantity: " + product.getStockQuantity()));
                                    } else {
                                        System.out.println("No products found with that stock quantity.");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid search option.");
                                    break;
                            }
                        case "8": // Low Stock Report
                            System.out.print("Enter the stock limit: ");
                            int limit = Integer.parseInt(scanner.nextLine());
                            List<Product> lowStockProducts = shopService.findLowStockProducts(limit);
                            if (!lowStockProducts.isEmpty()) {
                                System.out.println("Products with low stock (below " + limit + "):");
                                lowStockProducts.forEach(product ->
                                        System.out.println("ID: " + product.getID() + ", Name: " + product.getName() + ", Quantity: " + product.getStockQuantity()));
                            } else {
                                System.out.println("No products below the stock limit.");
                            }
                            break;

                        case "9": // Back
                            shopMenu = false;
                            break;

                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private static void createNewShop(ArrayList<Shop> shops, Scanner scanner) {
        System.out.print("Enter the ID of the new store: ");
        int newShopID = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the Name of the new store: ");
        String shopName = scanner.nextLine();
        System.out.print("Enter the Location of the new store: ");
        String shopLocation = scanner.nextLine();

        Shop newShop = new Shop(newShopID, shopName, shopLocation);

        shops.add(newShop);
        System.out.println("New store created successfully.");
    }
}
