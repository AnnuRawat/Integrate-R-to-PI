
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateFilterType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UpdateFilterType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Snapshot"/>
 *     &lt;enumeration value="Archive"/>
 *     &lt;enumeration value="SnapshotAndArchive"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UpdateFilterType")
@XmlEnum
public enum UpdateFilterType {

    @XmlEnumValue("Snapshot")
    SNAPSHOT("Snapshot"),
    @XmlEnumValue("Archive")
    ARCHIVE("Archive"),
    @XmlEnumValue("SnapshotAndArchive")
    SNAPSHOT_AND_ARCHIVE("SnapshotAndArchive");
    private final String value;

    UpdateFilterType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UpdateFilterType fromValue(String v) {
        for (UpdateFilterType c: UpdateFilterType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
