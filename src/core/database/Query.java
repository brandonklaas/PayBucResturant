/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.database;
import core.general.Person;
import java.lang.reflect.Field;

/**
 * @author Brandon
 */
public class Query {
    
    public Query(){
        
        Field[] fields = Person.class.getDeclaredFields();
        // returns all members including private members but not inherited members.
        
        for(int i = 0; i < fields.length; i++){
            System.out.println("Variable Name : " + fields[i].getName() +"\n"+ fields[i].getGenericType());
        }
        
    }
    
    public boolean insert(Object object){
        
        
        return true;
    }
    
    public static void main(String [] args){
        new Query();
    }
}
