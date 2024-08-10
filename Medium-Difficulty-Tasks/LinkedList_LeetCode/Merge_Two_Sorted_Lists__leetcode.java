package LinkedList_LeetCode;

class ListNode21 {
    int val;
    ListNode21 next;
    ListNode21(int x) {
        val = x;
        next = null;
    }
}

public class Merge_Two_Sorted_Lists__leetcode {
    public ListNode21 mergeTwoLists(ListNode21 l1, ListNode21 l2) {
        ListNode21 dummy = new ListNode21(0);
        ListNode21 current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            }

            else {
                current.next = l2;
                l2 = l2.next;
            }

            current = current.next;
        }

        // If either list still has elements left, append them
        if (l1 != null)
            current.next = l1;

        else if (l2 != null)
            current.next = l2;

        return dummy.next;
    }

    public static void main(String[] args) {
        // Example 1
        ListNode21 l1 = new ListNode21(1);
        l1.next = new ListNode21(2);
        l1.next.next = new ListNode21(4);

        ListNode21 l2 = new ListNode21(1);
        l2.next = new ListNode21(3);
        l2.next.next = new ListNode21(4);

        Merge_Two_Sorted_Lists__leetcode solution = new Merge_Two_Sorted_Lists__leetcode();
        ListNode21 result = solution.mergeTwoLists(l1, l2);
        printList(result); // Output: 1 -> 1 -> 2 -> 3 -> 4 -> 4

        // Example 2
        l1 = null;
        l2 = null;
        result = solution.mergeTwoLists(l1, l2);
        printList(result); // Output: (empty list)

        // Example 3
        l1 = null;
        l2 = new ListNode21(0);
        result = solution.mergeTwoLists(l1, l2);
        printList(result); // Output: 0
    }

    public static void printList(ListNode21 node) {
        while (node != null) {
            System.out.print(node.val);
            node = node.next;

            if (node != null)
                System.out.print(" -> ");
        }

        System.out.println();
    }
}
