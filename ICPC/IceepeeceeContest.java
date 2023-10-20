import java.util.*;

public class IceepeeceeContest {
    private double theta;
    private static Iceepeecee iceepeecee;
    static final double PI = 2 * Math.acos(0);

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

    public static void simulate(int[][][] islands, int[][][] flights) throws IceepeeceeException {
        try {
            Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
            String[] allIslands = new String[islands.length];
            String[] temporal = new String[islands.length];
            allIslands = iceepeecee.islands();
            Arrays.sort(allIslands);
            double lo = 0.0;
            double hi = 90;
            double best = -1;
            for (int rep = 0; rep < 64; rep++) {
                double th = (hi + lo) / 2;
                iceepeecee.makeInvisible();
                iceepeecee.photograph(th);
                iceepeecee.makeVisible();
                temporal = iceepeecee.observedIslands();
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
                iceepeecee.makeInvisible();
            } else {
                iceepeecee.makeInvisible();
                iceepeecee.makeVisible();
                iceepeecee.photograph(best);
            }
        } catch (IceepeeceeException e) {
            throw e; // Relanza la excepción para su manejo en un nivel superior
        }
    }
}
