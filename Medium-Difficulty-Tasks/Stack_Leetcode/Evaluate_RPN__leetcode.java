package Stack_Leetcode;

import java.util.Stack;

public class Evaluate_RPN__leetcode {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;

                case "-":
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;

                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;

                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a / b);
                    break;

                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Evaluate_RPN__leetcode evaluator = new Evaluate_RPN__leetcode();

        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println(evaluator.evalRPN(tokens1)); // Output: 9

        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println(evaluator.evalRPN(tokens2)); // Output: 6

        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evaluator.evalRPN(tokens3)); // Output: 22
    }
}
