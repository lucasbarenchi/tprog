
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
@WebServiceClient(name = "VideoWebServiceImplService", targetNamespace = "http://implementation.server.main.com/")
public class VideoWebServiceImplService
    extends Service
{

    private static URL VIDEOWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    private static WebServiceException VIDEOWEBSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName VIDEOWEBSERVICEIMPLSERVICE_QNAME = new QName("http://implementation.server.main.com/", "VideoWebServiceImplService");

    public VideoWebServiceImplService() {
        super(__getWsdlLocation(), VIDEOWEBSERVICEIMPLSERVICE_QNAME);
    }

    public VideoWebServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), VIDEOWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public VideoWebServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, VIDEOWEBSERVICEIMPLSERVICE_QNAME);
    }

    public VideoWebServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, VIDEOWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public VideoWebServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public VideoWebServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns VideoWebService
     */
    @WebEndpoint(name = "VideoWebServiceImplPort")
    public VideoWebService getVideoWebServiceImplPort() {
        return super.getPort(new QName("http://implementation.server.main.com/", "VideoWebServiceImplPort"), VideoWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns VideoWebService
     */
    @WebEndpoint(name = "VideoWebServiceImplPort")
    public VideoWebService getVideoWebServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://implementation.server.main.com/", "VideoWebServiceImplPort"), VideoWebService.class, features);
    }

    private static URL __getWsdlLocation() {
    	URL url = null;
        WebServiceException e = null;
        try {
        	PropertiesLoader pl = new PropertiesLoader();
            url = new URL(String.format("http://%s:%s/uytube/video?wsdl", pl.getIP(), pl.getPort()));
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        } catch (Exception ex) {
        	System.out.println("Failed to read properties");
        	ex.printStackTrace();
        }
        VIDEOWEBSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        VIDEOWEBSERVICEIMPLSERVICE_EXCEPTION = e;
        
        if (VIDEOWEBSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw VIDEOWEBSERVICEIMPLSERVICE_EXCEPTION;
        }
        return VIDEOWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
