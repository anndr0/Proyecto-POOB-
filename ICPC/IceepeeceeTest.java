import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class IceepeeceeTest {
    private Iceepeecee iceepeecee;
    @Before
    public void setUp() {
        iceepeecee = new Iceepeecee(300, 300); // Creamos una instancia de Iceepeecee con dimensiones válidas
    }
    
    @After
    public void tearDown() {
        iceepeecee.finish(); // Finaliza la simulación después de cada prueba
    }
    
    // addIsland ------------------------------------ //
    @Test
    public void testAddIslandWithValidColorAndVertices() {
        // Agregamos una isla con color y vértices válidos
        iceepeecee.addIsland("brown", new int[][] {{40,20},{60,10},{75,20},{60,30}});

        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());

        // Verificamos que la isla fue agregada correctamente
        Island island = iceepeecee.getIsland("brown");
        assertNotNull(island);
        assertEquals("brown", island.getColor());
    }
    
    @Test
    public void testAddIslandWithInvalidColor() {
        // Agregamos una isla con un color inválido (repetido)
        iceepeecee.addIsland("brown", new int[][] {{50, 50}, {100, 50}, {100, 100}, {50, 100}});

        // Verificamos que la operación no haya sido exitosa
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //
    
    // photograph(double theta) --------------------- //
    @Test
    public void testPhotographWithValidTheta() {
        iceepeecee.addFlight("red", new int[] {0,30,20}, new int[] {78,70,5});
        iceepeecee.photograph(48.26); 
        assertTrue(iceepeecee.isOperationSuccess());
    }

    @Test
    public void testPhotographWithInvalidTheta() {
        iceepeecee.addFlight("blue", new int[] {100, 100, 0}, new int[] {200, 200, 0});
        iceepeecee.photograph(-Math.PI); // Ángulo negativo
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //
    
    // makeVisible() --------------------- //
    @Test
    public void testMakeVisible() {
        // Agregamos una isla y un vuelo para la prueba
        iceepeecee.addIsland("red", new int[][] {{20,30},{50,50},{10,50}});
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        iceepeecee.photograph("red", 48.26);
        // Llamamos al método makeVisible
        iceepeecee.makeVisible();

        // Verificamos si la operación fue exitosa
        assertTrue(iceepeecee.isOperationSuccess());

        // Puedes realizar más aserciones aquí si es necesario
    
    }
    // ---------------------------------------------- //
    
    @Test
    public void testMakeInvisible() {
        // Agregamos una isla y un vuelo para la prueba
        iceepeecee.addIsland("red", new int[][] {{20,30},{50,50},{10,50}});
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        iceepeecee.photograph("red", 48.26);

        iceepeecee.makeInvisible();
        // Verificamos si la operación fue exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    
    }
    
    // locationIsland(String color) --------------------- //
    @Test
    public void testIslandLocation() {
        // Agregar una isla con color y vértices válidos
        iceepeecee.addIsland("brown", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        
        // Obtener la ubicación de la isla
        int[][] location = iceepeecee.islandLocation("brown");
        
        // Verificar que la operación haya sido exitosa (la isla existe)
        assertTrue(iceepeecee.isOperationSuccess());
        
        // Verificar que la ubicación sea la esperada
        int[][] expectedLocation = {{40, 20}, {60, 10}, {75, 20}, {60, 30}};
        assertArrayEquals(expectedLocation, location);
    }
    
    @Test
    public void testIslandLocationWithInvalidColor() {
        // Intentar obtener la ubicación de una isla con un color inválido
        int[][] location = iceepeecee.islandLocation("nonExistentColor");
        
        // Verificar que la operación no haya sido exitosa (la isla no existe)
        assertFalse(iceepeecee.isOperationSuccess());
        
        // Verificar que la ubicación sea nula (no se encontró la isla)
        assertNull(location);
    }
    // ---------------------------------------------- //
    
    // locationFlight(String color) --------------------- //
    @Test
    public void testFlightLocation() {
        iceepeecee.addFlight("red", new int[] { 100, 100, 0 }, new int[] { 200, 200, 0 });
        iceepeecee.addFlight("blue", new int[] { 300, 300, 0 }, new int[] { 400, 400, 0 });
        // Verifica si el método flightLocation devuelve la ubicación correcta de un vuelo
        int[][] locationRed = iceepeecee.flightLocation("red");
        int[][] locationBlue = iceepeecee.flightLocation("blue");

        assertNotNull(locationRed);
        assertNotNull(locationBlue);

        // Verifica las coordenadas de inicio para los vuelos "red" y "blue"
        assertArrayEquals(new int[] { 100, 100, 0 }, locationRed[0]);
        assertArrayEquals(new int[] { 300, 300, 0 }, locationBlue[0]);

        // Verifica las coordenadas de destino para los vuelos "red" y "blue"
        assertArrayEquals(new int[] { 200, 200, 0 }, locationRed[1]);
        assertArrayEquals(new int[] { 400, 400, 0 }, locationBlue[1]);
    }
    
    @Test
    public void testFlightLocationWrong() {
        iceepeecee.addFlight("red", new int[] { 100, 100, 0 }, new int[] { 200, 200, 0 });
        iceepeecee.addFlight("blue", new int[] { 300, 300, 0 }, new int[] { 400, 400, 0 });
        // Verifica si el método flightLocation devuelve la ubicación correcta de un vuelo existente
        int[][] locationRed = iceepeecee.flightLocation("red");
        int[][] locationBlue = iceepeecee.flightLocation("blue");

        assertNotNull(locationRed);
        assertNotNull(locationBlue);

        // Verifica las coordenadas de inicio para los vuelos "red" y "blue"
        assertArrayEquals(new int[] { 100, 100, 0 }, locationRed[0]);
        assertArrayEquals(new int[] { 300, 300, 0 }, locationBlue[0]);

        // Verifica las coordenadas de destino para los vuelos "red" y "blue"
        assertArrayEquals(new int[] { 200, 200, 0 }, locationRed[1]);
        assertArrayEquals(new int[] { 400, 400, 0 }, locationBlue[1]);

        // Intenta obtener la ubicación de un vuelo que no existe (esto debería fallar)
        int[][] locationNonExistent = iceepeecee.flightLocation("nonexistent");
        assertNull(locationNonExistent); // Esperamos que esto sea nulo (fallo)
    }
    // ---------------------------------------------- //
    
    
    // photograph(String flightColor, double theta) --------------------- //
    @Test
    public void testPhotograph() {
        String flightColor = "red";
        double theta = 48.26; 

        int[] from = {0, 30, 20};
        int[] to = {78, 70, 5};
        iceepeecee.addFlight(flightColor, from, to);
        
        iceepeecee.photograph(flightColor, theta);

        assertTrue(iceepeecee.isOperationSuccess());

    }
    // ---------------------------------------------------------------- //
    
    
    // Iceepeecee(int[][][] islands, int[][][] flights) --------------------- //
    @Test
    public void testIceepeeceeProblemF() {
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
    // ----------------------------------------------------------------------- //
    
    @Test
    public void testIslasContenidasEnFotografias() {
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
        double theta = 48.26;
        iceepeecee.photograph(theta);
        // Llama al método que deseas probar
        String[] islasContenidas = iceepeecee.islasContenidasEnFotografias();

        // Realiza afirmaciones (assertions) para verificar el resultado esperado
        // Supongamos que esperamos que algunas islas estén contenidas en las fotografías
        assertTrue(islasContenidas.length > 0);
    }
}
