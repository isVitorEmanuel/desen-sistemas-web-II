package main.java.com.sistema_gerenciamento.domain;

public class Product {
    private int ID;
    private String name;
    private String description;
    private double costPrice;
    private double salePrice;
    private long stockQuantity;

    public Product(int ID, String name, String description, double costPrice, double salePrice, long stockQuantity) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
    }

    /*
     * Getters.
     */
    public int getID() { return this.ID; }
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public double getCostPrice() { return this.costPrice; }
    public double getCostSale() { return this.salePrice; }
    public long getStockQuantity() { return this.stockQuantity; }

    /*
     * Setters.
     */
    public void setID(int ID) { this.ID = ID; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCostPrice(double costPrice) { this.costPrice = costPrice; }
    public void setCostSale(double salePrice) { this.salePrice = salePrice; }
    public void setStockQuantity(long stockQuantity) { this.stockQuantity = stockQuantity; }
}
