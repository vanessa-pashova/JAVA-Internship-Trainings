package Stack;

import StructNodes.Node;
import java.util.Stack;

public class PalindromeLinkedList {

    public static boolean isPalindrome(Node<Integer> node) {
        if (node == null)
            throw new IllegalArgumentException("StructNodes.Node should not be null");

        if (node.next == null)
            return true;

        Stack<Integer> stack = new Stack<>();
        Node<Integer> slow = node;
        Node<Integer> fast = node;

        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null)
            slow = slow.next;

        while (slow != null) {
            if (!stack.pop().equals(slow.data))
                return false;

            slow = slow.next;
        }

        return true;
    }

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(1);
        node.next = new Node<>(2);
        node.next.next = new Node<>(2);
        node.next.next.next = new Node<>(1);

        System.out.println(PalindromeLinkedList.isPalindrome(node) ? "true" : "false");
    }
}