import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Polygon class represents a polygon shape on the canvas. It allows you
 * to create, display, and manipulate polygons.
 * Polygons can be made visible or invisible, and their vertices can be added
 * and accessed.
 * 
 * @author [Ana María Durán y Laura Natalia Rojas]
 * @version [23/09/23]
 */
public class Polygon {
    private List<Point> vertices;
    private String color;
    private boolean isVisible;

    /**
     * Constructs a Polygon object with default settings.
     * The polygon is initially colored black and invisible.
     */
    public Polygon() {
        vertices = new ArrayList<>();
        this.color = color; 
        isVisible = false;
    }

    /**
     * Constructs a Polygon object with the given vertex array and color.
     * The polygon is initially colored black and invisible.
     * 
     * @param vertexArray An array of vertices to initialize the polygon.
     * @param color       The color of the polygon.
     */
    public Polygon(String color, int[][] vertexArray) {
        this();
        addVerticesFromArray(vertexArray);
        setColor(color);
    }
    
    /**
     * Add a vertex to the polygon.
     * 
     * @param x The x-coordinate of the vertex.
     * @param y The y-coordinate of the vertex.
     */
    public void addVertex(int x, int y) {
        vertices.add(new Point(x, y));
    }    
    
    /**
     * Add multiple vertices to the polygon from a 2D array.
     * 
     * @param vertexArray A 2D array of vertices to add.
     */
    public void addVerticesFromArray(int[][] vertexArray) {
        for (int i = 0; i < vertexArray.length; i++) {
            if (vertexArray[i].length == 2) {
                addVertex(vertexArray[i][0], vertexArray[i][1]);
            }
        }
    }

    /**
     * Get the polygon as an AWT Polygon object.
     * 
     * @return An AWT Polygon object representing the polygon.
     */
    public java.awt.Polygon getPolygon() {
        int[] xPoints = new int[vertices.size()];
        int[] yPoints = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            Point vertex = vertices.get(i);
            xPoints[i] = vertex.getX();
            yPoints[i] = vertex.getY();
        }
        return new java.awt.Polygon(xPoints, yPoints, vertices.size());
    }

    /**
     * Get the number of vertices in the polygon.
     * 
     * @return The number of vertices in the polygon.
     */
    public int getVertexCount() {
        return vertices.size();
    }

    /**
     * Set the color of the polygon.
     * 
     * @param newColor The new color for the polygon.
     */
    public void setColor(String newColor) {
        color = newColor;
    }

    /**
     * Make this polygon visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this polygon invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /**
     * Draw the polygon on the canvas if it is visible.
     */
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xPoints = new int[vertices.size()];
            int[] yPoints = new int[vertices.size()];

            for (int i = 0; i < vertices.size(); i++) {
                Point vertex = vertices.get(i);
                xPoints[i] = vertex.getX();
                yPoints[i] = vertex.getY();
            }

            canvas.draw(this, color, new java.awt.Polygon(xPoints, yPoints, vertices.size()), 255);
            canvas.wait(10);
        }
    }

    /**
     * Clear all vertices from the polygon.
     */
    public void clear() {
        vertices.clear();
    }
    
    /**
     * Erase the polygon from the canvas if it is visible.
     */
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Get the vertex array of the polygon.
     * 
     * @return A 2D array representing the vertices of the polygon.
     */
    public int[][] getVertexArray() {
        int[][] vertexArray = new int[vertices.size()][2];
    
        for (int i = 0; i < vertices.size(); i++) {
            Point vertex = vertices.get(i);
            vertexArray[i][0] = vertex.getX();
            vertexArray[i][1] = vertex.getY();
        }
    
        return vertexArray;
    }
    
    /**
     * Get a string representation of the polygon.
     * 
     * @return A string describing the polygon and its vertices.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Polygon with ").append(vertices.size()).append(" vertices: [");
    
        for (int i = 0; i < vertices.size(); i++) {
            Point vertex = vertices.get(i);
            sb.append("(").append(vertex.getX()).append(", ").append(vertex.getY()).append(")");
    
            if (i < vertices.size() - 1) {
                sb.append(", ");
            }
        }
    
        sb.append("]");
    
        return sb.toString();
    }
}
