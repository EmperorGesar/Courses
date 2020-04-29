import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main of asn3 for computing the MST of the graph with Prim's algorithm implemented
 */

public class asn3 {

    public static void main(String[] args){

        try{

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));

            int n = Integer.parseInt(reader.readLine());
            int[][] adjList = new int[n + 1][n];
            int[][] weights = new int[n + 1][n];

            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= n - 1; j++){

                    adjList[i][j] = 0;
                    weights[i][j] = 0;

                }
            }

            String next = reader.readLine();
            String[] current;
            int u, v, w;

            while (next != null){

                current = next.split("\\s+");

                u = Integer.parseInt(current[0]);
                v = Integer.parseInt(current[1]);
                w = Integer.parseInt(current[2]);

                for (int i = 1; i <= n - 1; i++){
                    if (adjList[u][i] == 0){

                        adjList[u][i] = v;
                        weights[u][i] = w;
                        break;

                    }
                }

                for (int i = 1; i <= n - 1; i++){
                    if (adjList[v][i] == 0){

                        adjList[v][i] = u;
                        weights[v][i] = w;
                        break;

                    }
                }

                next = reader.readLine();

            }

            System.out.println("1. The input graph (adjacency list representation, w for weight): ");

            for (int i = 1; i <= n; i++){

                System.out.print(i + ":  ");

                for (int j = 1; j <= n - 1; j++){

                    if (adjList[i][j] != 0){
                        System.out.print(adjList[i][j] + ", w: " + weights[i][j] + ";    ");
                    }

                }

                System.out.println();

            }

            System.out.println();
            System.out.println("2. The edges of the minimum spanning tree (w for weight): ");

            int[] keys = new int[n + 1];
            int[] pres = new int[n + 1];

            for (int i = 1; i <= n; i++){
                keys[i] = Integer.MAX_VALUE;
                pres[i] = 0;
            }

            keys[1] = 0;
            heap queue = new heap(keys, n);

            for (int i = 1; i <= n; i++){

                u = queue.min_id();
                queue.delete_min();

                if (u != 0 ){
                    System.out.println(pres[u] + ", " + u + ", w: " + keys[u] + "; ");
                }

                for (int j = 1; j <= n - 1; j++){
                    if (adjList[u][j] != 0){
                        if (queue.in_heap(adjList[u][j]) && weights[u][j] < keys[adjList[u][j]]){

                            pres[adjList[u][j]] = u;
                            keys[adjList[u][j]] = weights[u][j];
                            queue.decrease_key(adjList[u][j], keys[adjList[u][j]]);

                        }
                    }
                }

            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
