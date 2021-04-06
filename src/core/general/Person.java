/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.general;

import core.enums.Gender;
import core.enums.Race;

/**
 *
 * @author Brandon
 */
public class Person {
    private String IDNumber;
    private String firstname;
    private String lastname;
    private String email;
    private String cellNo;
    private String homeAddress;
    private Race   race;
    private Gender gender;
    
    public Person(){
        
    }
    
    public Person(String firstname, String lastname, String cellNo){
        setFirstname(firstname);
        setLastname(lastname);
        setCellNo(cellNo);
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    
    @Override
    public String toString(){
        return "Str.IDNUMBER="  +getIDNumber()+"#"+
               "Str.FIRSTNAME=" +getFirstname()+"#"+
               "Str.LASTNAME="  +getLastname()+"#"+
               "Str.EMAIL="     +getEmail()+"#"+
               "Str.CELLNO="    +getCellNo()+"#"+
               "Str.HOMEADDRESS="+getHomeAddress()+"#"+
               "Int.RACE="      +getRace().getID()+"#"+
               "Int.GENDER="    +getGender().getID();
    }
}
