/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.database;

import core.enums.Gender;
import core.general.Account;
import core.general.Employee;
import core.general.Product;
import core.general.Service;
import core.general.Branch;
import core.general.Occupation;
import core.general.Transaction; 
import core.utilities.Session;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Brandon
 */
public class DatabaseAccessObject {
    private Connection connection;
    private boolean offline;
    private Session session;
    
    public DatabaseAccessObject(boolean offline, Session session) {
        this.offline = offline;
        this.session = session;
        setQueries();
        if (Files.exists(new File("database").toPath()) == false && offline == true) {
            connection = new DatabaseConnection(offline).getConnection();
            createTables();
        } else {
            connection = new DatabaseConnection(offline).getConnection();
        }
        
        checkTables();
    }
    
    public void createTables() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(CREATE_SERVICES);
            stmt.executeUpdate(CREATE_BRANCH);
            stmt.executeUpdate(CREATE_EMPLOYEE);
            stmt.executeUpdate(CREATE_PRODUCT);
            stmt.executeUpdate(CREATE_TRANSACTIONS);
            stmt.executeUpdate(CREATE_ACCOUNT);
            stmt.executeUpdate(CREATE_OCCUPATION);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void checkTables() {
        
        if (offline == false) {
            if (tableExists("Services")) {
                System.out.println("Tables Exist");
            } else {
                createTables();
            }
        }
        
    }

