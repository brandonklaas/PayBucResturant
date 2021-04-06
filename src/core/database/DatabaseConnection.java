
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandon
 */
public class DatabaseConnection {
    
    private Connection connection;
    
    public DatabaseConnection(boolean offline){
        if(offline){
            connectOffline();
        } else {
            connectOnline();
        }
        
    }
    
    public boolean connectOffline() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:database; create=true;");
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean connectOnline() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://160.153.131.189:3306/MicolliDatabase", "miccoli", "Keabetswe1-");
            System.out.println("Connected to Database on 160.153.131.189");
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public boolean closeConnection(){
        try {
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
}
