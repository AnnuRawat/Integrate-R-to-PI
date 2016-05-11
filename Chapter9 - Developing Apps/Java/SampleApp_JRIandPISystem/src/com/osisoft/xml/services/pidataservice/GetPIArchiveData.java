
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="requests" type="{http://xml.osisoft.com/services/PIDataService}ArrayOfPIArcDataRequest" minOccurs="0"/>
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
    "requests"
})
@XmlRootElement(name = "GetPIArchiveData")
public class GetPIArchiveData {

    protected ArrayOfPIArcDataRequest requests;

    /**
     * Gets the value of the requests property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPIArcDataRequest }
     *     
     */
    public ArrayOfPIArcDataRequest getRequests() {
        return requests;
    }

    /**
     * Sets the value of the requests property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPIArcDataRequest }
     *     
     */
    public void setRequests(ArrayOfPIArcDataRequest value) {
        this.requests = value;
    }

}