    public void setQueries() {
        if(offline){
            SELECT_EMPLOYEE_BY_OCCUPATION = Derby.SELECT_EMPLOYEE_BY_OCCUPATION;
            
            SELECT_TRANSACTIONS_BY_BRANCH = Derby.SELECT_TRANSACTIONS_BY_BRANCH;
            SELECT_OCCUPATION  = Derby.SELECT_OCCUPATION;
            SELECT_SITE         = Derby.SELECT_SITE;
            SELECT_SERVICES     = Derby.SELECT_SERVICES;
            SELECT_TRANSACTIONS = Derby.SELECT_TRANSACTIONS;
            SELECT_EMPLOYEE     = Derby.SELECT_EMPLOYEE;
            SELECT_PRODUCT      = Derby.SELECT_PRODUCT; 
            SELECT_ACCOUNT      = Derby.SELECT_ACCOUNT;
            
            DELETE_OCCUPATION   = Derby.DELETE_OCCUPATION;
            DELETE_SITE         = Derby.DELETE_SITE;
            DELETE_SERVICES     = Derby.DELETE_SERVICES;
            DELETE_TRANSACTIONS = Derby.DELETE_TRANSACTIONS;
            DELETE_EMPLOYEE     = Derby.DELETE_EMPLOYEE;
            DELETE_PRODUCT      = Derby.DELETE_PRODUCT; 
            DELETE_ACCOUNT      = Derby.DELETE_ACCOUNT;
    
            UPDATE_OCCUPATION   = Derby.UPDATE_OCCUPATION;
            UPDATE_SITE         = Derby.UPDATE_SITE;
            UPDATE_SERVICES     = Derby.UPDATE_SERVICES;
            UPDATE_TRANSACTIONS = Derby.UPDATE_TRANSACTIONS;
            UPDATE_EMPLOYEE     = Derby.UPDATE_EMPLOYEE;
            UPDATE_PRODUCT      = Derby.UPDATE_PRODUCT;
            UPDATE_ACCOUNT      = Derby.UPDATE_ACCOUNT;
            
            INSERT_OCCUPATION   = Derby.INSERT_OCCUPATION;
            INSERT_SITE         = Derby.INSERT_SITE;
            INSERT_SERVICES     = Derby.INSERT_SERVICES;
            INSERT_TRANSACTIONS = Derby.INSERT_TRANSACTIONS;
            INSERT_EMPLOYEE     = Derby.INSERT_EMPLOYEE;
            INSERT_PRODUCT      = Derby.INSERT_PRODUCT;
            INSERT_ACCOUNT      = Derby.INSERT_ACCOUNT;
            
            CREATE_OCCUPATION   = Derby.CREATE_OCCUPATION;
            CREATE_BRANCH       = Derby.CREATE_BRANCH;
            CREATE_SERVICES     = Derby.CREATE_SERVICES;
            CREATE_TRANSACTIONS = Derby.CREATE_TRANSACTIONS;
            CREATE_EMPLOYEE     = Derby.CREATE_EMPLOYEE;
            CREATE_PRODUCT      = Derby.CREATE_PRODUCT;
            CREATE_ACCOUNT      = Derby.CREATE_ACCOUNT;
            
        } else {
            SELECT_EMPLOYEE_BY_OCCUPATION = MySQL.SELECT_EMPLOYEE_BY_OCCUPATION;
            
            SELECT_TRANSACTIONS_BY_BRANCH = MySQL.SELECT_TRANSACTIONS_BY_BRANCH;
            SELECT_OCCUPATION  = MySQL.SELECT_OCCUPATION;
            SELECT_SITE         = MySQL.SELECT_SITE;
            SELECT_SERVICES     = MySQL.SELECT_SERVICES;
            SELECT_TRANSACTIONS = MySQL.SELECT_TRANSACTIONS;
            SELECT_EMPLOYEE     = MySQL.SELECT_EMPLOYEE;
            SELECT_PRODUCT      = MySQL.SELECT_PRODUCT; 
            SELECT_ACCOUNT      = MySQL.SELECT_ACCOUNT;
            
            DELETE_OCCUPATION   = MySQL.DELETE_OCCUPATION;
            DELETE_SITE         = MySQL.DELETE_SITE;
            DELETE_SERVICES     = MySQL.DELETE_SERVICES;
            DELETE_TRANSACTIONS = MySQL.DELETE_TRANSACTIONS;
            DELETE_EMPLOYEE     = MySQL.DELETE_EMPLOYEE;
            DELETE_PRODUCT      = MySQL.DELETE_PRODUCT; 
            DELETE_ACCOUNT       = MySQL.DELETE_ACCOUNT;
    
            UPDATE_OCCUPATION   = MySQL.UPDATE_OCCUPATION;
            UPDATE_SITE         = MySQL.UPDATE_SITE;
            UPDATE_SERVICES     = MySQL.UPDATE_SERVICES;
            UPDATE_TRANSACTIONS = MySQL.UPDATE_TRANSACTIONS;
            UPDATE_EMPLOYEE     = MySQL.UPDATE_EMPLOYEE;
            UPDATE_PRODUCT      = MySQL.UPDATE_PRODUCT;
            UPDATE_ACCOUNT      = MySQL.UPDATE_ACCOUNT;
            
            INSERT_OCCUPATION   = MySQL.INSERT_OCCUPATION;
            INSERT_SITE         = MySQL.INSERT_SITE;
            INSERT_SERVICES     = MySQL.INSERT_SERVICES;
            INSERT_TRANSACTIONS = MySQL.INSERT_TRANSACTIONS;
            INSERT_EMPLOYEE     = MySQL.INSERT_EMPLOYEE;
            INSERT_PRODUCT      = MySQL.INSERT_PRODUCT;
            INSERT_ACCOUNT      = MySQL.INSERT_ACCOUNT;
            
            CREATE_OCCUPATION   = MySQL.CREATE_OCCUPATION;
            CREATE_BRANCH       = MySQL.CREATE_BRANCH;
            CREATE_SERVICES     = MySQL.CREATE_SERVICES;
            CREATE_TRANSACTIONS = MySQL.CREATE_TRANSACTIONS;
            CREATE_EMPLOYEE     = MySQL.CREATE_EMPLOYEE;
            CREATE_PRODUCT      = MySQL.CREATE_PRODUCT;
            CREATE_ACCOUNT      = MySQL.CREATE_ACCOUNT; 
        }
    }
    
    public boolean tableExists(String tablename) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(CHECK_TABLE);        
            ps.setString(1, tablename);

