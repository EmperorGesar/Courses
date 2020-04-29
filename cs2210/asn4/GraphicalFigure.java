/**
 * The class to store the data of the figure,
 * and the pixels in the figure are to be stored into a BST
 */

public class GraphicalFigure implements GraphicalFigureADT{

    private int identifier, width, height;
    private String type;
    private Location offset;
    private BinarySearchTree tree;

    /**
     * Constructor
     * @param id The identifier of the figure
     * @param width The width of the figure
     * @param height The height of the figure
     * @param type The type of the figure
     * @param pos The offset of the figure
     */
    public GraphicalFigure (int id, int width, int height, String type, Location pos){

        this.identifier = id;
        this.width = width;
        this.height = height;
        this.type = type;
        this.offset = pos;
        this.tree = new BinarySearchTree();

    }

    /**
     * Get the width of the figure
     * @return The width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the figure
     * @return The height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Get the type of the figure
     * @return The type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the identifier of the figure
     * @return The identifier
     */
    public int getId() {
        return this.identifier;
    }

    /**
     * Get the offset of the figure
     * @return The offset
     */
    public Location getOffset() {
        return this.offset;
    }

    /**
     * Set the offset of the figure
     * @param value The target offset to be set
     */
    public void setOffset(Location value) {
        this.offset = value;
    }

    /**
     * Set the type of the figure
     * @param t The target type to be set
     */
    public void setType(String t) {
        this.type = t;
    }

    /**
     * Insert a pixel data into the BST
     * @param pix The target pixel data
     * @throws DuplicatedKeyException When the target pixel already exists in the BST
     */
    public void addPixel(Pixel pix) throws DuplicatedKeyException {

        this.tree.put(this.tree.getRoot(), pix);

    }

    /**
     * Determine whether the two figures overlap
     * @param fig The other figure
     * @return When overlap, return true, otherwise false
     */
    public boolean intersects(GraphicalFigure fig) {

        int offsetX = this.offset.xCoord();
        int offsetY = this.offset.yCoord();

        int offsetFigX = fig.getOffset().xCoord();
        int offsetFigY = fig.getOffset().yCoord();

        try {

            Pixel first = this.tree.smallest(this.tree.getRoot());
            Pixel last = this.tree.largest(this.tree.getRoot());

            int x, y, xTarget, yTarget;

            for (Pixel cur = first; cur != last; cur = this.tree.successor(this.tree.getRoot(), cur.getLocation())){

                x = cur.getLocation().xCoord();
                y = cur.getLocation().yCoord();

                xTarget = x + offsetX - offsetFigX;
                yTarget = y + offsetY - offsetFigY;

                if (fig.tree.get(fig.tree.getRoot(), new Location(xTarget, yTarget)) != null) return true;

            }

            return false;

        } catch (EmptyTreeException e) {
            e.printStackTrace();
        }

        return false;

    }

}
