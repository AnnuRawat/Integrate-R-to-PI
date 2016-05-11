
package com.osisoft.xml.services.pidataservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTimedValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTimedValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimedValue" type="{http://xml.osisoft.com/services/PIDataService}TimedValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTimedValue", propOrder = {
    "timedValue"
})
public class ArrayOfTimedValue {

    @XmlElement(name = "TimedValue", nillable = true)
    protected List<TimedValue> timedValue;

    /**
     * Gets the value of the timedValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timedValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimedValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimedValue }
     * 
     * 
     */
    public List<TimedValue> getTimedValue() {
        if (timedValue == null) {
            timedValue = new ArrayList<TimedValue>();
        }
        return this.timedValue;
    }

}
