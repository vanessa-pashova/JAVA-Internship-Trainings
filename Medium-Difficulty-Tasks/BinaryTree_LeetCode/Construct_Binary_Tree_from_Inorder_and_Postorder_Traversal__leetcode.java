package BinaryTree_LeetCode;

import java.util.HashMap;
import java.util.Map;

class TreeNode5 {
    int val;
    TreeNode5 left;
    TreeNode5 right;
    TreeNode5(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal__leetcode {
    private int postorderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode5 buildTree(int[] inorder, int[] postorder) {
        postorderIndex = postorder.length - 1;
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inorderIndexMap.put(inorder[i], i);

        return arrayToTree(postorder, 0, inorder.length - 1);
    }

    private TreeNode5 arrayToTree(int[] postorder, int left, int right) {
        if (left > right)
            return null;

        // Select the postorderIndex element as the root and decrement it
        int rootValue = postorder[postorderIndex--];
        TreeNode5 root = new TreeNode5(rootValue);

        // Build right and left subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.right = arrayToTree(postorder, inorderIndexMap.get(rootValue) + 1, right);
        root.left = arrayToTree(postorder, left, inorderIndexMap.get(rootValue) - 1);

        return root;
    }

    public static void main(String[] args) {
        Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal__leetcode solution = new Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal__leetcode();

        // Example 1
        int[] inorder1 = {9, 3, 15, 20, 7};
        int[] postorder1 = {9, 15, 7, 20, 3};
        TreeNode5 root1 = solution.buildTree(inorder1, postorder1);
        printTree(root1); // Output should represent the tree structure

        // Example 2
        int[] inorder2 = {-1};
        int[] postorder2 = {-1};
        TreeNode5 root2 = solution.buildTree(inorder2, postorder2);
        printTree(root2); // Output should represent the tree structure
    }

    public static void printTree(TreeNode5 root) {
        if (root == null)
            return;

        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}
