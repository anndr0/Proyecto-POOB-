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
        isVisible = false;
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
            double z1 = from[2];
            double z2 = to[2];

            // Calcular el ángulo perpendicular
            double perpendicularAngle = Math.atan2(to[1] - from[1], to[0] - from[0]) + Math.PI / 2;
            double baseMenor = z1 * Math.tan(theta);
            double baseMayor = z2 * Math.tan(theta);

            // Calcular las coordenadas de los vértices perpendiculares al vuelo
            double x1 = from[0] + (baseMenor * Math.cos(perpendicularAngle));
            double y1 = from[1] + (baseMenor * Math.sin(perpendicularAngle));
            
            double x2 = to[0] + (baseMayor * Math.cos(perpendicularAngle));
            double y2 = to[1] + (baseMayor * Math.sin(perpendicularAngle));

            // Calcular las otras dos coordenadas
            double x3 = to[0] + (baseMayor * Math.cos(perpendicularAngle + Math.PI));
            double y3 = to[1] + (baseMayor * Math.sin(perpendicularAngle + Math.PI));

            double x4 = from[0] + (baseMenor * Math.cos(perpendicularAngle + Math.PI));
            double y4 = from[1] + (baseMenor * Math.sin(perpendicularAngle + Math.PI));

            int[] xPoints = {(int) x1, (int) x2, (int) x3, (int) x4};
            int[] yPoints = {(int) y1, (int) y2, (int) y3, (int) y4};

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
     * Get the angle (theta) of the photograph in degrees.
     *
     * @return The angle (theta) of the photograph in degrees.
     */
    public double getThetaDegrees() {
        return Math.toDegrees(theta);
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
                double x1 = from[0] + (baseMenor * Math.cos(perpendicularAngle));
                double y1 = from[1] + (baseMenor * Math.sin(perpendicularAngle));
                
                double x2 = to[0] + (baseMayor * Math.cos(perpendicularAngle));
                double y2 = to[1] + (baseMayor * Math.sin(perpendicularAngle));
    
                // Calcular las otras dos coordenadas
                double x3 = to[0] + (baseMayor * Math.cos(perpendicularAngle + Math.PI));
                double y3 = to[1] + (baseMayor * Math.sin(perpendicularAngle + Math.PI));
    
                double x4 = from[0] + (baseMenor * Math.cos(perpendicularAngle + Math.PI));
                double y4 = from[1] + (baseMenor * Math.sin(perpendicularAngle + Math.PI));
    
                // Redondear las coordenadas según la precisión deseada
                double precision = 1e-6; // Puedes ajustar esto según la precisión necesaria
                x1 = Math.round(x1 / precision) * precision;
                y1 = Math.round(y1 / precision) * precision;
                x2 = Math.round(x2 / precision) * precision;
                y2 = Math.round(y2 / precision) * precision;
                x3 = Math.round(x3 / precision) * precision;
                y3 = Math.round(y3 / precision) * precision;
                x4 = Math.round(x4 / precision) * precision;
                y4 = Math.round(y4 / precision) * precision;
    
                vertices.add(new Point((int)x1, (int)y1));
                vertices.add(new Point((int)x2, (int)y2));
                vertices.add(new Point((int)x3, (int)y3));
                vertices.add(new Point((int)x4, (int)y4));
            }
        }
        return vertices;
    }

}
