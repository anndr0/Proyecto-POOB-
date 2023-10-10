import java.util.List;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * The Iceepeecee class represents an environment for managing islands, flights, and photographs.
 * It allows users to add, delete, and interact with these elements within a graphical canvas.
 * 
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class Iceepeecee {
    private int length;
    private int width;
    private boolean isVisible;
    private HashMap<String, Island> islands;
    private HashMap<String, Flight> flights;
    private Canvas canvas;
    private boolean operationSuccess;
    private List<Photograph> photographs;
    
   

    /**
     * Constructs an Iceepeecee object with the specified canvas dimensions.
     * 
     * @param length The length of the canvas (must be positive).
     * @param width  The width of the canvas (must be positive).
     */
    public Iceepeecee(int length, int width) {
        if (length > 0 && width > 0) {
            this.length = length; // Inicializa la longitud del canvas
            this.width = width;   // Inicializa el ancho del canvas
            canvas = Canvas.getCanvas(length, width);
            canvas.setVisible(true);
            this.islands = new HashMap<>();
            this.flights = new HashMap<>();
            isVisible = false;
            operationSuccess = true;
            ok();
        } else {
            operationSuccess = false;
            ok();
            
        }
    }
    
    public Iceepeecee(int[][][] islands, int[][][] flights) {
        this.length = 300;
        this.width = 300; 
        canvas = Canvas.getCanvas(length, width);
        canvas.setVisible(true);
        
        this.islands = new HashMap<>();
        this.flights = new HashMap<>();
        isVisible = false;
        operationSuccess = true;
        ok();
        if (islands != null && flights != null) {
        
            for (int i = 0; i < islands.length; i++) {
                int[][] islandVertices = islands[i];
                String islandColor = getColorForIndex(i); // Obtener el color según el índice
                addIsland(islandColor, islandVertices);
            }
            
            for (int i = 0; i < flights.length; i++) {
                
                int[] from = flights[i][0];
                int[] to = flights[i][1];
                String flightColor = getColorForIndex(i); // Obtener el color según el índice
                addFlight(flightColor, from, to);
            }
            ok();
        } else {
            operationSuccess = false; // Indicar que la inicialización no fue exitosa
            ok();
        }
    }


    
    /**
     * Add an island to Iceepeecee.
     * Do not repeat the color pls( •ㅅ•)
     * -----
     *  Valid colors are: 
     *  "red", "green", "blue", "yellow", "purple", "cyan", "pink", "orange", 
     *  "brown", "gray", "magenta", "white", "lightBlue", "lime", "gold", "teal", "violet", "coral", "lavender", 
     *  "olive", "maroon", "turquoise", "navyBlue", "bistre", "burgundy", "crimson", "lightCyan", "cobalt", 
     *  "fuchsia", "garnet", "lightGray", "darkGray", "indigo", "lightLilac", "lightLime", "lightMagenta", 
     *  "lightBrown", "darkBrown", "lightOrange", "darkOrange", "lightGold", "darkGold", "silver", "lightPink", 
     *  "darkPink", "darkTurquoise", "lightGreen", "darkGreen", "lightViolet", "darkViolet"
     *  ----
     * @param color       The color of the island.
     * @param vertexArray The vertices of the island.
     */
    public void addIsland(String color, int[][] vertexArray) {
        if (!islands.containsKey(color)) {
            if (isWithinCanvasBounds(vertexArray)) {
                Island island = new Island(color, vertexArray);
                islands.put(color, island);
                operationSuccess = true; // Indicar que la adición fue exitosa
                ok();
            } else {
                operationSuccess = false; // Indicar que la adición no fue exitosa debido a ubicación fuera de los límites del canvas
                ok();
            }
        } else {
            operationSuccess = false; // Indicar que la adición no fue exitosa debido a repetición de color
            ok();
        }
    }
    
    /**
     * Get an island by its color.
     * 
     * @param color The color of the island to retrieve.
     * @return The Island object with the specified color, or null if not found.
     */
    public Island getIsland(String color) {
        return islands.get(color);
    }
    
    public Flight getFlight(String color) {
        return flights.get(color);
    }
    
    /**
     * Delete an island from Iceepeecee.
     * ╰(◣﹏◢)╯
     * @param color The color of the island to delete.
     */
    public void deleteIsland(String color) {
        Island island = islands.get(color);
        if (island != null) {
            islands.remove(color);
            island.delIsland(color); // Calls the method to delete the island in Island
            operationSuccess = true;
            ok();
        } else{
            operationSuccess = false;
            ok();
        }
    }
    
    /**
     * Make the island with the specified color visible.
     * 
     * @param color The color of the island to make visible.
     */
    public void makeIslandVisible(String color) {
        Island island = islands.get(color);
        if (island != null) {
            island.makeIslandVisible(color); // Make the polygon of the specified island visible
            operationSuccess = true;
            ok();
        } else{
            operationSuccess = false;
            ok();
        }
    }

    /**
     * Make the island with the specified color invisible.
     * 
     * @param color The color of the island to make invisible.
     */
    public void makeIslandInvisible(String color) {
        Island island = islands.get(color);
        if (island != null) {
            island.makeIslandInvisible(color); // Make the polygon of the specified island invisible
            operationSuccess = true;
            ok();
        }else{
            operationSuccess = false;
            ok();
        }
    } 
    
    
    /**
     * Get the location of an island by color.
     * 
     * @param island The color of the island to query.
     * @return An array of coordinates representing the location of the island, or null if not found.
     */
    public int[][] islandLocation(String island) {
        Island islandObj = islands.get(island);
        if (islandObj != null) {
            operationSuccess = true; // Indicate that the island location retrieval was successful
            ok();
            return islandObj.locationIsland(island);
        } else {
            operationSuccess = false; // Indicate that the island location retrieval was not successful
            ok();
            return null; // Returns null if the island does not exist
        }
    }

    
    /**
     * Add a flight to Iceepeecee.
     * Do not repeat the color pls( •ㅅ•)
     *  -----
     *  Valid colors are: 
     *  "red", "green", "blue", "yellow", "purple", "cyan", "pink", "orange", 
     *  "brown", "gray", "magenta", "white", "lightBlue", "lime", "gold", "teal", "violet", "coral", "lavender", 
     *  "olive", "maroon", "turquoise", "navyBlue", "bistre", "burgundy", "crimson", "lightCyan", "cobalt", 
     *  "fuchsia", "garnet", "lightGray", "darkGray", "indigo", "lightLilac", "lightLime", "lightMagenta", 
     *  "lightBrown", "darkBrown", "lightOrange", "darkOrange", "lightGold", "darkGold", "silver", "lightPink", 
     *  "darkPink", "darkTurquoise", "lightGreen", "darkGreen", "lightViolet", "darkViolet"
     *  ----
     * @param color The color of the flight.
     * @param from  The starting coordinates [x1, y1, z1].
     * @param to    The ending coordinates [x2, y2, z2].
     */
    public void addFlight(String color, int[] from, int[] to) {
        if (!flights.containsKey(color)) {
            Flight flight = new Flight(color, from, to);
            flights.put(color, flight);
            operationSuccess = true; // Indicar que la adición fue exitosa
            ok();
        } else {
            operationSuccess = false; // Indicar que la adición no fue exitosa debido a repetición de color
            ok();
        }
    }
    
    /**
     * Delete a flight from Iceepeecee.
     * ╰(◣﹏◢)╯ 
     * @param color The color of the flight to delete.
     */
    public void deleteFlight(String color) {
        Flight flight = flights.get(color);
        if (flight != null) {
            // Eliminar las fotografías asociadas al vuelo
            List<Photograph> photographs = flight.getPhotographs();
            for (Photograph photograph : photographs) {
                photograph.makeInvisible(); // Hacer invisible la fotografía
            }
            photographs.clear(); // Borrar todas las fotografías de la lista
    
            // Finalmente, eliminar el vuelo
            flights.remove(color);
            flight.deleteFlight(color); // Calls the method to delete the flight in Flight
            operationSuccess = true;
            ok();
        } else {
            operationSuccess = false;
            ok();
        }
    }

    
    /**
     * Get the location of a flight by color.
     * 
     * @param flight The color of the flight to query.
     * @return A string representing the location of the flight.
     */
    public int[][] flightLocation(String flight) {
        Flight flightObj = flights.get(flight);
        if (flightObj != null) {
            operationSuccess = true; // Indicate that the flight location retrieval was successful
            ok();
            return flightObj.locationFlight(flight);
        } else {
            operationSuccess = false; // Indicate that the flight location retrieval was not successful
            ok();
            return null; // Returns a message if the flight does not exist
        }
    }
    
        /**
     * Make the flight visible by its color.
     * 
     * @param color The color of the flight to make visible.
     */
    public void makeFlightVisible(String color) {
        Flight flight = flights.get(color);
        if (flight != null) {
            flight.isVisible = true;
            List<Photograph> photographs = flight.getPhotographs();
            for (Photograph photograph : photographs) {
                photograph.makeVisible(); // Hacer invisible la fotografía
            }
            flight.draw();
            operationSuccess = true;
            ok();
        } else {
            operationSuccess = false; 
            ok();
        }
    }
    
    /**
     * Make the flight invisible by its color.
     * 
     * @param color The color of the flight to make invisible.
     */
    public void makeFlightInvisible(String color) {
        Flight flight = flights.get(color);
        if (flight != null) {
            flight.isVisible = false;
            Canvas canvas = Canvas.getCanvas();
            List<Photograph> photographs = flight.getPhotographs();
            for (Photograph photograph : photographs) {
                photograph.makeInvisible(); // Hacer invisible la fotografía
            }
            canvas.erase(flight);
            operationSuccess = true;
            ok();
        } else {
            operationSuccess = false; 
            ok();
        }
    }
    
    /**
     * Capture a photograph from a specific flight at the given angle (theta).
     * 
     * @param flightColor The color of the flight to capture a photograph from.
     * @param theta       The angle (in radians) at which the photograph is taken.
     */
    public void photograph(String flightColor, double theta) {
        Flight flight = flights.get(flightColor); 
        if (flight != null) {
            flight.camera(flightColor, theta); 
            operationSuccess = true;
            ok();
        } else {
            System.out.println("Flight not found"); 
            operationSuccess = false;
            ok();
        }
    }
    
    /**
     * Capture photographs from all flights in Iceepeecee at the given angle (theta).
     * 
     * @param theta The angle (in radians) at which the photographs are taken.
     */
    public void photograph(double theta) {
        operationSuccess = true; 
        if (theta >= 0) { 
            for (Flight flight : flights.values()) {
                String color = flight.getColor(); 
                flight.camera(color, theta); 
                if (flight.ok()) {
                    operationSuccess = false;
                }
            }
        } else {
            operationSuccess = false; 
        }
        ok(); 
    }

    
    /**
     * Make all elements in Iceepeecee visible, including flights, islands, and photographs.
     */
    public void makeVisible() {
        operationSuccess = false;
        // Make all flights visible
        for (Flight flight : flights.values()) {
            String color = flight.getColor();
            flight.makeFlightVisible(color);
            if (flight.ok()) {
                operationSuccess = true;
            }
        }

        // Make all islands visible
        for (Island island : islands.values()) {
            String color = island.getColor();
            island.makeIslandVisible(color);
            if (island.ok()) {
                operationSuccess = true;
            }
        }
        
        // Make all photographs visible
        for (Flight flight : flights.values()) {
            List<Photograph> photographs = flight.getPhotographs();
            for (Photograph photograph : photographs) {
                photograph.makeVisible(); 
                if (photograph.ok()) {
                    operationSuccess = true;
                }
            }
        }
        
        ok();
    }

    /**
     * Make all elements in Iceepeecee invisible, including flights, islands, and photographs.
     */
    public void makeInvisible() {
        operationSuccess = false;
        // Make all flights invisible
        for (Flight flight : flights.values()) {
            String color = flight.getColor();
            flight.makeFlightInvisible(color);
            if (flight.ok()) {
                operationSuccess = true;
            }
        }
        // Make all islands invisible
        for (Island island : islands.values()) {
            String color = island.getColor();
            island.makeIslandInvisible(color);
            if (island.ok()) {
                operationSuccess = true;
            }
        }
        // Make all photographs invisible
        for (Flight flight : flights.values()) {
            List<Photograph> photographs = flight.getPhotographs();
            for (Photograph photograph : photographs) {
                photograph.makeInvisible(); 
                if (photograph.ok()) {
                    operationSuccess = true;
                }
            }
        }
        ok();
    }
    
    public double flightCamera(String color) {
        Flight flight = flights.get(color); 
        if (flight != null) {
            List<Photograph> photographs = flight.getPhotographs();
            if (!photographs.isEmpty()) {
                Photograph lastPhotograph = photographs.get(photographs.size() - 1);
                return lastPhotograph.getTheta();
            } else {
                System.out.println("No photographs taken for this flight.");
            }
        } else {
            System.out.println("Flight not found");
        }
        return 0.0;
    }
    
    /**
     * Muestra la información de todas las islas almacenadas en Iceepeecee.
     */
    public String[] islands() {
        List<String> islandLocations = new ArrayList<>();
    
        for (Island island : islands.values()) {
            String color = island.getColor();
            int[][] location = island.locationIsland(color);
    
            if (location != null) {
                StringBuilder locationString = new StringBuilder("Color: " + color + " Location: [");
                for (int i = 0; i < location.length; i++) {
                    locationString.append("[").append(location[i][0]).append(", ").append(location[i][1]).append("]");
                    if (i < location.length - 1) {
                        locationString.append(", ");
                    }
                }
                locationString.append("] ");
                islandLocations.add(locationString.toString());
            }
        }
    
        String[] islandLocationsArray = new String[islandLocations.size()];
        islandLocations.toArray(islandLocationsArray);
    
        return islandLocationsArray;
    }

    
    /**
     * Muestra la información de todos los vuelos almacenados en Iceepeecee.
     */
    public String[] flights() {
        List<String> flightLocations = new ArrayList<>();
    
        for (Flight flight : flights.values()) {
            String color = flight.getColor();
            int[][] location = flight.locationFlight(color);
    
            if (location != null) {
                StringBuilder locationString = new StringBuilder("Color: " + color + " Location: [");
                for (int i = 0; i < location.length; i++) {
                    locationString.append("[").append(location[i][0]).append(", ").append(location[i][1]).append(", ").append(location[i][2]).append("]");
                    if (i < location.length - 1) {
                        locationString.append(", ");
                    }
                }
                locationString.append("] ");
                flightLocations.add(locationString.toString());
            }
        }
    
        String[] flightLocationsArray = new String[flightLocations.size()];
        flightLocations.toArray(flightLocationsArray);
    
        return flightLocationsArray;
    }

    
    /**
     * Verifica si los vértices de la isla están dentro de los límites del canvas.
     * 
     * @param vertexArray The vertices of the island.
     * @return true si los vértices están dentro del canvas, false en caso contrario.
     */
    private boolean isWithinCanvasBounds(int[][] vertexArray) {
        for (int[] vertex : vertexArray) {
            int x = vertex[0];
            int y = vertex[1];
            if (x < 0 || x >= length || y < 0 || y >= width) {
                return false; 
            }
        }
        return true;
    }
    
        /**
     * Verifica si un conjunto de coordenadas está dentro de los límites del canvas.
     *
     * @param coordinates Las coordenadas a verificar [x, y, z].
     * @return true si las coordenadas están dentro del canvas, false si no lo están.
     */
    private boolean isWithinCanvasBounds(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        int z = coordinates[2];
        return x >= 0 && x <= length && y >= 0 && y <= width && z >= 0;
    }
    


    
    private String getColorForIndex(int index) {
        String[] colors = {
            "red", "green", "blue", "yellow", "purple", "cyan", "pink", "orange", "brown", "gray",
            "magenta", "white", "lightBlue", "lime", "gold", "teal", "violet", "coral", "lavender", "olive",
            "maroon", "turquoise", "navyBlue", "bistre", "navy blue", "burgundy", "crimson", "lightCyan",
            "cobalt", "fuchsia", "garnet", "lightGray", "darkGray", "indigo", "lightLilac", "lightLime",
            "lightMagenta", "lightBrown", "darkBrown", "lightOrange", "darkOrange", "lightGold", "darkGold",
            "silver", "lightPink", "darkPink", "darkTurquoise", "lightGreen", "darkGreen", "lightViolet", "darkViolet"
        };
        if (index >= 0 && index < colors.length) {
            return colors[index];
        } else {
            // Si el índice está fuera de rango, regresar un color por defecto
            return "black"; 
        }
    }
    
    /**
     * Muestra una ventana de diálogo con un mensaje informativo.
     * 
     * @param message El mensaje informativo a mostrar.
     */
    private void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra una ventana de diálogo con un mensaje de error.
     * 
     * @param message El mensaje de error a mostrar.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Verifica si la última operación en la simulación fue exitosa.
     */
    public void ok() {
        if (operationSuccess) {
            showInfoMessage("Operación exitosa.");
        } else {
            showErrorMessage("Operación no exitosa.");
        }
    }
    
    public boolean isOperationSuccess() {
        return operationSuccess;
    }
    
    /**
     * Finaliza la simulación y cierra el lienzo gráfico.
     */
    public void finish() {
        Canvas canvas = Canvas.getCanvas(); // Obtiene la instancia del lienzo gráfico
        if (canvas != null) {
            canvas.setVisible(false); // Oculta el lienzo gráfico
            canvas = null; // Establece el lienzo en null para liberar recursos
            System.out.println("Simulación finalizada.");
        }
    }
    
    public int[][] getIslandVertices(String islandColor) {
        Island island = islands.get(islandColor);
        if (island != null) {
            return island.getVertexArray();
        }
        return null; // Otra opción es devolver una matriz vacía en lugar de null si lo prefieres
    }

    public String[] islasContenidasEnFotografias() {
        List<String> islasContenidas = new ArrayList<>();
        
        // Itera a través de todas las islas
        for (Island island : islands.values()) {
            String islandColor = island.getColor();
            int[][] islandVertices = getIslandVertices(islandColor);
            
            // Verifica si la isla tiene vértices antes de continuar
            if (islandVertices != null && islandVertices.length > 0) {
                // Convierte los vértices de la isla en una lista de puntos
                List<Point> islandPoints = convertToPoints(islandVertices);
                
                // Itera a través de todas las fotografías
                for (Flight flight : flights.values()) {
                    List<Photograph> photographs = flight.getPhotographs();
                    
                    for (Photograph photograph : photographs) {
                        List<Point> photographVertices = photograph.getVertices();
                        
                        // Verifica si la isla está completamente contenida en la fotografía
                        if (isPolygonInsidePolygon(islandPoints, photographVertices)) {
                            islasContenidas.add(islandColor);
                            break; // Puedes omitir la búsqueda en otras fotografías
                        }
                    }
                }
            }
        }
        
        // Convierte la lista de islas contenidas en un arreglo de String
        String[] islasContenidasArray = islasContenidas.toArray(new String[0]);
        
        return islasContenidasArray;
    }
    
    /**
     * Convierte una matriz de vértices en una lista de puntos.
     * 
     * @param vertices La matriz de vértices.
     * @return Una lista de puntos.
     */
    private List<Point> convertToPoints(int[][] vertices) {
        List<Point> points = new ArrayList<>();
        for (int[] vertex : vertices) {
            if (vertex.length == 2) {
                points.add(new Point(vertex[0], vertex[1]));
            }
        }
        return points;
    }
    
    /**
     * Verifica si un polígono está completamente contenido dentro de otro polígono.
     * 
     * @param innerPolygon Los vértices del polígono interno.
     * @param outerPolygon Los vértices del polígono externo.
     * @return true si el polígono interno está completamente contenido en el externo, false en caso contrario.
     */
    private boolean isPolygonInsidePolygon(List<Point> innerPolygon, List<Point> outerPolygon) {
        for (Point point : innerPolygon) {
            if (!isPointInsidePolygon(point, outerPolygon)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Verifica si un punto dado está dentro de un polígono representado por una lista de vértices.
     * 
     * @param point   El punto a verificar como un objeto Point.
     * @param vertices La lista de vértices del polígono.
     * @return true si el punto está dentro del polígono, false en caso contrario.
     */
    public static boolean isPointInsidePolygon(Point point, List<Point> vertices) {
        int intersectCount = 0;
        double x1, x2, y1, y2;
    
        Point p1 = vertices.get(0);
        for (int i = 1; i <= vertices.size(); i++) {
            Point p2 = vertices.get(i % vertices.size());
    
            x1 = p1.getX();
            x2 = p2.getX();
            y1 = p1.getY();
            y2 = p2.getY();
    
            if (point.getY() > Math.min(y1, y2) && point.getY() <= Math.max(y1, y2) && point.getX() <= Math.max(x1, x2)
                    && y1 != y2) {
                double xIntersect = (point.getY() - y1) * (x2 - x1) / (y2 - y1) + x1;
                if (x1 == x2 || point.getX() <= xIntersect) {
                    intersectCount++;
                }
            }
            p1 = p2;
        }
    
        return intersectCount % 2 != 0;
    }

}
