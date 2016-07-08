
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
 *         &lt;element name="ListPathsByUpdateTicketResult" type="{http://xml.osisoft.com/services/PIDataService}ArrayOfString" minOccurs="0"/>
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
    "listPathsByUpdateTicketResult"
})
@XmlRootElement(name = "ListPathsByUpdateTicketResponse")
public class ListPathsByUpdateTicketResponse {

    @XmlElement(name = "ListPathsByUpdateTicketResult")
    protected ArrayOfString listPathsByUpdateTicketResult;

    /**
     * Gets the value of the listPathsByUpdateTicketResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getListPathsByUpdateTicketResult() {
        return listPathsByUpdateTicketResult;
    }

    /**
     * Sets the value of the listPathsByUpdateTicketResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setListPathsByUpdateTicketResult(ArrayOfString value) {
        this.listPathsByUpdateTicketResult = value;
    }

}
