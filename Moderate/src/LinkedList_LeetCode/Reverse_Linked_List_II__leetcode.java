package LinkedList_LeetCode;

class ListNode92 {
    int val;
    ListNode92 next;
    ListNode92(int x) {
        val = x;
        next = null;
    }
}

public class Reverse_Linked_List_II__leetcode {
    public ListNode92 reverseBetween(ListNode92 head, int left, int right) {
        if (head == null || left == right)
            return head;

        ListNode92 dummy = new ListNode92(0);
        dummy.next = head;
        ListNode92 prev = dummy;

        // Move `prev` to the node before the `left` position
        for (int i = 1; i < left; i++)
            prev = prev.next;

        ListNode92 start = prev.next;
        ListNode92 then = start.next;

        // Perform the reverse operation between `left` and `right`
        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = start.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Example 1
        ListNode92 head = new ListNode92(1);
        head.next = new ListNode92(2);
        head.next.next = new ListNode92(3);
        head.next.next.next = new ListNode92(4);
        head.next.next.next.next = new ListNode92(5);

        Reverse_Linked_List_II__leetcode solution = new Reverse_Linked_List_II__leetcode();
        ListNode92 result = solution.reverseBetween(head, 2, 4);
        printList(result); // Output: 1 -> 4 -> 3 -> 2 -> 5

        // Example 2
        head = new ListNode92(5);
        result = solution.reverseBetween(head, 1, 1);
        printList(result); // Output: 5
    }

    public static void printList(ListNode92 node) {
        while (node != null) {
            System.out.print(node.val);
            node = node.next;

            if (node != null)
                System.out.print(" -> ");

        }
        System.out.println();
    }
}
