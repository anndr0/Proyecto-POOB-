package ICPC;
import Shapes.*;
import java.util.List;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Iceepeecee class represents an environment for managing islands, flights, and photographs.
 * It allows users to add, delete, and interact with these elements within a graphical canvas.
 * 
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 03/11/23
 */
public class Iceepeecee {
    private int length;
    private int width;
    private boolean isVisible;
    private HashMap<String, Island> islands;
    private HashMap<String, Flight> flights;
    private Shapes.Canvas canvas;
    private boolean operationSuccess;
    private static double theta;
    private List<Photograph> photographs;
    private static String[] colores = {
            "red", "green", "coral", "purple", "cyan", "pink", "orange", "brown", "gray",
            "magenta", "white", "lightBlue", "lime", "gold", "teal", "violet", "yellow", "lavender", "olive",
            "maroon", "turquoise", "navyBlue", "bistre", "navy blue", "burgundy", "crimson", "lightCyan",
            "cobalt", "fuchsia", "garnet", "lightGray", "darkGray", "indigo", "lightLilac", "lightLime",
            "lightMagenta", "lightBrown", "darkBrown", "lightOrange", "darkOrange", "lightGold", "darkGold",
            "silver", "lightPink", "darkPink", "darkTurquoise", "lightGreen", "darkGreen", "lightViolet", "darkViolet"
        };

