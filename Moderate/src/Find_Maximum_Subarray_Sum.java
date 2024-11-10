public class Find_Maximum_Subarray_Sum {
    //(Kadane's Algorithm) - linear complexity
    public static int maxSubArraySum(int[] arr) {
        int max_so_far = arr[0];
        int max_ending_here = arr[0];

        //we start from the second element and we go till the end
        for (int i = 1; i < arr.length; i++) {
            max_ending_here = Math.max(arr[i], max_ending_here + arr[i]); // comparing the current element of the array with the sum of the current value and current element
            max_so_far = Math.max(max_so_far, max_ending_here); //comparing the max value till now with the current max value
        }

        return max_so_far;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Max sum of the subarray is: " + maxSubArraySum(arr)); // Output: 6
    }
}
