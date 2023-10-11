import java.util.*;

public class IceepeeceeContest {
    private double theta;
    private static Iceepeecee iceepeecee;
    static final double PI = 2 * Math.acos(0);
    
    public static double solve(int[][][] islands, int[][][] flights) {
    Iceepeecee iceepeecee = new Iceepeecee(islands, flights);
    String[] allIslands = iceepeecee.islands();
    Arrays.sort(allIslands);

    double lo = 0.0; // Ángulo inicial en grados
    double hi = Math.PI / 2; // Ángulo máximo en grados
    double bestAngle = -1.0;
    

    for (int rep = 0; rep < 64; rep++) {
        double th = (hi + lo) / 2;
        iceepeecee.photograph(th); // Usar grados directamente
        String[] observedIslands = iceepeecee.observedIslands();
        Arrays.sort(observedIslands);

        boolean areEqual = Arrays.equals(allIslands, observedIslands);

        if (areEqual) {
            bestAngle = th;
            lo = th; // Aumentar el ángulo
        } else {
            hi = th; // Reducir el ángulo
        }
    }

    if (bestAngle == -1) {
        return -1;
    } else {
        return bestAngle;
    }
}


}
