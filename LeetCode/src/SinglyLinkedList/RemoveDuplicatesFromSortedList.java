package SinglyLinkedList;

import StructNodes.Node;

public class RemoveDuplicatesFromSortedList {
    private static Node<Integer> removeDuplicates(Node<Integer> head) {
        if (head == null)
            throw new IllegalArgumentException("Head must not be null");

        Node<Integer> current = head;

        while (current != null && current.next != null) {
            if (current.data.equals(current.next.data))
                current.next = current.next.next;

            else
                current = current.next;
        }

        return head;
    }

    private static void print(Node<Integer> head) {
        while(head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(1);
        node.next = new Node<>(2);
        node.next.next = new Node<>(2);

        removeDuplicates(node);
        print(node);
    }
}
