
package com.main.server.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para playlistDT complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="playlistDT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categories" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://service.server.main.com/}channelDT" minOccurs="0"/>
 *         &lt;element name="playlistId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="private" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="videos" type="{http://service.server.main.com/}videoDT" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "playlistDT", propOrder = {
    "categories",
    "name",
    "owner",
    "playlistId",
    "_private",
    "videos"
})
public class PlaylistDT {

    @XmlElement(nillable = true)
    protected List<String> categories;
    protected String name;
    protected ChannelDT owner;
    protected Long playlistId;
    @XmlElement(name = "private")
    protected boolean _private;
    @XmlElement(nillable = true)
    protected List<VideoDT> videos;

    /**
     * Gets the value of the categories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCategories() {
        if (categories == null) {
            categories = new ArrayList<String>();
        }
        return this.categories;
    }

    /**
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtiene el valor de la propiedad owner.
     * 
     * @return
     *     possible object is
     *     {@link ChannelDT }
     *     
     */
    public ChannelDT getOwner() {
        return owner;
    }

    /**
     * Define el valor de la propiedad owner.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelDT }
     *     
     */
    public void setOwner(ChannelDT value) {
        this.owner = value;
    }

    /**
     * Obtiene el valor de la propiedad playlistId.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPlaylistId() {
        return playlistId;
    }

    /**
     * Define el valor de la propiedad playlistId.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPlaylistId(Long value) {
        this.playlistId = value;
    }

    /**
     * Obtiene el valor de la propiedad private.
     * 
     */
    public boolean isPrivate() {
        return _private;
    }

    /**
     * Define el valor de la propiedad private.
     * 
     */
    public void setPrivate(boolean value) {
        this._private = value;
    }

    /**
     * Gets the value of the videos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the videos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVideos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VideoDT }
     * 
     * 
     */
    public List<VideoDT> getVideos() {
        if (videos == null) {
            videos = new ArrayList<VideoDT>();
        }
        return this.videos;
    }

}
