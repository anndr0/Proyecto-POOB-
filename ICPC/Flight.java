import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Flight {
    private int[] from; // Coordenadas de inicio [x1, y1]
    private int[] to;   // Coordenadas de fin [x2, y2]
    private String color;
    private boolean isVisible;

    public Flight(String color, int[] from, int[] to) {
        this.color = color;
        this.from = from;
        this.to = to;
    }

    public int[] getFrom() {
        return from;
    }

    public int[] getTo() {
        return to;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String newColor) {
        color = newColor;
    }

    public void makeVisible() {
        isVisible = true;
        draw();
    }

    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.drawLine(from[0], from[1], to[0], to[1], color);
            canvas.wait(10);
        }
    }

}