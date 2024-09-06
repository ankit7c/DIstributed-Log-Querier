package org.example.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {

    public Properties readConfig(){
        Properties prop = null;
        try {
            prop = readPropertiesFile("src/main/resources/properties/application.properties");
            System.out.println("Machine Name: " + prop.getProperty("machine.ip"));
            System.out.println("Port No: " + prop.getProperty("port.number"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return prop;
    }

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }
}