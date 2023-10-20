import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

/**
 * The Island class represents an island with a polygon shape on the canvas.
 * It allows to create and manipulate islands.
 * Islands can be made visible or invisible, and their locations can be queried.
 * 
 * @author Laura Natalia Rojas and Ana Maria Duran
 * @version 23/09/23
 */
public class Island {
    private List<Point> vertices;
    private static HashMap<String, MyPolygon> islands = new HashMap<>();
    private boolean isVisible;
    private int[][] vertexArray;
    private String color;
    private boolean operationSuccess;
    private static Set<String> usedColors = new HashSet<>();

    /**
     * Constructs an Island object with the specified vertex array and color.
     * The island is created as a polygon with the given vertices and color.
     *  Los nombres de color válidos son:
     
     * @param vertexArray An array of vertices representing the island's shape.
     * @param color       The color of the island.
     */
    public Island(String color, int[][] vertexArray) throws IceepeeceeException {
        isVisible = true;
        this.color = color;
        
        if (usedColors.contains(color)) {
            throw new IceepeeceeException("El color " + color + " ya se ha utilizado para otra isla.");
        }
        usedColors.add(color);
        // Asigna los vértices proporcionados al miembro vertexArray
        this.vertexArray = vertexArray;
        MyPolygon polygon = new MyPolygon(color, vertexArray); // Create a Polygon with the specified color
        islands.put(color, polygon); // Use the color as the key in the map
        polygon.makeInvisible();
        polygon.draw();
        operationSuccess = true;
    }
    
    /**
     * Checks if the simulation is currently in a visible state.
     *
     * @return true if the simulation is visible, false if it's hidden.
     */
    public boolean isVisible(){
        return isVisible;
    }
    
    /**
     * Get the color of the island.
     * 
     * @return The color of the island.
     */
    public String getColor() {
        return this.color;
    }
    
    /**
     * Make the island with the specified color visible.
     * 
     * @param color The color of the island to make visible.
     */
    public void makeIslandVisible(String color) {
        MyPolygon polygon = islands.get(color);
        isVisible = true;
        if (polygon != null) {
            polygon.makeVisible(); // Make the polygon of the specified island visible
        }
    }

    /**
     * Make the island with the specified color invisible.
     * 
     * @param color The color of the island to make invisible.
     */
    public void makeIslandInvisible(String color) {
        MyPolygon polygon = islands.get(color);
        isVisible = false;
        if (polygon != null) {
            polygon.makeInvisible(); // Make the polygon of the specified island invisible
        }
    }   
    
    /**
     * Delete the island with the specified color.
     * 
     * @param color The color of the island to delete.
     */
    public void delIsland(String color) {
        MyPolygon polygon = islands.get(color);
        if (polygon != null) {
            polygon.makeInvisible();
            islands.remove(color);
            operationSuccess = true; // Indicar que la eliminación fue exitosa
        } else {
            operationSuccess = false; // Indicar que la eliminación no fue exitosa
        }
    }
    
    /**
     * Get the location of the island with the specified color.
     * 
     * @param color The color of the island to query.
     * @return A string representing the location of the island.
     */
    public int[][] locationIsland(String color) {
        MyPolygon polygon = islands.get(color);
        if (polygon != null) {
            return polygon.getVertexArray();
        } else {
            return null; // Return a message if the island does not exist
        }
    }
    
    /**
     * Get the vertex array of the island.
     * 
     * @return An array of vertices representing the island's shape.
     */
    public int[][] getVertexArray() {
        return vertexArray;
    }
    
    /**
     * Displays the islands stored in the HashMap, including their color and vertices.
     */
    private static void showIslands() {
        System.out.println("Islas almacenadas en el HashMap:");
        for (Map.Entry<String, MyPolygon> entry : islands.entrySet()) {
            String color = entry.getKey();
            MyPolygon polygon = entry.getValue();
            System.out.println("Color: " + color);
            System.out.println("Vertices: " + polygon.getVertexArray());
            System.out.println("------------------------");
        }
    }
        
    /**
     * Checks if the island is completely within the specified flight.
     *
     * @param flight The flight to be checked.
     * @return true if the island is completely within the flight, false otherwise.
     */
    public boolean ok() {
        // Verifica si la última operación fue exitosa.
        return operationSuccess;
    }
    
    
    /**
     * Checks if the island is entirely within the specified flight.
     *
     * @param flight The flight to be verified.
     * @return true if the island is entirely within the flight, false otherwise.
     */
    private boolean isCompletelyInsideFlight(Flight flight) {
        if (flight != null) {
            int[][] islandVertices = this.getVertexArray();
            if (islandVertices != null && islandVertices.length > 0) { // Verificar si la isla tiene vértices válidos
                int[] flightFrom = flight.getFrom();
                int[] flightTo = flight.getTo();
    
                for (int[] islandVertex : islandVertices) {
                    int x = islandVertex[0];
                    int y = islandVertex[1];
    
                    // Verifica si el vértice de la isla está dentro del vuelo
                    if (x < Math.min(flightFrom[0], flightTo[0]) || x > Math.max(flightFrom[0], flightTo[0]) ||
                        y < Math.min(flightFrom[1], flightTo[1]) || y > Math.max(flightFrom[1], flightTo[1])) {
                        return false; // El vértice de la isla no está dentro del vuelo
                    }
                }
                return true; // Todos los vértices de la isla están dentro del vuelo
            }
        }
        return false; // El vuelo es nulo o la isla no tiene vértices válidos
    }

    /**
     * Checks if a point (coordinates) is inside the flight.
     *
     * @param point      The coordinates of the point to be verified.
     * @param flightVertex The vertices of the flight.
     * @return true if the point is inside the flight, false otherwise.
     */

    private boolean isInsideFlight(int[] point, int[] flightVertex) {
        // Verifica si el punto (coordenadas) está dentro del vuelo (vértices del vuelo)
        return point[0] >= flightVertex[0] && point[0] <= flightVertex[2] &&
               point[1] >= flightVertex[1] && point[1] <= flightVertex[3];
    }
    
    /**
     * Draws the outline of the island with the specified color.
     *
     * @param color The color of the island whose outline will be drawn.
     */
    public void drawOutline(String color) {
        if (isVisible) {
            MyPolygon polygon = islands.get(color);
            Canvas canvas = Canvas.getCanvas();
            
            if (polygon != null) {
                List<Point> vertices = polygon.getVertices();
                int numVertices = vertices.size();
                
                if (numVertices >= 2) {
                    for (int i = 0; i < numVertices; i++) {
                        Point vertex1 = vertices.get(i);
                        Point vertex2 = vertices.get((i + 1) % numVertices); // Obtén el siguiente vértice o el primer vértice al cerrar el contorno
                        int x1 = vertex1.getX();
                        int y1 = vertex1.getY();
                        int x2 = vertex2.getX();
                        int y2 = vertex2.getY();
                        
                        // Dibuja una línea desde vertex1 a vertex2 con el color negro
                        canvas.drawLine(x1, y1, x2, y2, "black");
                    }
                }
            }
        }
    }
}