package LinkedList_LeetCode;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Linked_List_Cycle__leetcode {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        // Example 1
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next; // Creating a cycle

        Linked_List_Cycle__leetcode solution = new Linked_List_Cycle__leetcode();
        System.out.println(solution.hasCycle(head1)); // Output: true

        // Example 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2; // Creating a cycle

        System.out.println(solution.hasCycle(head2)); // Output: true

        // Example 3
        ListNode head3 = new ListNode(1);
        System.out.println(solution.hasCycle(head3)); // Output: false
    }
}
