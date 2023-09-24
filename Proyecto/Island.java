import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;
/**
 * Write a description of class Island here.
 * 
 * @author (Laura Natalia Rojas and Ana Maria Duran) 
 * @version (23/09/23)
 */

public class Island {
    private Map<String, Polygon> islands;
    private boolean isVisible;
    private int[][] vertexArray;
    private String color;

    public Island(int[][] vertexArray, String color) {
        islands = new HashMap<>();
        Polygon polygon = new Polygon(vertexArray, color); // Crea un Polygon con el color especificado
        islands.put(color, polygon); // Usa el color como clave en el mapa
        polygon.makeVisible();
        polygon.draw();
    }
    
    public void makeIslandVisible(String color) {
        Polygon polygon = islands.get(color);
        if (polygon != null) {
            polygon.makeVisible(); // Hace visible el polígono de la isla especificada
        }
    }

    public void makeIslandInvisible(String color) {
        Polygon polygon = islands.get(color);
        if (polygon != null) {
            polygon.makeInvisible(); // Hace invisible el polígono de la isla especificada
        }
    }
    
    public void delIsland(String color) {
        Polygon polygon = islands.get(color);
        if (polygon != null) {
            polygon.makeInvisible();
            islands.remove(color); // Elimina la isla del mapa
        }
    }
    
    public String locationIsland(String color) {
        Polygon polygon = islands.get(color);
        if (polygon != null) {
            int[][] coordinates = polygon.getVertexArray();
            StringBuilder location = new StringBuilder("Location of Island (Color: " + color + "): {");
            
            for (int i = 0; i < coordinates.length; i++) {
                location.append("{").append(coordinates[i][0]).append(", ").append(coordinates[i][1]).append("}");
                if (i < coordinates.length - 1) {
                    location.append(", ");
                }
            }
            
            location.append("}");
            return location.toString();
        } else {
            return "Island not found"; // Devuelve un mensaje si la isla no existe
        }
    }
    
    public int[][] getVertexArray() {
        return vertexArray;
    }

}







