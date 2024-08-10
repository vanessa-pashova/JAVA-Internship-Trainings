package LinkedList_LeetCode;

class ListNode1 {
    int val;
    ListNode1 next;
    ListNode1(int x) {
        val = x;
        next = null;
    }
}

public class Add_Two_Numbers__leetcode {
    public ListNode1 addTwoNumbers(ListNode1 l1, ListNode1 l2) {
        ListNode1 dummyHead = new ListNode1(0);
        ListNode1 p = l1, q = l2, current = dummyHead;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            int sum = x + y;

            current.next = new ListNode1(sum);
            current = current.next;

            if (p != null)
                p = p.next;

            if (q != null)
                q = q.next;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        // Example 1
        ListNode1 l1 = new ListNode1(2);
        l1.next = new ListNode1(4);
        l1.next.next = new ListNode1(3);

        ListNode1 l2 = new ListNode1(5);
        l2.next = new ListNode1(6);
        l2.next.next = new ListNode1(4);

        Add_Two_Numbers__leetcode solution = new Add_Two_Numbers__leetcode();
        ListNode1 result = solution.addTwoNumbers(l1, l2);
        printList(result); // Output: 7 -> 10 -> 7

        // Example 2
        l1 = new ListNode1(1);
        l1.next = new ListNode1(8);

        l2 = new ListNode1(0);

        result = solution.addTwoNumbers(l1, l2);
        printList(result); // Output: 1 -> 8
    }

    public static void printList(ListNode1 node) {
        while (node != null) {
            System.out.print(node.val);
            node = node.next;

            if (node != null)
                System.out.print(" -> ");
        }

        System.out.println();
    }
}
