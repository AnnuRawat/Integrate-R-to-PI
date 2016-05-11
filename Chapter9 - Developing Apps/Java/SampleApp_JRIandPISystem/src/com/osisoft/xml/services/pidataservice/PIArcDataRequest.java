
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PIArcDataRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PIArcDataRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xml.osisoft.com/services/PIDataService}PIDataRequest">
 *       &lt;sequence>
 *         &lt;element name="PIArcManner" type="{http://xml.osisoft.com/services/PIDataService}PIArcManner"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PIArcDataRequest", propOrder = {
    "piArcManner"
})
public class PIArcDataRequest
    extends PIDataRequest
{

    @XmlElement(name = "PIArcManner", required = true, nillable = true)
    protected PIArcManner piArcManner;

    /**
     * Gets the value of the piArcManner property.
     * 
     * @return
     *     possible object is
     *     {@link PIArcManner }
     *     
     */
    public PIArcManner getPIArcManner() {
        return piArcManner;
    }

    /**
     * Sets the value of the piArcManner property.
     * 
     * @param value
     *     allowed object is
     *     {@link PIArcManner }
     *     
     */
    public void setPIArcManner(PIArcManner value) {
        this.piArcManner = value;
    }

}
