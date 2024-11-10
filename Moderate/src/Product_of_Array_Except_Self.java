public class Product_of_Array_Except_Self {
    public static int getSum(int[] arr, int current) {
        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            if(i == current)
                continue;

            sum += arr[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for(int i = 0; i < arr.length; i++) {
            System.out.println(getSum(arr, i));
        }
    }
}