            // process the results
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
    public ArrayList<Branch> getBranches() {
        try {
            ArrayList<Branch> array = new ArrayList<>();
            Statement stmt = connection.createStatement();
            
            stmt.executeQuery(SELECT_SITE);
            ResultSet rs = stmt.getResultSet();
            
            while (rs.next()) {
                Branch branch = new Branch();
                branch.setId(rs.getInt("id"));
                branch.setName(rs.getString("Name"));
                branch.setAddress(rs.getString("Address"));
                branch.setBranchNo(rs.getString("BranchNo"));
                array.add(branch);
            }
            
            return array;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Product> getProducts() {
        try {
            ArrayList<Product> array = new ArrayList<>();
            Statement stmt = connection.createStatement();
            
            stmt.executeQuery(SELECT_PRODUCT);
            ResultSet rs = stmt.getResultSet();
            
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("Name"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("Price"));
                product.setTaxable((rs.getInt("Tax") == 1) ? true : false);
                array.add(product);
            }
            
            return array;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Service> getServices() {
        try {
            ArrayList<Service> array = new ArrayList<>();
            Statement stmt = connection.createStatement();

            stmt.executeQuery(SELECT_SERVICES);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("Name"));
                service.setDescription(rs.getString("Description"));
                service.setPrice(rs.getDouble("Price"));
                service.setTaxable((rs.getInt("Taxable") == 1) ? true : false);
                array.add(service);
            }

            return array;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Account> getAccounts() {
        try {
            ArrayList<Account> array = new ArrayList<>();
            Statement stmt = connection.createStatement();

            stmt.executeQuery(SELECT_ACCOUNT);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("Username"));
                account.setPassword(rs.getString("Password"));

                account.setAdmin((rs.getInt("Admin") == 1) ? true : false);
                account.setGuest((rs.getInt("Guest") == 1) ? true : false);
                account.setTransactions((rs.getInt("Transactions") == 1) ? true : false);
                account.setServices((rs.getInt("Services") == 1) ? true : false);
                account.setProducts((rs.getInt("Products") == 1) ? true : false);
                account.setEmployees((rs.getInt("Employees") == 1) ? true : false);
                account.setSettings((rs.getInt("Settings") == 1) ? true : false);
                account.setAccounts((rs.getInt("Accounts") == 1) ? true : false);
                array.add(account);
            }

            return array;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Branch getBranchByName(String Branch) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(SELECT_SITE_BY_NAME);
            pstmt.setString(1, Branch);
            pstmt.execute();

            ResultSet rs = pstmt.getResultSet();
            if (rs.next()) {

                Branch branch = new Branch();
                branch.setId(rs.getInt("id"));
                branch.setName(rs.getString("Name"));
                branch.setAddress(rs.getString("Address"));
                branch.setBranchNo(rs.getString("BranchNo"));
                
                return branch;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Employee> getEmployeesByOccupation(String Occupation) {
        try {
            ArrayList<Employee> array = new ArrayList<>();

            PreparedStatement pstmt = connection.prepareStatement(SELECT_EMPLOYEE_BY_OCCUPATION);
            pstmt.setString(1, Occupation);
            pstmt.execute();

            ResultSet rs = pstmt.getResultSet();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstname(rs.getString("Firstname"));
                employee.setLastname(rs.getString("Surname"));
                employee.setIDNumber(rs.getString("IDNumber"));
                employee.setHomeAddress(rs.getString("Address"));
                employee.setEmail(rs.getString("Email"));
                employee.setCellNo(rs.getString("CellNo"));
                employee.setTaxNo(rs.getString("TaxNo"));
                employee.setOccupation(rs.getString("Occupation"));
                employee.setEmployeeNo(rs.getString("EmployeeNo"));
                employee.setIDNumber(rs.getString("IDNumber"));
                employee.setGender(Gender.fromString(rs.getString("Gender")));
                array.add(employee);
            }

            return array;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Employee> getEmployees() {
        try {
            ArrayList<Employee> array = new ArrayList<>();
            Statement stmt = connection.createStatement();

            stmt.executeQuery(SELECT_EMPLOYEE);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstname(rs.getString("Firstname"));
                employee.setLastname(rs.getString("Surname"));
                employee.setIDNumber(rs.getString("IDNumber"));
                employee.setHomeAddress(rs.getString("Address"));
                employee.setEmail(rs.getString("Email"));
                employee.setCellNo(rs.getString("CellNo"));
                employee.setTaxNo(rs.getString("TaxNo"));
                employee.setOccupation(rs.getString("Occupation"));
                employee.setEmployeeNo(rs.getString("EmployeeNo"));
                employee.setIDNumber(rs.getString("IDNumber"));
                employee.setGender(Gender.fromString(rs.getString("Gender")));
                array.add(employee);
            }

            return array;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Transaction> getTransactions() {
        try {
            if (session.getSettings().getFilterBranches().equals("<Show All>")) {
                ArrayList<Transaction> array = new ArrayList<>();
                Statement stmt = connection.createStatement();

                stmt.executeQuery(SELECT_TRANSACTIONS);
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {

                    Transaction transaction = new Transaction();
                    transaction.setId(rs.getInt("id"));
                    transaction.setSite(rs.getInt("Site"));
                    transaction.setTitle(rs.getString("Title"));
                    transaction.setType(rs.getString("Type"));
                    transaction.setTypeID(rs.getInt("TypeID"));
                    transaction.setPayment(rs.getString("Payment"));
                    transaction.setPrice(rs.getDouble("Price"));
                    transaction.setEmployee(rs.getString("Employee"));
                    transaction.setCustomerName(rs.getString("CustomerName"));
                    transaction.setCustomerNumber(rs.getString("CustomerNumber"));
                    transaction.setDate(rs.getDate("Date"));
                    array.add(transaction);
                }

                return array;

            } else {
                
                ArrayList<Transaction> array = new ArrayList<>();
                PreparedStatement pstmt = connection.prepareStatement(SELECT_TRANSACTIONS_BY_BRANCH);
                pstmt.setInt(1, session.getBranch().getId());
                
                pstmt.executeQuery();
                ResultSet rs = pstmt.getResultSet();
                while (rs.next()) {

                    Transaction transaction = new Transaction();
                    transaction.setId(rs.getInt("id"));
                    transaction.setSite(rs.getInt("Site"));
                    transaction.setTitle(rs.getString("Title"));
                    transaction.setType(rs.getString("Type"));
                    transaction.setTypeID(rs.getInt("TypeID"));
                    transaction.setPayment(rs.getString("Payment"));
                    transaction.setPrice(rs.getDouble("Price"));
                    transaction.setEmployee(rs.getString("Employee"));
                    transaction.setCustomerName(rs.getString("CustomerName"));
                    transaction.setCustomerNumber(rs.getString("CustomerNumber"));
                    transaction.setDate(rs.getDate("Date"));
                    array.add(transaction);
                }

                return array;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Occupation> getOccupations() {
        try {
            ArrayList<Occupation> array = new ArrayList<>();
            Statement stmt = connection.createStatement();

            stmt.executeQuery(SELECT_OCCUPATION);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Occupation occupation = new Occupation();
                occupation.setId(rs.getInt("id"));
                occupation.setTitle(rs.getString("Title"));
                occupation.setResponsibilities(rs.getString("Responsibilities"));
                array.add(occupation);
            }

            return array;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insert(Object object) {

        try {
            if (object instanceof Account) {

                PreparedStatement pstmt = connection.prepareStatement(INSERT_ACCOUNT);
                pstmt.setString(1, (((Account) object).getUsername()));
                pstmt.setString(2, ((Account) object).getPassword());
                pstmt.setInt(3, (((Account) object).isAdmin()) ? 1 : 0);
                pstmt.setInt(4, (((Account) object).isGuest()) ? 1 : 0);
                pstmt.setInt(5, (((Account) object).isTransactions()) ? 1 : 0);
                pstmt.setInt(6, (((Account) object).isServices()) ? 1 : 0);
                pstmt.setInt(7, (((Account) object).isProducts()) ? 1 : 0);
                pstmt.setInt(8, (((Account) object).isEmployees()) ? 1 : 0);
                pstmt.setInt(9, (((Account) object).isSettings()) ? 1 : 0);
                pstmt.setInt(10, (((Account) object).isAccounts()) ? 1 : 0);
                pstmt.executeUpdate(); 

            } else if (object instanceof Branch) {

                PreparedStatement pstmt = connection.prepareStatement(INSERT_SITE);
                pstmt.setString(1, (((Branch) object).getName()));
                pstmt.setString(2, ((Branch) object).getAddress());
                pstmt.setString(3, ((Branch) object).getBranchNo());
                pstmt.executeUpdate(); 

            } else if (object instanceof Service) {

                PreparedStatement pstmt = connection.prepareStatement(INSERT_SERVICES);
                pstmt.setString(1, (((Service) object).getName()));
                pstmt.setString(2, ((Service) object).getDescription());
                pstmt.setDouble(3, ((Service) object).getPrice());
                pstmt.setInt(4, (((Service) object).isTaxable()) ? 1 : 0);
                pstmt.executeUpdate(); 

            } else if (object instanceof Product) {

                PreparedStatement pstmt = connection.prepareStatement(INSERT_PRODUCT);
                pstmt.setString(1, (((Product) object).getName()));
                pstmt.setString(2, ((Product) object).getDescription());
                pstmt.setDouble(3, ((Product) object).getPrice());
                pstmt.setInt(4, (((Product) object).isTaxable()) ? 1 : 0);
                pstmt.executeUpdate(); 

            } else if (object instanceof Transaction) {

                PreparedStatement pstmt = connection.prepareStatement(INSERT_TRANSACTIONS);
                pstmt.setInt(1, (((Transaction) object).getSite()));
                pstmt.setString(2, ((Transaction) object).getTitle());
                pstmt.setString(3, ((Transaction) object).getType());
                pstmt.setInt(4, ((Transaction) object).getTypeID());
                pstmt.setString(5, ((Transaction) object).getPayment());
                pstmt.setDouble(6, ((Transaction) object).getPrice());
                pstmt.setString(7, ((Transaction) object).getEmployee());
                pstmt.setString(8, ((Transaction) object).getCustomerName());
                pstmt.setString(9, ((Transaction) object).getCustomerNumber());
                pstmt.setDate(10, new java.sql.Date(((Transaction) object).getDate().getTime()));
                pstmt.executeUpdate(); 

            } else if (object instanceof Employee) {
    
                PreparedStatement pstmt = connection.prepareStatement(INSERT_EMPLOYEE);
                pstmt.setString(1, ((Employee) object).getEmployeeNo());
                pstmt.setString(2, ((Employee) object).getOccupation());
                pstmt.setInt(3, (((Employee) object).getMedicalAid() != null) ? ((Employee) object).getMedicalAid().getId() : -1);
                pstmt.setString(4, ((Employee) object).getTaxNo());
                pstmt.setString(5, ((Employee) object).getIDNumber());
                pstmt.setString(6, ((Employee) object).getFirstname());
                pstmt.setString(7, ((Employee) object).getLastname());
                pstmt.setString(8, ((Employee) object).getEmail());
                pstmt.setString(9, ((Employee) object).getCellNo());
                pstmt.setString(10, ((Employee) object).getHomeAddress());
                pstmt.setString(11, (((Employee) object).getRace() != null) ? ((Employee) object).getRace().toString() : null);
                pstmt.setString(12, ((Employee) object).getGender().toString());
                pstmt.executeUpdate(); 

            } else if (object instanceof Occupation) {

                PreparedStatement pstmt = connection.prepareStatement(INSERT_OCCUPATION);
                pstmt.setString(1, (((Occupation) object).getTitle()));
                pstmt.setString(2, ((Occupation) object).getResponsibilities());
                pstmt.executeUpdate(); 

            } 
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Object object) {
        try {
            if (object instanceof Account) {

                PreparedStatement pstmt = connection.prepareStatement(UPDATE_ACCOUNT);
                pstmt.setString(1, (((Account) object).getUsername()));
                pstmt.setString(2, ((Account) object).getPassword());
                pstmt.setInt(3, (((Account) object).isAdmin()) ? 1 : 0);
                pstmt.setInt(4, (((Account) object).isGuest()) ? 1 : 0);
                pstmt.setInt(5, (((Account) object).isTransactions()) ? 1 : 0);
                pstmt.setInt(6, (((Account) object).isServices()) ? 1 : 0);
                pstmt.setInt(7, (((Account) object).isProducts()) ? 1 : 0);
                pstmt.setInt(8, (((Account) object).isEmployees()) ? 1 : 0);
                pstmt.setInt(9, (((Account) object).isSettings()) ? 1 : 0);
                pstmt.setInt(10, (((Account) object).isAccounts()) ? 1 : 0);
                pstmt.setInt(11, ((Account) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Branch) {

                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SITE);
                pstmt.setString(1, (((Branch) object).getName()));
                pstmt.setString(2, ((Branch) object).getAddress());
                pstmt.setString(3, ((Branch) object).getBranchNo());
                pstmt.setInt(4, ((Branch) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Service) {

                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SERVICES);
                pstmt.setString(1, (((Service) object).getName()));
                pstmt.setString(2, ((Service) object).getDescription());
                pstmt.setDouble(3, ((Service) object).getPrice());
                pstmt.setInt(4, (((Service) object).isTaxable()) ? 1 : 0);
                pstmt.setInt(5, ((Service) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Product) {

                PreparedStatement pstmt = connection.prepareStatement(UPDATE_PRODUCT);
                pstmt.setString(1, (((Product) object).getName()));
                pstmt.setString(2, ((Product) object).getDescription());
                pstmt.setDouble(3, ((Product) object).getPrice());
                pstmt.setInt(4, (((Product) object).isTaxable()) ? 1 : 0);
                pstmt.setInt(5, ((Product) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Transaction) {

                PreparedStatement pstmt = connection.prepareStatement(UPDATE_TRANSACTIONS);
                pstmt.setInt(1, (((Transaction) object).getSite()));
                pstmt.setString(2, ((Transaction) object).getTitle());
                pstmt.setString(3, ((Transaction) object).getType());
                pstmt.setInt(4, ((Transaction) object).getTypeID());
                pstmt.setString(5, ((Transaction) object).getPayment());
                pstmt.setDouble(6, ((Transaction) object).getPrice());
                pstmt.setString(7, ((Transaction) object).getEmployee());
                pstmt.setString(8, ((Transaction) object).getCustomerName());
                pstmt.setString(9, ((Transaction) object).getCustomerNumber());
                pstmt.setDate(10, new java.sql.Date(((Transaction) object).getDate().getTime()));
                pstmt.setInt(11, ((Transaction) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Employee) {

                PreparedStatement pstmt = connection.prepareStatement(UPDATE_EMPLOYEE);
                pstmt.setString(1, ((Employee) object).getEmployeeNo());
                pstmt.setString(2, ((Employee) object).getOccupation());
                pstmt.setInt(3, (((Employee) object).getMedicalAid() != null) ? ((Employee) object).getMedicalAid().getId() : -1);
                pstmt.setString(4, ((Employee) object).getTaxNo());
                pstmt.setString(5, ((Employee) object).getIDNumber());
                pstmt.setString(6, ((Employee) object).getFirstname());
                pstmt.setString(7, ((Employee) object).getLastname());
                pstmt.setString(8, ((Employee) object).getEmail());
                pstmt.setString(9, ((Employee) object).getCellNo());
                pstmt.setString(10, ((Employee) object).getHomeAddress());
                pstmt.setString(11, (((Employee) object).getRace() != null) ? ((Employee) object).getRace().toString() : null);
                pstmt.setString(12, ((Employee) object).getGender().toString());
                pstmt.setInt(13, ((Employee) object).getId());                
                pstmt.executeUpdate();

            } else if (object instanceof Occupation) {

                PreparedStatement pstmt = connection.prepareStatement(UPDATE_OCCUPATION);
                pstmt.setString(1, (((Occupation) object).getTitle()));
                pstmt.setString(2, ((Occupation) object).getResponsibilities());
                pstmt.setInt(3, ((Occupation) object).getId());
                pstmt.executeUpdate();

            } 
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(Object object) {
        try {
            if (object instanceof Branch) {

                PreparedStatement pstmt = connection.prepareStatement(DELETE_SITE);
                pstmt.setInt(1, ((Branch) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Service) {

                PreparedStatement pstmt = connection.prepareStatement(DELETE_SERVICES);
                pstmt.setInt(1, ((Service) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Product) {

                PreparedStatement pstmt = connection.prepareStatement(DELETE_PRODUCT);
                pstmt.setInt(1, ((Product) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Transaction) {

                PreparedStatement pstmt = connection.prepareStatement(DELETE_TRANSACTIONS);
                pstmt.setInt(1, ((Transaction) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Employee) {

                PreparedStatement pstmt = connection.prepareStatement(DELETE_EMPLOYEE);
                pstmt.setInt(1, ((Employee) object).getId());
                pstmt.executeUpdate();

            } else if (object instanceof Account) {

                PreparedStatement pstmt = connection.prepareStatement(DELETE_ACCOUNT);
                pstmt.setInt(1, ((Account) object).getId());
                pstmt.executeUpdate();
                
            } else if (object instanceof Occupation) {

                PreparedStatement pstmt = connection.prepareStatement(DELETE_OCCUPATION);
                pstmt.setInt(1, ((Occupation) object).getId());
                pstmt.executeUpdate();
            }
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    private String SELECT_EMPLOYEE_BY_OCCUPATION = null;
    
            
    
    private String SELECT_SITE_BY_NAME = "SELECT * FROM Site WHERE Name = ?";
            
    private String SELECT_TRANSACTIONS_BY_BRANCH = null;
    
    private String SELECT_OCCUPATION   = null;
    
    private String SELECT_SITE         = null;
    
    private String SELECT_SERVICES     = null;
    
    private String SELECT_TRANSACTIONS = null;
                
    private String SELECT_EMPLOYEE     = null;
    
    private String SELECT_PRODUCT      = null;
    
    private String SELECT_ACCOUNT       = null;
    
    
    private String DELETE_OCCUPATION   = null;
    
    private String DELETE_SITE         = null;
    
    private String DELETE_SERVICES     = null;
    
    private String DELETE_TRANSACTIONS = null;
                
    private String DELETE_EMPLOYEE     = null;
    
    private String DELETE_PRODUCT      = null;
    
    
    private String DELETE_ACCOUNT       = null;
    
    
    private String UPDATE_OCCUPATION   = null;
        
    private String UPDATE_SITE         = null;
    
    private String UPDATE_SERVICES     = null;
    
    private String UPDATE_TRANSACTIONS = null;
    
    private String UPDATE_EMPLOYEE     = null;
    
    private String UPDATE_PRODUCT      = null;
        
    private String UPDATE_ACCOUNT      = null;
    
    
    private String INSERT_OCCUPATION   = null;
    
    private String INSERT_SITE         = null;
    
    private String INSERT_SERVICES     = null;
    
    private String INSERT_TRANSACTIONS = null;
    
    private String INSERT_EMPLOYEE     = null;
    
    private String INSERT_PRODUCT      = null;
    
    private String INSERT_ACCOUNT      = null;
    
    
    private String CREATE_OCCUPATION   = null;
    
    private String CHECK_TABLE         = null;
    
    private String CREATE_BRANCH       = null;
    
    private String CREATE_SERVICES     = null;
    
    private String CREATE_TRANSACTIONS = null;
    
    private String CREATE_EMPLOYEE     = null;
    
    private String CREATE_PRODUCT      = null;
    
    private String CREATE_ACCOUNT      = null;
}
