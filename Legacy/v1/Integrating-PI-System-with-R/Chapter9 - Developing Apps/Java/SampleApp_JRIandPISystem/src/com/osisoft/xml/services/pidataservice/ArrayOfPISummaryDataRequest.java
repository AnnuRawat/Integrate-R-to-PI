
package com.osisoft.xml.services.pidataservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPISummaryDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPISummaryDataRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PISummaryDataRequest" type="{http://xml.osisoft.com/services/PIDataService}PISummaryDataRequest" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPISummaryDataRequest", propOrder = {
    "piSummaryDataRequest"
})
public class ArrayOfPISummaryDataRequest {

    @XmlElement(name = "PISummaryDataRequest", nillable = true)
    protected List<PISummaryDataRequest> piSummaryDataRequest;

    /**
     * Gets the value of the piSummaryDataRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the piSummaryDataRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPISummaryDataRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PISummaryDataRequest }
     * 
     * 
     */
    public List<PISummaryDataRequest> getPISummaryDataRequest() {
        if (piSummaryDataRequest == null) {
            piSummaryDataRequest = new ArrayList<PISummaryDataRequest>();
        }
        return this.piSummaryDataRequest;
    }

}
