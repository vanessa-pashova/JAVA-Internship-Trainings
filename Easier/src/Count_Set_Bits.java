public static int checkBIts(int x) {
    int counter = 0;

    while(x > 0) {
        if(x % 2 == 1)
            counter++;

        x /= 2;
    }

    return counter;
}


public static void main(String[] args) {
    int num = 13;
    System.out.printf("Bits in %d: %d", num, checkBIts(num));
}
