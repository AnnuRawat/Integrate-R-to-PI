
package com.osisoft.xml.services.pidataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimeSeriesUpdates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeSeriesUpdates">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xml.osisoft.com/services/PIDataService}TimeSeries">
 *       &lt;sequence>
 *         &lt;element name="Updates" type="{http://xml.osisoft.com/services/PIDataService}ArrayOfTimedValueUpdate"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeSeriesUpdates", propOrder = {
    "updates"
})
public class TimeSeriesUpdates
    extends TimeSeries
{

    @XmlElement(name = "Updates", required = true, nillable = true)
    protected ArrayOfTimedValueUpdate updates;

    /**
     * Gets the value of the updates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTimedValueUpdate }
     *     
     */
    public ArrayOfTimedValueUpdate getUpdates() {
        return updates;
    }

    /**
     * Sets the value of the updates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTimedValueUpdate }
     *     
     */
    public void setUpdates(ArrayOfTimedValueUpdate value) {
        this.updates = value;
    }

}
