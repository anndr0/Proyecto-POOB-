

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


/**
 * The test class IceepeeceeCC2Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class IceepeeceeCC2Test
{
    private Iceepeecee iceepeecee;
    @Before
    public void setUp() {
        iceepeecee = new Iceepeecee(300, 300);
    }
    
    @After
    public void tearDown() {
        iceepeecee.finish();
    }

    // Iceepeecee(int[][][] islands, int[][][] flights) --------------------- //
    @Test
    public void TestIceepeeceeProblemF() {
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
    public void TestFailIceepeeceeProblemF() {
        // Definir los vértices de las islas
        int[][][] islands = {
            {{20, 30}, {300, 50}, {10, 50}},// se sale del canvas
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        int[][][] flights = {
            {{0, 0, 20}, {78, 70, 5}},
            {{55, 100, 20}, {70, 60, 10}}
        };
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        assertTrue(iceepeecee.isOperationSuccess());
    }

    // ----------------------------------------------------------------------- //
    @Test
    public void testIslands() {
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
        
        String[] expectedIslands = {
            "Color: red Location: [[20, 30], [50, 50], [10, 50]] ",
            "Color: green Location: [[40, 20], [60, 10], [75, 20], [60, 30]] ",
            "Color: blue Location: [[45, 60], [55, 55], [60, 60], [55, 65]] "
        };

        
        String[] islandLocations = iceepeecee.islands();
        
        assertArrayEquals(expectedIslands, islandLocations);
    }
    
    @Test
    public void testIslandsFail() {
        int[][][] islands = {
            {{320, 30}, {550, 50}, {1010, 50}}, // Cambiadas las coordenadas a valores fuera de los límites
            {{400, 200}, {600, 100}, {750, 200}, {600, 300}}, // Cambiadas las coordenadas a valores fuera de los límites
            {{450, 600}, {550, 550}, {600, 600}, {550, 650}} // Cambiadas las coordenadas a valores fuera de los límites
        };
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
    
        // Las coordenadas de las islas están fuera de los límites, por lo que se espera un fallo
        String[] islandLocations = iceepeecee.islands();
    
        // Comprobar que la lista de ubicaciones de islas esté vacía
        assertEquals(0, islandLocations.length);
    }
    
    // ----------------------------------------------------------------------- //   
    
    @Test
    public void testFlights() {
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
        
        String[] expectedFlights = {
            "Color: red Location: [[0, 30, 20], [78, 70, 5]] ",
            "Color: green Location: [[55, 0, 20], [70, 60, 10]] ",
            // Agrega más vuelos y su información esperada aquí
        };
        
        String[] flightLocations = iceepeecee.flights();
        
        assertArrayEquals(expectedFlights, flightLocations);
    }
    
    @Test
    public void testFlightsFail() {
        // Crear una instancia de Iceepeecee con islas pero sin vuelos
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        int[][][] flights = {}; // No se registran vuelos
        
        Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
        
        // Llamar al método flights() en una instancia sin vuelos
        String[] flightLocations = iceepeecee.flights();
        
        // Verificar que la lista de ubicaciones de vuelos esté vacía
        assertEquals(0, flightLocations.length);
    }


    // observedIslands() ---------------------------------------------------- //
    @Test
    public void TestRegisteredIslandsInPhotographs() {
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
        double theta = 48.031693036;
        iceepeecee.photograph(theta);

        String[] islasContenidas = iceepeecee.observedIslands();
        assertTrue(islasContenidas.length > 0);
    }
    // ----------------------------------------------------------------------- //
    
    // uselessFlights() ---------------------------------------------------- //
    @Test
    public void TestFlightsWithUnnecessaryPhotographs() {
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
        double theta = 30;
        iceepeecee.photograph(theta);

        String[] islasContenidas = iceepeecee.uselessFlights();
        assertTrue(islasContenidas.length > 0);
    }
    
    @Test
    public void TestNoRegisteredIslandsInPhotographs() {
        // Crear una instancia de Iceepeecee con islas y vuelos
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
    
        // Definir un ángulo tal que no haya islas contenidas en la fotografía
        double theta = 0.0; // Ángulo que no captura ninguna isla
    
        // Tomar una fotografía con el ángulo especificado
        iceepeecee.photograph(theta);
    
        // Obtener las islas contenidas en la fotografía
        String[] islasContenidas = iceepeecee.observedIslands();
    
        // Verificar que la lista de islas contenidas esté vacía
        assertEquals(0, islasContenidas.length);
    }

    // ----------------------------------------------------------------------- //
}