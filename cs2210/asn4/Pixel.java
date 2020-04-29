/**
 * The class to store the coordinates and the color of the pixel
 * <p>
 *
 * @author Wenhan Sun wsun228 251020850
 *
 */

public class Pixel {

    private Location location;
    private int color;

    /**
     * Constructor
     * @param p The coordinates of the pixel
     * @param color The color of the pixel represented as an integer value
     */
    public Pixel(Location p, int color){

        this.location = p;
        this.color = color;

    }

    /**
     * Get the coordinates of the pixel
     * @return The coordinates
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     * Get the color of the pixel
     * @return The integer representing the color
     */
    public int getColor(){
        return this.color;
    }

}
