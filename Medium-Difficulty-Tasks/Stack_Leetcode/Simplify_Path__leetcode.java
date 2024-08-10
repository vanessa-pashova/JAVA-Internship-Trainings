package Stack_Leetcode;

import java.util.Stack;

public class Simplify_Path__leetcode {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");

        for (String component : components) {
            if (component.equals("") || component.equals("."))
                continue; // Игнорираме празните компоненти и тези, които са "."

            else if (component.equals("..")) {
                // Ако компонентът е "..", връщаме се една директория назад
                if (!stack.isEmpty())
                    stack.pop();
            }

            else
                stack.push(component); // Добавяме валидните директории в стека
        }

        // Събираме компонентите от стека в каноничния път
        StringBuilder result = new StringBuilder();

        for (String dir : stack)
            result.append("/").append(dir);

        // Ако стекът е празен, означава, че сме в кореновата директория
        return result.length() > 0 ? result.toString() : "/";
    }

    public static void main(String[] args) {
         Simplify_Path__leetcode sp = new  Simplify_Path__leetcode();
        System.out.println(sp.simplifyPath("/home/")); // Output: "/home"
        System.out.println(sp.simplifyPath("/home//foo/")); // Output: "/home/foo"
        System.out.println(sp.simplifyPath("/home/user/Documents/../Pictures")); // Output: "/home/user/Pictures"
        System.out.println(sp.simplifyPath("/../")); // Output: "/"
        System.out.println(sp.simplifyPath("/.../a/../b/c/../d/./")); // Output: "/.../b/d"
    }
}

