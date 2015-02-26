
package org.example;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BookFlightError_QNAME = new QName("http://www.example.org", "bookFlightError");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ErrorType }
     * 
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link BookFlightOutput }
     * 
     */
    public BookFlightOutput createBookFlightOutput() {
        return new BookFlightOutput();
    }

    /**
     * Create an instance of {@link FlightInfoType }
     * 
     */
    public FlightInfoType createFlightInfoType() {
        return new FlightInfoType();
    }

    /**
     * Create an instance of {@link BookFlightInput }
     * 
     */
    public BookFlightInput createBookFlightInput() {
        return new BookFlightInput();
    }

    /**
     * Create an instance of {@link TripDataType }
     * 
     */
    public TripDataType createTripDataType() {
        return new TripDataType();
    }

    /**
     * Create an instance of {@link PersonDataType }
     * 
     */
    public PersonDataType createPersonDataType() {
        return new PersonDataType();
    }

    /**
     * Create an instance of {@link HotelInfoType }
     * 
     */
    public HotelInfoType createHotelInfoType() {
        return new HotelInfoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org", name = "bookFlightError")
    public JAXBElement<ErrorType> createBookFlightError(ErrorType value) {
        return new JAXBElement<ErrorType>(_BookFlightError_QNAME, ErrorType.class, null, value);
    }

}
