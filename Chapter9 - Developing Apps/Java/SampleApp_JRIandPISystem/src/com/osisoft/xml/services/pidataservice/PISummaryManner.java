
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PISummaryManner complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PISummaryManner">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xml.osisoft.com/services/PIDataService}PIManner">
 *       &lt;attribute name="SummaryValue" default="Average">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Average"/>
 *             &lt;enumeration value="Minimum"/>
 *             &lt;enumeration value="Maximum"/>
 *             &lt;enumeration value="Range"/>
 *             &lt;enumeration value="StdDev"/>
 *             &lt;enumeration value="Total"/>
 *             &lt;enumeration value="PStdDev"/>
 *             &lt;enumeration value="Count"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Intervals" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="WeightType" default="TimeWeighted">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="TimeWeighted"/>
 *             &lt;enumeration value="EventWeighted"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="UseStart" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PISummaryManner")
public class PISummaryManner
    extends PIManner
{

    @XmlAttribute(name = "SummaryValue")
    protected String summaryValue;
    @XmlAttribute(name = "Intervals", required = true)
    protected int intervals;
    @XmlAttribute(name = "WeightType")
    protected String weightType;
    @XmlAttribute(name = "UseStart")
    protected Boolean useStart;

    /**
     * Gets the value of the summaryValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryValue() {
        if (summaryValue == null) {
            return "Average";
        } else {
            return summaryValue;
        }
    }

    /**
     * Sets the value of the summaryValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryValue(String value) {
        this.summaryValue = value;
    }

    /**
     * Gets the value of the intervals property.
     * 
     */
    public int getIntervals() {
        return intervals;
    }

    /**
     * Sets the value of the intervals property.
     * 
     */
    public void setIntervals(int value) {
        this.intervals = value;
    }

    /**
     * Gets the value of the weightType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeightType() {
        if (weightType == null) {
            return "TimeWeighted";
        } else {
            return weightType;
        }
    }

    /**
     * Sets the value of the weightType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeightType(String value) {
        this.weightType = value;
    }

    /**
     * Gets the value of the useStart property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isUseStart() {
        if (useStart == null) {
            return false;
        } else {
            return useStart;
        }
    }

    /**
     * Sets the value of the useStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseStart(Boolean value) {
        this.useStart = value;
    }

}
