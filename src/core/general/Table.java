/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.general;

import core.enums.TableStatus;

/**
 *
 * @author Brandon
 */
public class Table {
    private int id;
    private String tableName;
    private TableStatus tableStatus;

    
    public Table(String name, TableStatus status){
        setTableName(name);
        setTableStatus(status);
    }
    
    public Table(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }
    
}
