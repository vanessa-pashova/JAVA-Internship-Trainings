import java.util.Arrays;

public class Prime_Factors {
    public static boolean primeNum(int num) {
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

    public static void primes(int num) {
        int size = 10, index = 0;
        int[] prime = new int[size];

        for (int i = 2; i <= num; i++) {
            if (num % i == 0 && primeNum(i)) {
                prime[index] = i;
                index++;
            }
        }

        for (int x = 0; x < index; x++)
            System.out.print(prime[x] + " ");

        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {6, 10, 8, 20, 16, 18, 231, 31};

        for (int num : arr) {
            System.out.print("Prime factors of " + num + ": ");
            primes(num);
        }
    }
}
