public class Find_Missing_Number {

    public static int findNumber(int[] arr, int size) {
        int num = 0;
        for(int i = 1; i < size - 1; i++) {
            if(arr[i - 1] == (arr[i] - 1) && (arr[i] + 1) == arr[i + 1])
                continue;

            else num = ((arr[i - 1] + arr[i + 1]) / 2);
        }

        return num;
    }

    public static void main(String[] args) {
        int size = 9;
        int[] arr = {1, 2, 3, 4, 5, 7, 8, 9, 10};

        System.out.println(findNumber(arr, size));
    }
}
