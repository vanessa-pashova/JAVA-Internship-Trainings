package Stack;

import java.util.Stack;

public class BaseballGame {
    private static int baseballScore(char[] operations) {
        if(operations == null || operations.length == 0)
            throw new IllegalArgumentException("Operations cannot be null or empty");

        Stack<Integer> stack = new Stack<>();

        for(char operation : operations) {
            if(operation == '+') {
                int top = stack.pop();
                int bothPoints = top + stack.peek();

                stack.push(top);
                stack.push(bothPoints);
            }

            else if(operation == 'D')
                stack.push(2 * stack.peek());

            else if(operation == 'C')
                stack.pop();

            else
                stack.push(Integer.parseInt(String.valueOf(operation)));
        }

        int result = 0;
        for(int i : stack)
            result += i;

        return result;
    }

    public static void main(String[] args) {
        char[] ops = {'5','2','C','D','+'};
        System.out.println(baseballScore(ops));
    }
}
