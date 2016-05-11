
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="updateTicket" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="maxWaitForUpdates" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *         &lt;element name="evtFilter" type="{http://xml.osisoft.com/services/PIDataService}UpdateFilterType"/>
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
    "updateTicket",
    "maxWaitForUpdates",
    "evtFilter"
})
@XmlRootElement(name = "GetPIUpdates")
public class GetPIUpdates {

    @XmlElement(required = true)
    protected String updateTicket;
    @XmlSchemaType(name = "unsignedShort")
    protected int maxWaitForUpdates;
    @XmlElement(required = true)
    protected UpdateFilterType evtFilter;

    /**
     * Gets the value of the updateTicket property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateTicket() {
        return updateTicket;
    }

    /**
     * Sets the value of the updateTicket property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateTicket(String value) {
        this.updateTicket = value;
    }

    /**
     * Gets the value of the maxWaitForUpdates property.
     * 
     */
    public int getMaxWaitForUpdates() {
        return maxWaitForUpdates;
    }

    /**
     * Sets the value of the maxWaitForUpdates property.
     * 
     */
    public void setMaxWaitForUpdates(int value) {
        this.maxWaitForUpdates = value;
    }

    /**
     * Gets the value of the evtFilter property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateFilterType }
     *     
     */
    public UpdateFilterType getEvtFilter() {
        return evtFilter;
    }

    /**
     * Sets the value of the evtFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateFilterType }
     *     
     */
    public void setEvtFilter(UpdateFilterType value) {
        this.evtFilter = value;
    }

}
