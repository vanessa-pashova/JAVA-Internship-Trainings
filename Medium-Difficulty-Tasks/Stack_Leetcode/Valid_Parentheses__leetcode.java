package Stack_Leetcode;

import java.util.Stack;

public class Valid_Parentheses__leetcode {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);

            else {
                if (stack.isEmpty())
                    return false;

                char top = stack.pop();

                if (    (c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Valid_Parentheses__leetcode vp = new Valid_Parentheses__leetcode();
        System.out.println(vp.isValid("()")); // Output: true
        System.out.println(vp.isValid("()[]{}")); // Output: true
        System.out.println(vp.isValid("(]")); // Output: false
    }
}
