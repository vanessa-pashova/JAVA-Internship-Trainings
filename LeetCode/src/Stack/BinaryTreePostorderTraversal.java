package Stack;

import java.util.Stack;

public class BinaryTreePostorderTraversal {
    public static void postorderTraversal(TreeNode root) {
        if (root == null)
            throw new IllegalArgumentException("The root of the binary tree cannot be null.");

        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.peek();
            if (root.right == null || root.right == prev) {
                System.out.print(root.value + " ");
                stack.pop();
                prev = root;
                root = null;
            }

            else
                root = root.right;
        }
    }

    public static void main(String[] args) {
        try {
            TreeNode root = new TreeNode(1);
            root.right = new TreeNode(2);
            root.right.left = new TreeNode(3);

            postorderTraversal(root);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
