
import java.util.List;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;

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
            //ok();
        } else {
            operationSuccess = false;
            //ok();
        }
    }
    
    /**
     * Constructs an instance of the Iceepeecee simulator with specified islands and flights data.
     *
     * @param islands An array representing the islands with their three-dimensional coordinates.
     * @param flights An array representing the flights with coordinates of starting and ending points.
     * @throws IceepeeceeException If an exception related to Iceepeecee operations occurs during initialization.
     */
    public Iceepeecee(int[][][] islands, int[][][] flights) throws IceepeeceeException {
        this.length = 300;
        this.width = 300;
        canvas = Canvas.getCanvas(length, width);
        canvas.setVisible(true);
    
        this.islands = new HashMap<>();
        this.flights = new HashMap<>();
        isVisible = false;
    
        operationSuccess = true;
    
        if (islands != null && flights != null) {
            for (int i = 0; i < islands.length; i++) {
                int[][] islandVertices = islands[i];
                String islandColor = getColorForIndex(i); // Obtener el color según el índice
                try {
                    addIsland(islandColor, islandVertices);
                } catch (IceepeeceeException e) {
                    operationSuccess = false; // Indicar que la inicialización no fue exitosa
                    throw e; // Relanzar la excepción hacia el llamador
                }
            }
    
            for (int i = 0; i < flights.length; i++) {
                int[] from = flights[i][0];
                int[] to = flights[i][1];
                String flightColor = getColorForIndex(i); // Obtener el color según el índice
                addFlight(flightColor, from, to);
            }
            //ok();
        } else {
            operationSuccess = false; // Indicar que la inicialización no fue exitosa
            //ok();
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
    public void addIsland(String color, int[][] vertexArray) throws IceepeeceeException {
        if (!islands.containsKey(color)) {
            addNormalIsland(color, vertexArray);
            operationSuccess = true;
        } else {
            operationSuccess = false;
            throw new IceepeeceeException("El color " + color + " ya se ha utilizado para otra isla.");
        }
    }
    
    public void addIsland(String type, String color, int[][] vertexArray) throws IceepeeceeException {
        if (type.equals("normal")) {
            addNormalIsland(color, vertexArray);
        }else if (type.equals("fixed")) {
            addFixedIsland(color, vertexArray);
        } else if (type.equals("surprising")) {
            addSurprisingIsland(color, vertexArray);
        }else if (type.equals("mistery")) {
            addMisteryIsland(color, vertexArray);
        } else {
            throw new IceepeeceeException("Tipo de isla desconocido: " + type);
        }
    }
    
    private void addNormalIsland(String color, int[][] vertexArray) throws IceepeeceeException {
        if (!islands.containsKey(color)) {
            if (isWithinCanvasBounds(vertexArray)) {
                Island island = new Island(color, vertexArray);
                islands.put(color, island);
                operationSuccess = true; // Indicar que la adición fue exitosa
            } else {
                operationSuccess = false; // Indicar que la adición no fue exitosa debido a ubicación fuera de los límites del canvas
            }
        } else {
            throw new IceepeeceeException("El color " + color + " ya se ha utilizado para otra isla.");
        }
    }
    
    private void addFixedIsland(String color, int[][] vertexArray) throws IceepeeceeException {
        if (!islands.containsKey(color)) {
            if (isWithinCanvasBounds(vertexArray)) {
                FixedIsland fixedIsland = new FixedIsland(color, vertexArray);
                islands.put(color, fixedIsland);
                operationSuccess = true; // Indicar que la adición fue exitosa
            } else {
                operationSuccess = false; // Indicar que la adición no fue exitosa debido a ubicación fuera de los límites del canvas
            }
        } else {
            throw new IceepeeceeException("El color " + color + " ya se ha utilizado para otra isla.");
        }
    }
    
    private void addSurprisingIsland(String color, int[][] vertexArray) throws IceepeeceeException {
        if (!islands.containsKey(color)) {
            if (isWithinCanvasBounds(vertexArray)) {
                SurprisingIsland surprisingIsland = new SurprisingIsland(color, vertexArray);
                islands.put(color, surprisingIsland);
                operationSuccess = true; // Indicar que la adición fue exitosa
            } else {
                operationSuccess = false; // Indicar que la adición no fue exitosa debido a ubicación fuera de los límites del canvas
            }
        } else {
            throw new IceepeeceeException("El color " + color + " ya se ha utilizado para otra isla.");
        }
    }
    
    private void addMisteryIsland(String color, int[][] vertexArray) throws IceepeeceeException {
        if (!islands.containsKey(color)) {
            if (isWithinCanvasBounds(vertexArray)) {
                MisteryIsland misteryIsland = new MisteryIsland(color, vertexArray);
                islands.put(color, misteryIsland);
                operationSuccess = true; // Indicar que la adición fue exitosa
            } else {
                operationSuccess = false; // Indicar que la adición no fue exitosa debido a ubicación fuera de los límites del canvas
            }
        } else {
            throw new IceepeeceeException("El color " + color + " ya se ha utilizado para otra isla.");
        }
    }

    /**
     * Get an island by its color.
     * 
     * @param color The color of the island to retrieve.
     * @return The Island object with the specified color, or null if not found.
     */
    private Island getIsland(String color) {
        return islands.get(color);
    }
    public HashMap getIslands (){
        return this.islands;
    }
    
    /**
     * Retrieves the flight object associated with the specified color.
     *
     * @param color The color identifier of the flight.
     * @return The Flight object corresponding to the provided color, or null if not found.
     */
    private Flight getFlight(String color) {
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
            if (island instanceof FixedIsland) {
                // Si es una FixedIsland, solo muestra el mensaje sin eliminarla
                ((FixedIsland)island).delIsland(color);
            } else {
                islands.remove(color);
                island.delIsland(color); // Llama al método para eliminar la isla en Island
                operationSuccess = true;
            }
        } else {
            operationSuccess = false;
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
            //ok();
        } else{
            operationSuccess = false;
            //ok();
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
            //ok();
        }else{
            operationSuccess = false;
            //ok();
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
            //ok();
            int [][] ubicacion = islandObj.locationIsland(island);
            makeVisible();
            return ubicacion;
        } else {
            operationSuccess = false; // Indicate that the island location retrieval was not successful
            //ok();
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
            //ok();
        } else {
            operationSuccess = false; // Indicar que la adición no fue exitosa debido a repetición de color
            //ok();
        }
    }
    
    public void addFlight(String type, String color, int[] from, int[] to){
        if (type.equals("normal")) {
            addNormalFlight(color, to,from);
        }else if (type.equals("lazy")) {
            addLazyFlight(color, to,from);
        } else if (type.equals("flat")) {
            addFlatFlight(color, to,from);
        }else {
            operationSuccess = false; // Indicar que la adición no fue exitosa debido a repetición de color
            //ok();
        }
    }
    
    private void addNormalFlight (String color, int[] from, int[] to){
        if (!flights.containsKey(color)) {
            Flight flight = new Flight(color, from, to);
            flights.put(color, flight);
            operationSuccess = true; // Indicar que la adición fue exitosa
            //ok();
        } else {
            operationSuccess = false; // Indicar que la adición no fue exitosa debido a repetición de color
            //ok();
        }
    }
    
    private void addLazyFlight (String color, int[] from, int[] to){
        if (!flights.containsKey(color)) {
            Flight lazyflight = new LazyFlight(color, from, to);
            flights.put(color, lazyflight);
            operationSuccess = true; // Indicar que la adición fue exitosa
            //ok();
        } else {
            operationSuccess = false; // Indicar que la adición no fue exitosa debido a repetición de color
            //ok();
        }
    }
    
    private void addFlatFlight (String color, int[] from, int[] to){
        if (!flights.containsKey(color)) {
            Flight flatflight = new FlatFlight(color, from, to);
            flights.put(color, flatflight);
            operationSuccess = true; // Indicar que la adición fue exitosa
            //ok();
        } else {
            operationSuccess = false; // Indicar que la adición no fue exitosa debido a repetición de color
            //ok();
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
            //ok();
        } else {
            operationSuccess = false;
            //ok();
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
            //ok();

            return flightObj.locationFlight(flight);
        } else {
            operationSuccess = false; // Indicate that the flight location retrieval was not successful
            //ok();
            return null; // Returns a message if the flight does not exist
        }
    }
    
    /**
     * Make the flight visible by its color.
     * 
     * @param color The color of the flight to make visible.
     */
    private void makeFlightVisible(String color) {
        Flight flight = flights.get(color);
        if (flight != null) {
            flight.isVisible = true;
            List<Photograph> photographs = flight.getPhotographs();
            for (Photograph photograph : photographs) {
                photograph.makeVisible(); // Hacer invisible la fotografía
            }
            flight.draw();
            operationSuccess = true;
            //ok();
        } else {
            operationSuccess = false; 
            //ok();
        }
    }
    
    /**
     * Make the flight invisible by its color.
     * 
     * @param color The color of the flight to make invisible.
     */
    private void makeFlightInvisible(String color) {
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
            //ok();
        } else {
            operationSuccess = false; 
            //ok();
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
            // Elimina todas las fotografías previas del vuelo
            flight.clearPhotographs();
    
            // Toma la nueva fotografía
            flight.camera(flightColor, theta); 
    
            // Verifica si hay islas dentro de la fotografía
            for (Island island : islands.values()) {
            String islandColor = island.getColor();
            int[][] islandVertices = getIslandVertices(islandColor);

            if (islandVertices != null && islandVertices.length > 0) {
                List<Point> islandPoints = convertToPoints(islandVertices);
                
                for (Flight f : flights.values()) {
                    List<Photograph> photographs = f.getPhotographs();
                    
                    for (Photograph photograph : photographs) {
                        List<Point> photographVertices = photograph.getVertices();

                        if (isPolygonInsidePolygon(islandPoints, photographVertices)) {
                            island.drawOutline(islandColor);
                        }
                    }
                }
            }
        }
            operationSuccess = true;
            //ok();
        } else {
            System.out.println("Flight not found"); 
            operationSuccess = false;
            //ok();
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
                for (Island island : islands.values()) {
                    String islandColor = island.getColor();
                    int[][] islandVertices = getIslandVertices(islandColor);
                    if (islandVertices != null && islandVertices.length > 0) {
                        List<Point> islandPoints = convertToPoints(islandVertices);
                        for (Flight f : flights.values()) {
                            List<Photograph> photographs = f.getPhotographs();
                            for (Photograph photograph : photographs) {
                                List<Point> photographVertices = photograph.getVertices();
                                if (isPolygonInsidePolygon(islandPoints, photographVertices)) {
                                    island.drawOutline(islandColor);
                                }
                            }
                        }
                    }
                }
    
                if (flight.ok()) {
                    operationSuccess = false;
                }
            }
        } else {
            operationSuccess = false;
        }
        //ok();
    }

    /**
     * Makes flights, islands, and photographs visible within the Iceepeecee simulation.
     * If any operation is successful during the process, it sets the operationSuccess flag to true.
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

        // Make all islands visible and draw their outline if being observed
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
        //ok();
    }

    /**
     * Makes flights, islands, and photographs invisible within the Iceepeecee simulation.
     * If any operation is successful during the process, it sets the operationSuccess flag to true.
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
        //ok();
    }


    /**
     * Retrieves the angle (theta) in degrees of the most recent photograph taken by a specific flight of the given color.
     *
     * @param color The color of the flight for which the photograph angle is requested.
     * @return The angle in degrees (theta) of the most recent photograph taken by the flight. If no photographs exist or the flight is not found, it returns 0.0.
     */
    public double flightCamera(String color) {
        Flight flight = flights.get(color); 
        if (flight != null) {
            List<Photograph> photographs = flight.getPhotographs();
            if (!photographs.isEmpty()) {
                Photograph lastPhotograph = photographs.get(photographs.size() - 1);
                return lastPhotograph.getThetaDegrees();
            } else {
                System.out.println("No photographs taken for this flight.");
            }
        } else {
            System.out.println("Flight not found");
        }
        return 0.0;
    }
    
    /**
     * Retrieves information about all the islands stored in Iceepeecee.
     *
     * @return An array of strings representing the colors of all stored islands.
     */
    public String[] islands() {
        List<String> islandColors = new ArrayList<>();
    
        for (Island island : islands.values()) {
            String color = island.getColor();
            islandColors.add(color);
        }
    
        String[] islandColorsArray = new String[islandColors.size()];
        islandColors.toArray(islandColorsArray);
        operationSuccess = true;
        return islandColorsArray;
    }

    /**
     * Retrieves information about all the flights stored in Iceepeecee.
     *
     * @return An array of strings representing the colors of all stored flights.
     */
    public String[] flights() {
        List<String> flightInfo = new ArrayList<>();
    
        for (Flight flight : flights.values()) {
            String color = flight.getColor();
            flightInfo.add(color);
        }
    
        String[] flightLocationsArray = new String[flightInfo.size()];
        operationSuccess = true;
        flightInfo.toArray(flightLocationsArray);
        return flightLocationsArray;
    }

    
    /**
     * Checks if the island's vertices are within the canvas boundaries.
     *
     * @param vertexArray The vertices of the island.
     * @return true if the vertices are within the canvas boundaries, false otherwise.
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
     * Checks if a set of coordinates is within the canvas boundaries.
     *
     * @param coordinates The coordinates to verify [x, y, z].
     * @return true if the coordinates are within the canvas, false otherwise.
     */
    private boolean isWithinCanvasBounds(int[] coordinates) {
        int x = coordinates[0];
        int y = coordinates[1];
        int z = coordinates[2];
        return x >= 0 && x <= length && y >= 0 && y <= width && z >= 0;
    }
    
    /**
     * Retrieves a color based on the provided index.
     *
     * @param index The index used to select a color.
     * @return The color corresponding to the given index or "black" if the index is out of range.
     */
    private String getColorForIndex(int index) {
        String[] colors = {
            "red", "green", "coral", "coral", "purple", "cyan", "pink", "orange", "brown", "gray",
            "magenta", "white", "lightBlue", "lime", "gold", "teal", "violet", "yellow", "lavender", "olive",
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
     * Displays an information message in a dialog window.
     *
     * @param message The informative message to display.
     */
    private void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Displays an error message in a dialog window.
     *
     * @param message The error message to display.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Checks if the last operation in the simulation was successful.
     */
    public void ok() {
        if (operationSuccess) {
            showInfoMessage("Operation successful.");
        } else {
            showErrorMessage("Operation not successful.");
        }
    }
    
    /**
     * Checks if the last operation in the simulation was successful.
     *
     * @return true if the last operation was successful, false otherwise.
     */
    public boolean isOperationSuccess() {
        return operationSuccess;
    }
    
    /**
     * Finishes the simulation and closes the graphic canvas.
     */
    public void finish() {
        Canvas canvas = Canvas.getCanvas();
        if (canvas != null) {
            canvas.setVisible(false);
            canvas = null;
            System.out.println("Simulation finished.");
        }
    }
    
    /**
     * Retrieves the vertices of an island given its color.
     *
     * @param islandColor The color of the island.
     * @return The array of vertices for the specified island, or null if the island is not found.
     */
    private int[][] getIslandVertices(String islandColor) {
        Island island = islands.get(islandColor);
        if (island != null) {
            return island.getVertexArray();
        }
        return null;
    }

    /**
     * Retrieves the colors of islands that have been observed by one or more flight photographs.
     *
     * @return An array of strings representing the colors of observed islands.
     */
    public String[] observedIslands() {
        List<String> observedIslandsList = new ArrayList<>();
        
        for (Island island : islands.values()) {
            String islandColor = island.getColor();
            int[][] islandVertices = getIslandVertices(islandColor);

            if (islandVertices != null && islandVertices.length > 0) {
                List<Point> islandPoints = convertToPoints(islandVertices);
                
                for (Flight flight : flights.values()) {
                    List<Photograph> photographs = flight.getPhotographs();
                    
                    for (Photograph photograph : photographs) {
                        List<Point> photographVertices = photograph.getVertices();

                        if (isPolygonInsidePolygon(islandPoints, photographVertices)) {
                            observedIslandsList.add(islandColor);
                            operationSuccess = true;
                            break;
                        }
                    }
                }
            } else{
                operationSuccess = false;
            }
        }
        
        String[] observedIslands = observedIslandsList.toArray(new String[0]);
        return observedIslands;
    }

    /**
     * Retrieves the colors of flights that did not capture any information about the islands.
     *
     * @return An array of strings representing the colors of useless flights.
     */
    public String[] uselessFlights() {
        List<String> uselessFlightList = new ArrayList<>();
        
        for (Flight flight : flights.values()) {
            List<Photograph> photographs = flight.getPhotographs();
            boolean hasIsland = false;
    
            for (Photograph photograph : photographs) {
                List<Point> photographVertices = photograph.getVertices();
                for (Island island : islands.values()) {
                    String islandColor = island.getColor();
                    int[][] islandVertices = getIslandVertices(islandColor);
    
                    if (isPolygonInsidePolygon(convertToPoints(islandVertices), photographVertices)) {
                        
                        hasIsland = true;
                        break;
                    }
                }
    
                if (hasIsland) {
                    break; 
                }
            }
    
            if (!hasIsland) {
                uselessFlightList.add(flight.getColor());
                operationSuccess = true;
            }
        }
    
        // Convierte la lista de vuelos inútiles en un arreglo de String
        String[] uselessFlightsArray = uselessFlightList.toArray(new String[0]);
        return uselessFlightsArray;
    }
    
    /**
     * Converts an array of vertices to a list of points.
     * 
     * @param vertices The vertex matrix.
     * @return A list of points.
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
     * Checks if a polygon is completely contained within another polygon.
     * 
     * @param innerPolygon The vertices of the internal polygon.
     * @param outerPolygon The vertices of the external polygon.
     * @return true if the inner polygon is completely contained in the outer one, false otherwise.
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
     * Checks if a given point is inside a polygon represented by a list of vertices.
     *
     * @param point     The point to be checked as a Point object.
     * @param vertices  The list of vertices of the polygon.
     * @return true if the point is inside the polygon, false otherwise.
     */

    private static boolean isPointInsidePolygon(Point point, List<Point> vertices) {
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
    
    /**
     * Retrieves a list of all the vertices of the islands in Iceepeecee.
     *
     * @return A list of arrays of island vertices.
     */
    private List<int[][]> getAllIslandVertices() {
        List<int[][]> allVertices = new ArrayList<>();
    
        for (Island island : islands.values()) {
            int[][] vertices = island.getVertexArray();
            allVertices.add(vertices);
        }
        return allVertices;
    }
}