import java.awt.*;

/**
 * The Photograph class represents a photograph taken during a flight at a specific angle (theta).
 * Photographs can be created, displayed, and adjusted for visibility.
 */
public class Photograph {
    private String flightColor;
    private double theta; // Ángulo en radianes
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
        this.theta = theta;
        isVisible = false;
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
     * Make the photograph visible.
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Draw the photograph on the canvas.
     * The photograph is drawn as a trapezoid shape representing the field of view.
     * The opacity of the photograph is adjusted based on the flight's color.
     */
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            Flight flight = Flight.getFlightByColor(flightColor); // Obtén el vuelo actual

            if (flight != null) {
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

                int[] xPoints = {x4, x6, x5, x3};
                int[] yPoints = {y1, y2, y4, y3};

                // Get the color of the flight and adjust the opacity
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
}
