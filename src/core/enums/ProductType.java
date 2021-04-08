/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.enums;

/**
 * @author Brandon
 */
public enum ProductType {
    SINGLE(0, "SINGLE"),
    COMBO(1, "COMBO"),
    SIDE(2, "SIDE"),
    OPTIONAL(3, "OPTIONAL"),
    DRINK(4, "DRINK"),
    DESSERT(5, "DESSERT");
    
    final int value;
    final String label;

    ProductType(int value, String label){
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

    public static ProductType fromString(final String str) {
        for (ProductType e : ProductType.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static ProductType fromId(final int id) {
        for (ProductType e : ProductType.values()) {
            if (e.value == id) {
                return e;
            }
        }
        return null;
    }
    
}
