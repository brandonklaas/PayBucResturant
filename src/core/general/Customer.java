/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.general;

/**
 *
 * @author Brandon
 */
public class Customer extends Person{
    private int id;
    private String PaybucAcc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaybucAcc() {
        return PaybucAcc;
    }

    public void setPaybucAcc(String PaybucAcc) {
        this.PaybucAcc = PaybucAcc;
    }
    
}
