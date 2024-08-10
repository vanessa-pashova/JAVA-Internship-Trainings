import java.util.Scanner;

public class Binary_to_Decimal_Conversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bin = scanner.nextInt();

        System.out.printf("Number in binary: %d; Number in decimal: %d", bin, convertToDecimal(bin));
    }

    public static int convertToDecimal(int x) {
        int num = 0, power = 0;
        while(x != 0) {
            int current = (x % 10) * (powerOfTwo(power));
            num += current;
            power++;
            x /= 10;
        }

        return num;
    }

    public static int powerOfTwo(int pow) {
        int num = 2;

        if(pow == 0)
            return 1;

        else {
            for(short i = 1; i < pow; i++)
                num *= 2;
        }

        return num;
    }
}
