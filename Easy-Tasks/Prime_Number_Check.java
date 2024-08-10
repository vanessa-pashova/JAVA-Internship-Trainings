import java.util.Scanner;

public class Prime_Number_Check {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        if(!primeCheck(x))
            System.out.println("false");

        else System.out.println("true");
    }

    public static Boolean primeCheck(int num) {
        boolean flag = false;
        if(num == 1 || num == 2 || num == 3 || num == 5 || num == 7)
            flag = true;

        else {
            for(short i = 2; i < 10; i++) {
                if(num % i == 0) {
                    flag = false;
                    break;
                }

                else flag = true;
            }
        }

        return flag;
    }
}
