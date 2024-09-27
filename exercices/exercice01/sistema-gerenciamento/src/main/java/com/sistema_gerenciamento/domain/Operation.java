package main.java.com.sistema_gerenciamento.domain;

import main.java.com.sistema_gerenciamento.enums.TypeOperation;
import java.util.Calendar;

public class Operation {
    TypeOperation typeOperation;
    Calendar dateOperation;
    int idProduct;

    public Operation(TypeOperation typeOperation, Calendar dateOperation, int idProduct) {
        this.typeOperation = typeOperation;
        this.dateOperation = dateOperation;
        this.idProduct = idProduct;
    }

    /*
     * Getters.
     */
    public TypeOperation getTypeOperation() { return this.typeOperation; }
    public Calendar getDateOperation() { return this.dateOperation; }
    public int getIdProduct() { return this.idProduct; }

    /*
     * Setters.
     */
    public void setTypeOperation(TypeOperation typeOperation) { this.typeOperation = typeOperation; }
    public void setDateOperation(Calendar dateOperation) { this.dateOperation = dateOperation; }
    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }
}
