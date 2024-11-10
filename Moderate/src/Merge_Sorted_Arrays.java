public class Merge_Sorted_Arrays {
    public static void mergeSorted(int[] arr1, int[] arr2, int size1, int size2) {
        int sizeNew = size1 + size2;
        int[] union = new int[sizeNew];

        int index = 0, i = 0, j = 0;
        while(index < sizeNew) {
            union[index] = arr1[i];
            union[index + 1] = arr2[j];
            index += 2;
            i++;
            j++;
        }

        for(int x = 0; x < sizeNew; x++)
            System.out.print(union[x] + " ");

        System.out.println();
    }

    public static void mergeSorted2(int[] arr1, int[] arr2, int size1, int size2) {
        int newSize = size1 + size2;
        int[] union = new int[newSize];
        int index = 0, i = 0, j = 0;

        while(i < size1 && j < size2 && index < newSize) {
            union[index] = arr1[i];
            union[index + 1] = arr2[j];
            index += 2;
            i++;
            j++;
        }

        for(i = 1; i < newSize - 1; i++) {
            for(j = i + 1; j < newSize; j++) {
                if(union[i] > union[j]) {
                    int temp = union[i];
                    union[i] = union[j];
                    union[j] = temp;
                }
            }
        }

        for(int x = 0; x < newSize; x++)
            System.out.print(union[x] + " ");
    }

    public static void main(String[] args) {
        int[] odd = {1, 3, 5, 7, 9};
        int[] even = {2, 4, 6, 8, 10};

        mergeSorted(odd, even, 5, 5);

        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {6, 7, 8, 9, 10};

        mergeSorted2(arr1, arr2, 5, 5);
    }
}
