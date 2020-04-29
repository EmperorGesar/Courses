/**
 * Fibonacci numbers compute with time complexity O(n)
 */

public class asn1_b {

    public static int[] add(int[] x, int[] y){

        int[] sum = new int[64];
        int carry = 0;

        for (int i = 0; i < 64; i++){
            sum[i] = x[i] + y[i] + carry;
            carry = sum[i] / 10;
            sum[i] = sum[i] % 10;
        }

        return sum;

    }

    public static int[][] fib(int n){

        int[][] result = new int[2][64];

        if (n == 0){
            result[1][0] = 0;
        } else if (n == 1) {
            result[0][0] = 0;
            result[1][0] = 1;
        } else {

            result = fib(n - 1);

            int[] temp = result[1];
            result[1] = add(result[0], result[1]);
            result[0] = temp;

        }

        return result;

    }

    public static void main(String[] args){

        int[][] cur;
        boolean start;

        for (int i = 0; i <= 30; i++){

            System.out.print("fib(" + i * 10 + ") ");
            if (i == 0) System.out.print("  = ");
            else if (i < 10) System.out.print(" = ");
            else System.out.print("= ");

            start = false;
            cur = fib(i * 10);

            for (int j = 63; j >= 0; j--){

                if (cur[1][j] != 0) start = true;
                if (start) System.out.print(cur[1][j]);

            }

            if (i == 0) System.out.print(cur[1][0]);
            System.out.println();

        }

    }

}
