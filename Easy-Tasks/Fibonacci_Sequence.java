import java.util.Scanner;

public class Fibonacci_Sequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        System.out.printf("Fib Calc: %d", fibonacciSeq(N));
    }

    public static long fibonacciSeq(int num) {
        if(num < 0)
            throw new IllegalArgumentException("Should be greater than 0!\n");

        else if(num == 0)
            return 0;

        else if(num == 1)
            return 1;

        else
            return fibonacciSeq(num - 1) + fibonacciSeq(num - 2);
    }
}

