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
public enum TableStatus {
    OPEN(0, "PENDING"),
    RESERVED(1, "SERVED"),
    OCCUPIED(2, "OCCUPIED");
    
    final int value;
    final String label;

    TableStatus(int value, String label){
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

    public static TableStatus fromString(final String str) {
        for (TableStatus e : TableStatus.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static TableStatus fromId(final int id) {
        for (TableStatus e : TableStatus.values()) {
            if (e.value == id) {
                return e;
            }
        }
        return null;
    }
    
}
