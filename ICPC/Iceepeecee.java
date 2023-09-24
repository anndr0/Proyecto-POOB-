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
    private Canvas canvas;
    
    public Iceepeecee(int length, int width) {
        canvas = Canvas.getCanvas();
        canvas.setVisible(false);
        islands = new HashMap<>();
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
    
    
    
}
