package BinaryTree_LeetCode;

class TreeNode7 {
    int val;
    TreeNode7 left;
    TreeNode7 right;
    TreeNode7(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Path_Sum__leetcode {
    public boolean hasPathSum(TreeNode7 root, int targetSum) {
        if (root == null)
            return false;

        // Check if the current node is a leaf node and its value is equal to the remaining sum
        if (root.left == null && root.right == null && root.val == targetSum)
            return true;

        // Recursively check the left and right subtrees with the updated targetSum
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode7 root1 = new TreeNode7(5);
        root1.left = new TreeNode7(4);
        root1.right = new TreeNode7(8);
        root1.left.left = new TreeNode7(11);
        root1.left.left.left = new TreeNode7(7);
        root1.left.left.right = new TreeNode7(2);
        root1.right.left = new TreeNode7(13);
        root1.right.right = new TreeNode7(4);
        root1.right.right.right = new TreeNode7(1);

        Path_Sum__leetcode solution = new Path_Sum__leetcode();
        boolean result1 = solution.hasPathSum(root1, 22);
        System.out.println(result1); // Output: true

        // Example 2
        TreeNode7 root2 = new TreeNode7(1);
        root2.left = new TreeNode7(2);
        boolean result2 = solution.hasPathSum(root2, 1);
        System.out.println(result2); // Output: false
    }
}
