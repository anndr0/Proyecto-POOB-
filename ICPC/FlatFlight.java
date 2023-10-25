
/**
 * Write a description of class FlatFlight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlatFlight extends Flight {
    public FlatFlight(String color, int[] from, int[] to) {
        super(color, from ,new int[] {to[0], to[1], from[2]});
    }
}

