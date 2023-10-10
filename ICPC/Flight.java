import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * The Flight class represents a flight with coordinates, color, and visibility.
 * Flights can be created, displayed, and manipulated.
 * They can be made visible or invisible, and photographs can be taken during flights.
 * 
 * @author [Your Name]
 * @version [Date]
 */

public class Flight {
    
    private int[] from; // Starting coordinates [x1, y1, z1]
    private int[] to;   // Ending coordinates [x2, y2, z2]
    private String color;
    public boolean isVisible;
    private static HashMap<String, Flight> flights = new HashMap<>();
    private List<Photograph> photographs;
    private boolean operationSuccess;
    private static Set<String> usedColors = new HashSet<>();
    
    /**
     * Constructs a Flight object with the specified color and coordinates.
     * The flight is initially visible.
     * 
     * @param color The color of the flight.
     * @param from  The starting coordinates [x1, y1, z1].
     * @param to    The ending coordinates [x2, y2, z2].
     */
    public Flight(String color, int[] from, int[] to) {
        if (usedColors.contains(color)) {
            throw new IllegalArgumentException("El color " + color + " ya se ha utilizado para otra isla.");
        }
        this.color = color;
        this.from = from;
        this.to = to;
        isVisible = true;
        photographs = new ArrayList<>();
        flights.put(color, this);
        draw();
    }
    
    public boolean isVisible(){
        return isVisible;
    }
    
    /**
     * Get the starting coordinates of the flight.
     * 
     * @return An array containing the starting coordinates [x1, y1, z1].
     */
    public int[] getFrom() {
        return from;
    }
    
    /**
     * Get the ending coordinates of the flight.
     * 
     * @return An array containing the ending coordinates [x2, y2, z2].
     */
    public int[] getTo() {
        return to;
    }
    
    /**
     * Get the color of the flight.
     * 
     * @return The color of the flight.
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Set the color of the flight.
     * 
     * @param newColor The new color for the flight.
     */
    public void setColor(String newColor) {
        color = newColor;
    }
    
    /**
     * Make the flight visible by its color.
     * 
     * @param color The color of the flight to make visible.
     */
    public void makeFlightVisible(String color) {
        Flight flight = flights.get(color);
        if (flight != null) {
            flight.isVisible = true;
            flight.draw();
        }
    }
    
    /**
     * Make the flight invisible by its color.
     * 
     * @param color The color of the flight to make invisible.
     */
    public void makeFlightInvisible(String color) {
        Flight flight = flights.get(color);
        if (flight != null) {
            flight.isVisible = false;
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(flight);
        }
    }

    /**
     * Draw the flight on the canvas.
     */
    public void draw() {
        int[] from = getFrom();
        int[] to = getTo();
        int[] xPoints = {from[0], to[0]};
        int[] yPoints = {from[1], to[1]};
        Canvas canvas = Canvas.getCanvas();
        canvas.draw(this, color, new java.awt.Polygon(xPoints, yPoints, 2), 255);
    }
        
    /**
     * Delete the flight by its color.
     * 
     * @param color The color of the flight to delete.
     */
    public void deleteFlight(String color) {
        Flight flight = flights.get(color);
        if (flight != null) {
            flight.makeFlightInvisible(color);
            flights.remove(color);
        }
    }
    
    /**
     * Clear all the photographs taken during the flight, making previous ones invisible and keeping the last one registered.
     */
    public void clearPhotographs() {
        // Make previous photographs invisible
        for (int i = 0; i < photographs.size() - 1; i++) {
            photographs.get(i).makeInvisible();
        }
    
        // Clear all photographs except the last one
        if (photographs.size() > 0) {
            photographs.subList(0, photographs.size() - 1).clear();
        }
    }


    /**
     * Capture a photograph during the flight with a specified angle.
     * 
     * @param color The color of the flight.
     * @param theta  The angle at which the photograph is taken.
     */
    public void camera(String color, double theta) {
        Photograph photograph = new Photograph(color, theta);
        // Reemplazar la última fotografía si ya existía una
        if (!photographs.isEmpty()) {
            clearPhotographs();
        }
        photographs.add(photograph);
    }

    
    
    /**
     * Get a flight by its color.
     * 
     * @param color The color of the flight to retrieve.
     * @return The Flight object with the specified color.
     */
    public static Flight getFlightByColor(String color) {
        return flights.get(color);
    }
    
    /**
     * Get the list of photographs taken during the flight.
     * 
     * @return A list of photographs taken during the flight.
     */
    public List<Photograph> getPhotographs() {
        return photographs;
    }
    
    /**
     * Get the location of the flight by its color.
     * 
     * @param color The color of the flight to query.
     * @return A string representing the location of the flight.
     */
    public int[][] locationFlight(String color) {
        Flight flight = Flight.getFlightByColor(color);
        if (flight != null) {
            int[] from = flight.getFrom();
            int[] to = flight.getTo();
    
            int[][] location = {from, to};
        return location;
        } else {
            return null;
        }
    }
    
    /**
     * Calculate the distance between the starting and ending points of the flight.
     * 
     * @return The distance between the starting and ending points.
     */
    public double getDistance() {
        int dx = to[0] - from[0];
        int dy = to[1] - from[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    /**
     * Verifica si la última operación en la simulación fue exitosa.
     *
     * @return true si la última operación fue exitosa, false si no lo fue.
     */
    public boolean ok() {
        return operationSuccess;
    }
    
    /**
     * Get the angle (theta) of the last photograph taken by the flight.
     * 
     * @param color The color of the flight to query.
     * @return The angle (theta) of the last photograph taken by the flight.
     */
    public double flightCamera(String color) {
        Flight flight = flights.get(color);
        if (flight != null) {
            List<Photograph> photographs = flight.getPhotographs();
            if (!photographs.isEmpty()) {
                // Obtener la última fotografía de la lista de fotografías
                Photograph lastPhotograph = photographs.get(photographs.size() - 1);
                return lastPhotograph.getTheta();
            }
        }
        // Devolver un valor predeterminado (puedes cambiarlo según tus necesidades)
        return 0.0;
    }
    
        /**
     * Imprime la información de todas las fotografías tomadas durante el vuelo.
     */
    public void printPhotographs() {
        System.out.println("Fotografías tomadas durante el vuelo de color " + color + ":");
        for (Photograph photograph : photographs) {
            System.out.println("Ángulo (theta): " + photograph.getTheta());
            // Aquí puedes agregar más información sobre la fotografía si es necesario
        }
    }

    
}