import java.util.Scanner;

public class Power_of_Two {
     public static boolean checkPower(int x) {
         boolean flag = false;

         if(x == 1)
             flag = true;

         for(int i = 1; i < x; i++) {
             if(i * i == x) {
                 flag = true;
                 break;
             }
         }

         return flag;
     }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        System.out.printf("Power of Two check: %b", checkPower(x));
    }
}
