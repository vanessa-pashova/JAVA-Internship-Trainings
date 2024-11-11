package Stack;
import java.util.Stack;
import java.util.Scanner;

public class ValidParentheses {
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char symbol : s.toCharArray()) {
            if(symbol == '(' || symbol == '{' || symbol == '[')
                stack.push(symbol);

            else {
                if(stack.isEmpty())
                    return false;

                char top = stack.peek();

                if(symbol == ')' && top == '(' || symbol == '}' && top == '{' || symbol == ']' && top == '[')
                    stack.pop();

                else
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
