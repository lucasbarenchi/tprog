
package com.main.server.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para playlists complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="playlists">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="playlists" type="{http://service.server.main.com/}playlistDT" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "playlists", propOrder = {
    "playlists"
})
public class Playlists {

    @XmlElement(nillable = true)
    protected List<PlaylistDT> playlists;

    /**
     * Gets the value of the playlists property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the playlists property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlaylists().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlaylistDT }
     * 
     * 
     */
    public List<PlaylistDT> getPlaylists() {
        if (playlists == null) {
            playlists = new ArrayList<PlaylistDT>();
        }
        return this.playlists;
    }

}
