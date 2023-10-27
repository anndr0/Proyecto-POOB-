
package ICPC;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PruebasAceptacion.
 *
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class PruebasAceptacion
{
    @Test
    public void TestIceepeeceeDR1() {
        try{
            
            // Descubriendo las Islas de Iceepeecee
            // En un remoto archipiélago conocido como "Iceepeecee," el presidente quería conocer la ubicación exacta de sus islas.
            int[][][] islands = {
                {{20, 30}, {50, 50}, {10, 50}},
                {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
                {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
            };
            
            // Como el presidente de iceepeecee quiere conocer la ubicacion de sus islas, para esto contrata vuelos comerciales que sobrevuelen su territorio
            int[][][] flights = {
                {{0,30,20},{78,70,5}},
                {{55,0,20},{70,60,10}}
            };
            Iceepeecee iceepeeceeDR = new Iceepeecee(islands, flights);
            iceepeeceeDR.makeInvisible();
            // En su recorrido, los vuelos toman las siguientes fotos
            double theta = 48.031693036;
            iceepeeceeDR.photograph(theta);
            
            // Después de haber tomado las fotos, el presidente quiere saber cuáles islas fueron identificadas
            iceepeeceeDR.observedIslands();
        
            // Debido a que las islas fueron identificadas, el presidente desea conocer su ubicación exacta
            String[] observedIslands = iceepeeceeDR.observedIslands();
        
            for (String island : observedIslands) {
                int[][] location = iceepeeceeDR.islandLocation(island);
                assertNotNull(location);
            }
            
            // Tambien desea saber que vuelos no obtuvieron ninguna informacion
            String[] uselessFlights = iceepeeceeDR.uselessFlights();
            assertNotNull(uselessFlights);
            assertTrue(uselessFlights.length == 0);
        }catch (IceepeeceeException e) {
            fail("Se produjo una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    public void TestIceepeeceeDR2() {
        try{
            /*
             * En un remoto archipiélago conocido como "Iceepeecee," el presidente quería conocer la ubicación exacta de sus islas. 
             * Para lograr esto, decidió contratar vuelos comerciales que sobrevolaran su territorio. 
             * Creó un sistema especial llamado "Iceepeecee" para gestionar estos vuelos y las fotografías tomadas durante el viaje.
             * 
             * Primero, el presidente definió la ubicación de las islas en el archipiélago. 
             * Las islas se representaron mediante coordenadas en un sistema tridimensional. 
             * Además, programó la ruta de los vuelos comerciales que tomarían fotos del archipiélago. 
             * Estos vuelos también se definieron con coordenadas que indicaban su posición y altitud a lo largo del recorrido.
             */
            int[][][] islands = {
                {{20, 30}, {50, 50}, {10, 50}},
                {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
                {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
            };
            
            int[][][] flights = {
                {{0,30,20},{78,70,5}},
                {{55,0,20},{70,60,10}}
            };
            
            /*
             * Con estos datos, se creó un objeto Iceepeecee que representa el sistema de control de vuelos y fotografía.
             */
            Iceepeecee iceepeeceeDR = new Iceepeecee(islands, flights);
            //iceepeeceeDR.makeInvisible();
            /*
             * A medida que los vuelos realizaban su recorrido, tomaban fotografías a lo largo del trayecto. 
             * El presidente quería saber el ángulo en el que se tomaron algunas de las fotografías, 
             * por lo que llamó al método flightCamera(color) pasando el color del vuelo del cual quería obtener la información.
             */
            String flightColor = "red"; // Supongamos que se quiere el ángulo de la foto del vuelo azul.
            
            double theta = 48.3;
            iceepeeceeDR.photograph(theta);
            double theta1 = 48.4;
            iceepeeceeDR.photograph(theta1);
            double thetaFC = iceepeeceeDR.flightCamera(flightColor);
            iceepeeceeDR.makeInvisible();
            iceepeeceeDR.photograph(thetaFC);
            
            /*
             * Después de haber tomado las fotos, el presidente deseaba identificar cuáles islas fueron observadas. 
             * Para obtener esta información, utilizó el método observedIslands().
             */
            String[] observedIslands = iceepeeceeDR.observedIslands();
    
            /*
             * El presidente también quería conocer la ubicación exacta de las islas identificadas, 
             * por lo que recorrió la lista de islas observadas y utilizó el método islandLocation(color) 
             * para obtener las coordenadas de cada isla.
             */
        
            for (String island : observedIslands) {
                int[][] location = iceepeeceeDR.islandLocation(island);
                assertNotNull(location);
            }
        
            /*
             * Finalmente, el presidente deseaba saber si había vuelos que no proporcionaron información útil, 
             * es decir, vuelos que no tomaron ninguna foto. Utilizó el método uselessFlights() para obtener una lista de estos vuelos.
             */
            
            String[] uselessFlights = iceepeeceeDR.uselessFlights();
            assertNotNull(uselessFlights);
            assertTrue(uselessFlights.length == 0); // Asegurarse de que no haya vuelos inútiles.
    
        }catch (IceepeeceeException e) {
            fail("Se produjo una excepción inesperada: " + e.getMessage());
        }
    }
    
}
