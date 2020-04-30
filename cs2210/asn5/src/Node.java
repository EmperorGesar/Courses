/**
 * The node of the graph which stores the number and the marking status
 */

public class Node {

    private int name;
    private boolean mark;

    /**
     * Constructor
     * @param name The number of the node
     */
    public Node(int name){

        this.name = name;
        this.mark = false;

    }

    /**
     * Set the marking status of the node
     * @param mark The target marking status to set
     */
    public void setMark(boolean mark){
        this.mark = mark;
    }

    /**
     * Get the marking status of the node
     * @return The marking status of the node
     */
    public boolean getMark(){
        return this.mark;
    }

    /**
     * Get the number of the node
     * @return The number of the node
     */
    public int getName(){
        return this.name;
    }

}
