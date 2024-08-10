package BinaryTree_LeetCode;

class TreeNode3 {
    int val;
    TreeNode3 left;
    TreeNode3 right;
    TreeNode3(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Symmetric_Tree__leetcode {
    public boolean isSymmetric(TreeNode3 root) {
        if (root == null)
            return true;

        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode3 t1, TreeNode3 t2) {
        if (t1 == null && t2 == null)
            return true;

        if (t1 == null || t2 == null)
            return false;

        return (t1.val == t2.val) && isMirror(t1.right, t2.left) && isMirror(t1.left, t2.right);
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode3 root1 = new TreeNode3(1);
        root1.left = new TreeNode3(2);
        root1.right = new TreeNode3(2);
        root1.left.left = new TreeNode3(3);
        root1.left.right = new TreeNode3(4);
        root1.right.left = new TreeNode3(4);
        root1.right.right = new TreeNode3(3);

        Symmetric_Tree__leetcode solution = new Symmetric_Tree__leetcode();
        System.out.println(solution.isSymmetric(root1)); // Output: true

        // Example 2
        TreeNode3 root2 = new TreeNode3(1);
        root2.left = new TreeNode3(2);
        root2.right = new TreeNode3(2);
        root2.left.right = new TreeNode3(3);
        root2.right.right = new TreeNode3(3);

        System.out.println(solution.isSymmetric(root2)); // Output: false
    }
}
