package Shapes;
import ICPC.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version: 1.6 (shapes)
 */
public class Canvas{
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static Canvas canvasSingleton;

    /**
     * Factory method to get the canvas singleton object.
     */
    public static Canvas getCanvas(){
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("BlueJ Shapes Demo", 300, 300, 
                                         Color.white);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }
    
    public static Canvas getCanvas(int length, int width){
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("BlueJ Shapes Demo", width, length, 
                                         Color.white);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    //  ----- instance part -----

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColour;
    public Image canvasImage;
    private List <Object> objects;
    private HashMap <Object,ShapeDescription> shapes;
    
    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgClour  the desired background colour of the canvas
     */
    private Canvas(String title, int width, int height, Color bgColour){
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColour = bgColour;
        frame.pack();
        objects = new ArrayList <Object>();
        shapes = new HashMap <Object,ShapeDescription>();
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible){
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColour);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  referenceObject  an object to define identity for this shape
     * @param  color            the color of the shape
     * @param  shape            the shape object to be drawn on the canvas
     */
     // Note: this is a slightly backwards way of maintaining the shape
     // objects. It is carefully designed to keep the visible shape interfaces
     // in this project clean and simple for educational purposes.
    public void draw(Object referenceObject, String color, Shape shape, int alpha){
        objects.remove(referenceObject);   // just in case it was already there
        objects.add(referenceObject);      // add at the end
        shapes.put(referenceObject, new ShapeDescription(shape, color, alpha));
        redraw();
    }
 
    /**
     * Erase a given shape's from the screen.
     * @param  referenceObject  the shape object to be erased 
     */
    public void erase(Object referenceObject){
        objects.remove(referenceObject);   // just in case it was already there
        shapes.remove(referenceObject);
        redraw();
    }

    /**
     * Set the foreground colour of the Canvas.
     * @param  newColour   the new colour for the foreground of the Canvas 
     */
    public void setForegroundColor(String colorString){
        if(colorString.equals("red"))
            graphic.setColor(Color.red);
        else if(colorString.equals("black"))
            graphic.setColor(Color.black);
        else if(colorString.equals("blue"))
            graphic.setColor(Color.blue);
        else if(colorString.equals("yellow"))
            graphic.setColor(Color.yellow);
        else if(colorString.equals("green"))
            graphic.setColor(Color.green);
        else if(colorString.equals("magenta"))
            graphic.setColor(Color.magenta);
        else if(colorString.equals("white"))
            graphic.setColor(Color.white);
        else if(colorString.equals("grey"))
            graphic.setColor(Color.LIGHT_GRAY);
        else if(colorString.equals("orange"))
            graphic.setColor(Color.orange);
            
        else
            graphic.setColor(Color.black);
    }
    
    /**
     * Sets the foreground color with the specified color string and alpha transparency.
     *
     * @param colorString The color string (e.g., "red", "green") to set as the foreground color.
     * @param alpha       The alpha transparency value (0-255) to adjust the color opacity.
     */
    public void setForegroundColor(String colorString, int alpha) {
        Color color;
        switch (colorString) {
            case "red":
                color = new Color(255, 0, 0, alpha);
                break;
            case "green":
                color = new Color(0, 255, 0, alpha);
                break;
            case "blue":
                color = new Color(0, 0, 255, alpha);
                break;
            case "yellow":
                color = new Color(255, 255, 0, alpha);
                break;
            case "purple":
                color = new Color(128, 0, 128, alpha);
                break;
            case "cyan":
                color = new Color(0, 255, 255, alpha);
                break;
            case "pink":
                color = new Color(255, 182, 193, alpha);
                break;
            case "orange":
                color = new Color(255, 165, 0, alpha);
                break;
            case "brown":
                color = new Color(139, 69, 19, alpha);
                break;
            case "gray":
                color = new Color(128, 128, 128, alpha);
                break;
            case "magenta":
                color = new Color(255, 0, 255, alpha);
                break;
            case "white":
                color = new Color(255, 255, 255, alpha);
                break;
            case "lightBlue":
                color = new Color(173, 216, 230, alpha);
                break;
            case "lime":
                color = new Color(50, 205, 50, alpha);
                break;
            case "gold":
                color = new Color(255, 215, 0, alpha);
                break;
            case "teal":
                color = new Color(0, 128, 128, alpha);
                break;
            case "violet":
                color = new Color(238, 130, 238, alpha);
                break;
            case "coral":
                color = new Color(255, 127, 80, alpha);
                break;
            case "lavender":
                color = new Color(230, 230, 250, alpha);
                break;
            case "olive":
                color = new Color(128, 128, 0, alpha);
                break;
            case "maroon":
                color = new Color(128, 0, 0, alpha);
                break;
            case "turquoise":
                color = new Color(64, 224, 208, alpha);
                break;
            case "navyBlue":
                color = new Color(0, 0, 128, alpha);
                break;
            case "bistre":
                color = new Color(63, 42, 20, alpha);
                break;
            case "navy blue":
                color = new Color(0, 0, 128, alpha);
                break;
            case "burgundy":
                color = new Color(128, 0, 32, alpha);
                break;
            case "crimson":
                color = new Color(220, 20, 60, alpha);
                break;
            case "lightCyan":
                color = new Color(224, 255, 255, alpha);
                break;
            case "cobalt":
                color = new Color(0, 255, 255, alpha);
                break;
            case "fuchsia":
                color = new Color(255, 0, 255, alpha);
                break;
            case "garnet":
                color = new Color(128, 0, 0, alpha);
                break;
            case "lightGray":
                color = new Color(192, 192, 192, alpha);
                break;
            case "darkGray":
                color = new Color(64, 64, 64, alpha);
                break;
            case "indigo":
                color = new Color(75, 0, 130, alpha);
                break;
            case "lightLilac":
                color = new Color(255, 240, 245, alpha);
                break;
            case "lightLime":
                color = new Color(191, 255, 0, alpha);
                break;
            case "lightMagenta":
                color = new Color(255, 204, 204, alpha);
                break;
            case "lightBrown":
                color = new Color(210, 180, 140, alpha);
                break;
            case "darkBrown":
                color = new Color(139, 69, 19, alpha);
                break;
            case "lightOrange":
                color = new Color(255, 215, 170, alpha);
                break;
            case "darkOrange":
                color = new Color(255, 140, 0, alpha);
                break;
            case "lightGold":
                color = new Color(255, 235, 205, alpha);
                break;
            case "darkGold":
                color = new Color(255, 215, 0, alpha);
                break;
            case "silver":
                color = new Color(192, 192, 192, alpha);
                break;
            case "lightPink":
                color = new Color(255, 228, 225, alpha);
                break;
            case "darkPink":
                color = new Color(255, 105, 97, alpha);
                break;
            case "darkTurquoise":
                color = new Color(0, 128, 128, alpha);
                break;
            case "lightGreen":
                color = new Color(144, 238, 144, alpha);
                break;
            case "darkGreen":
                color = new Color(0, 100, 0, alpha);
                break;
            case "lightViolet":
                color = new Color(216, 191, 216, alpha);
                break;
            case "darkViolet":
                color = new Color(148, 0, 211, alpha);
                break;
            default:
                color = new Color(0, 0, 0, alpha);
                break;
        }
        graphic.setColor(color);
    }


    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (Exception e){
            // ignoring exception at the moment
        }
    }

