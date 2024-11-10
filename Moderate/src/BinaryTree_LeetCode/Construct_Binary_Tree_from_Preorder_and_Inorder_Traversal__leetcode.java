package BinaryTree_LeetCode;

import java.util.HashMap;
import java.util.Map;

class TreeNode4 {
    int val;
    TreeNode4 left;
    TreeNode4 right;
    TreeNode4(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal__leetcode {
    private int preorderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode4 buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderIndexMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++)
            inorderIndexMap.put(inorder[i], i);

        return arrayToTree(preorder, 0, inorder.length - 1);
    }

    private TreeNode4 arrayToTree(int[] preorder, int left, int right) {
        if (left > right)
            return null;

        // Select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode4 root = new TreeNode4(rootValue);

        // Build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);

        return root;
    }

    public static void main(String[] args) {
        Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal__leetcode solution = new Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal__leetcode();

        // Example 1
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        TreeNode4 root1 = solution.buildTree(preorder1, inorder1);
        printTree(root1); // Output should represent the tree structure

        // Example 2
        int[] preorder2 = {-1};
        int[] inorder2 = {-1};
        TreeNode4 root2 = solution.buildTree(preorder2, inorder2);
        printTree(root2); // Output should represent the tree structure
    }

    public static void printTree(TreeNode4 root) {
        if (root == null)
            return;

        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}
