package LinkedList_LeetCode;

class ListNode19 {
    int val;
    ListNode19 next;
    ListNode19(int x) {
        val = x;
        next = null;
    }
}

public class Remove_Nth_Node_From_End_of_List__leetcode {
    public ListNode19 removeNthFromEnd(ListNode19 head, int n) {
        ListNode19 dummy = new ListNode19(0);
        dummy.next = head;
        ListNode19 fast = dummy;
        ListNode19 slow = dummy;

        // Move fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++)
            fast = fast.next;

        // Move both fast and slow pointers until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        // Example 1
        ListNode19 head = new ListNode19(1);
        head.next = new ListNode19(2);
        head.next.next = new ListNode19(3);
        head.next.next.next = new ListNode19(4);
        head.next.next.next.next = new ListNode19(5);

        Remove_Nth_Node_From_End_of_List__leetcode solution = new Remove_Nth_Node_From_End_of_List__leetcode();
        ListNode19 result = solution.removeNthFromEnd(head, 2);
        printList(result); // Output: 1 -> 2 -> 3 -> 5

        // Example 2
        head = new ListNode19(1);
        result = solution.removeNthFromEnd(head, 1);
        printList(result); // Output: (empty list)

        // Example 3
        head = new ListNode19(1);
        head.next = new ListNode19(2);
        result = solution.removeNthFromEnd(head, 1);
        printList(result); // Output: 1
    }

    public static void printList(ListNode19 node) {
        while (node != null) {
            System.out.print(node.val);
            node = node.next;

            if (node != null)
                System.out.print(" -> ");
        }

        System.out.println();
    }
}
