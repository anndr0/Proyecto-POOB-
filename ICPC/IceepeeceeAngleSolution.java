import java.util.ArrayList;
// Clase IceepeeceeAngleSolution que resuelve el problema del ángulo
class IceepeeceeAngleSolution {
    public IceepeeceeAngleSolution() {
        // Constructor de la clase
    }

    // Método para resolver el problema del ángulo
    public double solveproblem(int[][][] islands, int[][][] flights) {
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
            return -1; // Imposible
        } else {
            return (hi + lo) / 2 * 180 / Math.PI; // Ángulo mínimo en grados
        }
    }
    
    // Método para verificar si todas las islas han sido vistas
    private boolean allIslandsSeen(boolean[] seen) {
        for (boolean islandSeen : seen) {
            if (!islandSeen) {
                return false;
            }
        }
        return true;
    }
    
    // Método para verificar la intersección de dos segmentos de línea
    private boolean lineSegmentIntersection(double[] a, double[] b, double[] c, double[] d) {
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
    
    // Método para calcular el producto cruz de dos vectores
    private double crossProduct(double x1, double y1, double x2, double y2) {
        return x1 * y2 - y1 * x2;
    }
    
    // Método para simular el problema y mostrar el resultado
    public void simulate(int[][][] islands, int[][][] flights) {
        double angle = solveproblem(islands, flights);
        
        if (angle == -1) {
            System.out.println("impossible");
        } else {
            System.out.printf("%.9f%n", angle);
        }
    }
}
