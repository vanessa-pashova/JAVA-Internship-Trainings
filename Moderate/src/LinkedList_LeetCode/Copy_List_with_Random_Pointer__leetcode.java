package LinkedList_LeetCode;

class Node138 {
    int val;
    Node138 next;
    Node138 random;

    public Node138(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Copy_List_with_Random_Pointer__leetcode {
    public Node138 copyRandomList(Node138 head) {
        if (head == null)
            return null;

        // Step 1: Create new nodes and interleave them with the original nodes
        Node138 current = head;
        while (current != null) {
            Node138 newNode = new Node138(current.val);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next;
        }

        // Step 2: Assign random pointers to the new nodes
        current = head;
        while (current != null) {
            if (current.random != null)
                current.next.random = current.random.next;

            current = current.next.next;
        }

        // Step 3: Separate the original list and the copied list
        current = head;
        Node138 copiedHead = head.next;
        Node138 copiedCurrent = copiedHead;

        while (current != null) {
            current.next = current.next.next;

            if (copiedCurrent.next != null)
                copiedCurrent.next = copiedCurrent.next.next;

            current = current.next;
            copiedCurrent = copiedCurrent.next;
        }

        return copiedHead;
    }

    public static void main(String[] args) {
        // Example 1
        Node138 node1 = new Node138(7);
        Node138 node2 = new Node138(13);
        Node138 node3 = new Node138(11);
        Node138 node4 = new Node138(10);
        Node138 node5 = new Node138(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node1.random = null;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        Copy_List_with_Random_Pointer__leetcode solution = new Copy_List_with_Random_Pointer__leetcode();
        Node138 copiedHead = solution.copyRandomList(node1);
        printList(copiedHead);
    }

    public static void printList(Node138 node) {
        while (node != null) {
            System.out.print("Node value: " + node.val + ", Random value: " + (node.random != null ? node.random.val : "null") + "\n");
            node = node.next;
        }
    }
}
