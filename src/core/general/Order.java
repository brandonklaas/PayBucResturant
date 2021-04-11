/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.general;

import java.util.ArrayList;

/** 
 * @author Brandon
 */
public class Order {
    private int id;
    private String orderNumber;
    private int tableID;
    private int employeeID;
    private ArrayList<OrderedProducts> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public ArrayList<OrderedProducts> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrderedProducts> products) {
        this.products = products;
    } 
    
}
