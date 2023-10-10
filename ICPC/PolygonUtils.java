import java.awt.*;
import java.util.List;

public class PolygonUtils {

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
                if (x1 == x2 || point.getX() <= xIntersect)
                    intersectCount++;
            }
            p1 = p2;
        }

        return intersectCount % 2 != 0;
    }

    public static void main(String[] args) {
        // Ejemplo de uso:
        List<Point> polygonVertices = List.of(
            new Point(0, 0),
            new Point(5, 0),
            new Point(5, 5),
            new Point(0, 5)
        );

        Point testPoint1 = new Point(2, 2);
        Point testPoint2 = new Point(6, 6);

        boolean isInside1 = isPointInsidePolygon(testPoint1, polygonVertices);
        boolean isInside2 = isPointInsidePolygon(testPoint2, polygonVertices);

        System.out.println("El punto 1 está dentro del polígono: " + isInside1);
        System.out.println("El punto 2 está dentro del polígono: " + isInside2);
    }
}
