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
    private int[] from; // Coordenadas de inicio [x1, y1]
    private int[] to;   // Coordenadas de fin [x2, y2]
    private String color;
    private boolean isVisible;
    private static HashMap<String, Fligth> fligths = new HashMap<>();
        
    public Fligth(String color, int[] from, int[] to) {
        this.color = color;
        this.from = from;
        this.to = to;
        isVisible = true; // Establecer el vuelo como visible al crearlo
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
            // Dibuja una línea del mismo color que el fondo para "borrar" el vuelo existente
            canvas.drawLine(flight.from[0], flight.from[1], flight.to[0], flight.to[1], "white");
        }
    }

    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            // Redondea las coordenadas a números enteros
            int x1 = Math.round(from[0]);
            int y1 = Math.round(from[1]);
            int x2 = Math.round(to[0]);
            int y2 = Math.round(to[1]);
            canvas.drawLine(x1, y1, x2, y2,color);
        }
    }
        
    public void delFligth(String color) {
        Fligth flight = fligths.get(color);
        if (flight != null) {
            flight.makeFligthInvisible(color);
            fligths.remove(color); // Elimina la isla del mapa
        }
    }

    
    
    public static Fligth getFlightByColor(String color) {
        return fligths.get(color);
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

}