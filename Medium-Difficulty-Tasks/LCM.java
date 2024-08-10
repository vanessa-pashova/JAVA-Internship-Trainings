public class LCM {
    public static int lcm(int x, int y) {
        int min = Math.min(x, y), divisor = 0;

        if(x == 1 || y == 1)
            return divisor = 1;

        for(int i = 2; i < min; i++) {
            if(x % i == 0 && y % i == 0) {
                divisor = i;
                break;
            }
        }

        return divisor;
    }

    public static void main(String[] args) {
        int num1 = 300, num2 = 35;
        System.out.println(lcm(num1, num2));
    }
}
