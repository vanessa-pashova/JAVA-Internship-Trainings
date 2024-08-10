public class Find_Intersection_of_Two_Arrays {
    public static boolean exists(int[] arr, int size, int value) {
        boolean flag = false;
        for(int i = 0; i < size; i++) {
            if(value == arr[i]) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public static int smallerSize(int size1, int size2) {
        return Math.min(size1, size2);
    }

    public static int greaterSize(int size1, int size2) {
        return Math.max(size1, size2);
    }

    public static void print(int[] arr, int size) {
        for(int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
    }

    public static void union(int[] arr1, int[] arr2, int size1, int size2) {
        int smallerSize = smallerSize(size1, size2), greaterSize = greaterSize(size1, size2), indexUnion = 0;
        int[] union = new int[smallerSize];

        if(size1 < size2) {
            for(int i = 0; i < smallerSize; i++) {
                for(int j = 0; j < greaterSize; j++) {
                    if(arr1[i] == arr2[j] && !exists(union, smallerSize, arr1[i])) {
                        union[indexUnion] = arr1[i];
                        indexUnion++;
                    }
                }
            }
        }

        else if(size1 > size2) {
            for(int i = 0; i < smallerSize; i++) {
                for(int j = 0; j < greaterSize; j++) {
                    if(arr1[j] == arr2[i] && !exists(union, smallerSize, arr1[i])) {
                        union[indexUnion] = arr1[j];
                        indexUnion++;
                    }
                }
            }
        }

        print(union, indexUnion);
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7}, arr2 = {5, 6, 7, 8, 9, 0};
        union(arr2, arr1, 6, 7);
    }
}
