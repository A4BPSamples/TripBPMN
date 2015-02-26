
package org.example;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tripData" type="{http://www.example.org}tripDataType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tripData"
})
@XmlRootElement(name = "bookFlightInput")
public class BookFlightInput {

    @XmlElement(required = true)
    protected TripDataType tripData;

    /**
     * Gets the value of the tripData property.
     * 
     * @return
     *     possible object is
     *     {@link TripDataType }
     *     
     */
    public TripDataType getTripData() {
        return tripData;
    }

    /**
     * Sets the value of the tripData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TripDataType }
     *     
     */
    public void setTripData(TripDataType value) {
        this.tripData = value;
    }

}
