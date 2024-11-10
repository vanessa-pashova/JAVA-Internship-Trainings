public static boolean checkIfPerfect(int x) {
    int sum = 0;
    for(short i = 1; i < x; i++) {
        if(x % i == 0)
            sum += i;
    }

    return sum == x;
}

public static void main(String[] args) {
    int num = 28, num2 = 6, num3 = 4;
    System.out.println(checkIfPerfect(num) ? "true" : "false");
    System.out.println(checkIfPerfect(num2) ? "true" : "false");
    System.out.println(checkIfPerfect(num3) ? "true" : "false");
}