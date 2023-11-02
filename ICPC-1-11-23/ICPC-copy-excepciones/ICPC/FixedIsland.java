package ICPC;
import Shapes.*;
import javax.swing.JOptionPane;
/**
 * Write a description of class FixedIsland here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FixedIsland extends Island {
    private MyPolygon polygon;
    
    public FixedIsland(String color, int[][] vertexArray) throws IceepeeceeException {
        super(color, vertexArray); // Llama al constructor de la clase base
        //makeIslandVisible(color);
        this.drawOutline(color, "darkGreen");
    }
    
    @Override
    public void makeIslandVisible(String color) {
        MyPolygon polygon = islands.get(color);
        isVisible = true;
        if (polygon != null) {
            polygon.makeVisible(); // Make the polygon of the specified island visible
        }
        this.drawOutline(color, "darkGreen");
    }
    
    @Override
    public void delIsland(String color) {
        operationSuccess = true;
        JOptionPane.showMessageDialog(null, "No se puede eliminar una FixedIsland.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    
}

