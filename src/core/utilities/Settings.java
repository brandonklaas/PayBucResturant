package core.utilities;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
    A class to handle the settings of the program
**/
public class Settings {
    private Properties properties;
    private File propertiesFile;
    private InputStream input;

    /**
        A default constructor to create an instance of this class
    **/
    public Settings() {
        properties = new Properties();
        initializeFile();
    };

    /*
        Initialize the properties file
    */
    private void initializeFile() {
        propertiesFile = new File("Settings/properties.config");

        if (!propertiesFile.exists()) {
            try {
                propertiesFile.createNewFile();
                OutputStream output = new FileOutputStream("Settings/properties.config");

                properties.setProperty("DefaultBranch"          , "<None>");
                properties.setProperty("PayBucAccount"          , "");
                properties.setProperty("Tax"                    , "");
                properties.setProperty("FilterBranches"         , "<Show All>");

                // save properties to project root folder
                properties.store(output, null);
                output.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {

                input = new FileInputStream("Settings/properties.config");
                properties.load(input);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (IOException ex) {
                ex.printStackTrace();
                }
            }
        }
    }

    public void saveSettings() {
        try {
            propertiesFile.delete();
            propertiesFile = new File("Settings/properties.config");

            propertiesFile.createNewFile();
            OutputStream output = new FileOutputStream("Settings/properties.config");
            properties.store(output, null);

        } catch (IOException ex) {
                ex.printStackTrace();
        }
    }

    public String getDefaultBranch(){
        return properties.getProperty("DefaultBranch");
    }

    public String getPayBucAccount(){
        return properties.getProperty("PayBucAccount");
    }

    public String getTax(){
        return properties.getProperty("Tax");
    }

    public String getFilterBranches(){
        return properties.getProperty("FilterBranches");
    }

    // ================ SETTER METHODS ========================
    public void setDefaultBranch(String value){
        properties.setProperty("DefaultBranch", value);
    }
    
    public void setBayBucAccount(String value){
        properties.setProperty("PayBucAccount", value);
    }
    
    public void setTax(String value){
        properties.setProperty("Tax", value);
    }
    
    
    public void setFilterBranches(String value){
        properties.setProperty("FilterBranches", value);
    }

}
