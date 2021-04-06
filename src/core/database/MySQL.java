/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.database;

/**
 *
 * @author brand
 */
public class MySQL {
    
    public static final String SELECT_EMPLOYEE_BY_OCCUPATION     = "SELECT * FROM Employees WHERE Occupation = ?";
    
                    
    public static final String SELECT_SITE         = "SELECT * FROM Site";
    
    public static final String SELECT_SERVICES     = "SELECT * FROM Services";
    
    public static final String SELECT_TRANSACTIONS_BY_BRANCH = "SELECT * FROM Transactions WHERE Site = ?";
    
    public static final String SELECT_TRANSACTIONS = "SELECT * FROM Transactions";
                
    public static final String SELECT_EMPLOYEE     = "SELECT * FROM Employees";
    
    public static final String SELECT_PRODUCT      = "SELECT * FROM Products";
    
    public static final String SELECT_BRANCH       = "SELECT * FROM Branch";
    
    public static final String SELECT_ACCOUNT       = "SELECT * FROM Accounts";
                
    public static final String SELECT_OCCUPATION   = "SELECT * FROM Occupation";
    
    
    
    public static final String DELETE_SITE         = "DELETE FROM Site WHERE id = ?;";
    
    public static final String DELETE_SERVICES     = "DELETE FROM Services WHERE id = ?;";
    
    public static final String DELETE_TRANSACTIONS = "DELETE FROM Transactions WHERE id = ?;";
                
    public static final String DELETE_EMPLOYEE     = "DELETE FROM Employees WHERE id = ?;";
    
    public static final String DELETE_PRODUCT      = "DELETE FROM Products  WHERE id = ?;";
    
    public static final String DELETE_BRANCH       = "DELETE FROM Branch  WHERE id = ?;";
    
    public static final String DELETE_ACCOUNT       = "DELETE FROM Accounts  WHERE id = ?;";
    
    public static final String DELETE_OCCUPATION   = "DELETE FROM Occupation WHERE id = ?";
    
    
        
    public static final String UPDATE_SITE         = "UPDATE Site SET Name = ? , Address = ? , BranchNo = ? WHERE id = ?;";
    
    public static final String UPDATE_SERVICES     = "UPDATE Services  SET Name = ?,  Description = ?, Price = ?, Taxable = ? WHERE id = ?;";
    
    public static final String UPDATE_TRANSACTIONS = "UPDATE Transactions SET Site = ? , Title = ?, Type = ?,  TypeID = ?,  Payment = ?,  Price = ?, Employee = ?, CustomerName = ?, CustomerNumber = ?, Date = ? WHERE id = ?;";
                
    public static final String UPDATE_EMPLOYEE     = "UPDATE Employees SET EmployeeNo = ?,  Occupation = ?,  MedicalAid = ?, TaxNo = ?, IDNumber = ?, Firstname = ?, Surname = ?, Email = ?, CellNo = ?, Address = ?, Race = ?, Gender = ? WHERE id = ?;";
    
    public static final String UPDATE_PRODUCT      = "UPDATE Products SET Name = ?, Description = ?, Price = ?, TaxNo = ? WHERE id = ?;";
        
    public static final String UPDATE_ACCOUNT      = "UPDATE Accounts SET Username = ?,  Password = ?,  Admin = ?, Guest = ?, Transactions = ?, Services = ?, Products = ?, Employees = ?, Settings = ?, Accounts = ? WHERE id = ?;";
    
    public static final String UPDATE_OCCUPATION     = "UPDATE Occupation SET Title = ?, Resposibilities = ? WHERE id = ?";
    
    
    
    public static final String INSERT_SITE         = "INSERT INTO Site (Name , Address, BranchNo) VALUES (?,?,?);";
    
    public static final String INSERT_SERVICES     = "INSERT INTO Services (Name,  Description, Price, Taxable) VALUES (?,?,?,?);";
    
    public static final String INSERT_TRANSACTIONS = "INSERT INTO Transactions (Site , Title , Type,  TypeID,  Payment,  Price, Employee, CustomerName, CustomerNumber, Date) VALUES (?,?,?,?,?,?,?,?,?,?);";
                
    public static final String INSERT_EMPLOYEE     = "INSERT INTO Employees (EmployeeNo,  Occupation,  MedicalAid,TaxNo, IDNumber, Firstname,   Surname,  Email, CellNo, Address, Race, Gender) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    
    public static final String INSERT_PRODUCT      = "INSERT INTO Products (Name,  Description, ImagePath, Price,  TaxNo) VALUES (?,?,?,?,?);";
    
    public static final String INSERT_ACCOUNT      = "INSERT INTO Accounts (Username,  Password,  Admin, Guest, Transactions, Services, Products, Employees, Settings, Accounts) VALUES (?,?,?,??,?,?,?,?,?);";
    
    public static final String INSERT_OCCUPATION     = "INSERT INTO Occupation (Title,  Resposibilities) VALUES (?,?)";
    
    
    
    public static final String CHECK_TABLE         = "SHOW TABLES LIKE ?;";
    
    public static final String CREATE_BRANCH       = "CREATE TABLE Site (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, Name varchar(255),  Address varchar(255), BranchNo varchar(255));";
    
    public static final String CREATE_SERVICES     = "CREATE TABLE Services (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, Name varchar(255),  Description varchar(255), Price decimal(18,2),   Taxable int);";
    
    public static final String CREATE_TRANSACTIONS = "CREATE TABLE Transactions (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, Site int, Title varchar(255), Type varchar(100),  TypeID int,  Payment varchar(255),  Price decimal(18,2), "
                                                    + "Employee varchar(255), CustomerName varchar(255),   CustomerNumber varchar(255),  Date Date, FOREIGN KEY (Site) REFERENCES Site(id));";
                
    public static final String CREATE_EMPLOYEE     = "CREATE TABLE Employees (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, EmployeeNo varchar(100),  Occupation varchar(255),  MedicalAid varchar(255), TaxNo varchar(255), "
                                                    + "IDNumber varchar(255), Firstname varchar(255),   Surname varchar(255),  Email varchar(255), CellNo varchar(255), Address varchar(255), Race int, Gender int );";
    
    public static final String CREATE_PRODUCT      = "CREATE TABLE Products (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, Name varchar(255),  Description varchar(255), ImagePath varchar(255) , Price decimal(18,2),  Tax int);";
    
    public static final String CREATE_ACCOUNT      = "CREATE TABLE Accounts (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, Username varchar(255), Password varchar(255), Admin int, Guest int, Transactions int, Services int, Products int,"
                                                    + "Employees int, Settings int, Accounts int)";
    
    public static final String CREATE_OCCUPATION   = "CREATE TABLE Occupation (id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, Title varchar(255),  Resposibilities varchar(255))";
    
}
