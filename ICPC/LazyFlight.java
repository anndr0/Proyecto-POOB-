import javax.swing.JOptionPane;
/**
 * Write a description of class LazyFlight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LazyFlight extends Flight {
    private boolean hasTakenPhotograph;

    public LazyFlight(String color, int[] from, int[] to) {
        super(color, from, to);
        hasTakenPhotograph = false; // Inicialmente, no se ha tomado ninguna fotografía
    }

    @Override
    public void camera(String color, double theta) {
        if (!hasTakenPhotograph) { // Verifica si no se ha tomado una fotografía previamente
            Photograph photograph = new Photograph(color, theta);
            hasTakenPhotograph = true; // Marca que se ha tomado una fotografía
        } else {
            // Ya se ha tomado una fotografía previamente, no se permite tomar otra
            JOptionPane.showMessageDialog(null, "Ya se ha tomado una fotografía durante este vuelo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
