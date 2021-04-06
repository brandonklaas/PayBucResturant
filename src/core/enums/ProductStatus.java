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
public enum ProductStatus {
    PENDING(0, "PENDING"),
    SERVED(1, "SERVED");
    
    final int value;
    final String label;

    ProductStatus(int value, String label){
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

    public static ProductStatus fromString(final String str) {
        for (ProductStatus e : ProductStatus.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static ProductStatus fromId(final int id) {
        for (ProductStatus e : ProductStatus.values()) {
            if (e.value == id) {
                return e;
            }
        }
        return null;
    }
    
}