    /**
     * Constructs an Iceepeecee object with the specified canvas dimensions.
     *
     * @param length The length of the canvas (must be positive).
     * @param width  The width of the canvas (must be positive).
     */
    public Iceepeecee(int length, int width) {
        try {
            if (length > 0 && width > 0) {
                this.length = length; // Initialize the canvas length
                this.width = width;   // Initialize the canvas width
                canvas = Shapes.Canvas.getCanvas(length, width);
                canvas.setVisible(true);
                this.islands = new HashMap<>();
                this.flights = new HashMap<>();
                isVisible = false;
                operationSuccess = true;
                ok();
            } else {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.NOT_VALID_VALUES);
            }
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            showErrorMessage("Error initializing Iceepeecee: " + e.getMessage());
        }
    }

    /**
     * Constructs an instance of the Iceepeecee simulator with specified islands and flights data.
     *
     * @param islands An array representing the islands with their three-dimensional coordinates.
     * @param flights An array representing the flights with coordinates of starting and ending points.
     * @throws IceepeeceeException If an exception related to Iceepeecee operations occurs during initialization.
     */
    public Iceepeecee(int[][][] islands, int[][][] flights) {
        this.length = 300;
        this.width = 300;
        canvas = Shapes.Canvas.getCanvas(length, width);
        canvas.setVisible(true);
        this.islands = new HashMap<>();
        this.flights = new HashMap<>();
        isVisible = false;
            
        if (islands != null && flights != null) {
            for (int i = 0; i < islands.length; i++) {
                int[][] islandVertices = islands[i];
                String islandColor = getColorForIndex(this.islands); // Obtener el color según el índice
                addIsland(islandColor, islandVertices);
            } 
            for (int i = 0; i < flights.length; i++) {
                int[] from = flights[i][0];
                int[] to = flights[i][1];
                String flightColor = getColorForIndex(this.flights); // Obtener el color según el índice
                addFlight(flightColor, from, to);
            }
            operationSuccess = true;
            //ok();
        } else {
            operationSuccess = false;
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
    public void addIsland(String color, int[][] vertexArray) {
        try {
            // Check if an island with the same vertices but different color already exists
            String existingIsland = findIslandWithSameVertices(vertexArray);
            if (existingIsland != null) {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.ALREADY_EXISTS_ISLAND);
            }
            if (islands.containsKey(color)) {
                throw new IceepeeceeException(IceepeeceeException.ALREADY_EXISTS_COLOR_ISLAND);
            }
            if (isWithinCanvasBounds(vertexArray)) {
                Island island = new Island(color, vertexArray);
                islands.put(color, island);
                operationSuccess = true;
            } else {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.OUT_OF_BOUNDS);
            }
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            if (e.getMessage().equals(IceepeeceeException.ALREADY_EXISTS_COLOR_ISLAND)) {
                color = getUnusedColor(islands);
                Island island = new Island(color, vertexArray);
                islands.put(color, island);
                operationSuccess = true;
                showInfoMessage("The island's color has been changed because it already existed.");
            } else {
                operationSuccess = false;
                showErrorMessage("Error while adding the island: " + e.getMessage());
            }
        }
    }

    /**
     * Add an island to Iceepeecee based on its type.
     * -----
     *  Valid colors are: 
     *  "red", "green", "blue", "yellow", "purple", "cyan", "pink", "orange", 
     *  "brown", "gray", "magenta", "white", "lightBlue", "lime", "gold", "teal", "violet", "coral", "lavender", 
     *  "olive", "maroon", "turquoise", "navyBlue", "bistre", "burgundy", "crimson", "lightCyan", "cobalt", 
     *  "fuchsia", "garnet", "lightGray", "darkGray", "indigo", "lightLilac", "lightLime", "lightMagenta", 
     *  "lightBrown", "darkBrown", "lightOrange", "darkOrange", "lightGold", "darkGold", "silver", "lightPink", 
     *  "darkPink", "darkTurquoise", "lightGreen", "darkGreen", "lightViolet", "darkViolet"
     *  ----
     *
     * @param type        The type of the island.
     * @param color       The color of the island.
     * @param vertexArray The vertices of the island.
     */
    public void addIsland(String type, String color, int[][] vertexArray) {
        try {
            // Check if an island with the same vertices but a different color already exists
            String existingIsland = findIslandWithSameVertices(vertexArray);
            if (existingIsland != null) {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.ALREADY_EXISTS_ISLAND);
            }
            if (islands.containsKey(color)) {
                throw new IceepeeceeException(IceepeeceeException.ALREADY_EXISTS_COLOR_ISLAND);
            }
            if (isWithinCanvasBounds(vertexArray)) {
                Island newIsland = createIsland(type, color, vertexArray);
                islands.put(color, newIsland);
                operationSuccess = true;
            } else {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.OUT_OF_BOUNDS);
            }
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            if (e.getMessage().equals(IceepeeceeException.ALREADY_EXISTS_COLOR_ISLAND)) {
                color = getUnusedColor(islands);
                try {
                    Island newIsland = createIsland(type, color, vertexArray);
                    islands.put(color, newIsland);
                    operationSuccess = true;
                    showInfoMessage("The island's color has been changed because it already existed.");
                } catch (IceepeeceeException error) {
                    showErrorMessage("No more colors available");
                }
            } else {
                operationSuccess = false;
                showErrorMessage("Error while adding the island: " + e.getMessage());
            }
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
        try {
            String existingFlight = findFlightWithSamePoints(from, to);
            if (existingFlight != null) {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.ALREADY_EXISTS_FLIGHT);
            }
    
            if (flights.containsKey(color)) {
                throw new IceepeeceeException(IceepeeceeException.ALREADY_EXISTS_COLOR_FLIGHT);
            }
    
            if (isWithinCanvasBounds(from) && isWithinCanvasBounds(to)) {
                Flight flight = new Flight(color, from, to);
                flights.put(color, flight);
                operationSuccess = true;
            } else {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.OUT_OF_BOUNDS);
            }
        } catch (IceepeeceeException e) {
            if (e.getMessage().equals(IceepeeceeException.ALREADY_EXISTS_COLOR_FLIGHT)) {
                String newColor = getUnusedColor(flights);
                Flight flight = new Flight(newColor, from, to);
                flights.put(color, flight);
                operationSuccess = true;
                showInfoMessage("The flight's color has been changed because it already existed.");
            } else {
                operationSuccess = false;
                showErrorMessage("Error while adding the flight: " + e.getMessage());
            }
        }
    }
    
    /**
     * Add a flight to Iceepeecee based on its type.
     * Valid colors are listed in the method description.
     *
     * @param type  The type of the flight.
     * @param color The color of the flight.
     * @param from  The starting coordinates [x1, y1, z1].
     * @param to    The ending coordinates [x2, y2, z2].
     */
    public void addFlight(String type, String color, int[] from, int[] to) {
        try {
            String existingFlight = findFlightWithSamePoints(from, to);
            if (existingFlight != null) {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.ALREADY_EXISTS_FLIGHT);
            }
            if (flights.containsKey(color)) {
                throw new IceepeeceeException(IceepeeceeException.ALREADY_EXISTS_COLOR_FLIGHT);
            }
            if (isWithinCanvasBounds(from) && isWithinCanvasBounds(to)) {
                Flight flight = createFlightByType(type, color, from, to);
                flights.put(color, flight);
                operationSuccess = true;
            } else {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.OUT_OF_BOUNDS);
            }
        } catch (IceepeeceeException e) {
            if (e.getMessage().equals(IceepeeceeException.ALREADY_EXISTS_COLOR_FLIGHT)) {
                String newColor = getUnusedColor(flights);
                try {
                    Flight flight = createFlightByType(type, newColor, from, to);
                    flights.put(newColor, flight);
                    operationSuccess = true;
                    showInfoMessage("The flight's color has been changed because it already existed.");
                } catch (IceepeeceeException error) {
                    showErrorMessage("No more colors available");
                }
            } else {
                operationSuccess = false;
                showErrorMessage("Error while adding the flight: " + e.getMessage());
            }
        }
    }
    
    /**
     * Capture a photograph from a specific flight at the given angle (theta).
     *
     * @param flightColor The color of the flight to capture a photograph from.
     * @param theta       The angle (in radians) at which the photograph is taken.
     */
    public void photograph(String flightColor, double theta) {
        try {
            if (theta > 0 && theta <= 90) {
                Flight flight = flights.get(flightColor);
                if (flight != null) {
                    // Clear all previous photographs of the flight
                    flight.clearPhotographs();
                    // Take the new photograph
                    flight.camera(flightColor, theta);
                    // Check if there are islands within the photograph
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
                                        island.drawOutline(islandColor, "black");
                                    }
                                }
                            }
                        }
                    }
                    operationSuccess = true;
                    //ok();
                } else {
                    throw new IceepeeceeException(IceepeeceeException.NO_FLIGHT_FOUND);
                }
            } else {
                throw new IceepeeceeException("Theta value must be greater than 0 and less than or equal to 90.");
            }
            //ok();
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            showErrorMessage("Error taking photograph: " + e.getMessage());
        }
    }
    
    /**
     * Capture photographs from all flights in Iceepeecee at the given angle (theta).
     *
     * @param theta The angle (in radians) at which the photographs are taken.
     */
    public void photograph(double theta) {
        try {
            if (theta > 0 && theta <= 90) {
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
                                        island.drawOutline(islandColor, "black");
                                    }
                                }
                            }
                        }
                    }
                }
                operationSuccess = true;
                //ok();
            } else {
                throw new IceepeeceeException("Theta value must be greater than 0 or less than or equal to 90");
            }
            //ok();
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            showErrorMessage("Error taking photographs: " + e.getMessage());
        }
    }

    
    /**
     * Delete an island from Iceepeecee.
     * ╰(◣﹏◢)╯
     * @param color The color of the island to delete.
     */
    public void deleteIsland(String color) {
        try {
            Island island = islands.get(color);
            if (island != null) {
                islands.remove(color);
                island.delIsland(color);
                operationSuccess = true;
                if (!(island instanceof FixedIsland)) {
                    showInfoMessage("Island deleted successfully.");
                }
            } else {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.ISLAND_NO_FOUND);
            }
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            showErrorMessage("Error deleting the island: " + e.getMessage());
        }
    }
    
    /**
     * Delete a flight from Iceepeecee.
     * ╰(◣﹏◢)╯ 
     * @param color The color of the flight to delete.
     */
    public void deleteFlight(String color) {
        try {
            Flight flight = flights.get(color);
            if (flight != null) {
                // Delete the photographs associated with the flight
                List<Photograph> photographs = flight.getPhotographs();
                for (Photograph photograph : photographs) {
                    photograph.makeInvisible(); // Make the photograph invisible
                }
                photographs.clear(); // Clear all photographs from the list
                // Finally, delete the flight
                flights.remove(color);
                flight.deleteFlight(color); // Calls the method to delete the flight in Flight
                operationSuccess = true;
                //ok();
            } else {
                operationSuccess = false;
                throw new IceepeeceeException(IceepeeceeException.NO_FLIGHT_FOUND);
            }
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            showErrorMessage("Error deleting the flight: " + e.getMessage());
        }
    }
    
    /**
     * Get the location of an island by color.
     * 
     * @param island The color of the island to query.
     * @return An array of coordinates representing the location of the island, or null if not found.
     */
    public int[][] islandLocation(String island) {
        try {
            Island islandObj = islands.get(island);
            if (islandObj != null) {
                int[][] ubicacion = islandObj.locationIsland(island);
                operationSuccess = true; 
                //ok();
                return ubicacion;
            } else {
                throw new IceepeeceeException(IceepeeceeException.ISLAND_NO_FOUND);
            }
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            showErrorMessage("Error retrieving island location: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get the location of a flight by color.
     * 
     * @param flight The color of the flight to query.
     * @return A string representing the location of the flight.
     */
    public int[][] flightLocation(String flight) {
        try {
            Flight flightObj = flights.get(flight);
            if (flightObj != null) {
                int[][] location = flightObj.locationFlight(flight);
                operationSuccess = true; 
                //ok();
                return location;
            } else {
                operationSuccess = false; // Indicate that the flight location retrieval was not successful
                throw new IceepeeceeException(IceepeeceeException.NO_FLIGHT_FOUND);
            }
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            showErrorMessage("Error retrieving flight location: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Retrieves the angle (theta) in degrees of the most recent photograph taken by a specific flight of the given color.
     *
     * @param color The color of the flight for which the photograph angle is requested.
     * @return The angle in degrees (theta) of the most recent photograph taken by the flight. If no photographs exist or the flight is not found, it returns 0.0.
     */
    public double flightCamera(String color) {
        try {
            Flight flight = flights.get(color); 
            if (flight != null) {
                List<Photograph> photographs = flight.getPhotographs();
                if (!photographs.isEmpty()) {
                    Photograph lastPhotograph = photographs.get(photographs.size() - 1);
                    theta = lastPhotograph.getThetaDegrees();
                    operationSuccess = true; 
                    //ok();
                    return lastPhotograph.getThetaDegrees();
                } else {
                    throw new IceepeeceeException(IceepeeceeException.NO_PHOTOS_IN_FLIGHT);
                }
            } else {
                throw new IceepeeceeException(IceepeeceeException.NO_FLIGHT_FOUND);
            }
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            showErrorMessage("Error retrieving the angle of the last photograph: " + e.getMessage());
            return 0.0; // Default value in case of exception
        }
    }
    
    /**
     * Retrieves information about all the islands stored in Iceepeecee.
     *
     * @return An array of strings representing the colors of all stored islands.
     */
    public String[] islands() {
        try {
            List<String> islandColors = new ArrayList<>();
            for (Island island : islands.values()) {
                String color = island.getColor();
                islandColors.add(color);
            }
            if (islandColors.isEmpty()) {
                throw new IceepeeceeException(IceepeeceeException.NO_ISLANDS);
            }
            String[] islandColorsArray = new String[islandColors.size()];
            islandColors.toArray(islandColorsArray);
            operationSuccess = true; 
            //ok();
            return islandColorsArray;
        } catch (IceepeeceeException e) {
            operationSuccess = false;
            showErrorMessage("Error retrieving the list of islands: " + e.getMessage());
            return new String[0];
        }
    }
    
    /**
     * Retrieves information about all the flights stored in Iceepeecee.
     *
     * @return An array of strings representing the colors of all stored flights.
     */
    public String[] flights() {
        try {
            List<String> flightInfo = new ArrayList<>();
            for (Flight flight : flights.values()) {
                String color = flight.getColor();
                flightInfo.add(color);
            }
            if (flightInfo.isEmpty()) {
                throw new IceepeeceeException(IceepeeceeException.NO_FLIGHTS);
            }
            String[] flightLocationsArray = new String[flightInfo.size()];
            flightInfo.toArray(flightLocationsArray);
            operationSuccess = true;
            //ok();
            return flightLocationsArray;
        } catch (Exception e) {
            operationSuccess = false;
            showErrorMessage("Error retrieving the list of flights: " + e.getMessage());
            return new String[0];
        }
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
                            //island.drawOutline(islandColor, "black");
                            //ok();
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
            }
        }
        operationSuccess = true;
        // Convert the list of useless flights into a String array
        String[] uselessFlightsArray = uselessFlightList.toArray(new String[0]);
        return uselessFlightsArray;
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
     * Finishes the simulation and closes the graphic canvas.
     */
    public void finish() {
        Shapes.Canvas canvas = Shapes.Canvas.getCanvas();
        if (canvas != null) {
            canvas.setVisible(false);
            canvas = null;
            JOptionPane.showMessageDialog(null, "Simulation finished.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
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
     * Create an island with the specified type, color, and vertex array.
     *
     * @param type        The type of the island (e.g., "normal", "fixed", "surprising", "mystery").
     * @param color       The color of the island.
     * @param vertexArray The array of vertices defining the island's shape.
     * @return An instance of the created island.
     * @throws IceepeeceeException If the provided island type is unknown.
     */
    private Island createIsland(String type, String color, int[][] vertexArray) throws IceepeeceeException {
        switch (type) {
            case "normal":
                return new Island(color, vertexArray);
            case "fixed":
                return new FixedIsland(color, vertexArray);
            case "surprising":
                return new SurprisingIsland(color, vertexArray);
            case "mistery":
                return new MisteryIsland(color, vertexArray);
            default:
                throw new IceepeeceeException(IceepeeceeException.ISLAND_TYPE_UNKNOWN);
        }
    }
    
    /**
     * Create a flight with the specified type, color, starting coordinates, and ending coordinates.
     *
     * @param type  The type of the flight (e.g., "normal", "lazy", "flat").
     * @param color The color of the flight.
     * @param from  The starting coordinates [x1, y1, z1].
     * @param to    The ending coordinates [x2, y2, z2].
     * @return An instance of the created flight.
     * @throws IceepeeceeException If the provided flight type is unknown.
     */
    private Flight createFlightByType(String type, String color, int[] from, int[] to) throws IceepeeceeException {
        switch (type) {
            case "normal":
                return new Flight(color, from, to);
            case "lazy":
                return new LazyFlight(color, from, to);
            case "flat":
                return new FlatFlight(color, from, to);
            default:
                throw new IceepeeceeException(IceepeeceeException.FLIGHT_TYPE_UNKNOWN);
        }
    }
    
    /**
     * Get an unused color that is not present in the provided type (HashMap).
     *
     * @param type The type (e.g., islands or flights) to check for color availability.
     * @return A color that is not already in use in the provided type.
     */
    private String getUnusedColor(HashMap type) {
        for (String candidateColor : colores) {
            if (!type.containsKey(candidateColor)) {
               return candidateColor;
            }
        }
        return "black"; // If all colors are in use, return "black."
    }
    
    /**
     * Find the color of an island that has the same vertices as the ones being added.
     *
     * @param vertexArray The array of vertices defining the island's shape.
     * @return The color of the existing island with the same vertices, or null if not found.
     */
    private String findIslandWithSameVertices(int[][] vertexArray) {
        for (Map.Entry<String, Island> entry : islands.entrySet()) {
            String color = entry.getKey();
            Island island = entry.getValue();
            int[][] existingVertices = island.getVertexArray();
            
            if (Arrays.deepEquals(vertexArray, existingVertices)) {
                return color;
            }
        }
        return null;
    }
    
    /**
     * Find the color of a flight with the same starting and ending points.
     *
     * @param from The starting coordinates [x1, y1, z1].
     * @param to   The ending coordinates [x2, y2, z2].
     * @return The color of the existing flight with the same points, or null if not found.
     */
    private String findFlightWithSamePoints(int[] from, int[] to) {
        for (Map.Entry<String, Flight> entry : flights.entrySet()) {
            Flight flight = entry.getValue();
            int[] existingFrom = flight.getFrom();
            int[] existingTo = flight.getTo();
    
            if (Arrays.equals(existingFrom, from) && Arrays.equals(existingTo, to)) {
                return entry.getKey(); // Return the color of the existing flight
            }
        }
        return null; // If no flight with the same points is found, return null.
    }
    
    /**
     * Make the island with the specified color visible.
     * 
     * @param color The color of the island to make visible.
     */
    private void makeIslandVisible(String color) throws IceepeeceeException {
        Island island = islands.get(color);
        if (island != null) {
            island.makeIslandVisible(color); // Make the polygon of the specified island visible
            operationSuccess = true;
            //ok();
        } else{
            operationSuccess = false;
            //ok();
            throw new IceepeeceeException(IceepeeceeException.ISLAND_NO_FOUND);
        }
    }

    /**
     * Make the island with the specified color invisible.
     * 
     * @param color The color of the island to make invisible.
     */
    private void makeIslandInvisible(String color) throws IceepeeceeException {
        Island island = islands.get(color);
        if (island != null) {
            island.makeIslandInvisible(color); // Make the polygon of the specified island invisible
            operationSuccess = true;
            //ok();
        }else{
            operationSuccess = false;
            //ok();
            throw new IceepeeceeException(IceepeeceeException.ISLAND_NO_FOUND);
        }
    } 

    /**
     * Make the flight visible by its color.
     * 
     * @param color The color of the flight to make visible.
     */
    public void makeFlightVisible(String color)throws IceepeeceeException {
        Flight flight = flights.get(color);
        if (flight != null) {
            flight.isVisible = true;
            List<Photograph> photographs = flight.getPhotographs();
            for (Photograph photograph : photographs) {
                photograph.makeVisible(); 
            }
            flight.draw();
            operationSuccess = true;
            ok();
        } else {
            operationSuccess = false;
            throw new IceepeeceeException(IceepeeceeException.NO_FLIGHT_FOUND);
        }
    }
    
    /**
     * Make the flight invisible by its color.
     * 
     * @param color The color of the flight to make invisible.
     */
    private void makeFlightInvisible(String color)throws IceepeeceeException {
        Flight flight = flights.get(color);
        if (flight != null) {
            flight.isVisible = false;
            Shapes.Canvas canvas = Shapes.Canvas.getCanvas();
            List<Photograph> photographs = flight.getPhotographs();
            for (Photograph photograph : photographs) {
                photograph.makeInvisible(); 
            }
            canvas.erase(flight);
            operationSuccess = true;
            //ok();
        } else {
            operationSuccess = false; 
            throw new IceepeeceeException(IceepeeceeException.NO_FLIGHT_FOUND);
        }
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
    private String getColorForIndex(HashMap contenedor) {
        String color = colores[0];
        for(int i = 0; i<colores.length; i++){
            if(contenedor.containsKey(colores[i])==false){
                color = colores[i];
                break;
            }
        }
        return color;
    }
    
    /**
     * Retrieves the vertices of an island given its color.
     *
     * @param islandColor The color of the island.
     * @return The array of vertices for the specified island, or null if the island is not found.
     */
    public int[][] getIslandVertices(String islandColor) {
        Island island = islands.get(islandColor);
        if (island != null) {
            return island.getVertexArray();
        }
        return null;
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
    
    /**
     * Get the starting coordinates of a flight based on its color.
     *
     * @param color The color of the flight.
     * @return An array containing the starting coordinates [x1, y1, z1].
     */
    public int[] getFrom(String color) {
        if (flights.containsKey(color)) {
            return flights.get(color).getFrom();
        } else {
            return null; // O puedes lanzar una excepción o manejar de otra forma si el vuelo no existe
        }
    }
    
    /**
     * Get the ending coordinates of a flight based on its color.
     *
     * @param color The color of the flight.
     * @return An array containing the ending coordinates [x2, y2, z2].
     */
    public int[] getTo(String color) {
        if (flights.containsKey(color)) {
            return flights.get(color).getTo();
        } else {
            return null; // O puedes lanzar una excepción o manejar de otra forma si el vuelo no existe
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
    
    /**
     * Get a list of all islands stored in Iceepeecee.
     * 
     * @return A HashMap containing all the islands.
     */
    public HashMap getIslands (){
        return this.islands;
    }
    
    /**
     * Get the current value of the theta angle.
     * 
     * @return The value of the theta angle in degrees.
     */
    public double getTheta() {
        return theta;
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
     *
     * @return true if the last operation was successful, false otherwise.
     */
    public boolean isOperationSuccess() {
        return operationSuccess;
    }
}