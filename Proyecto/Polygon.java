
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Point> vertices;
    private String color;
    private boolean isVisible;

    public Polygon() {
        vertices = new ArrayList<>();
        this.color = "black"; 
        isVisible = false;
    }

    public Polygon(int[][] vertexArray, String color) {
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

    public java.awt.Polygon getPolygon() {
        int[] xPoints = new int[vertices.size()];
        int[] yPoints = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            Point vertex = vertices.get(i);
            xPoints[i] = vertex.getX();
            yPoints[i] = vertex.getY();
        }
        return new java.awt.Polygon(xPoints, yPoints, vertices.size());
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public void setColor(String newColor) {
        color = newColor;
    }

    /**
     * Make this hexagon visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this hexagon invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
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

            canvas.draw(this, color, new java.awt.Polygon(xPoints, yPoints, vertices.size()),255);
            canvas.wait(10);
        }
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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Polygon with ").append(vertices.size()).append(" vertices: [");
    
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

}
