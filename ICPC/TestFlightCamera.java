import java.util.ArrayList;
import java.util.List;

public class TestFlightCamera {
    public static void main(String[] args) {
        // Crear un vuelo de ejemplo
        int[] from = {0, 30, 20};
        int[] to = {78, 70, 5};
        Fligth flight = new Fligth("red", from, to);

        // Usar la cámara para tomar una fotografía del vuelo
        double teta = 48.295; // Ángulo de la cámara (45 grados)
        flight.camera(teta);

        // Obtener la lista de fotografías del vuelo
        List<Photograph> photographs = flight.getPhotographs();

        // Verificar si se tomó una fotografía
        if (!photographs.isEmpty()) {
            System.out.println("Se tomó una fotografía del vuelo.");
        } else {
            System.out.println("No se tomó ninguna fotografía del vuelo.");
        }
    }
}

