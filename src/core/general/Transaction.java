/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.general;

import core.enums.PaymentType;
import java.util.Date;

/**
 *
 * @author Brandon
 */
public class Transaction {
    private int id;
    private int site;
    private int employeeID;
    private int tableID;
    private int orderID;
    private String orderNumber;
    private int vat;
    private double Price;
    private double Tip;
    private PaymentType Payment;
    private Date date;
 
    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }


    public PaymentType getPayment() {
        return Payment;
    }

    public void setPayment(PaymentType Payment) {
        this.Payment = Payment;
    }
  

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    } 

    public double getTip() {
        return Tip;
    }

    public void setTip(double Tip) {
        this.Tip = Tip;
    }
    
    
    
}
