/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.general;

/**
 *
 * @author Brandon
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private Table table;
    private Double price;
    private boolean prepared = false;
    private boolean taxable;
    
    public Product(String name, String desc, Double price, boolean taxable){
        this.name = name;
        this.description = desc;
        this.price = price;
        this.taxable = taxable;
    }
    
    public Product(String name, String desc, Double price, boolean taxable, Table table) {
        this.name = name;
        this.description = desc;
        this.price = price;
        this.taxable = taxable;
        this.table = table;
    }
    
    public Product(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isPrepared() {
        return prepared;
    }

    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }
    
    
    
}
