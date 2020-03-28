
package com.main.server.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para historyDT complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="historyDT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="visitCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lastVisit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="videoDt" type="{http://service.server.main.com/}videoDT" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "historyDT", propOrder = {
    "visitCount",
    "lastVisit",
    "videoDt"
})
public class HistoryDT {

    protected int visitCount;
    protected String lastVisit;
    protected VideoDT videoDt;

    /**
     * Obtiene el valor de la propiedad visitCount.
     * 
     */
    public int getVisitCount() {
        return visitCount;
    }

    /**
     * Define el valor de la propiedad visitCount.
     * 
     */
    public void setVisitCount(int value) {
        this.visitCount = value;
    }

    /**
     * Obtiene el valor de la propiedad lastVisit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastVisit() {
        return lastVisit;
    }

    /**
     * Define el valor de la propiedad lastVisit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastVisit(String value) {
        this.lastVisit = value;
    }

    /**
     * Obtiene el valor de la propiedad videoDt.
     * 
     * @return
     *     possible object is
     *     {@link VideoDT }
     *     
     */
    public VideoDT getVideoDt() {
        return videoDt;
    }

    /**
     * Define el valor de la propiedad videoDt.
     * 
     * @param value
     *     allowed object is
     *     {@link VideoDT }
     *     
     */
    public void setVideoDt(VideoDT value) {
        this.videoDt = value;
    }

}
