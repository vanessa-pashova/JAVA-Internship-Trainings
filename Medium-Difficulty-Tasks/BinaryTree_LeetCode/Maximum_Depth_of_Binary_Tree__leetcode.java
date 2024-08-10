package BinaryTree_LeetCode;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Maximum_Depth_of_Binary_Tree__leetcode {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        Maximum_Depth_of_Binary_Tree__leetcode solution = new Maximum_Depth_of_Binary_Tree__leetcode();
        System.out.println(solution.maxDepth(root1)); // Output: 3

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = null;
        root2.right = new TreeNode(2);

        System.out.println(solution.maxDepth(root2)); // Output: 2
    }
}
