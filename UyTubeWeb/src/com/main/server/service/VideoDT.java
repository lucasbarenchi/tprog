
package com.main.server.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para videoDT complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="videoDT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allComments" type="{http://service.server.main.com/}commentDT" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="category" type="{http://service.server.main.com/}categoryDT" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://service.server.main.com/}commentDT" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="disliked" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="liked" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="private" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uploadDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uploader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="videoId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "videoDT", propOrder = {
    "allComments",
    "category",
    "comments",
    "description",
    "disliked",
    "length",
    "liked",
    "_private",
    "title",
    "uploadDate",
    "uploader",
    "url",
    "videoId"
})
public class VideoDT {

    @XmlElement(nillable = true)
    protected List<CommentDT> allComments;
    protected CategoryDT category;
    @XmlElement(nillable = true)
    protected List<CommentDT> comments;
    protected String description;
    @XmlElement(nillable = true)
    protected List<String> disliked;
    protected int length;
    @XmlElement(nillable = true)
    protected List<String> liked;
    @XmlElement(name = "private")
    protected boolean _private;
    protected String title;
    protected String uploadDate;
    protected String uploader;
    protected String url;
    protected Long videoId;

    /**
     * Gets the value of the allComments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allComments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommentDT }
     * 
     * 
     */
    public List<CommentDT> getAllComments() {
        if (allComments == null) {
            allComments = new ArrayList<CommentDT>();
        }
        return this.allComments;
    }

    /**
     * Obtiene el valor de la propiedad category.
     * 
     * @return
     *     possible object is
     *     {@link CategoryDT }
     *     
     */
    public CategoryDT getCategory() {
        return category;
    }

    /**
     * Define el valor de la propiedad category.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryDT }
     *     
     */
    public void setCategory(CategoryDT value) {
        this.category = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommentDT }
     * 
     * 
     */
    public List<CommentDT> getComments() {
        if (comments == null) {
            comments = new ArrayList<CommentDT>();
        }
        return this.comments;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the disliked property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the disliked property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisliked().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDisliked() {
        if (disliked == null) {
            disliked = new ArrayList<String>();
        }
        return this.disliked;
    }

    /**
     * Obtiene el valor de la propiedad length.
     * 
     */
    public int getLength() {
        return length;
    }

    /**
     * Define el valor de la propiedad length.
     * 
     */
    public void setLength(int value) {
        this.length = value;
    }

    /**
     * Gets the value of the liked property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the liked property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLiked().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLiked() {
        if (liked == null) {
            liked = new ArrayList<String>();
        }
        return this.liked;
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
     * Obtiene el valor de la propiedad title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Define el valor de la propiedad title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Obtiene el valor de la propiedad uploadDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * Define el valor de la propiedad uploadDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadDate(String value) {
        this.uploadDate = value;
    }

    /**
     * Obtiene el valor de la propiedad uploader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploader() {
        return uploader;
    }

    /**
     * Define el valor de la propiedad uploader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploader(String value) {
        this.uploader = value;
    }

    /**
     * Obtiene el valor de la propiedad url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define el valor de la propiedad url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Obtiene el valor de la propiedad videoId.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVideoId() {
        return videoId;
    }

    /**
     * Define el valor de la propiedad videoId.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVideoId(Long value) {
        this.videoId = value;
    }

}
