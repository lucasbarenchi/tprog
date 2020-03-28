package com.uytube;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	
	static public String IP = "";
	static public String PORT = "";
	
    public String getPort() throws IOException {
        Properties props = getProperties();
        String port = props.getProperty("port");
        System.out.println("######### Este es el port: " + port);
        return port;
    }
    
    public String getIP() throws IOException {
        Properties props = getProperties();
        String ip = props.getProperty("ip"); 
        System.out.println("######### Esta es la ip: " + ip);
        return ip;
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
