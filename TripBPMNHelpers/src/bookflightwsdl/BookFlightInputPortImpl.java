package bookflightwsdl;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import java.util.Random;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.BindingType;
import javax.xml.ws.FaultAction;
import javax.xml.ws.soap.SOAPBinding;

import org.example.BookFlightInput;
import org.example.BookFlightOutput;
import org.example.FlightInfoType;
import org.example.HotelInfoType;
import org.example.ObjectFactory;
import org.example.TripDataType;

@WebService(name = "bookFlightInputPort", targetNamespace = "urn:BookFlightWSDL", serviceName = "bookFlightService", portName = "bookFlightPort", wsdlLocation = "/WEB-INF/wsdl/BookFlightWSDL.wsdl")
@XmlSeeAlso( { ObjectFactory.class })
@javax.jws.soap.SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT, parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.BARE)
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class BookFlightInputPortImpl {
    private LinkedList<Reservation> reservations = new LinkedList<Reservation>();
    private LinkedList<FlightInfoType> revokedReservations = new LinkedList<FlightInfoType>();
    
    public BookFlightInputPortImpl() {
    }

    @javax.jws.soap.SOAPBinding(parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.BARE)
    @Action(input = "urn:BookFlightWSDL/bookFlightOperation", output = "urn:BookFlightWSDL/bookFlightInputPort/bookFlightOperationResponse", fault = { @FaultAction(className =
                        BookFlightErrorMessage.class, value = "urn:BookFlightWSDL/bookFlightInputPort/bookFlightOperation/Fault/bookFlightError") })
    @WebMethod(action = "urn:BookFlightWSDL/bookFlightOperation")
    @WebResult(name = "bookFlightOutput", targetNamespace = "http://www.example.org", partName = "bookFlightOutputMessagePart")
    public BookFlightOutput bookFlightOperation(@WebParam(name = "bookFlightInput", partName = "bookFlightInputMessagePart", targetNamespace = "http://www.example.org")
        BookFlightInput bookFlightInputMessagePart) throws BookFlightErrorMessage {
        TripDataType tripData = bookFlightInputMessagePart.getTripData();
                    
        if(tripData.getStartDate().toGregorianCalendar().compareTo(tripData.getEndDate().toGregorianCalendar()) > 0) {
            throwErrorMessage("End date cannot be lower than start date.", 1); 
        }
        
        if(tripData.getStartDate().toGregorianCalendar().compareTo(GregorianCalendar.getInstance()) < 0) {
            throwErrorMessage("Start date cannot be lower than today.", 2);
        }
        
        String reservationNumber = String.format("FLR-%04d", reservations.size() + 1);
        String flightNumber = String.format("XF-%02d", randInt(1, 99));
        
        reservations.add(new Reservation(tripData.getClientData().getFirstName(), 
                                         tripData.getClientData().getLastName(),
                                         tripData.getClientData().getPESEL(),
                                         tripData.getStartDate().toGregorianCalendar(),
                                         tripData.getEndDate().toGregorianCalendar(),
                                         tripData.getDestinationName(),
                                         reservationNumber,
                                         flightNumber));
        
        return generateOutput(tripData.getStartDate().toGregorianCalendar(),
                              tripData.getEndDate().toGregorianCalendar(),
                              tripData.getDestinationName(),
                              reservationNumber,
                              flightNumber);
    }

    @javax.jws.soap.SOAPBinding(parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.BARE)
    @Action(input = "urn:BookFlightWSDL/bookFlightCompensateOperation")
    @WebMethod(action = "urn:BookFlightWSDL/bookFlightCompensateOperation")
    @Oneway
    public void bookFlightCompensateOperation(@WebParam(name = "bookFlightOutput", partName = "bookFlightOutputMessagePart", targetNamespace = "http://www.example.org")
        BookFlightOutput bookFlightOutputMessagePart) {
        revokedReservations.add(bookFlightOutputMessagePart.getFlightInfo());
    }
    
    private BookFlightOutput generateOutput(GregorianCalendar startDate, GregorianCalendar endDate, String destinationName, String reservationNumber, String flightNumber) {
        BookFlightOutput output = new BookFlightOutput();
        
        long diff = (endDate.getTime().getTime() - startDate.getTime().getTime()) / (24*60*60*1000);
        if(diff > 14) {
            output.setResult(false);            
        } else {
            FlightInfoType info = new FlightInfoType();
            info.setReservationNumber(reservationNumber);
            info.setFlightNumber(flightNumber);

            output.setResult(true);
            output.setFlightInfo(info);
        }

        return output;
    }
    
    private void throwErrorMessage(String message, int code) throws BookFlightErrorMessage {
        org.example.ErrorType error = new org.example.ErrorType();
        error.setCode(code);
        error.setMessage(message);
        
        throw new BookFlightErrorMessage(message, error);
    }
    
    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    class Reservation {
        public String firstName;
        public String lastName;
        public String PESEL;
        public GregorianCalendar startDate;
        public GregorianCalendar endDate;
        public String destinationName;
        public String reservationNumber;
        public String flightNumber;
        
        public Reservation(String firstName, String lastName, String PESEL,GregorianCalendar startDate, GregorianCalendar endDate, 
                           String destinationName, String reservationNumber, String flightNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.PESEL = PESEL;
            this.startDate = startDate;
            this.endDate = endDate;
            this.destinationName = destinationName;
            this.reservationNumber = reservationNumber;
            this.flightNumber = flightNumber;
        }
    }
}
