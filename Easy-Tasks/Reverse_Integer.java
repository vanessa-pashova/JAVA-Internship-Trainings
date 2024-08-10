public static int reverseNum(int num) {
    int result = 0;

    while(num > 0) {
        result = (result * 10) + (num % 10);
        num /= 10;
    }

    return result;
}

public static long reverseNum(long num) {
    long result = 0;

    while(num > 0) {
        result = (result * 10) + (num % 10);
        num /= 10;
    }

    return result;
}

public static void main(String[] args) {
    int num1 = 12345;
    long num2 = 123456789L;

    System.out.println(reverseNum(num1));
    System.out.println(reverseNum(num2));
}