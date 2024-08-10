package BinaryTree_LeetCode;

class TreeNode1 {
    int val;
    TreeNode1 left;
    TreeNode1 right;
    TreeNode1(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Same_Tree__leetcode {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both trees are empty
        if (p == null && q == null)
            return true;

        // One tree is empty, and the other is not
        if (p == null || q == null)
            return false;

        // Both trees are not empty, compare current nodes and recurse
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);

        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);

        Same_Tree__leetcode solution = new Same_Tree__leetcode();
        System.out.println(solution.isSameTree(p1, q1)); // Output: true

        // Example 2
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);

        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);

        System.out.println(solution.isSameTree(p2, q2)); // Output: false
    }
}
