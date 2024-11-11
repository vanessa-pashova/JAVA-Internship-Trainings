package StructNodes;


public class TreeNode {
    //It's not a good or common practice on my part, but for the purposes of saving redundant code, the attributes will be public
    public int value;
    public TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
    }
}
