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
    private Photograph lastPhotograph;
    
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
     * Capture a photograph during the flight with a specified angle.
     * 
     * @param color The color of the flight.
     * @param theta The angle at which the photograph is taken.
     */
    public void camera(String color, double theta) {
        if (lastPhotograph != null) {
            lastPhotograph.makeInvisible(); // Hacer invisible la última fotografía
        }
        lastPhotograph = new Photograph(color, theta);
        photographs.add(lastPhotograph);
        lastPhotograph.makeVisible(); // Hacer visible la nueva fotografía
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
                Photograph lastPhotograph = photographs.get(photographs.size() - 1);
                return lastPhotograph.getTheta();
            } else {
                System.out.println("No photographs taken for this flight.");
            }
        } else {
            System.out.println("Flight not found");
        }
        return 0.0;
    }
}
