
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
 *         &lt;element name="SignUpForPIUpdatesResult" type="{http://xml.osisoft.com/services/PIDataService}SignUpResults" minOccurs="0"/>
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
    "signUpForPIUpdatesResult"
})
@XmlRootElement(name = "SignUpForPIUpdatesResponse")
public class SignUpForPIUpdatesResponse {

    @XmlElement(name = "SignUpForPIUpdatesResult")
    protected SignUpResults signUpForPIUpdatesResult;

    /**
     * Gets the value of the signUpForPIUpdatesResult property.
     * 
     * @return
     *     possible object is
     *     {@link SignUpResults }
     *     
     */
    public SignUpResults getSignUpForPIUpdatesResult() {
        return signUpForPIUpdatesResult;
    }

    /**
     * Sets the value of the signUpForPIUpdatesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignUpResults }
     *     
     */
    public void setSignUpForPIUpdatesResult(SignUpResults value) {
        this.signUpForPIUpdatesResult = value;
    }

}
