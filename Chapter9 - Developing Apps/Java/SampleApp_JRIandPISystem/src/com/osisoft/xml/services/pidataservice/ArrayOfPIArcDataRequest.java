
package com.osisoft.xml.services.pidataservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPIArcDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPIArcDataRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PIArcDataRequest" type="{http://xml.osisoft.com/services/PIDataService}PIArcDataRequest" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPIArcDataRequest", propOrder = {
    "piArcDataRequest"
})
public class ArrayOfPIArcDataRequest {

    @XmlElement(name = "PIArcDataRequest", nillable = true)
    protected List<PIArcDataRequest> piArcDataRequest;

    /**
     * Gets the value of the piArcDataRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the piArcDataRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPIArcDataRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PIArcDataRequest }
     * 
     * 
     */
    public List<PIArcDataRequest> getPIArcDataRequest() {
        if (piArcDataRequest == null) {
            piArcDataRequest = new ArrayList<PIArcDataRequest>();
        }
        return this.piArcDataRequest;
    }

}
