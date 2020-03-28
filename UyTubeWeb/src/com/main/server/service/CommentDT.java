
package com.main.server.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para commentDT complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="commentDT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="commentedVideo" type="{http://service.server.main.com/}videoDT" minOccurs="0"/>
 *         &lt;element name="commenter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responses" type="{http://service.server.main.com/}commentDT" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "commentDT", propOrder = {
    "commentDate",
    "commentId",
    "commentedVideo",
    "commenter",
    "responses",
    "text"
})
public class CommentDT {

    protected String commentDate;
    protected Long commentId;
    protected VideoDT commentedVideo;
    protected String commenter;
    @XmlElement(nillable = true)
    protected List<CommentDT> responses;
    protected String text;

    /**
     * Obtiene el valor de la propiedad commentDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentDate() {
        return commentDate;
    }

    /**
     * Define el valor de la propiedad commentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentDate(String value) {
        this.commentDate = value;
    }

    /**
     * Obtiene el valor de la propiedad commentId.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * Define el valor de la propiedad commentId.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCommentId(Long value) {
        this.commentId = value;
    }

    /**
     * Obtiene el valor de la propiedad commentedVideo.
     * 
     * @return
     *     possible object is
     *     {@link VideoDT }
     *     
     */
    public VideoDT getCommentedVideo() {
        return commentedVideo;
    }

    /**
     * Define el valor de la propiedad commentedVideo.
     * 
     * @param value
     *     allowed object is
     *     {@link VideoDT }
     *     
     */
    public void setCommentedVideo(VideoDT value) {
        this.commentedVideo = value;
    }

    /**
     * Obtiene el valor de la propiedad commenter.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommenter() {
        return commenter;
    }

    /**
     * Define el valor de la propiedad commenter.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommenter(String value) {
        this.commenter = value;
    }

    /**
     * Gets the value of the responses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommentDT }
     * 
     * 
     */
    public List<CommentDT> getResponses() {
        if (responses == null) {
            responses = new ArrayList<CommentDT>();
        }
        return this.responses;
    }

    /**
     * Obtiene el valor de la propiedad text.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Define el valor de la propiedad text.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

}
