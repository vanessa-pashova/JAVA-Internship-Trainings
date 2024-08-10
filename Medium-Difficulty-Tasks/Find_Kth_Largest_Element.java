import java.util.Random;

public class Find_Kth_Largest_Element {
    public static int getKthElement(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, arr.length - k);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right)
            return arr[left];

        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);

        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex)
            return arr[k];

        else if (k < pivotIndex)
            return quickSelect(arr, left, pivotIndex - 1, k);

        else
            return quickSelect(arr, pivotIndex + 1, right, k);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 2, 8, 3, 7, 4, 6, 5, 0};
        int k = 2;
        System.out.println("The " + k + "th largest element is " + getKthElement(arr, k));
    }
}
