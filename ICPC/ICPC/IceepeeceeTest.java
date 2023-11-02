
package ICPC;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class IceepeeceeTest.
 *
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
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
/*
    // addIsland ------------------------------------ //
    @Test
    public void testAddIslandWithValidColorAndVertices() {
        // Agregamos una isla con color y vértices válidos
        try {
            iceepeecee.addIsland("brown", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});

            // Verificamos que la operación haya sido exitosa
            assertTrue(true);
        } catch (IceepeeceeException e) {
            fail("Se produjo una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testAddIslandWithInvalidColor() {
        // Agregamos una isla con un color inválido (repetido)
        try {
            iceepeecee.addIsland("brown", new int[][] {{50, 50}, {100, 50}, {100, 100}, {50, 100}});
            fail("Se esperaba una excepción IceepeeceeException");
        } catch (IceepeeceeException e1) {
            assertEquals("El color brown ya se ha utilizado para otra isla.", e1.getMessage());
        }
    }
    // ---------------------------------------------- //
/*
    // photograph(double theta) --------------------- //
    @Test
    public void testPhotographWithValidTheta() {
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        iceepeecee.photograph(48.26);
        assertTrue(true);
    }

    @Test
    public void testPhotographWithInvalidTheta() {
        iceepeecee.addFlight("blue", new int[] {100, 100, 0}, new int[] {200, 200, 0});
        iceepeecee.photograph(-Math.PI); // Ángulo negativo
    }
    // ---------------------------------------------- //
*/
    // makeVisible() --------------------- //
    @Test
    public void testMakeVisible() {
        // Agregamos una isla y un vuelo para la prueba
        try {
            iceepeecee.addIsland("red", new int[][] {{20, 30}, {50, 50}, {10, 50}});
            iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
            iceepeecee.photograph("red", 48.26);
        } catch (IceepeeceeException e) {
            fail("Se produjo una excepción inesperada: " + e.getMessage());
        }

        // Llamamos al método makeVisible
        iceepeecee.makeVisible();
        assertTrue(true);
        
        // Puedes realizar más aserciones aquí si es necesario
    }
    // ---------------------------------------------- //
}
