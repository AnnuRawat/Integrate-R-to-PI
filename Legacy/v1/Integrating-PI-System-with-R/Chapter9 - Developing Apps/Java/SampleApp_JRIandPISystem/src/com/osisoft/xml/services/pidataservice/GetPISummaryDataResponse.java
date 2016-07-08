
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
 *         &lt;element name="GetPISummaryDataResult" type="{http://xml.osisoft.com/services/PIDataService}ArrayOfTimeSeries" minOccurs="0"/>
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
    "getPISummaryDataResult"
})
@XmlRootElement(name = "GetPISummaryDataResponse")
public class GetPISummaryDataResponse {

    @XmlElement(name = "GetPISummaryDataResult")
    protected ArrayOfTimeSeries getPISummaryDataResult;

    /**
     * Gets the value of the getPISummaryDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTimeSeries }
     *     
     */
    public ArrayOfTimeSeries getGetPISummaryDataResult() {
        return getPISummaryDataResult;
    }

    /**
     * Sets the value of the getPISummaryDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTimeSeries }
     *     
     */
    public void setGetPISummaryDataResult(ArrayOfTimeSeries value) {
        this.getPISummaryDataResult = value;
    }

}
