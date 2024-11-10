package LinkedList_LeetCode;

class ListNode82 {
    int val;
    ListNode82 next;
    ListNode82(int x) {
        val = x;
        next = null;
    }
}

public class Remove_Duplicates_from_Sorted_List_II__leetcode {
    public ListNode82 deleteDuplicates(ListNode82 head) {
        ListNode82 dummy = new ListNode82(0);
        dummy.next = head;
        ListNode82 prev = dummy;

        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val)
                    head = head.next;

                prev.next = head.next;
            }

            else
                prev = prev.next;

            head = head.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Example 1
        ListNode82 head = new ListNode82(1);
        head.next = new ListNode82(2);
        head.next.next = new ListNode82(3);
        head.next.next.next = new ListNode82(3);
        head.next.next.next.next = new ListNode82(4);
        head.next.next.next.next.next = new ListNode82(4);
        head.next.next.next.next.next.next = new ListNode82(5);

        Remove_Duplicates_from_Sorted_List_II__leetcode solution = new Remove_Duplicates_from_Sorted_List_II__leetcode();
        ListNode82 result = solution.deleteDuplicates(head);
        printList(result); // Output: 1 -> 2 -> 5

        // Example 2
        head = new ListNode82(1);
        head.next = new ListNode82(1);
        head.next.next = new ListNode82(1);
        head.next.next.next = new ListNode82(2);
        head.next.next.next.next = new ListNode82(3);

        result = solution.deleteDuplicates(head);
        printList(result); // Output: 2 -> 3
    }

    public static void printList(ListNode82 node) {
        while (node != null) {
            System.out.print(node.val);
            node = node.next;

            if (node != null)
                System.out.print(" -> ");
        }

        System.out.println();
    }
}
