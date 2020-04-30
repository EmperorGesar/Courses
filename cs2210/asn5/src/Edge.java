/**
 * The edge of the graph which stores the two nodes and the type
 */

public class Edge {

    private Node firstNode;
    private Node secondNode;
    private int type;

    /**
     * Constructor
     * @param u The first node
     * @param v The second node
     * @param type The type, 0 for public, 1 for private and -1 for reward
     */
    public Edge(Node u, Node v, int type){

        this.firstNode = u;
        this.secondNode = v;
        this.type = type;

    }

    /**
     * Get the first node of the edge
     * @return The first node
     */
    public Node firstEndpoint(){
        return this.firstNode;
    }

    /**
     * Get the second node of the edge
     * @return The second node
     */
    public Node secondEndpoint(){
        return this.secondNode;
    }

    /**
     * Get the type of the edge
     * @return The type of the edge
     */
    public int getType(){
        return this.type;
    }

}
