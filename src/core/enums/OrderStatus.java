/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.enums;

/**
 *
 * @author Brandon
 */
public enum OrderStatus {
    UNPAID(0, "UNPAID"),
    PAID(1, "PAID");
    
    final int value;
    final String label;

    OrderStatus(int value, String label){
        this.label = label;
        this.value = value;
    }
    
    
    @Override
    public String toString() {
        return label;
    }
    
    public int getID(){
        return value;
    }

    public static OrderStatus fromString(final String str) {
        for (OrderStatus e : OrderStatus.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static OrderStatus fromId(final int id) {
        for (OrderStatus e : OrderStatus.values()) {
            if (e.value == id) {
                return e;
            }
        }
        return null;
    }
    
}
