package ICPC;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
/**
 * The `FlatFlight` class represents a flat flight with coordinates, color, and visibility.
 * Flat flights can be created, displayed, and manipulated, and they are represented
 * as lines with arrowheads at both ends.
 * 
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class FlatFlight extends Flight {
    
    /**
     * Constructs a `FlatFlight` object with the specified color and coordinates.
     * The flight is initially visible.
     * 
     * @param color The color of the flight.
     * @param from  The starting coordinates [x1, y1, z1].
     * @param to    The ending coordinates [x2, y2, z2].
     */
    public FlatFlight(String color, int[] from, int[] to) {
        super(color, from ,new int[] {to[0], to[1], from[2]});
    }
    
    /**
     * Draw the flat flight on the canvas as a line with arrowheads at both ends.
     */
    @Override
    public void draw() {
        int[] from = getFrom();
        int[] to = getTo();
        Shapes.Canvas canvas = Shapes.Canvas.getCanvas();
    
        Path2D.Double arrowPath = new Path2D.Double();
        double arrowSize = 10.0;
    
        // Calculate the angle of the line
        double angle = Math.atan2(to[1] - from[1], to[0] - from[0]);
    
        // Calculate the coordinates of the first arrowhead
        double x1 = to[0] - arrowSize * Math.cos(angle - Math.PI / 6);
        double y1 = to[1] - arrowSize * Math.sin(angle - Math.PI / 6);
        double x2 = to[0];
        double y2 = to[1];
        double x3 = to[0] - arrowSize * Math.cos(angle + Math.PI / 6);
        double y3 = to[1] - arrowSize * Math.sin(angle + Math.PI / 6);
    
        // Draw the first arrowhead
        arrowPath.moveTo(x1, y1);
        arrowPath.lineTo(x2, y2);
        arrowPath.lineTo(x3, y3);
    
        // Calculate the coordinates of the second arrowhead
        x1 = from[0] + arrowSize * Math.cos(angle - Math.PI / 6);
        y1 = from[1] + arrowSize * Math.sin(angle - Math.PI / 6);
        x2 = from[0];
        y2 = from[1];
        x3 = from[0] + arrowSize * Math.cos(angle + Math.PI / 6);
        y3 = from[1] + arrowSize * Math.sin(angle + Math.PI / 6);
    
        // Draw the second arrowhead
        arrowPath.moveTo(x1, y1);
        arrowPath.lineTo(x2, y2);
        arrowPath.lineTo(x3, y3);
    
        // Draw the line
        arrowPath.moveTo(from[0], from[1]);
        arrowPath.lineTo(to[0], to[1]);
    
        canvas.draw(this, color, arrowPath, 255);
    }
}

