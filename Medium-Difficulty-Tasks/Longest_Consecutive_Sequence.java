import java.util.Arrays;

public class Longest_Consecutive_Sequence {
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) //we check if the array is empty
            return 0;

        Arrays.sort(nums);  //sort the array

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) { // skipping the duplicating elements
                if (nums[i] == nums[i - 1] + 1)
                    currentStreak++;

                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        longestStreak = Math.max(longestStreak, currentStreak);

        return longestStreak;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("Longest consecutive sequence length: " + longestConsecutive(nums)); // Output: 4
    }
}
