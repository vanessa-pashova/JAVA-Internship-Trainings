import java.util.Scanner;

public class Sum_of_Digits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        System.out.printf("Sum of %d: %d", x, sumOfDigits(x));
    }

    public static int sumOfDigits(int x) {
        int sum = 0;
        while(x != 0) {
            sum += x % 10;
            x /= 10;
        }

        return sum;
    }
}
