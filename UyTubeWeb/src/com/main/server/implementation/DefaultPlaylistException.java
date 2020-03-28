
package com.main.server.implementation;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "DefaultPlaylistException", targetNamespace = "http://service.server.main.com/")
public class DefaultPlaylistException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private com.main.server.service.DefaultPlaylistException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public DefaultPlaylistException(String message, com.main.server.service.DefaultPlaylistException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public DefaultPlaylistException(String message, com.main.server.service.DefaultPlaylistException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.main.server.service.DefaultPlaylistException
     */
    public com.main.server.service.DefaultPlaylistException getFaultInfo() {
        return faultInfo;
    }

}
