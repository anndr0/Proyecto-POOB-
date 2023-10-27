package ICPC;
import Shapes.*;
import java.util.List;
/**
 * Write a description of class MisteryIsland here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MisteryIsland extends Island  {
    public MisteryIsland(String color, int[][] vertexArray) throws IceepeeceeException {
        super(color, vertexArray);
        Canvas canvas = Canvas.getCanvas();
        makeIslandInvisible(color);

        MyPolygon polygon = islands.get(color); // Obtiene el objeto MyPolygon de la isla

        if (polygon != null) {
            List<Point> vertices = polygon.getVertices(); // Obtiene la lista de vértices de MyPolygon

            int numVertices = vertices.size();
            int[] xPoints = new int[numVertices];
            int[] yPoints = new int[numVertices];

            for (int i = 0; i < numVertices; i++) {
                Point vertex = vertices.get(i);
                xPoints[i] = vertex.getX();
                yPoints[i] = vertex.getY();
            }

            canvas.draw(this, color, new java.awt.Polygon(xPoints, yPoints, numVertices), 20);
        }
    }
    
    @Override
    public void makeIslandVisible(String color) {
        Canvas canvas = Canvas.getCanvas();
        MyPolygon polygon = islands.get(color);
        isVisible = false;
        if (polygon != null) {
            List<Point> vertices = polygon.getVertices(); // Obtiene la lista de vértices de MyPolygon

            int numVertices = vertices.size();
            int[] xPoints = new int[numVertices];
            int[] yPoints = new int[numVertices];

            for (int i = 0; i < numVertices; i++) {
                Point vertex = vertices.get(i);
                xPoints[i] = vertex.getX();
                yPoints[i] = vertex.getY();
            }

            canvas.draw(this, color, new java.awt.Polygon(xPoints, yPoints, numVertices), 20);
        }
    }   
    
    @Override
    public void makeIslandInvisible(String color) {
        Canvas canvas = Canvas.getCanvas();
        canvas.erase(this);
    }
    
    @Override
    protected int[][] getVertexArray() {
        int [][] tempVertices = new int [vertexArray[0].length][vertexArray[1].length];
        for (int i=0; i< vertexArray[0].length; i++){
            for (int j=0; j< vertexArray[1].length; j++){
                tempVertices[i][j] = -1;
            }
        }
        return tempVertices;
    }
}
