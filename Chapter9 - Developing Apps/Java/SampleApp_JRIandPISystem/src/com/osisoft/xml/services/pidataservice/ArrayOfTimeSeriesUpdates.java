
package com.osisoft.xml.services.pidataservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTimeSeriesUpdates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTimeSeriesUpdates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeSeriesUpdates" type="{http://xml.osisoft.com/services/PIDataService}TimeSeriesUpdates" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTimeSeriesUpdates", propOrder = {
    "timeSeriesUpdates"
})
public class ArrayOfTimeSeriesUpdates {

    @XmlElement(name = "TimeSeriesUpdates", nillable = true)
    protected List<TimeSeriesUpdates> timeSeriesUpdates;

    /**
     * Gets the value of the timeSeriesUpdates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeSeriesUpdates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeSeriesUpdates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeSeriesUpdates }
     * 
     * 
     */
    public List<TimeSeriesUpdates> getTimeSeriesUpdates() {
        if (timeSeriesUpdates == null) {
            timeSeriesUpdates = new ArrayList<TimeSeriesUpdates>();
        }
        return this.timeSeriesUpdates;
    }

}
