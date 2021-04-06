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
public enum PaymentType {
    CASH(0, "CASH"),
    CARD(1, "CARD"),
    VOUCHER(2, "VOUCHER");
    
    final int value;
    final String label;

    PaymentType(int value, String label){
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

    public static PaymentType fromString(final String str) {
        for (PaymentType e : PaymentType.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static PaymentType fromId(final int id) {
        for (PaymentType e : PaymentType.values()) {
            if (e.value == id) {
                return e;
            }
        }
        return null;
    }
    
}
