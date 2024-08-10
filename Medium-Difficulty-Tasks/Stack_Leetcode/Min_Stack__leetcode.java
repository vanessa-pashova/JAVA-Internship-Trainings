package Stack_Leetcode;

import java.util.Stack;

class Min_Stack__leetcode {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** Initialize the stack object. */
    public Min_Stack__leetcode() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /** Push element val onto the stack. */
    public void push(int val) {
        stack.push(val);

        if (minStack.isEmpty() || val <= minStack.peek())
            minStack.push(val);

    }

    /** Removes the element on the top of the stack. */
    public void pop() {
        if (stack.peek().equals(minStack.peek()))
            minStack.pop();

        stack.pop();
    }

    /** Get the top element. */
    public int top() {
        return stack.peek();
    }

    /** Retrieve the minimum element in the stack. */
    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        Min_Stack__leetcode minStack = new Min_Stack__leetcode();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}

