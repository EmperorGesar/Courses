/**
 * Fibonacci numbers compute with time complexity O(Fn)
 */

public class asn1_a {

    public static long fib(int n){

        if (n == 0){
            return 0;
        } else if (n == 1) {
            return 1;
        }else{
            return fib(n - 1) + fib(n - 2);
        }

    }

    public static void main(String[] args){

        for (int i = 0; i <= 10; i++){

            System.out.print(" fib (" + i * 5 + ") ");
            if (i < 2) System.out.print(" = ");
            else System.out.print("= ");
            System.out.println(fib(i * 5));

        }

    }

}
