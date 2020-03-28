
package com.main.server.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.main.server.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EntityNotFoundException_QNAME = new QName("http://service.server.main.com/", "EntityNotFoundException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.main.server.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EntityNotFoundException }
     * 
     */
    public EntityNotFoundException createEntityNotFoundException() {
        return new EntityNotFoundException();
    }

    /**
     * Create an instance of {@link CategoryDT }
     * 
     */
    public CategoryDT createCategoryDT() {
        return new CategoryDT();
    }

    /**
     * Create an instance of {@link CategoryDTArray }
     * 
     */
    public CategoryDTArray createCategoryDTArray() {
        return new CategoryDTArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.main.com/", name = "EntityNotFoundException")
    public JAXBElement<EntityNotFoundException> createEntityNotFoundException(EntityNotFoundException value) {
        return new JAXBElement<EntityNotFoundException>(_EntityNotFoundException_QNAME, EntityNotFoundException.class, null, value);
    }

}
