import java.util.ArrayList;

class IceepeeceeContest {
    // Constructor de la clase
    public IceepeeceeContest() {
    }

    // Método para resolver el problema y devolver el ángulo mínimo
    public double solve(int[][][] islands, int[][][] flights) {
        double lo = 0.0, hi = Math.PI / 2;
    
        for (int rep = 0; rep < 64; rep++) {
            double th = (hi + lo) / 2;
            boolean[] seen = new boolean[islands.length];
    
            for (int f = 0; f < flights.length; f++) {
                double FZ1 = flights[f][2][2];
                double FZ2 = flights[f][3][2];
                double[] ortho = new double[]{flights[f][0][1] - flights[f][1][1], flights[f][1][0] - flights[f][0][0]};
                double len = Math.sqrt(ortho[0] * ortho[0] + ortho[1] * ortho[1]);
                ortho[0] /= len;
                ortho[1] /= len;
    
                ArrayList<double[]> poly = new ArrayList<>();
                poly.add(new double[]{flights[f][0][0] - ortho[0] * (FZ1 * Math.tan(th)), flights[f][0][1] - ortho[1] * (FZ1 * Math.tan(th))});
                poly.add(new double[]{flights[f][1][0] - ortho[0] * (FZ2 * Math.tan(th)), flights[f][1][1] - ortho[1] * (FZ2 * Math.tan(th))});
                poly.add(new double[]{flights[f][1][0] + ortho[0] * (FZ2 * Math.tan(th)), flights[f][1][1] + ortho[1] * (FZ2 * Math.tan(th))});
                poly.add(new double[]{flights[f][0][0] + ortho[0] * (FZ1 * Math.tan(th)), flights[f][0][1] + ortho[1] * (FZ1 * Math.tan(th))});
    
                double mxx = 1e7;
                for (double[] p : poly) {
                    mxx = Math.max(mxx, p[0]);
                }
    
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
    
                                if (LineSegIntersection(a, b, islandPoint, p)) {
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
    
            if (allIslandsSeen(seen)) {
                hi = th;
            } else {
                lo = th;
            }
        }
    
        if (hi == Math.PI / 2) {
            return -1; // Imposible
        } else {
            return (hi + lo) / 2 * 180 / Math.PI; // Ángulo mínimo en grados
        }
    }

    // Método para verificar si se han visto todas las islas
    private boolean allIslandsSeen(boolean[] seen) {
        for (boolean islandSeen : seen) {
            if (!islandSeen) {
                return false;
            }
        }
        return true;
    }

    // Método para verificar la intersección de dos segmentos de línea
    private boolean LineSegIntersection(double[] a, double[] b, double[] c, double[] d) {
        double cp1 = CrossProd(b[0] - a[0], b[1] - a[1], c[0] - a[0], c[1] - a[1]);
        double cp2 = CrossProd(b[0] - a[0], b[1] - a[1], d[0] - a[0], d[1] - a[1]);
        if (cp1 > 0 && cp2 > 0) {
            return false;
        }
        if (cp1 < 0 && cp2 < 0) {
            return false;
        }
        cp1 = CrossProd(d[0] - c[0], d[1] - c[1], a[0] - c[0], a[1] - c[1]);
        cp2 = CrossProd(d[0] - c[0], d[1] - c[1], b[0] - c[0], b[1] - c[1]);
        if (cp1 > 0 && cp2 > 0) {
            return false;
        }
        if (cp1 < 0 && cp2 < 0) {
            return false;
        }
        return true;
    }

    // Método para calcular el producto cruz
    private double CrossProd(double x1, double y1, double x2, double y2) {
        return x1 * y2 - y1 * x2;

    }

    // Método para simular la solución en el simulador
    public void simulate(int[][][] islands, int[][][] flights) {
        double angle = solve(islands, flights);

        if (angle == -1) {
            System.out.println("impossible");
        } else {
            System.out.printf("%.9f%n", angle);
        }
    }

}
