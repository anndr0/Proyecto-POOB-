import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyPolygon{
    private int numVertices;
    private List<Point> vertices;
    private String color;
    private boolean isVisible;
    private Number[] xPoints;
    private Number[] yPoints;
    private int alpha;
    
    public MyPolygon() {
        vertices = new ArrayList<>();
        this.color = color; 
        isVisible = false;
    }

    public MyPolygon(String color, int[][] vertexArray) {
        this();
        addVerticesFromArray(vertexArray);
        setColor(color);
    }

    
    public void addVertex(int x, int y) {
        vertices.add(new Point(x, y));
    }    
    
    public void addVerticesFromArray(int[][] vertexArray) {
        for (int i = 0; i < vertexArray.length; i++) {
            if (vertexArray[i].length == 2) {
                addVertex(vertexArray[i][0], vertexArray[i][1]);
            }
        }
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public void setColor(String newColor) {
        color = newColor;
    }

    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    public void clear() {
        vertices.clear();
    }
    
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    public int[][] getVertexArray() {
        int[][] vertexArray = new int[vertices.size()][2];
    
        for (int i = 0; i < vertices.size(); i++) {
            Point vertex = vertices.get(i);
            vertexArray[i][0] = vertex.getX();
            vertexArray[i][1] = vertex.getY();
        }
    
        return vertexArray;
    }
    
    public List<Point> getVertices() {
        return vertices;
    }    
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyPolygon with ").append(vertices.size()).append(" vertices: [");
    
        for (int i = 0; i < vertices.size(); i++) {
            Point vertex = vertices.get(i);
            sb.append("(").append(vertex.getX()).append(", ").append(vertex.getY()).append(")");
    
            if (i < vertices.size() - 1) {
                sb.append(", ");
            }
        }
    
        sb.append("]");
    
        return sb.toString();
    }
    
    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xPoints = new int[vertices.size()];
            int[] yPoints = new int[vertices.size()];

            for (int i = 0; i < vertices.size(); i++) {
                Point vertex = vertices.get(i);
                xPoints[i] = vertex.getX();
                yPoints[i] = vertex.getY();
            }

            canvas.draw(this, color, new Polygon(xPoints, yPoints, vertices.size()), 255);
            canvas.wait(10);
        }
    }
}