
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UpdateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Archive"/>
 *     &lt;enumeration value="Snapshot"/>
 *     &lt;enumeration value="Delete"/>
 *     &lt;enumeration value="Edit"/>
 *     &lt;enumeration value="AddNoReplace"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UpdateType")
@XmlEnum
public enum UpdateType {

    @XmlEnumValue("Archive")
    ARCHIVE("Archive"),
    @XmlEnumValue("Snapshot")
    SNAPSHOT("Snapshot"),
    @XmlEnumValue("Delete")
    DELETE("Delete"),
    @XmlEnumValue("Edit")
    EDIT("Edit"),
    @XmlEnumValue("AddNoReplace")
    ADD_NO_REPLACE("AddNoReplace");
    private final String value;

    UpdateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UpdateType fromValue(String v) {
        for (UpdateType c: UpdateType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
