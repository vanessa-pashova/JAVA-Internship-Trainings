public class GCD {
    public static int gcd(int x, int y) {
        int min = Math.min(x, y), divisor = 0;
        for(int i = min; i > 0; i--) {
            if(x % i == 0 && y % i == 0) {
                divisor = i;
                break;
            }
        }

        return divisor;
    }

    public static void main(String[] args) {
        int x = 24, y = 32;

        System.out.println(gcd(x, y));
    }
}
