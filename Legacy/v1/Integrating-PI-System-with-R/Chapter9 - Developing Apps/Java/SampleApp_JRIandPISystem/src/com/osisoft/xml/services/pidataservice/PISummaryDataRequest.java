
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PISummaryDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PISummaryDataRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xml.osisoft.com/services/PIDataService}PIDataRequest">
 *       &lt;sequence>
 *         &lt;element name="PISummaryManner" type="{http://xml.osisoft.com/services/PIDataService}PISummaryManner"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PISummaryDataRequest", propOrder = {
    "piSummaryManner"
})
public class PISummaryDataRequest
    extends PIDataRequest
{

    @XmlElement(name = "PISummaryManner", required = true, nillable = true)
    protected PISummaryManner piSummaryManner;

    /**
     * Gets the value of the piSummaryManner property.
     * 
     * @return
     *     possible object is
     *     {@link PISummaryManner }
     *     
     */
    public PISummaryManner getPISummaryManner() {
        return piSummaryManner;
    }

    /**
     * Sets the value of the piSummaryManner property.
     * 
     * @param value
     *     allowed object is
     *     {@link PISummaryManner }
     *     
     */
    public void setPISummaryManner(PISummaryManner value) {
        this.piSummaryManner = value;
    }

}
