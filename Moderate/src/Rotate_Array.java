public class Rotate_Array {
    public static void shift(int[] arr, int shift, int size) {
        for(int i = 0; i < shift; i++) {
            for(int j = 0; j < size - 1; j++) {
                int temp = arr[size - 1];
                arr[size - 1] = arr[size - 2 - j];
                arr[size - 2 - j] = temp;
            }
        }

        for(int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int size = 5, shift = 1;

        shift(arr, shift, size);
    }
}
