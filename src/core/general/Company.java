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
public class Company {
    private int id;
    private String Name;
    private String RegNo;
    private String Sector;
    private String Description;
    private String ContactNo;
    private String Website;
    private String location;
    private Object companyLogo;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRegNo() {
        return RegNo;
    }

    public void setRegNo(String RegNo) {
        this.RegNo = RegNo;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String Sector) {
        this.Sector = Sector;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String Website) {
        this.Website = Website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(Object companyLogo) {
        this.companyLogo = companyLogo;
    }
    
    
    
}
