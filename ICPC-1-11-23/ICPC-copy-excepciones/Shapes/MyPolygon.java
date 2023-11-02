package Shapes;
import ICPC.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MyPolygon class represents a polygon with vertices. It can be drawn and
 * manipulated within a canvas.
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class MyPolygon {
    private int numVertices;
    private List<ICPC.Point> vertices;
    private String color;
    private boolean isVisible;
    private Number[] xPoints;
    private Number[] yPoints;
    private int alpha;

    /**
     * Constructs an empty MyPolygon object.
     */
    public MyPolygon() {
        vertices = new ArrayList<>();
        isVisible = false;
    }

    /**
     * Constructs a MyPolygon with the specified color and vertices.
     *
     * @param color        The color of the polygon.
     * @param vertexArray  An array of vertices represented as [x, y] pairs.
     */
    public MyPolygon(String color, int[][] vertexArray) {
        this();
        addVerticesFromArray(vertexArray);
        setColor(color);
    }

    /**
     * Adds a vertex with the specified coordinates to the polygon.
     *
     * @param x The x-coordinate of the vertex.
     * @param y The y-coordinate of the vertex.
     */
    public void addVertex(int x, int y) {
        vertices.add(new ICPC.Point(x, y));
    }

    /**
     * Adds vertices from a 2D array where each row is a [x, y] pair.
     *
     * @param vertexArray An array of vertices represented as [x, y] pairs.
     */
    public void addVerticesFromArray(int[][] vertexArray) {
        for (int i = 0; i < vertexArray.length; i++) {
            if (vertexArray[i].length == 2) {
                addVertex(vertexArray[i][0], vertexArray[i][1]);
            }
        }
    }

    /**
     * Gets the count of vertices in the polygon.
     *
     * @return The number of vertices in the polygon.
     */
    public int getVertexCount() {
        return vertices.size();
    }

    /**
     * Sets the color of the polygon.
     *
     * @param newColor The new color to set for the polygon.
     */
    public void setColor(String newColor) {
        color = newColor;
    }

    /**
     * Makes the polygon visible and draws it on the canvas.
     */
    public void makeVisible() {
        isVisible = true; 
        draw();    
    }

    /**
     * Makes the polygon invisible and erases it from the canvas.
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    /**
     * Clears all vertices from the polygon.
     */
    public void clear() {
        vertices.clear();
    }

    /**
     * Erases the polygon from the canvas if it's visible.
     */
    public void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Gets the vertices of the polygon as a 2D array.
     *
     * @return An array of vertices represented as [x, y] pairs.
     */
    public int[][] getVertexArray() {
        int[][] vertexArray = new int[vertices.size()][2];

        for (int i = 0; i < vertices.size(); i++) {
            ICPC.Point vertex = vertices.get(i);
            vertexArray[i][0] = vertex.getX();
            vertexArray[i][1] = vertex.getY();
        }

        return vertexArray;
    }

    /**
     * Gets a list of Point objects representing the vertices of the polygon.
     *
     * @return A list of Point objects representing the polygon's vertices.
     */
    public List<ICPC.Point> getVertices() {
        return vertices;
    }

    /**
     * Draws the polygon on the canvas if it's visible.
     */
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xPoints = new int[vertices.size()];
            int[] yPoints = new int[vertices.size()];

            for (int i = 0; i < vertices.size(); i++) {
                ICPC.Point vertex = vertices.get(i);
                xPoints[i] = vertex.getX();
                yPoints[i] = vertex.getY();
            }

            canvas.draw(this, color, new Polygon(xPoints, yPoints, vertices.size()), 255);
            canvas.wait(10);
        }
    }
}
