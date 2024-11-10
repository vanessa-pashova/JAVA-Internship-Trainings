import java.util.Scanner;

public class Remove_Duplicates_from_Array {
    public static void reorder(char[] arr, int index, int size) {
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }

        arr[size - 1] = '\0'; //end of array
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int sizeOfInput = input.length();
        char[] array = input.toCharArray();

        for (int i = 0; i < sizeOfInput - 1; i++) {
            for (int j = i + 1; j < sizeOfInput; j++) {
                if (array[i] == array[j]) {
                    reorder(array, j, sizeOfInput);
                    sizeOfInput--;
                    j--;
                }
            }
        }

        String result = new String(array, 0, sizeOfInput);
        System.out.printf("After removing the duplicates: %s%n", result);
    }
}
