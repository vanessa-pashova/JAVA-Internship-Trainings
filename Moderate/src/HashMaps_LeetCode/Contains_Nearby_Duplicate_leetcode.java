package HashMaps_LeetCode;

import java.util.HashMap;

public class Contains_Nearby_Duplicate_leetcode {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int lastIndex = map.get(nums[i]);

                if (i - lastIndex <= k)
                    return true;
            }

            map.put(nums[i], i);
        }

        return false;
    }

    public static void main(String[] args) {
        Contains_Nearby_Duplicate_leetcode cnd = new Contains_Nearby_Duplicate_leetcode();
        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        System.out.println(cnd.containsNearbyDuplicate(nums1, k1)); // Output: true

        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;
        System.out.println(cnd.containsNearbyDuplicate(nums2, k2)); // Output: true

        int[] nums3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;
        System.out.println(cnd.containsNearbyDuplicate(nums3, k3)); // Output: false
    }
}
