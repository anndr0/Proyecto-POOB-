import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Write a description of class Iceepeecee here.
 * 
 * @author (Ana María Durán And Laura Natalia Rojas) 
 * @version (23/03/23)
 */
public class Iceepeecee {
    private int length;
    private int width;
    private boolean isVisible;
    private Map<String, Island> islands;
    private HashMap<String, Fligth> fligths;
    private Canvas canvas;
    
    public Iceepeecee(int length, int width) {
        canvas = Canvas.getCanvas(length,width);
        canvas.setVisible(true);
        islands = new HashMap<>();
        fligths = new HashMap<>();
        isVisible = false;
    }
    
    /**
     * Add an island to Iceepeecee.
     * @param color       the color of the island
     * @param vertexArray the vertices of the island
     */
    public void addIsland(String color, int[][] vertexArray) {
        Island island = new Island(vertexArray, color);
        islands.put(color, island);
        
    }
    
    /**
     * Delete an island from Iceepeecee.
     * @param color the color of the island to delete
     */
    public void deleteIsland(String color) {
        Island island = islands.get(color);
        if (island != null) {
            islands.remove(color);
            island.delIsland(color); // Llama al método para borrar la isla en Island
        }
    }
    
    /**
     * Get the location of an island by color.
     * @param island the color of the island to query
     * @return a string representing the location of the island
     */
    public String islandLocation(String island) {
        Island islandObj = islands.get(island);
        if (islandObj != null) {
            return islandObj.locationIsland(island);
        } else {
            return "Island not found"; // Devuelve un mensaje si la isla no existe
        }
    }
    
        /**
     * Add an island to Iceepeecee.
     * @param color       the color of the island
     * @param vertexArray the vertices of the island
     */
    public void addFligth(String color, int[] from, int[] to) {
        Fligth fligth = new Fligth(color,from, to);
        fligths.put(color, fligth);
        
    }
    
    /**
     * Delete an island from Iceepeecee.
     * @param color the color of the island to delete
     */
    public void deleteFligth(String color) {
        Fligth fligth = fligths.get(color);
        if (fligth != null) {
            fligths.remove(color);
            fligth.delFligth(color); // Llama al método para borrar la isla en Island
        }
    }
    
    /**
     * Get the location of an island by color.
     * @param island the color of the island to query
     * @return a string representing the location of the island
     */
    public String fligthLocation(String fligth) {
        Fligth islandObj = fligths.get(fligth);
        if (islandObj != null) {
            return islandObj.locationFligth(fligth);
        } else {
            return "Island not found"; // Devuelve un mensaje si la isla no existe
        }
    }
}
