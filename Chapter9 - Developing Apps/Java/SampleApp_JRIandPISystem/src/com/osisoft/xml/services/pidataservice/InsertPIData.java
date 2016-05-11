
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="events" type="{http://xml.osisoft.com/services/PIDataService}ArrayOfTimeSeries" minOccurs="0"/>
 *         &lt;element name="duplicateSwitch">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="InsertDuplicate"/>
 *               &lt;enumeration value="ReplaceDuplicate"/>
 *               &lt;enumeration value="ReplaceOnlyDuplicate"/>
 *               &lt;enumeration value="ErrorDuplicate"/>
 *               &lt;enumeration value="ErrorDuplicatesSilent"/>
 *               &lt;enumeration value="ReplaceOnlyDuplicatesSilent"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "events",
    "duplicateSwitch"
})
@XmlRootElement(name = "InsertPIData")
public class InsertPIData {

    protected ArrayOfTimeSeries events;
    @XmlElement(required = true)
    protected String duplicateSwitch;

    /**
     * Gets the value of the events property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTimeSeries }
     *     
     */
    public ArrayOfTimeSeries getEvents() {
        return events;
    }

    /**
     * Sets the value of the events property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTimeSeries }
     *     
     */
    public void setEvents(ArrayOfTimeSeries value) {
        this.events = value;
    }

    /**
     * Gets the value of the duplicateSwitch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuplicateSwitch() {
        return duplicateSwitch;
    }

    /**
     * Sets the value of the duplicateSwitch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuplicateSwitch(String value) {
        this.duplicateSwitch = value;
    }

}
