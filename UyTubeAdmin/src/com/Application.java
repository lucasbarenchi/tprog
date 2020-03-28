package com;

import java.awt.Frame;

import javax.xml.ws.Endpoint;

import com.main.gui.MainWindow;
import com.main.logic.dao.AbstractDAO;
import com.main.server.PropertiesLoader;
import com.main.server.implementation.CategoryWebServiceImpl;
import com.main.server.implementation.PlaylistWebServiceImpl;
import com.main.server.implementation.UserWebServiceImpl;
import com.main.server.implementation.VideoWebServiceImpl;

public class Application {
    
    public static void main(String[] args) {
        AbstractDAO.initSchema();
        MainWindow mainWindow = new MainWindow();
        mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
        mainWindow.setVisible(true);
        
        
        try {
            PropertiesLoader pl = new PropertiesLoader();
            String port = pl.getPort();
            String ip = pl.getIP();
            
            Endpoint.publish(String.format("http://%s:%s/uytube/user", ip, port), new UserWebServiceImpl());
            Endpoint.publish(String.format("http://%s:%s/uytube/video", ip, port), new VideoWebServiceImpl());
            Endpoint.publish(String.format("http://%s:%s/uytube/category", ip, port), new CategoryWebServiceImpl());
            Endpoint.publish(String.format("http://%s:%s/uytube/playlist", ip, port), new PlaylistWebServiceImpl());
            
            System.out.println(String.format("#### Se levanta web service en http://%s:%s/uytube/user", ip, port));
            System.out.println(String.format("#### Se levanta web service en http://%s:%s/uytube/video", ip, port));
            System.out.println(String.format("#### Se levanta web service en http://%s:%s/uytube/category", ip, port));
            System.out.println(String.format("#### Se levanta web service en http://%s:%s/uytube/playlist", ip, port));
        } catch (Exception e) {
            System.out.println("Error al leer archivo de configuraciones para levantar web services.");
        }
        
    }
}
