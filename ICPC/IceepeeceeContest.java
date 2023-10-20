import java.util.*;
/**
 * The IceepeeceeContest class provides methods for solving and simulate Iceepeecee marathon problem.
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class IceepeeceeContest {
    private double theta;
    private static Iceepeecee iceepeecee;
    private Canvas canvas;
    static final double PI = 2 * Math.acos(0);
    
    /**
     * Solves an Iceepeecee challenge using given islands and flights data.
     *
     * @param islands An array of islands represented as three-dimensional coordinates.
     * @param flights An array of flights represented as coordinates of starting and ending points.
     * @return The calculated solution for the challenge as an angle in degrees.
     * @throws IceepeeceeException If an exception related to Iceepeecee operations occurs.
     */
    public static double solve(int[][][] islands, int[][][] flights) throws IceepeeceeException {
        try {
            Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
            String[] allIslands = new String[islands.length];
            String[] allIslandsTemporal = new String[islands.length];
            allIslands = iceepeecee.islands();
            Arrays.sort(allIslands);
            double lo = 0.0;
            double hi = 90;
            double best = -1;
            for (int rep = 0; rep < 64; rep++) {
                double th = (hi + lo) / 2;
                iceepeecee.makeInvisible();
                iceepeecee.photograph(th);
                allIslandsTemporal = iceepeecee.observedIslands();

                Arrays.sort(allIslandsTemporal);
                boolean areEqual = Arrays.equals(allIslands, allIslandsTemporal);
                iceepeecee.makeInvisible();
                if (areEqual) {
                    hi = th;
                    best = th;
                } else {
                    lo = th;
                }
            }

            if (best == -1) {
                System.out.println("Impossible");
                return -1;
            } else {
                double theta = (best);
                iceepeecee.makeInvisible();
                return theta;
            }
        } catch (IceepeeceeException e) {
            throw e; // Relanza la excepción para su manejo en un nivel superior
        }
    }
    
    /**
     * Simulates an Iceepeecee challenge using given islands and flights data.
     *
     * @param islands An array of islands represented as three-dimensional coordinates.
     * @param flights An array of flights represented as coordinates of starting and ending points.
     * @throws IceepeeceeException If an exception related to Iceepeecee operations occurs.
     */
    public static void simulate(int[][][] islands, int[][][] flights) throws IceepeeceeException {
        try {
            Iceepeecee iceepeeceeSimulate = new Iceepeecee(islands, flights);
            String[] allIslands = new String[islands.length];
            String[] temporal = new String[islands.length];
            allIslands = iceepeeceeSimulate.islands();
            Arrays.sort(allIslands);
            double lo = 0.0;
            double hi = 90;
            double best = -1;
            for (int rep = 0; rep < 64; rep++) {
                double th = (hi + lo) / 2;
                iceepeeceeSimulate.makeInvisible();
                iceepeeceeSimulate.makeVisible();
                iceepeeceeSimulate.photograph(th);
                
                temporal = iceepeeceeSimulate.observedIslands();
                Arrays.sort(temporal);

                boolean areEqual = Arrays.equals(allIslands, temporal);
                if (areEqual) {
                    hi = th;
                    best = th;
                } else {
                    lo = th;
                }
            }

            if (best == -1) {
                iceepeeceeSimulate.makeInvisible();
            } else {
                iceepeeceeSimulate.makeInvisible();
                iceepeeceeSimulate.makeVisible();
                iceepeeceeSimulate.photograph(best);
            }
 
            iceepeeceeSimulate.finish();
        } catch (IceepeeceeException e) {
            throw e; // Relanza la excepción para su manejo en un nivel superior
        }
    }
}
