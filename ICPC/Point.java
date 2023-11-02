
/**
 * The Point class represents a 2D point with x and y coordinates.
 * @author Ana María Durán And Laura Natalia Rojas
 * @version 23/03/23
 */
public class Point {
    private int x;
    private int y;

    /**
     * Constructs a new Point object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return The x-coordinate of the point.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return The y-coordinate of the point.
     */
    public int getY() {
        return y;
    }
}
