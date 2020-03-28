package com.main.server;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    
    
    public String getPort() throws IOException {
        Properties props = getProperties();
        return props.getProperty("port");
    }
    
    public  String getIP() throws IOException {
        Properties props = getProperties();
        return props.getProperty("ip");
    }
    
    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        String propFilePath = System.getProperty("user.home") + "/.UyTube/config.properties";
        System.out.println(propFilePath);
        InputStream in = new FileInputStream(propFilePath);
        props.load(in);
        
        return props;
    }
    
}
