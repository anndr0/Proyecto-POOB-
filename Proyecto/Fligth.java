import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Write a description of class Flight here.
 * 
 * @author (Laura Natalia Rojas and Ana Maria Duran) 
 * @version (23/09/23)
 */

public class Fligth {
    private int[] from; // Coordenadas de inicio [x1, y1, z1]
    private int[] to;   // Coordenadas de fin [x2, y2, z2]
    private String color;
    private boolean isVisible;
    private static HashMap<String, Fligth> fligths = new HashMap<>();
    private List<Photograph> photographs;
        
    public Fligth(String color, int[] from, int[] to) {
        this.color = color;
        this.from = from;
        this.to = to;
        isVisible = true; // Establecer el vuelo como visible al crearlo
        photographs = new ArrayList<>();
        fligths.put(color, this); // Agregar este vuelo al HashMap usando el color como clave
        draw(); // Dibujar el vuelo automáticamente
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
    
    public int getFromZ() {
        return from[2];
    }

    public int getToZ() {
        return to[2];
    }
    
    public void setColor(String newColor) {
        color = newColor;
    }
    
    
    public void makeFligthVisible(String color) {
        Fligth flight = fligths.get(color);
        if (flight != null) {
            flight.isVisible = true; // Hace visible el vuelo especificado
            flight.draw(); // Vuelve a dibujar el vuelo
        }
    }
    
    public void makeFligthInvisible(String color) {
        Fligth flight = fligths.get(color);
        if (flight != null) {
            flight.isVisible = false; // Hace invisible el vuelo especificado
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(flight); // Borra la línea del vuelo
        }
    }


    public void draw() {
        int[] from = getFrom();
        int[] to = getTo();
        int[] xPoints = {from[0], to[0]};
        int[] yPoints = {from[1], to[1]};
        Canvas canvas = Canvas.getCanvas();
        canvas.draw(this, color, new java.awt.Polygon(xPoints, yPoints, 2), 255);
    }
        
    public void delFligth(String color) {
        Fligth flight = fligths.get(color);
        if (flight != null) {
            flight.makeFligthInvisible(color);
            fligths.remove(color); // Elimina la isla del mapa
        }
    }

    public void camera(double teta) {
        Photograph photograph = new Photograph(this, teta); // Crear la fotografía con el vuelo actual y el ángulo especificado
        photographs.add(photograph); // Agregar la fotografía a la lista de fotografías
    }

    
    public static Fligth getFlightByColor(String color) {
            return fligths.get(color);
    }
    
    public List<Photograph> getPhotographs() {
        return photographs;
    }
    
    public String locationFligth(String color) {
        Fligth flight = Fligth.getFlightByColor(color);
        if (flight != null) {
            int[] from = flight.getFrom();
            int[] to = flight.getTo();
    
            StringBuilder location = new StringBuilder("Location of Flight (Color: " + color + "): {");
            location.append("From: {").append(from[0]).append(", ").append(from[1]).append(", ").append(from[2]).append("}, ");
            location.append("To: {").append(to[0]).append(", ").append(to[1]).append(", ").append(to[2]).append("}");
            location.append("}");
    
            return location.toString();
        } else {
            return "Flight not found"; // Devuelve un mensaje si el vuelo no existe
        }
    }
    
    public double getDistance() {
        int dx = to[0] - from[0];
        int dy = to[1] - from[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

}