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
public class Employee extends Person{
    private int         id;
    private String      employeeNo;
    private String  occupation;
    private MedicalAid  medicalAid;
    private String      taxNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String EmployeeNo) {
        this.employeeNo = EmployeeNo;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public MedicalAid getMedicalAid() {
        return medicalAid;
    }

    public void setMedicalAid(MedicalAid medicalAid) {
        this.medicalAid = medicalAid;
    }
    
    @Override
    public String toString(){
        return super.toString()+"#"+
               "Str.EMPLOYEENO=" +getEmployeeNo()+"#"+
               "Int.MEDICALAID=" +getMedicalAid().getId()+"#"+
               "Str.TAXNO="      +getTaxNo();
    }
    
}
