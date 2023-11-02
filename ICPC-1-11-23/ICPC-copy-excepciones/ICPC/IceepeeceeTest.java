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
    
    // _(length : int, width : int) ------------------------------------ //
    @Test
    public void testConstructorValid() {
        // Creamos un iceepeecee con dimensiones validas
        Iceepeecee iceepeecee = new Iceepeecee(300, 300);
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testConstructorInvalid() {
        // Creamos un iceepeecee con dimensiones invalidas
        Iceepeecee iceepeecee = new Iceepeecee(300, -100);
        // Verificamos que la operación haya fallado
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //

    // addIsland ------------------------------------ //
    @Test
    public void testAddIslandValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos una isla con color y vértices válidos
        iceepeecee.addIsland("brown", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }

    @Test
    public void testAddIslandInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos una isla con color y vértices válidos
        iceepeecee.addIsland("brown", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        // Agregamos una isla vértices inválidos (vertices que ya existen)
        iceepeecee.addIsland("red", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        // Verificamos que la operación haya fallado
        assertFalse(iceepeecee.isOperationSuccess());        
    }
    // ---------------------------------------------- //

    // addFlight ------------------------------------ //
    @Test
    public void testAddFlightValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());        
    }
    
        @Test
    public void testAddFlightInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        // Agregamos un vuelo con vértices inválidos(repetidos)
        iceepeecee.addFlight("brown", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        // Verificamos que la operación haya fallado
        assertFalse(iceepeecee.isOperationSuccess());        
    }
    // ---------------------------------------------- //   
    
    // photograph(String flightColor, double theta) ------------------------------------ //
    @Test
    public void testPhotographColorValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        // Agregamos una fotografia con angulo valido
        iceepeecee.photograph("red",48.26);
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testPhotographColorInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        // Agregamos una fotografia con angulo invalido
        iceepeecee.photograph("blue",-1);
        // Verificamos que la operación haya fallado
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //   
    
    // photograph(double theta) --------------------- //
    
    @Test
    public void testPhotographValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        // Agregamos una fotografia con angulo valido
        iceepeecee.photograph(48.26);
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testPhotographInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        // Agregamos una fotografia con angulo invalido
        iceepeecee.photograph(-1);
        // Verificamos que la operación haya fallado
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //  
    
    // deleteIsland(String color) --------------------- //
    @Test
    public void testDeleteIslandValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos una isla con vértices válidos
        iceepeecee.addIsland("brown", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        // Elimina la isla correspondiente
        iceepeecee.deleteIsland("brown");
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testDeleteIslandInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos una isla con vértices válidos
        iceepeecee.addIsland("brown", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        // Elimina la isla correspondiente
        iceepeecee.deleteIsland("red");
        // Verificamos que la operación haya fallado
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //  
    
    // deleteFlight(String color) --------------------- //
    @Test
    public void testDeleteFlightValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});        
        // Elimina el vuelo correspondiente
        iceepeecee.deleteFlight("red");
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testDeleteFlightInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});          
        // Elimina el vuelo con un color invalido
        iceepeecee.deleteFlight("brown");
        // Verificamos que la operación haya fallado
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //
    
    // islandLocation(String island) --------------------- //
    @Test
    public void testIslandLocationValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos una isla con vértices válidos
        iceepeecee.addIsland("brown", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        // Pedimos la ubicacion de la isla correspondiente
        iceepeecee.islandLocation("brown");
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testIslandLocationInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addIsland("brown", new int[][] {{40, 20}, {60, 10}, {75, 20}, {60, 30}});
        // Pedimos la ubicacion de la isla invalida
        iceepeecee.islandLocation("blue");
        // Verificamos que la operación haya fallado
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //  
    
    // flightLocation(String flight) --------------------- //
    @Test
    public void testFlightLocationValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});         
        // Elimina el vuelo correspondiente
        iceepeecee.flightLocation("red");
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testFlightLocationInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});         
        // Elimina el vuelo correspondiente
        iceepeecee.flightLocation("blue");
        // Verificamos que la operación haya sido exitosa
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //  
    
    // flightCamera(String color)) --------------------- //
    @Test
    public void testFlightCameraValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});  
        
        // Toma una foto
        iceepeecee.photograph("red", 48);
        // Toma otra foto
        iceepeecee.photograph("red", 47);
        
        // Buscamos el angulo de la ultima foto tomada
        iceepeecee.flightCamera("red");
        
        // Angulo esperado
        double expectedAngle = 47;
        // Angulo actual
        double actualAngle = iceepeecee.getTheta();
        // Verificamos que el angulo coincida con el ultimo angulo tomado
        assertEquals(expectedAngle, actualAngle, 0);
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testFlightCameraInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        // Agregamos un vuelo con vértices válidos
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});  
        
        // Toma una foto
        iceepeecee.photograph("red", 48);
        // Toma otra foto
        iceepeecee.photograph("red", -1);
        
        // Buscamos el angulo de la ultima foto tomada
        iceepeecee.flightCamera("red");
        
        // Angulo esperado
        double expectedAngle = -1;
        // Angulo actual
        double actualAngle = iceepeecee.getTheta();
        // Verificamos que el angulo no coincida con el ultimo angulo tomado
        assertNotEquals(expectedAngle, actualAngle, 0);
    }
    // ---------------------------------------------- //  
    
    //  makeVisible() --------------------- //
    @Test
    public void testMakeVisibleValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // Agregamos una isla, un vuelo y una foto
        iceepeecee.addIsland("red", new int[][] {{20, 30}, {50, 50}, {10, 50}});
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        iceepeecee.photograph("red", 48.26);

        // Llamamos al método makeVisible
        iceepeecee.makeVisible();
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testMakeVisibleInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // No agregamos una isla, un vuelo y una foto

        // Llamamos al método makeVisible
        iceepeecee.makeVisible();
        // Verificamos que la operación haya sido exitosa
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //
    
    //  makeInvisible() --------------------- //
    @Test
    public void testMakeInvisibleValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // Agregamos una isla, un vuelo y una foto
        iceepeecee.addIsland("red", new int[][] {{20, 30}, {50, 50}, {10, 50}});
        iceepeecee.addFlight("red", new int[] {0, 30, 20}, new int[] {78, 70, 5});
        iceepeecee.photograph("red", 48.26);

        // Llamamos al método makeVisible
        iceepeecee.makeInvisible();
        // Verificamos que la operación haya sido exitosa
        assertTrue(iceepeecee.isOperationSuccess());
    }
    
    @Test
    public void testMakeInvisibleInvalid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // No agregamos una isla, un vuelo y una foto

        // Llamamos al método makeVisible
        iceepeecee.makeInvisible();
        // Verificamos que la operación haya sido exitosa
        assertFalse(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //
    
    // finish() --------------------- //
    @Test
    public void testFinishValid() {
        // Creamos un iceepeecee
        Iceepeecee iceepeecee = new Iceepeecee(300,300);
        
        // Inicializado antes de llamar a finish
        Shapes.Canvas canvas = Shapes.Canvas.getCanvas();
        assertNotNull(canvas);

        // Llama a finish
        iceepeecee.finish();
        assertTrue(iceepeecee.isOperationSuccess());
    }
    // ---------------------------------------------- //
}
