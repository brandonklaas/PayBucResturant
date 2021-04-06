/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.general;

import java.util.ArrayList;

/**
 *
 * @author brand
 */
public class Order {
    private int id;
    private int OrderNumber;
    private String TableName;
    private ArrayList<Product> products;
    private String Employee;
    private double Tip;
    private int Tax;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(int OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String Employee) {
        this.Employee = Employee;
    }

    public double getTip() {
        return Tip;
    }

    public void setTip(double Tip) {
        this.Tip = Tip;
    }

    public int getTax() {
        return Tax;
    }

    public void setTax(int Tax) {
        this.Tax = Tax;
    }
    
}
