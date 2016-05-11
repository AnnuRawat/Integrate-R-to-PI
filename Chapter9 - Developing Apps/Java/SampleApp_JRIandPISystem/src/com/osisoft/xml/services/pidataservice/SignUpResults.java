
package com.osisoft.xml.services.pidataservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SignUpResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignUpResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UpdateTicket" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Errors" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ErrDescs" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ErrorCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignUpResults", propOrder = {
    "updateTicket",
    "errors",
    "errDescs",
    "errorCount"
})
public class SignUpResults {

    @XmlElement(name = "UpdateTicket", required = true)
    protected String updateTicket;
    @XmlElement(name = "Errors", type = Integer.class)
    protected List<Integer> errors;
    @XmlElement(name = "ErrDescs")
    protected List<String> errDescs;
    @XmlElement(name = "ErrorCount")
    protected int errorCount;

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
     * Gets the value of the errors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getErrors() {
        if (errors == null) {
            errors = new ArrayList<Integer>();
        }
        return this.errors;
    }

    /**
     * Gets the value of the errDescs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errDescs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrDescs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getErrDescs() {
        if (errDescs == null) {
            errDescs = new ArrayList<String>();
        }
        return this.errDescs;
    }

    /**
     * Gets the value of the errorCount property.
     * 
     */
    public int getErrorCount() {
        return errorCount;
    }

    /**
     * Sets the value of the errorCount property.
     * 
     */
    public void setErrorCount(int value) {
        this.errorCount = value;
    }

}
