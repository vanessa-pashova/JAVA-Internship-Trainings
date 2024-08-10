import java.util.Scanner;

public class Factorial_Calculation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        short num = scanner.nextShort();
        System.out.printf("Factorial of %d: %d", num, factorial(num));
    }

    public static Short factorial(short num) {
        if(num > 0) {
            short x = 1;
            for(short i = 1; i <= num; i++)
                x *= i;

            return x;
        }

        else return 0;
    }
}
