package ICPC;
import javax.swing.JOptionPane;
/**
 * Write a description of class FixedIsland here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FixedIsland extends Island {
    public FixedIsland(String color, int[][] vertexArray) throws IceepeeceeException {
        super(color, vertexArray); // Llama al constructor de la clase base
        makeIslandVisible(color);
        this.drawOutline(color, "darkGreen");
    }
    
    @Override
    public void delIsland(String color) {
        JOptionPane.showMessageDialog(null, "No se puede eliminar una FixedIsland.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
}

