import java.util.ArrayList;
import java.util.Iterator;

/**
 * The graph to represent the map with nodes and edges
 * <p>
 *
 * @author Wenhan Sun wsun228 251020850
 *
 */

public class Graph implements GraphADT {

    private Node[] nodes;
    private Edge[][] edges;

    /**
     * Constructor
     * @param n The number of the nodes contain in the graph
     */
    public Graph(int n){

        this.nodes = new Node[n];
        this.edges = new Edge[n][n];

        for (int i = 0; i < n; i++){
            nodes[i] = new Node(i);
        }

    }

    /**
     * Get the target node with number
     * @param name The number of the node
     * @return The target node
     * @throws GraphException The node does not exist
     */
    public Node getNode(int name) throws GraphException {

        if (name < nodes.length){
            return nodes[name];
        } else {
            throw new GraphException("The node does not exist.");
        }

    }

    /**
     * Connect two nodes to form an edge
     * @param nodeu The first node
     * @param nodev The second node
     * @param edgeType The type of the edge
     * @throws GraphException The node(s) does not exist or the edge is already in the graph
     */
    public void insertEdge(Node nodeu, Node nodev, int edgeType) throws GraphException {

        if (nodeu.getName() < nodes.length && nodev.getName() < nodes.length){

            try {

                Edge edge = getEdge(nodeu, nodev);
                throw new GraphException("The edge is already in the graph.");

            } catch (GraphException e){

                Edge edge = new Edge(nodeu, nodev, edgeType);
                edges[nodeu.getName()][nodev.getName()] = edge;
                edges[nodev.getName()][nodeu.getName()] = edge;

            }

        } else {
            throw new GraphException("The node(s) does not exist.");
        }

    }

    /**
     * Get the iterator of the adjacent edges of the target node
     * @param u The target node
     * @return The iterator of the adjacent edges of the target node, null if the node has no adjacent edge
     * @throws GraphException The node does not exist
     */
    public Iterator incidentEdges(Node u) throws GraphException {

        if (u.getName() < nodes.length){

            boolean found = false;
            ArrayList<Edge> result = new ArrayList<>();

            for (int i = 0; i < nodes.length; i++){

                if (edges[u.getName()][i] != null){

                    found = true;
                    result.add(edges[u.getName()][i]);

                }

            }

            if (found) return result.iterator();
            else return null;

        } else {
            throw new GraphException("The node does not exist.");
        }

    }

    /**
     * Get the target edge with its two nodes
     * @param u The first node
     * @param v The second node
     * @return The target edge
     * @throws GraphException The node(s) does not exist or the edge does not exist
     */
    public Edge getEdge(Node u, Node v) throws GraphException {

        if (u.getName() < nodes.length && v.getName() < nodes.length){

            if (edges[u.getName()][v.getName()] != null){
                return edges[u.getName()][v.getName()];
            } else {
                throw new GraphException("The edge does not exist.");
            }

        } else {
            throw new GraphException("The node(s) does not exist.");
        }

    }

    /**
     * Determine whether two nodes are adjacent
     * @param u The first node
     * @param v The second node
     * @return True if the nodes are adjacent, false otherwise
     * @throws GraphException The node(s) does not exist
     */
    public boolean areAdjacent(Node u, Node v) throws GraphException {

        if (u.getName() < nodes.length && v.getName() < nodes.length){

            try {

                Edge edge = getEdge(u, v);
                return true;

            } catch (GraphException e){
                return false;
            }

        } else {
            throw new GraphException("The node(s) does not exist.");
        }

    }

}
