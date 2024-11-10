import java.util.Scanner;
public class Palindrome_Check {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        if(palindromCheck(str))
            System.out.println("true");

        else System.out.println("false");
    }

    public static boolean palindromCheck(String str) {
        if (str == null || str.isEmpty())
            return false;

        int left = 0, right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }
}

