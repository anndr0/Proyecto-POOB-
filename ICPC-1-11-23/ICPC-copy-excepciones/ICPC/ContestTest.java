package ICPC;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * The test class ContestTest.
 *
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class ContestTest {

    @Test
    public void testSolve() {
        // Define tus datos de prueba (islands y flights)
        int[][][] islands = {
            {{20,30},{50,50},{10,50}},
            {{40,20},{60,10},{75,20},{60,30}},
            {{45,60},{55,55},{60,60},{55,65}}
        };
        int[][][] flights = {
            {{0,30,20},{78,70,5}},
            {{55,0,20},{70,60,10}}
        };

        // Calcula el ángulo usando la función solve
        double result = IceepeeceeContest.solveProblem(islands, flights);

        // Agrega aquí el valor esperado según tus datos de prueba
        double expected = 48.031693036;
        
        // Comprueba que el último valor del "ok" sea "true"
        boolean lastOkValue = IceepeeceeContest.isOperationSuccess();
        assertTrue(lastOkValue);

        // Comprueba si el resultado coincide con el valor esperado
        assertEquals(expected, result, 0.1); // Tolerancia
    }
    
    @Test
    public void testSimulate() {
        int[][][] islands = {
            {{20, 30}, {50, 50}, {10, 50}},
            {{40, 20}, {60, 10}, {75, 20}, {60, 30}},
            {{45, 60}, {55, 55}, {60, 60}, {55, 65}}
        };
        int[][][] flights = {
            {{0, 30, 20}, {78, 70, 5}},
            {{55, 0, 20}, {70, 60, 10}}
        };

        IceepeeceeContest.simulate(islands, flights);
        // Comprueba que el ángulo mínimo calculado es correcto
        double expectedAngle = 48.031693036;
        double actualAngle = IceepeeceeContest.getMinAngle();
        assertEquals(expectedAngle, actualAngle, 0.1);  // Usamos un margen de error pequeño

        // Comprueba que el último valor del "ok" sea "true"
        boolean lastOkValue = IceepeeceeContest.isOperationSuccess();
        assertTrue(lastOkValue);
 
    }
}
