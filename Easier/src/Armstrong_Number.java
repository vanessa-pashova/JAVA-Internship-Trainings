public static int countOfDigits(int x) {
    int counter = 0;

    while(x > 0) {
        x /= 10;
        counter++;
    }

    return counter;
}

public static boolean checkArmstrongNum(int num) {
    int pow = countOfDigits(num), sum = 0, temp = num;
    while(temp > 0) {
        int current = temp % 10, addend = current;

        for(short i = 1; i < pow; i++)
            addend *= current;

        sum += addend;
        temp /= 10;
    }

    return sum == num;
}

public static void main(String[] args) {
    int num = 370, num2 = 1634;

    System.out.println(checkArmstrongNum(num) ? "true" : "false");
    System.out.println(checkArmstrongNum(num2) ? "true" : "false");
}