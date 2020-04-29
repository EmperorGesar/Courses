import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main of asn2
 */

public class asn2 {

    public static void main(String[] args){

        try{

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));

            char[][] scheme = new char[71][71];
            String[] current;

            for (int i = 0; i < 71; i++){

                current = reader.readLine().split("");

                for (int j = 0; j < 71; j++){
                    scheme[i][j] = current[j].toCharArray()[0];
                }

            }

            uandf unionFind = new uandf(71 * 71 + 1);

            System.out.println("1. The input binary image:");

            for (int i = 0; i < 71; i++){

                for (int j = 0; j < 71; j++){

                    System.out.print(scheme[i][j]);

                    if (scheme[i][j] == '+'){

                        if (j != 70 && scheme[i][j + 1] == '+'){
                            unionFind.union_sets(71 * i + j, 71 * i + j + 1);
                        }

                        if (i != 70 && scheme[i + 1][j] == '+'){
                            unionFind.union_sets(71 * i + j, 71 * i + j + 71);
                        }

                    } else {
                        unionFind.union_sets(71 * i + j, 71 * 71);
                    }

                }

                System.out.println();

            }

            int size = unionFind.final_sets();

            System.out.println();
            System.out.println("2. The connected component image:");

            int[] finalized = unionFind.getParent();

            for (int i = 0; i < 71 * 71; i++){

                if (finalized[i] == 0) System.out.print(" ");
                else System.out.print((char) (finalized[i] + 64));
                if ((i + 1) % 71 == 0) System.out.println();

            }
            
            System.out.println();
            System.out.println("3. The list sorted by component size:");

            int[][] list = unionFind.getRepresent();

            for (int[] ints : list) {
                System.out.println((char) (ints[0] + 64) + ": " + ints[1]);
            }

            System.out.println();
            System.out.println("4. The connected component image with size less than 4 removed:");

            int cur = 0;
            for (int i = 0; i < 71 * 71; i++){

                if (finalized[i] == 0){
                    System.out.print(" ");
                } else {

                    for (int j = 0; j < size; j++){
                        if (finalized[i] == list[j][0]) cur = j;
                    }

                    if (list[cur][1] < 4) System.out.print(" ");
                    else System.out.print((char) (finalized[i] + 64));

                }

                if ((i + 1) % 71 == 0) System.out.println();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
