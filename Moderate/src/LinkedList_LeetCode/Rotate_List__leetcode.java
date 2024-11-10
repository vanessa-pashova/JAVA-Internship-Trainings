package LinkedList_LeetCode;

class ListNode61 {
    int val;
    ListNode61 next;
    ListNode61(int x) {
        val = x;
        next = null;
    }
}

public class Rotate_List__leetcode {
    public ListNode61 rotateRight(ListNode61 head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;

        // Find the length of the list
        ListNode61 current = head;
        int len = 1;
        while (current.next != null) {
            current = current.next;
            len++;
        }

        // Connect the end of the list to the head to form a cycle
        current.next = head;

        // Find the new head after rotation
        k = k % len;
        for (int i = 0; i < len - k; i++)
            current = current.next;

        // Break the cycle and set the new head
        ListNode61 newHead = current.next;
        current.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        // Example 1
        ListNode61 head = new ListNode61(1);
        head.next = new ListNode61(2);
        head.next.next = new ListNode61(3);
        head.next.next.next = new ListNode61(4);
        head.next.next.next.next = new ListNode61(5);

        Rotate_List__leetcode solution = new Rotate_List__leetcode();
        ListNode61 result = solution.rotateRight(head, 2);
        printList(result); // Output: 4 -> 5 -> 1 -> 2 -> 3

        // Example 2
        head = new ListNode61(0);
        head.next = new ListNode61(1);
        head.next.next = new ListNode61(2);
        result = solution.rotateRight(head, 4);
        printList(result); // Output: 2 -> 0 -> 1
    }

    public static void printList(ListNode61 node) {
        while (node != null) {
            System.out.print(node.val);
            node = node.next;

            if (node != null)
                System.out.print(" -> ");

        }

        System.out.println();
    }
}