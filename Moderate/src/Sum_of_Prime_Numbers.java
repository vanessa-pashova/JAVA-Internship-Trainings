public class Sum_of_Prime_Numbers {
    public static boolean primeCheck(int x) {
        boolean flag = false;
        if(x == 1 || x == 2 || x == 3 || x == 5 || x == 7)
            flag = true;

        else {
            for(int i = 2; i < 8; i++) {
                if(x % i == 0) {
                    flag = false;
                    break;
                }

                else flag = true;
            }
        }

        return flag;
    }

    public static int sumOfPrimes(int n) {
        int sum = 0;

        for(int i = 1; i <= n; i++) {
            if(primeCheck(i))
                sum += i;
        }

        return sum;
    }

    public static void main(String[] args) {
        int N = 15;

        System.out.print("Sum of primes: " + sumOfPrimes(N));
    }
}
