package ICPC;
/**
 * This custom exception is used to handle specific errors related to the Iceepeecee class.
 * It can be thrown when exceptional conditions occur during the execution of methods in the Iceepeecee class.
 * 
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */

public class IceepeeceeException extends Exception {
public static final String ALREADY_EXISTS = "Isla o vuelo ya existe en Iceepeecee";
public static final String TYPE_UNKNOWN = "Este tipo no existe";
public static final String OUT_OF_BOUNDS = "Isla o vuelo se sale de los limites del canvas";
public static final String NO_PHOTOS_IN_FLIGHT = "Este vuelo no tiene fotos";
public static final String NO_FLIGHT_FOUND = "Vuelo no encontrado";
public static final String CANT_DELETE_FIXED_ISLAND = "No se puede eliminar una Fixed island";
    /**
     * Constructor for the IceepeeceeException.
     *
     * @param message The error message describing the reason for the exception.
     */
    public IceepeeceeException(String message) {
        super(message);
    }
}