
package ICPC;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


/**
 * The test class IceepeeceeCC2Test.
 *
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class IceepeeceeCC2Test
{

    // Iceepeecee(int[][][] islands, int[][][] flights) --------------------- //
    @Test
    public void testProblemFValid() {
        
        // Definir los vértices de las islas
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        assertTrue(iceepeecee.isOperationSuccess());

    }
    @Test
    public void testProblemFInvalid() {
 
        // Definir los vértices de las islas
        int[][][] islands = {
            {{20, 30}, {300, 603}, {10, 50}},// se sale del canvas
            {{40, 20}, {60, 10}, {75, 401}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 301}}
        };
        // Definir los vértices de los vuelos
        int[][][] flights = {
            {{0, 0, 20}, {78, 70, 5}},
            {{55, 100, 20}, {70, 60, 10}}}
        ;
        
        // Creamos el iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        // No se deben crear las islas por lo que se salen del canvas, 
        // "islas" debe estar vacia
        String[] islas = iceepeecee.islands();
        // Verificamos
        assertEquals(islas.length, 0);
    }
    // ----------------------------------------------------------------------- //   
    
    // islands() ----------------------------------------------------------------------- //
    @Test
    public void testIslandsValid() {
        // Definir los vértices de las islas
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        
        // Definir los vértices de los vuelos
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };
        
        // Creamos el iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        
        // Islas esperadas
        String[] expectedIslands = {
            "red",
            "green",
            "coral"
        };
        
        // Obtiene las islas que se encuentren actualmente
        String[] islandLocations = iceepeecee.islands();
        
        // Comprobamos que sean iguales
        assertArrayEquals(expectedIslands, islandLocations);
    }
    @Test
    public void testIslandsInvalid() {
        // Definir los vértices de las islas
        int[][][] islands = {
            {{320, 30}, {550, 50}, {1010, 50}}, // Cambiadas las coordenadas a valores fuera de los límites
            {{400, 200}, {600, 100}, {750, 200}, {600, 300}}, // Cambiadas las coordenadas a valores fuera de los límites
            {{450, 600}, {550, 550}, {600, 600}, {550, 650}} // Cambiadas las coordenadas a valores fuera de los límites
        };
        // Definir los vértices de los vuelos
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };
        // Creamos el iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
    
        // Las coordenadas de las islas están fuera de los límites, por lo que se espera un fallo
        String[] islandLocations = iceepeecee.islands();
        
        // Comprobar que la lista de ubicaciones de islas esté vacía
        assertEquals(0, islandLocations.length);

    }
    // ----------------------------------------------------------------------- //   
    
    // flights() ------------------------------------------ // 
    @Test
    public void testFlightsValid() {
        // Definir los vértices de las islas
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        // Definir los vértices de los vuelos
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };
        // Creamos el iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        // Vuelos esperados
        String[] expectedFlights = {
            "red",
            "green",
        };
        // Llamar al método flights(), vuelos actuales        
        String[] flightLocations = iceepeecee.flights();
        // Comprobar que la isla coincida
        assertArrayEquals(expectedFlights, flightLocations);
    }
    @Test
    public void testFlightsInvalid() {

        // Crear una instancia de Iceepeecee con islas pero sin vuelos
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        int[][][] flights = {}; // No se registran vuelos
        
        // Creamos el iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        
        // Llamar al método flights() en una instancia sin vuelos
        String[] flightLocations = iceepeecee.flights();
        
        // Verificar que la lista de ubicaciones de vuelos esté vacía
        assertEquals(0, flightLocations.length);

    }
    // ----------------------------------------------------------------------- //   

    // observedIslands() ---------------------------------------------------- //
    @Test
    public void testObservedIslandsValid() {
        // Definimos las coordenadas de las islas y los vuelos
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };
        
        // Creamos una instancia de Iceepeecee con las coordenadas de islas y vuelos
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        
        // Especificamos un ángulo (theta) para tomar una fotografía
        double theta = 48.031693036;
        
        // Tomamos una fotografía con el ángulo especificado
        iceepeecee.photograph(theta);
    
        // Llamamos al método para obtener las islas observadas
        String[] islasContenidas = iceepeecee.observedIslands();
        
        // Verificamos que se haya detectado al menos una isla observada
        assertTrue(islasContenidas.length > 0);
    }
    @Test
    public void testObservedIslandsInvalid() {
        // Definimos las coordenadas de las islas y los vuelos
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };
        
        // Creamos una instancia de Iceepeecee con las coordenadas de islas y vuelos
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        
        // Especificamos un ángulo (theta) para tomar una fotografía
        double theta = 30;
        
        // Tomamos una fotografía con el ángulo especificado
        iceepeecee.photograph(theta);
    
        // Llamamos al método para obtener las islas observadas
        String[] islasContenidas = iceepeecee.observedIslands();
        
        // Verificamos que no se haya detectado ninguna isla observada
        assertTrue(islasContenidas.length == 0);
    }
    // ----------------------------------------------------------------------- //
    
    // uselessFlights() ---------------------------------------------------- //
    @Test
    public void testUselessFlightsValid() {
        // Definimos las coordenadas de las islas y los vuelos
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };
        
        // Creamos una instancia de Iceepeecee con las coordenadas de islas y vuelos
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        
        // Especificamos un ángulo (theta) para tomar una fotografía
        double theta = 30;
        
        // Tomamos una fotografía con el ángulo especificado
        iceepeecee.photograph(theta);
    
        // Llamamos al método para obtener los vuelos inútiles
        String[] vuelosInutiles = iceepeecee.uselessFlights();
        
        // Verificamos que se haya detectado al menos un vuelo inútil
        assertTrue(vuelosInutiles.length > 0);
    }
    
    @Test
    public void testUselessFlightsInvalid() {
        // Definimos las coordenadas de las islas y los vuelos
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };
        
        // Creamos una instancia de Iceepeecee con las coordenadas de islas y vuelos
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        
        // Especificamos un ángulo (theta) para tomar una fotografía
        double theta = 48.03;
        
        // Tomamos una fotografía con el ángulo especificado
        iceepeecee.photograph(theta);
    
        // Llamamos al método para obtener los vuelos inútiles
        String[] vuelosInutiles = iceepeecee.uselessFlights();
        
        // Verificamos que se haya detectado al menos un vuelo inútil
        assertTrue(vuelosInutiles.length == 0);
    }
    // ----------------------------------------------------------------------- //
}
