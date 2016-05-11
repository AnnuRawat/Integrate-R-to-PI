
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PIArcManner complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PIArcManner">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xml.osisoft.com/services/PIDataService}PIManner">
 *       &lt;attribute name="RetrievalType" default="Compressed">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Compressed"/>
 *             &lt;enumeration value="Interpolated"/>
 *             &lt;enumeration value="PlotValues"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="NumValues" type="{http://www.w3.org/2001/XMLSchema}int" default="400" />
 *       &lt;attribute name="Boundaries" default="Inside">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Inside"/>
 *             &lt;enumeration value="Outside"/>
 *             &lt;enumeration value="Interpolated"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PIArcManner")
public class PIArcManner
    extends PIManner
{

    @XmlAttribute(name = "RetrievalType")
    protected String retrievalType;
    @XmlAttribute(name = "NumValues")
    protected Integer numValues;
    @XmlAttribute(name = "Boundaries")
    protected String boundaries;

    /**
     * Gets the value of the retrievalType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetrievalType() {
        if (retrievalType == null) {
            return "Compressed";
        } else {
            return retrievalType;
        }
    }

    /**
     * Sets the value of the retrievalType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetrievalType(String value) {
        this.retrievalType = value;
    }

    /**
     * Gets the value of the numValues property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getNumValues() {
        if (numValues == null) {
            return  400;
        } else {
            return numValues;
        }
    }

    /**
     * Sets the value of the numValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumValues(Integer value) {
        this.numValues = value;
    }

    /**
     * Gets the value of the boundaries property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoundaries() {
        if (boundaries == null) {
            return "Inside";
        } else {
            return boundaries;
        }
    }

    /**
     * Sets the value of the boundaries property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoundaries(String value) {
        this.boundaries = value;
    }

}
