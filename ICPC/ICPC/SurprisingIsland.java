package ICPC;
import Shapes.*;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class SurprisingIsland here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SurprisingIsland extends Island {

    private MyPolygon polygon;
    
    public SurprisingIsland(String color, int[][] vertexArray) throws IceepeeceeException {
        super(color, vertexArray);
        makeIslandVisible(color);
        this.drawOutline(color, "fuchsia");
    }
    
    
    @Override
    public int[][] locationIsland(String color) {
        MyPolygon polygon = islands.get(color);
        if (color != null && color.equals(getColor()) && polygon.getVertexCount() > 3) {
            loseRandomVertex(polygon);
            JOptionPane.showMessageDialog(null, "Una SurprisingIsland ha perdido un vértice al obtener su ubicación.", "¡Sorpresa!", JOptionPane.INFORMATION_MESSAGE);
        }
        return polygon.getVertexArray(); 
    }

    // Método para perder un vértice de forma aleatoria
    private List<Point> loseRandomVertex(MyPolygon polygon) {
        List<Point> vertices = polygon.getVertices();
        if (!vertices.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(vertices.size());
            vertices.remove(randomIndex);
        
        }
        return vertices; 
    }
}