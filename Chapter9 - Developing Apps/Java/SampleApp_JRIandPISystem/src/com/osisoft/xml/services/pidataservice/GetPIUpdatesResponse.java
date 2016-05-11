
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
 *         &lt;element name="GetPIUpdatesResult" type="{http://xml.osisoft.com/services/PIDataService}ArrayOfTimeSeriesUpdates" minOccurs="0"/>
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
    "getPIUpdatesResult"
})
@XmlRootElement(name = "GetPIUpdatesResponse")
public class GetPIUpdatesResponse {

    @XmlElement(name = "GetPIUpdatesResult")
    protected ArrayOfTimeSeriesUpdates getPIUpdatesResult;

    /**
     * Gets the value of the getPIUpdatesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTimeSeriesUpdates }
     *     
     */
    public ArrayOfTimeSeriesUpdates getGetPIUpdatesResult() {
        return getPIUpdatesResult;
    }

    /**
     * Sets the value of the getPIUpdatesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTimeSeriesUpdates }
     *     
     */
    public void setGetPIUpdatesResult(ArrayOfTimeSeriesUpdates value) {
        this.getPIUpdatesResult = value;
    }

}
