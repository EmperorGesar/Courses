import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The class to operate the map file and perform searching process with the graph
 */

public class RoadMap {

    private Graph graph;

    private int start;
    private int end;
    private int initialBudget;
    private int toll;
    private int gain;

    private ArrayList<Node> path;
    private boolean found;

    /**
     * Constructor
     * @param inputFile The file of the map
     * @throws MapException Input file does not exist or the operation of the graph has error
     */
    public RoadMap(String inputFile) throws MapException{

        try{

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            int scale = Integer.parseInt(reader.readLine());

            this.start = Integer.parseInt(reader.readLine());
            this.end = Integer.parseInt(reader.readLine());

            int width = Integer.parseInt(reader.readLine());
            int length = Integer.parseInt(reader.readLine());

            this.initialBudget = Integer.parseInt(reader.readLine());

            this.toll = Integer.parseInt(reader.readLine());
            this.gain = Integer.parseInt(reader.readLine());

            int widthScheme = width * 2 - 1;
            int lengthScheme = length * 2 - 1;

            char[][] scheme = new char[lengthScheme][widthScheme];

            String[] current;

            for (int i = 0; i < lengthScheme; i++){

                current = reader.readLine().split("");

                for (int j = 0; j < widthScheme; j++){
                    scheme[i][j] = current[j].toCharArray()[0];
                }

            }

            this.graph = new Graph(length * width);
            Node u, v;
            int type = 0;

            for (int i = 0; i < lengthScheme; i++){

                for (int j = 0; j < widthScheme; j++){

                    if (i % 2 == 0 && j % 2 == 0){

                        int r = i / 2;
                        int c = j / 2;

                        if (c < width - 1 && scheme[i][j + 1] != 'X'){

                            u = this.graph.getNode(r * width + c);
                            v = this.graph.getNode(r * width + c + 1);

                            if (scheme[i][j + 1] == 'F'){
                                type = 0;
                            } else if (scheme[i][j + 1] == 'T'){
                                type = 1;
                            } else if (scheme[i][j + 1] == 'C'){
                                type = -1;
                            }

                            this.graph.insertEdge(u, v, type);

                        }

                        if (r < length - 1 && scheme[i + 1][j] != 'X'){

                            u = this.graph.getNode(r * width + c);
                            v = this.graph.getNode((r + 1) * width + c);

                            if (scheme[i + 1][j] == 'F'){
                                type = 0;
                            } else if (scheme[i + 1][j] == 'T'){
                                type = 1;
                            } else if (scheme[i + 1][j] == 'C'){
                                type = -1;
                            }

                            this.graph.insertEdge(u, v, type);

                        }

                    }

                }

            }

            this.path = new ArrayList<>();
            this.found = false;

        } catch (IOException e){
            throw new MapException("Input file does not exist.");
        } catch (GraphException e){
            throw new MapException(e.getMessage());
        }

    }

    /**
     * Get the graph representing the map
     * @return The graph representing the map
     */
    public Graph getGraph(){
        return this.graph;
    }

    /**
     * Get the number of the starting node
     * @return The number of the starting node
     */
    public int getStartingNode(){
        return this.start;
    }

    /**
     * Get the number of the destination node
     * @return The number of the destination node
     */
    public int getDestinationNode(){
        return this.end;
    }

    /**
     * Get the initial budget to start searching
     * @return The initial budget
     */
    int getInitialMoney(){
        return this.initialBudget;
    }

    /**
     * Get the iterator of the nodes from the staring node to the destination node
     * @param start The number of the starting node
     * @param destination The number of the destination node
     * @param initialMoney The initial budget to start searching
     * @return The iterator of the nodes which form a path, null if the path does not exist
     */
    public Iterator findPath(int start, int destination, int initialMoney){

        try{

            dfs(this.graph.getNode(start), initialMoney);

            if (this.found) return this.path.iterator();
            else return null;

        } catch (GraphException e){
            System.out.println(e.getMessage());
        }

        return null;

    }

    /**
     * Private method used to perform depth first search
     * @param current The current node during the searching process
     * @param money The status of money
     */
    private void dfs(Node current, int money){

        current.setMark(true);
        this.path.add(current);

        if (current.getName() != this.end){

            try{

                Iterator iterator = this.graph.incidentEdges(current);

                if (iterator != null && !this.found){

                    int balance = money;

                    while (iterator.hasNext() && !this.found){

                        Edge edge = (Edge) iterator.next();
                        Node adjacent;

                        if (edge.firstEndpoint().getName() == current.getName()){
                            adjacent = edge.secondEndpoint();
                        } else {
                            adjacent = edge.firstEndpoint();
                        }

                        if (!adjacent.getMark() && adjacent.getName() != this.start){

                            if (edge.getType() == 1){
                                money = money - this.toll;
                            } else if (edge.getType() == -1){
                                money = money + this.gain;
                            }

                            if (money >= 0) dfs(adjacent, money);

                        }

                        money = balance;

                    }

                    if (!this.found){

                        current.setMark(false);
                        this.path.remove(current);

                    }

                }

            } catch (GraphException e){
                System.out.println(e.getMessage());
            }

        } else {
            this.found = true;
        }

    }

}
