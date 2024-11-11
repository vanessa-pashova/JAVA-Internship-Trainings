package Stack;

import StructNodes.TreeNode;
import java.util.Stack;


public class BinaryTreePreorderTraversal {
    public static void preorderTraversal(TreeNode root) {
        if(root == null)
            throw new IllegalArgumentException("Root cannot be null");

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                System.out.print(curr.value + " ");
                curr = curr.left;
            }

            curr = stack.pop();
            curr = curr.right;
        }
    }

    public static void main(String[] args) {
        try {
            TreeNode root = new TreeNode(1);
            root.right = new TreeNode(2);
            root.right.left = new TreeNode(3);

            BinaryTreePreorderTraversal.preorderTraversal(root);
        } catch (IllegalArgumentException e) {
            System.err.print(e.getMessage());
        }
    }
}
