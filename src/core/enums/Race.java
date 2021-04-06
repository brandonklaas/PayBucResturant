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
public enum Race {
    BLACK(0, "Black"),
    WHITE(1, "White"),
    INDIAN(2, "Indian"),
    COLOURED(3, "Coloured");
    
    final int value;
    final String label;

    Race(int value, String label){
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

    public static Race fromString(final String str) {
        for (Race e : Race.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static Race fromId(final int id) {
        for (Race e : Race.values()) {
            if (e.value == id) {
                return e;
            }
        }
        return null;
    }
    
}
