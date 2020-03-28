
package com.main.server.service;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ratingEnum.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="ratingEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LIKE"/>
 *     &lt;enumeration value="DISLIKE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ratingEnum")
@XmlEnum
public enum RatingEnum {

    LIKE,
    DISLIKE;

    public String value() {
        return name();
    }

    public static RatingEnum fromValue(String v) {
        return valueOf(v);
    }

}
