import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main of asn2
 */

public class asn2 {

    public static void main(String args[]){

        try{

            BufferedReader reader = new BufferedReader(new FileReader("girl.img"));

            String[] current;
            char[][] token = new char[71][71];

            System.out.println("1. The input binary image:");

            for (int i = 0; i < 71; i++){

                current = reader.readLine().split("");

                for (int j = 0; j < 71; j++){

                    token[i][j] = current[j].toCharArray()[0];
                    System.out.print(token[i][j]);

                }

                System.out.println();

            }

            uandf unionFind = new uandf(71 * 71 + 1);

            for (int i = 0; i < 71; i++) {

                for (int j = 0; j < 71; j++) {

                    if (token[i][j] == '+'){

                        if (j != 70 && token[i][j + 1] == '+'){
                            unionFind.union_sets(71 * i + j, 71 * i + j + 1);
                        }

                        if (i != 70 && token[i + 1][j] == '+'){
                            unionFind.union_sets(71 * i + j, 71 * i + j + 71);
                        }

                    } else {
                        unionFind.union_sets(71 * i + j, 71 * 71);
                    }

                }

            }

            int size = unionFind.final_sets();

            System.out.println();
            System.out.println("2. The connected component image:");

            int[] finalSet = unionFind.getParent();
            int[][] count = new int[size][2];

            for (int i = 0; i < size; i++){
                count[i][0] = i;
                count[i][1] = 0;
            }

            for (int i = 0; i < 71 * 71; i++) {

                if (finalSet[i] != 0) {
                    System.out.print((char) (finalSet[i] + 64));
                    count[finalSet[i] - 1][1] = count[finalSet[i] - 1][1] + 1;
                } else {
                    System.out.print(" ");
                }

                if ((i + 1) % 71 == 0) System.out.println();

            }

            sort(count, 0, size - 1);

            System.out.println();
            System.out.println("3. The list sorted by component size:");

            for (int i = 0; i < size; i++){
                System.out.println((char) (count[i][0] + 65) + ": " + count[i][1]);
            }

            System.out.println();
            System.out.println("4. The connected component image with size less than 4 removed:");

            int cur = 0;
            for (int i = 0; i < 71 * 71; i++) {

                if (finalSet[i] == 0){
                    System.out.print(" ");
                } else {

                    for (int j = 0; j < size; j++){
                        if (finalSet[i] - 1 == count[j][0]) cur = j;
                    }

                    if (count[cur][1] < 4) System.out.print(" ");
                    else System.out.print((char) (finalSet[i] + 64));

                }

                if ((i + 1) % 71 == 0) System.out.println();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sort(int[][] array, int p, int r){

        if (p < r){

            int x = array[r][1];
            int q = p - 1;
            int temp;

            for (int i = p; i < r; i++){

                if (array[i][1] <= x){

                    q = q + 1;
                    temp = array[q][1]; array[q][1] = array[i][1]; array[i][1] = temp;
                    temp = array[q][0]; array[q][0] = array[i][0]; array[i][0] = temp;

                }

            }

            temp = array[q + 1][1]; array[q + 1][1] = array[r][1]; array[r][1] = temp;
            temp = array[q + 1][0]; array[q + 1][0] = array[r][0]; array[r][0] = temp;

            q = q + 1;
            sort(array, p, q - 1);
            sort(array, q + 1, r);

        }

    }

}
