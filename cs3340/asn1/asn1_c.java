/**
 * Fibonacci numbers compute with time complexity O(log(n))
 */

public class asn1_c {

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

    public static int[] times(int[] x, int[] y){

        int[] product = new int[64];
        int carry, length = 0;

        for (int i = 63; i >= 0; i--){
            if (y[i] != 0){
                length = i + 1;
                break;
            }
        }

        for (int i = 0; i < 64; i++){

            carry = 0;

            if (length + i < 64) {

                for (int j = 0; j < length; j++){
                    product[i + j] = product[i + j] + x[i] * y[j] + carry;
                    carry = product[i + j] / 10;
                    product[i + j] = product[i + j] % 10;
                }

                product[length + i] = carry;

            }

        }

        return product;

    }

    public static int[][] multiply(int[][] a, int[][] b){

        int[][] result = new int[4][64];

        result[0] = add(times(a[0], b[0]), times(a[1], b[2]));
        result[1] = add(times(a[0], b[1]), times(a[1], b[3]));
        result[2] = add(times(a[2], b[0]), times(a[3], b[2]));
        result[3] = add(times(a[2], b[1]), times(a[3], b[3]));

        return result;

    }

    public static int[][] fib(int n){

        int[][] result = new int[4][64];

        if (n == 0){

            result[0][0] = 1; result[1][0] = 0;
            result[2][0] = 0; result[3][0] = 1;

            return result;

        } else if (n == 1){

            result[0][0] = 1; result[1][0] = 1;
            result[2][0] = 1; result[3][0] = 0;

            return result;

        } else {
            result = fib(n / 2);
            return multiply(multiply(result, result), fib(n % 2));
        }

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
                if (cur[2][j] != 0) start = true;
                if (start) System.out.print(cur[2][j]);
            }

            if (i == 0) System.out.print(cur[1][0]);
            System.out.println();

        }

    }

}
