/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.utilities;

import core.database.DatabaseAccessObject;
import core.general.Account;
import core.general.Branch;

/**
 *
 * @author brand
 */
public class Session {
    private DatabaseAccessObject database;
    private Account loggedInUser; 
    private Branch branch;
    private Settings settings;
    
    public Session(){
        settings = new Settings();
        database = new DatabaseAccessObject(true, this);
        
        if (settings.getDefaultBranch().equals("<None>") == false) {
            branch = database.getBranchByName(settings.getDefaultBranch());
        } 
    }

    public DatabaseAccessObject getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseAccessObject database) {
        this.database = database;
    }

    public Account getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Account loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
    
    
}
