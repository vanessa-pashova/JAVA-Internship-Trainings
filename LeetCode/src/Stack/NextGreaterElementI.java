package Stack;

import java.util.Stack;

public class NextGreaterElementI {
    private static Stack<Integer> nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0)
            throw new IllegalArgumentException("Arrs must not be empty");

        Stack<Integer> stack = new Stack<>();

        for(int current : nums1) {
            boolean found = false, pushed = false;

            for(int j = 0; j < nums2.length; ++j) {
                if(current == nums2[j])
                    found = true;

                if(found && (j + 1 < nums2.length) && current < nums2[j + 1]) {
                    stack.push(nums2[j + 1]);
                    pushed = true;
                    break;
                }
            }

            if(!pushed)
                stack.push(-1);
        }

        return stack;
    }

    public static void main(String[] args) {
        int arr1[] = {4, 1, 2}, arr2[] = {1, 3, 4, 2};
        System.out.println(nextGreaterElement(arr1, arr2));

        System.out.println();

        int arr3[] = {2, 4}, arr4[] = {1, 2, 3, 4};
        System.out.println(nextGreaterElement(arr3, arr4));
    }
}
