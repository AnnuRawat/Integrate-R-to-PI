
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimeSeries complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeSeries">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xml.osisoft.com/services/PIDataService}DataItem">
 *       &lt;sequence>
 *         &lt;element name="TimedValues" type="{http://xml.osisoft.com/services/PIDataService}ArrayOfTimedValue"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ErrDesc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Error" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="SeriesID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DataType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UOM" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeSeries", propOrder = {
    "timedValues"
})
@XmlSeeAlso({
    TimeSeriesUpdates.class
})
public class TimeSeries
    extends DataItem
{

    @XmlElement(name = "TimedValues", required = true, nillable = true)
    protected ArrayOfTimedValue timedValues;
    @XmlAttribute(name = "Path")
    protected String path;
    @XmlAttribute(name = "ErrDesc")
    protected String errDesc;
    @XmlAttribute(name = "Error", required = true)
    protected int error;
    @XmlAttribute(name = "SeriesID")
    protected String seriesID;
    @XmlAttribute(name = "DataType")
    protected String dataType;
    @XmlAttribute(name = "UOM")
    protected String uom;

    /**
     * Gets the value of the timedValues property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTimedValue }
     *     
     */
    public ArrayOfTimedValue getTimedValues() {
        return timedValues;
    }

    /**
     * Sets the value of the timedValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTimedValue }
     *     
     */
    public void setTimedValues(ArrayOfTimedValue value) {
        this.timedValues = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

    /**
     * Gets the value of the errDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrDesc() {
        return errDesc;
    }

    /**
     * Sets the value of the errDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrDesc(String value) {
        this.errDesc = value;
    }

    /**
     * Gets the value of the error property.
     * 
     */
    public int getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     */
    public void setError(int value) {
        this.error = value;
    }

    /**
     * Gets the value of the seriesID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeriesID() {
        return seriesID;
    }

    /**
     * Sets the value of the seriesID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeriesID(String value) {
        this.seriesID = value;
    }

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataType(String value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the uom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOM() {
        return uom;
    }

    /**
     * Sets the value of the uom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOM(String value) {
        this.uom = value;
    }

}
