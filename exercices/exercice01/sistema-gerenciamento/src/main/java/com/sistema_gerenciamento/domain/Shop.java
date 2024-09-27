package main.java.com.sistema_gerenciamento.domain;

import main.java.com.sistema_gerenciamento.enums.TypeShop;

public class Shop {
    TypeShop typeShop;
    int ID;
    String name;
    String location;
    Storage storage;

    public Shop(TypeShop typeShop, int ID, String name, String location, Storage storage) {
        this.typeShop = typeShop;
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.storage = storage;
    }

    /*
     * Getters.
     */
    public TypeShop getTypeShop() { return this.typeShop; }
    public int getID() { return this.ID; }
    public String getName() { return this.name; }
    public String getLocation() { return this.location; }
    public Storage getStorage() { return this.storage; }

    /*
     * Setters.
     */
    public void setTypeShop(TypeShop typeShop) { this.typeShop = typeShop; }
    public void setID(int ID) { this.ID = ID; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setStorage(Storage storage) { this.storage = storage; }
}
