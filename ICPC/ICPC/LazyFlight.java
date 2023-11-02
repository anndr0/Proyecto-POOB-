package ICPC;
import javax.swing.JOptionPane;
import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
/**
 * The LazyFlight class represents a flight that can draw an arrow and take a photograph.
 * This class extends the Flight class.
 * 
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class LazyFlight extends Flight {
    
    private boolean hasTakenPhotograph;
    
    /**
     * Constructs a LazyFlight object with the specified color and coordinates.
     * The flight is initially visible and has not taken a photograph.
     * 
     * @param color The color of the flight.
     * @param from  The starting coordinates [x1, y1, z1].
     * @param to    The ending coordinates [x2, y2, z2].
     */
    public LazyFlight(String color, int[] from, int[] to) throws IceepeeceeException {
        super(color, from, to);
        hasTakenPhotograph = false; // Inicialmente, no se ha tomado ninguna fotografía
    }
    
    /**
     * Draw the flight on the canvas as an arrow.
     */
    @Override
    public void draw() {
        int[] from = getFrom();
        int[] to = getTo();
        Shapes.Canvas canvas = Shapes.Canvas.getCanvas();

        Path2D.Double arrowPath = new Path2D.Double();
        arrowPath.append(new Line2D.Double(from[0], from[1], to[0], to[1]), false);

        double arrowSize = 10.0;
        double angle = Math.atan2(to[1] - from[1], to[0] - from[0]);
        double x1 = to[0] - arrowSize * Math.cos(angle - Math.PI / 6);
        double y1 = to[1] - arrowSize * Math.sin(angle - Math.PI / 6);
        double x2 = to[0];
        double y2 = to[1];
        double x3 = to[0] - arrowSize * Math.cos(angle + Math.PI / 6);
        double y3 = to[1] - arrowSize * Math.sin(angle + Math.PI / 6);

        arrowPath.moveTo(x1, y1);
        arrowPath.lineTo(x2, y2);
        arrowPath.lineTo(x3, y3);

        canvas.draw(this, color, arrowPath, 255);
    }
    
    /**
     * Take a photograph during the flight with a specified color and theta angle.
     * Only one photograph can be taken per flight.
     * 
     * @param color The color of the photograph.
     * @param theta The angle (theta) of the photograph.
     */
    @Override
    public void camera(String color, double theta) {
        if (!hasTakenPhotograph) { // Verifica si no se ha tomado una fotografía previamente
            Photograph photograph = new Photograph(color, theta);
            hasTakenPhotograph = true; // Marca que se ha tomado una fotografía
        } else {
            // Ya se ha tomado una fotografía previamente, no se permite tomar otra
            JOptionPane.showMessageDialog(null, "Ya se ha tomado una fotografía durante este vuelo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
