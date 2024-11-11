package StructNodes;

public class Node<T> {
    //It's not a good or common practice on my part, but for the purposes of saving redundant code, the attributes will be public
    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
