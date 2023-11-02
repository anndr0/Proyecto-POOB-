package ICPC;
/**
 * This custom exception is used to handle specific errors related to the Iceepeecee class.
 * It can be thrown when exceptional conditions occur during the execution of methods in the Iceepeecee class.
 * 
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */

public class IceepeeceeException extends Exception {
public static final String ALREADY_EXISTS_ISLAND = "Isla ya existe en Iceepeecee";
public static final String ALREADY_EXISTS_FLIGHT = "Vuelo ya existe en Iceepeecee";
public static final String ISLAND_TYPE_UNKNOWN = "Este tipo de isla no existe";
public static final String FLIGHT_TYPE_UNKNOWN = "Este tipo de vuelo no existe";
public static final String OUT_OF_BOUNDS = "Isla o vuelo se sale de los limites del canvas";
public static final String NO_PHOTOS_IN_FLIGHT = "Este vuelo no tiene fotos";
public static final String NO_FLIGHT_FOUND = "Vuelo no encontrado";
public static final String ISLAND_NO_FOUND = "No se encontró la isla identificada con este color";
public static final String CANT_DELETE_FIXED_ISLAND = "No se puede eliminar una Fixed island";
public static final String NO_ISLANDS = "No existen islas en Iceepeecee";
public static final String NO_FLIGHTS = "No existen vuelos en Iceepeecee";
public static final String NOT_VALID_VALUES = "La longitud y el ancho deben ser valores positivos.";
public static final String ALREADY_EXISTS_COLOR_ISLAND = "Una isla ya tiene este color";
public static final String ALREADY_EXISTS_COLOR_FLIGHT = "Un vuelo ya tiene este color";




    /**
     * Constructor for the IceepeeceeException.
     *
     * @param message The error message describing the reason for the exception.
     */
    public IceepeeceeException(String message) {
        super(message);
    }
}