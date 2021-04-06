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
public enum Gender {
    MALE(0, "Male"),
    FEMALE(1, "Female");
    
    final int value;
    final String label;

    Gender(int value, String label){
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
    
    public static Gender fromString(final String str) {
        for (Gender e : Gender.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static Gender fromId(final int id) {
        for (Gender e : Gender.values()) {
            if (e.value == id) {
                return e;
            }
        }
        return null;
    }
    
}
