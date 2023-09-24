import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.Color;


public class Photograph {
    private Fligth fligth;
    private String fligthColor; // Cambiado el nombre para evitar colisión de nombres
    private double teta; // Ángulo en radianes
    private boolean isVisible;

    public Photograph(Fligth fligth, double teta) {
        this.fligthColor = fligthColor;
        this.fligth = fligth;
        this.teta = teta;
        isVisible = true;
        draw();
    }
    

    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            if (fligth == null) {
                return; // Vuelo no encontrado, no se puede dibujar la sombra
            }

            int[] from = fligth.getFrom();
            int[] to = fligth.getTo();
            int z1 = from[2];
            int z2 = to[2];

            // Calcular las bases del trapecio
            double baseMenor = z1 * Math.tan(teta);
            double baseMayor = z2 * Math.tan(teta);

            // Calcular los vértices del trapecio
            int x1 = from[0];
            int x2 = to[0];
            int y1 = from[1];
            int y2 = to[1];

            int x3 = x1 + (int) baseMenor;
            int x4 = x1 - (int) baseMenor;
            
            int x5 = x2 + (int) baseMayor;
            int x6 = x2 - (int) baseMayor;
            
            int y3 = y1;
            int y4 = y2;

            int[] xPoints = {x4, x6, x5, x3};
            int[] yPoints = {y1, y2, y4, y3};
            
            // Obtener el color del vuelo y ajustar la opacidad
            String fligthColor = fligth.getColor();
            canvas.draw(this, "orange", new java.awt.Polygon(xPoints, yPoints, 4), 80);
            canvas.wait(10);
        }
    }
    
}