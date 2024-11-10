public class Sum_of_Squares {
    public static int sum(int N) {
        int sum = 0;
        for(int i = 1; i <= N; i++)
            sum = sum + (i * i);

        return sum;
    }

    public static void main(String[] args) {
        int N = 3;
        System.out.println(sum(N));
    }
}
