package BinaryTree_LeetCode;

class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;
    TreeNode2(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Invert_Binary_Tree__leetcode {
    public TreeNode2 invertTree(TreeNode2 root) {
        if (root == null)
            return null;

        // Swap the left and right children
        TreeNode2 temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively invert the left and right subtrees
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode2 root1 = new TreeNode2(4);
        root1.left = new TreeNode2(2);
        root1.right = new TreeNode2(7);
        root1.left.left = new TreeNode2(1);
        root1.left.right = new TreeNode2(3);
        root1.right.left = new TreeNode2(6);
        root1.right.right = new TreeNode2(9);

        Invert_Binary_Tree__leetcode solution = new Invert_Binary_Tree__leetcode();
        TreeNode2 result1 = solution.invertTree(root1);
        printTree(result1); // Output should represent the inverted tree [4, 7, 2, 9, 6, 3, 1]

        // Example 2
        TreeNode2 root2 = new TreeNode2(2);
        root2.left = new TreeNode2(1);
        root2.right = new TreeNode2(3);

        TreeNode2 result2 = solution.invertTree(root2);
        printTree(result2); // Output should represent the inverted tree [2, 3, 1]
    }

    public static void printTree(TreeNode2 root) {
        if (root == null)
            return;

        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}
