

public class Move_Zeroes {
    public static final int SIZE = 14;
    public static void main(String[] args) {
        int[] array = {1, 4, 0, 3, 0, 2, 6, 7, 0, 10, 5, 0, 8, 9};

        moveZeroes(array, SIZE);
        print(array, SIZE);
    }


    public static void moveZeroes(int[] arr, int size) {
        for(short i = 0; i < size; i++) {
            if(arr[i] == 0)
                reorder(arr, i, size);
        }
    }

    public static void reorder(int[] arr, int index, int size) {
        for(int i = index; i < size - 1; i++) {
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
    }

    public static void print(int[] arr, int size) {
        for(int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
    }
}