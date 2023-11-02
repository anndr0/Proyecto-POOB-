package ICPC;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class IceepeeceeCC4test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class IceepeeceeCC4test
{
    
    @Test
    public void testFixedValid(){
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // creamos una fixed island
        iceepeecee.addIsland("fixed","orange", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        
        // intentamos eliminarla
        iceepeecee.deleteIsland("orange");
        // OperationSuccess es true porque efectivamente la isla no se puede eliminar
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testSurprisingValid(){
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // creamos una fixed island
        iceepeecee.addIsland("surprising","orange", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        iceepeecee.islandLocation("orange");
        
        // Obtenemos la cantidad de lados de la isla
        int[][] vertices = iceepeecee.islandLocation("orange");
        int ladoCount = vertices.length;

        // Verificamos que la isla tenga 3 lados
        int expectedSides = 3;
        assertTrue(iceepeecee.isOperationSuccess());
        assertEquals(expectedSides, ladoCount);
    }
    @Test
    public void testSurprisingInvalid(){
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // creamos una fixed island
        iceepeecee.addIsland("surprising","lightGray", new int[][] {{40, 20}, {60, 10}, {75, 20}});
        iceepeecee.islandLocation("orange");
        
        // Obtenemos la cantidad de lados de la isla
        int[][] vertices = iceepeecee.islandLocation("lightGray");
        int ladoCount = vertices.length;

        // Verificamos que la isla tenga 3 lados -> no se quitan lados ya que el minimo es 3
        int expectedSides = 3;
        assertTrue(iceepeecee.isOperationSuccess());
        assertEquals(expectedSides, ladoCount);
    }
    
    @Test
    public void testLazyValid(){
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // creamos una lazy flight
        iceepeecee.addFlight("lazy","lightGray", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        
        // Tomamos una fotografia
        iceepeecee.photograph(45);
        
        // Intentamos tomar otra fotografia       
        iceepeecee.photograph(48);
        
        // No se toman mas fotografias, por lo que la operacion es exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testFlatValid(){
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // creamos una lazy flight
        iceepeecee.addFlight("flat","lightGray", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        iceepeecee.makeVisible();
        int initialZ = iceepeecee.getTo("lightGray")[2];
        iceepeecee.photograph(45);
        int finalZ = iceepeecee.getTo("lightGray")[2];

        // Verificamos que la coordenada z no cambie al tomar la fotografía
        assertEquals(initialZ, finalZ);
        assertTrue(iceepeecee.isOperationSuccess());
    }
}
