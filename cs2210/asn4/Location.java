/**
 * The class to store the x and y coordinates of the pixel in the figure
 * <p>
 *
 * @author Wenhan Sun wsun228 251020850
 *
 */

public class Location {

    private int xCoordinate, yCoordinate;

    /**
     * Constructor
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Location(int x, int y){

        this.xCoordinate = x;
        this.yCoordinate = y;

    }

    /**
     * Get the x coordinate of the pixel
     * @return The x coordinate
     */
    public int xCoord(){
        return this.xCoordinate;
    }

    /**
     * Get the y coordinate of the pixel
     * @return The y coordinate
     */
    public int yCoord(){
        return this.yCoordinate;
    }

    /**
     * Compare the coordinates of the pixel with another pixel
     * @param p The coordinates of the other pixel
     * @return 1 when x > x'; -1 when x < x'; when x = x', 1 when y > y'; -1 when y < y'; 0 when y = y'
     */
    public int compareTo (Location p){

        if (this.xCoord() < p.xCoord()){
            return -1;
        } else if (this.xCoord() > p.xCoord()){
            return 1;
        } else {

            if (this.yCoord() < p.yCoord()){
                return -1;
            } else if (this.yCoord() > p.yCoord()){
                return 1;
            } else {
                return 0;
            }

        }

    }

}
