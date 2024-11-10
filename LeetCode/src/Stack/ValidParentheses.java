package Stack;
import java.util.Stack;
import java.util.Scanner;

public class ValidParentheses {
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char symbol : s.toCharArray()) {
            if(symbol == '(')
                stack.push(')');

            else if(symbol == '{')
                stack.push('}');

            else if(symbol == '[')
                stack.push(']');

            else {
                if(stack.isEmpty() || stack.pop() != symbol)
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        ValidParentheses vp = new ValidParentheses();
        System.out.println(vp.isValid(s));
        scanner.close();
    }
}
