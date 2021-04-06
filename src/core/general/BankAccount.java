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
public class BankAccount {
    private int id;
    private String BankName;
    private String BranchNumber;
    private String AccountNumber;
    private String AccountType;

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }

    public String getBranchNumber() {
        return BranchNumber;
    }

    public void setBranchNumber(String BranchNumber) {
        this.BranchNumber = BranchNumber;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }
    
}
