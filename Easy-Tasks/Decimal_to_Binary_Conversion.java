import java.util.Scanner;

public class Decimal_to_Binary_Conversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dec = scanner.nextInt();
        scanner.close();

        int[] bin = convertToBinary(dec);

        System.out.print("Decimal number: " + dec + "; Binary number: ");
        for (int i = 0; i < bin.length; i++) {
            System.out.print(bin[i]);
        }
    }

    public static int[] convertToBinary(int num) {
        int[] number = new int[32];
        int index = 0;

        while (num > 0) {
            number[index] = num % 2;
            index++;
            num /= 2;
        }

        for (int i = 0; i < index / 2; i++) {
            int temp = number[i];
            number[i] = number[index - 1 - i];
            number[index - 1 - i] = temp;
        }

        int[] result = new int[index];
        for (int i = 0; i < index; i++)
            result[i] = number[i];

        return result;
    }
}
