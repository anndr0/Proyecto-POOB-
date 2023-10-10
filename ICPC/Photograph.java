import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Photograph class represents a photograph taken during a flight at a specific angle (theta).
 * Photographs can be created, displayed, and adjusted for visibility.
 * 
 * @author [Your Name]
 * @version [Date]
 */
public class Photograph {

    private Flight flight;
    private String flightColor; // To avoid naming conflicts
    private double theta; // Angle in radians
    private boolean isVisible;
    private boolean operationSuccess;

    /**
     * Constructs a Photograph object associated with a flight color and angle (theta).
     * The photograph is initially visible.
     * 
     * @param flightColor The color of the flight associated with this photograph.
     * @param theta       The angle (in radians) at which the photograph is taken.
     */
    public Photograph(String flightColor, double theta) {
        this.flightColor = flightColor;
        this.theta = Math.toRadians(theta);
        isVisible = true;
        this.flight = Flight.getFlightByColor(flightColor);
        
        // Determine if the photograph should be initially visible based on the flight's visibility
        if (flight != null && flight.isVisible) {
            isVisible = true;
        } else {
            isVisible = false;
        }
    
        // Add the photograph to the flight's list of photographs
        if (flight != null) {
            flight.getPhotographs().add(this);
        }
    
        // Draw the photograph
        draw();
    }
    
    /**
     * Make the photograph invisible.
     */
    public void makeInvisible() {
        isVisible = false;
        Canvas canvas = Canvas.getCanvas();
        canvas.erase(this);
    }

    /**
     * Make the photograph invisible.
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    public boolean isVisible(){
        return isVisible;
    }
    
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            Flight flight = Flight.getFlightByColor(flightColor);

            if (flight != null) {
                int[] from = flight.getFrom();
                int[] to = flight.getTo();
                int z1 = from[2];
                int z2 = to[2];
                
                // Calcular el ángulo perpendicular
                double perpendicularAngle = Math.atan2(to[1] - from[1], to[0] - from[0]) + Math.PI / 2;
                double baseMenor = z1 * Math.tan(theta);
                double baseMayor = z2 * Math.tan(theta);

                // Calcular las coordenadas de los vértices perpendiculares al vuelo
                int x1 = from[0] + (int) (baseMenor * Math.cos(perpendicularAngle));
                int y1 = from[1] + (int) (baseMenor * Math.sin(perpendicularAngle));
                
                int x2 = to[0] + (int) (baseMayor * Math.cos(perpendicularAngle));
                int y2 = to[1] + (int) (baseMayor * Math.sin(perpendicularAngle));

                // Calcular las otras dos coordenadas
                int x3 = to[0] + (int) (baseMayor * Math.cos(perpendicularAngle + Math.PI));
                int y3 = to[1] + (int) (baseMayor * Math.sin(perpendicularAngle + Math.PI));

                int x4 = from[0] + (int) (baseMenor * Math.cos(perpendicularAngle + Math.PI));
                int y4 = from[1] + (int) (baseMenor * Math.sin(perpendicularAngle + Math.PI));

                int[] xPoints = {x1, x2, x3, x4};
                int[] yPoints = {y1, y2, y3, y4};

                String flightColor = flight.getColor();
                canvas.draw(this, flightColor, new java.awt.Polygon(xPoints, yPoints, 4), 80);
            }
        }
    }
    
    /**
     * Get the angle (theta) of the photograph.
     * 
     * @return The angle (theta) of the photograph.
     */
    public double getTheta() {
        this.theta = theta;
        return theta;
    }
    /**
     * Set the angle (theta) of the photograph.
     * 
     * @param theta The new angle (theta) of the photograph.
     */
    public double setTheta() {
        this.theta = theta;
        return theta;
    }

    /**
     * Verifies if the last operation in the makeVisible function was successful.
     * 
     * @return true if the last operation was successful, false otherwise.
     */
    public boolean ok() {
        return operationSuccess;
    }
    
    public List<Point> getVertices() {
        List<Point> vertices = new ArrayList<>();

        if (isVisible) {
            int[] from = flight.getFrom();
            int[] to = flight.getTo();
            int z1 = from[2];
            int z2 = to[2];

            // Calculate the bases of the trapezoid
            double baseMenor = z1 * Math.tan(theta);
            double baseMayor = z2 * Math.tan(theta);

            // Calculate the vertices of the trapezoid
            int x1 = from[0];
            int x2 = to[0];
            int y1 = from[1];
            int y2 = to[1];

            int x3 = x1 + (int) baseMenor;
            int x4 = x1 - (int) baseMenor;
            
            int x5 = x2 + (int) baseMayor;
            int x6 = x2 - (int) baseMayor;
            
            int y3 = y1;
            int y4 = y2;

            vertices.add(new Point(x4, y1));
            vertices.add(new Point(x6, y2));
            vertices.add(new Point(x5, y4));
            vertices.add(new Point(x3, y3));
        }

        return vertices;
    }
}
