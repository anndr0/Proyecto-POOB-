package ICPC;
/**
 * This custom exception is used to handle specific errors related to the Iceepeecee class.
 * It can be thrown when exceptional conditions occur during the execution of methods in the Iceepeecee class.
 * 
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */

public class IceepeeceeException extends Exception 
{
    /**
     * Constructor for the IceepeeceeException.
     *
     * @param message The error message describing the reason for the exception.
     */
    public IceepeeceeException(String message) {
        super(message);
    }
}