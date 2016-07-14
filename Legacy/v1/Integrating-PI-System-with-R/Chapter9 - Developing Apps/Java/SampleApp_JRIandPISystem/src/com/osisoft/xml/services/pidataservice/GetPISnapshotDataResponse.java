
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
 *         &lt;element name="GetPISnapshotDataResult" type="{http://xml.osisoft.com/services/PIDataService}ArrayOfTimeSeries" minOccurs="0"/>
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
    "getPISnapshotDataResult"
})
@XmlRootElement(name = "GetPISnapshotDataResponse")
public class GetPISnapshotDataResponse {

    @XmlElement(name = "GetPISnapshotDataResult")
    protected ArrayOfTimeSeries getPISnapshotDataResult;

    /**
     * Gets the value of the getPISnapshotDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTimeSeries }
     *     
     */
    public ArrayOfTimeSeries getGetPISnapshotDataResult() {
        return getPISnapshotDataResult;
    }

    /**
     * Sets the value of the getPISnapshotDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTimeSeries }
     *     
     */
    public void setGetPISnapshotDataResult(ArrayOfTimeSeries value) {
        this.getPISnapshotDataResult = value;
    }

}
