import java.util.*;
/**
 * The IceepeeceeContest class provides methods for solving and simulate Iceepeecee marathon problem.
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class IceepeeceeContest {
    private double theta;
    private static Iceepeecee iceepeecee;
    static final double PI = 2 * Math.acos(0);
    
    /**
     * Solves an Iceepeecee challenge using given islands and flights data.
     *
     * @param islands An array of islands represented as three-dimensional coordinates.
     * @param flights An array of flights represented as coordinates of starting and ending points.
     * @return The calculated solution for the challenge as an angle in degrees.
     * @throws IceepeeceeException If an exception related to Iceepeecee operations occurs.
     */
    private double solveIceepeecee(int[][][] islands, int[][][] flights) throws IceepeeceeException {
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
    
    /**
     * Solves the angle problem based on islands and flights data.
     *
     * @param islands 3D array representing the islands' coordinates.
     * @param flights 3D array representing the flights' coordinates.
     * @return The minimum angle in degrees, or -1 if it's impossible.
     */
    public static double solveProblem(int[][][] islands, int[][][] flights) {
        // Inicialización de variables
        double lo = 0.0, hi = Math.PI / 2;
        
        // Bucle principal (método de búsqueda binaria)
        for (int rep = 0; rep < 64; rep++) {
            double th = (hi + lo) / 2;
            boolean[] seen = new boolean[islands.length];
            
            // Bucle para procesar vuelos
            for (int f = 0; f < flights.length; f++) {
                double FZ1 = flights[f][0][2];
                double FZ2 = flights[f][1][2];
                double[] ortho = new double[]{flights[f][0][1] - flights[f][1][1], flights[f][1][0] - flights[f][0][0]};
                double len = Math.sqrt(ortho[0] * ortho[0] + ortho[1] * ortho[1]);
                ortho[0] /= len;
                ortho[1] /= len;
                
                ArrayList<double[]> poly = new ArrayList<>();
                // Creación de polígono a partir de vuelo y ángulo
                poly.add(new double[]{flights[f][0][0] - ortho[0] * (FZ1 * Math.tan(th)), flights[f][0][1] - ortho[1] * (FZ1 * Math.tan(th))});
                poly.add(new double[]{flights[f][1][0] - ortho[0] * (FZ2 * Math.tan(th)), flights[f][1][1] - ortho[1] * (FZ2 * Math.tan(th))});
                poly.add(new double[]{flights[f][1][0] + ortho[0] * (FZ2 * Math.tan(th)), flights[f][1][1] + ortho[1] * (FZ2 * Math.tan(th))});
                poly.add(new double[]{flights[f][0][0] + ortho[0] * (FZ1 * Math.tan(th)), flights[f][0][1] + ortho[1] * (FZ1 * Math.tan(th))});
                
                double mxx = 1e7;
                
                for (double[] p : poly) {
                    mxx = Math.max(mxx, p[0]);
                }
                
                // Bucle para verificar la visibilidad de las islas
                for (int i = 0; i < islands.length; i++) {
                    if (!seen[i]) {
                        boolean fail = false;
                        for (int j = 0; j < islands[i].length; j++) {
                            int cnt = 0;
                            for (int k = 0; k < poly.size(); k++) {
                                double[] a = poly.get(k);
                                double[] b = poly.get((k + 1) % poly.size());
                                double[] islandPoint = new double[]{islands[i][j][0], islands[i][j][1]};
                                double[] p = new double[]{mxx + 1337, islandPoint[1] + 7331};
                                
                                if (lineSegmentIntersection(a, b, islandPoint, p)) {
                                    cnt++;
                                }
                            }
                            if (cnt % 2 == 0) {
                                fail = true;
                                break;
                            }
                        }
                        if (!fail) {
                            seen[i] = true;
                        }
                    }
                }
            }
            
            // Actualización de límites para la búsqueda binaria
            if (allIslandsSeen(seen)) {
                hi = th;
            } else {
                lo = th;
            }
        }
        
        // Comprobar si el ángulo es imposible
        if (hi == Math.PI / 2) {
            System.out.println("Imposible");
            return -1; // Imposible
        } else {
            return (hi + lo) / 2 * 180 / Math.PI; // Ángulo mínimo en grados
        }
    }
    
     /**
     * Checks if all islands have been seen.
     *
     * @param seen An array of booleans representing island visibility.
     * @return True if all islands have been seen, false otherwise.
     */
    private static boolean allIslandsSeen(boolean[] seen) {
        for (boolean islandSeen : seen) {
            if (!islandSeen) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if two line segments intersect.
     *
     * @param a The first point of the first line segment.
     * @param b The second point of the first line segment.
     * @param c The first point of the second line segment.
     * @param d The second point of the second line segment.
     * @return True if the line segments intersect, false otherwise.
     */
    private static boolean lineSegmentIntersection(double[] a, double[] b, double[] c, double[] d) {
        double cp1 = crossProduct(b[0] - a[0], b[1] - a[1], c[0] - a[0], c[1] - a[1]);
        double cp2 = crossProduct(b[0] - a[0], b[1] - a[1], d[0] - a[0], d[1] - a[1]);
        if (cp1 > 0 && cp2 > 0) {
            return false;
        }
        if (cp1 < 0 && cp2 < 0) {
            return false;
        }
        cp1 = crossProduct(d[0] - c[0], d[1] - c[1], a[0] - c[0], a[1] - c[1]);
        cp2 = crossProduct(d[0] - c[0], d[1] - c[1], b[0] - c[0], b[1] - c[1]);
        if (cp1 > 0 && cp2 > 0) {
            return false;
        }
        if (cp1 < 0 && cp2 < 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Calculates the cross product of two vectors.
     *
     * @param x1 The x-coordinate of the first vector.
     * @param y1 The y-coordinate of the first vector.
     * @param x2 The x-coordinate of the second vector.
     * @param y2 The y-coordinate of the second vector.
     * @return The cross product of the two vectors.
     */
    private static double crossProduct(double x1, double y1, double x2, double y2) {
        return x1 * y2 - y1 * x2;
    }
}
