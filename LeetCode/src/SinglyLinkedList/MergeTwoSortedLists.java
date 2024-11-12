package SinglyLinkedList;

import StructNodes.Node;

public class MergeTwoSortedLists {
    private static Node<Integer> mergeTwoLists(Node<Integer> head1, Node<Integer> head2) {
        if(head1 == null && head2 == null)
            throw new IllegalArgumentException("Lists must not be null");

        Node<Integer> dummy = new Node<>(0), current = dummy;

        while(head1 != null && head2 != null) {
            if(head1.data < head2.data) {
                current.next = head1;
                head1 = head1.next;
            }

            else {
                current.next = head2;
                head2 = head2.next;
            }

            current = current.next;
        }

        if(head1 != null)
            current.next = head1;

        else if(head2 != null)
            current.next = head2;

        return dummy.next;
    }

    public static void print(Node<Integer> head) {
        while(head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node<Integer> list1 = new Node<>(1);
        list1.next = new Node<>(2);
        list1.next.next = new Node<>(4);

        Node<Integer> list2 = new Node<>(1);
        list2.next = new Node<>(3);
        list2.next.next = new Node<>(4);

        Node<Integer> head = mergeTwoLists(list1, list2);
        print(head);
    }
}