    /**
     * Redraw ell shapes currently on the Canvas.
     */
    public void redraw(){
        erase();
        for(Iterator i=objects.iterator(); i.hasNext(); ) {
                       shapes.get(i.next()).draw(graphic);
        }
        canvas.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase(){
        Color original = graphic.getColor();
        graphic.setColor(backgroundColour);
        Dimension size = canvas.getSize();
        graphic.fill(new java.awt.Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }


    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel{
        public void paint(Graphics g){
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class ShapeDescription{
        private Shape shape;
        private String colorString;
        private int alpha;
        public ShapeDescription(Shape shape, String color, int alpha){
            this.shape = shape;
            colorString = color;
            this.alpha = alpha;
        }

        public void draw(Graphics2D graphic){
            setForegroundColor(colorString, alpha);
            graphic.draw(shape);
            graphic.fill(shape);  
        }
    }
    
    /**
     * Draws a line with the specified coordinates and color on the canvas.
     *
     * @param x1     The x-coordinate of the starting point of the line.
     * @param y1     The y-coordinate of the starting point of the line.
     * @param x2     The x-coordinate of the ending point of the line.
     * @param y2     The y-coordinate of the ending point of the line.
     * @param color  The color of the line.
     */
    public void drawLine(int x1, int y1, int x2, int y2, String color) {
        setForegroundColor(color, 255);
    
        // coordenadas dentro del canvas
        x1 = Math.max(0, Math.min(x1, canvas.getWidth()));
        y1 = Math.max(0, Math.min(y1, canvas.getHeight()));
        x2 = Math.max(0, Math.min(x2, canvas.getWidth()));
        y2 = Math.max(0, Math.min(y2, canvas.getHeight()));
        
        // Dibujar la línea
        graphic.drawLine(x1, y1, x2, y2);
        canvas.repaint();
    }
    
    
}

