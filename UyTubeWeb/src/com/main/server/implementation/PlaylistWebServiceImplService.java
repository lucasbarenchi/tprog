
package com.main.server.implementation;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import com.uytube.PropertiesLoader;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PlaylistWebServiceImplService", targetNamespace = "http://implementation.server.main.com/")
public class PlaylistWebServiceImplService
    extends Service
{

    private static URL PLAYLISTWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    private static WebServiceException PLAYLISTWEBSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName PLAYLISTWEBSERVICEIMPLSERVICE_QNAME = new QName("http://implementation.server.main.com/", "PlaylistWebServiceImplService");


    public PlaylistWebServiceImplService() {
        super(__getWsdlLocation(), PLAYLISTWEBSERVICEIMPLSERVICE_QNAME);
    }

    public PlaylistWebServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PLAYLISTWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public PlaylistWebServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, PLAYLISTWEBSERVICEIMPLSERVICE_QNAME);
    }

    public PlaylistWebServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PLAYLISTWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public PlaylistWebServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PlaylistWebServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PlaylistWebService
     */
    @WebEndpoint(name = "PlaylistWebServiceImplPort")
    public PlaylistWebService getPlaylistWebServiceImplPort() {
        return super.getPort(new QName("http://implementation.server.main.com/", "PlaylistWebServiceImplPort"), PlaylistWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PlaylistWebService
     */
    @WebEndpoint(name = "PlaylistWebServiceImplPort")
    public PlaylistWebService getPlaylistWebServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://implementation.server.main.com/", "PlaylistWebServiceImplPort"), PlaylistWebService.class, features);
    }

    private static URL __getWsdlLocation() {
    	URL url = null;
        WebServiceException e = null;
        try {
        	PropertiesLoader pl = new PropertiesLoader();
            url = new URL(String.format("http://%s:%s/uytube/playlist?wsdl", pl.getIP(), pl.getPort()));
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        } catch (Exception ex) {
        	System.out.println("Failed to read properties");
        	ex.printStackTrace();
        }
        PLAYLISTWEBSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        PLAYLISTWEBSERVICEIMPLSERVICE_EXCEPTION = e;
    	
        if (PLAYLISTWEBSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw PLAYLISTWEBSERVICEIMPLSERVICE_EXCEPTION;
        }
        return PLAYLISTWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
