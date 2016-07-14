
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
 *         &lt;element name="InsertPIDataResult" type="{http://xml.osisoft.com/services/PIDataService}TimeSeries" minOccurs="0"/>
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
    "insertPIDataResult"
})
@XmlRootElement(name = "InsertPIDataResponse")
public class InsertPIDataResponse {

    @XmlElement(name = "InsertPIDataResult")
    protected TimeSeries insertPIDataResult;

    /**
     * Gets the value of the insertPIDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link TimeSeries }
     *     
     */
    public TimeSeries getInsertPIDataResult() {
        return insertPIDataResult;
    }

    /**
     * Sets the value of the insertPIDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSeries }
     *     
     */
    public void setInsertPIDataResult(TimeSeries value) {
        this.insertPIDataResult = value;
    }

}
