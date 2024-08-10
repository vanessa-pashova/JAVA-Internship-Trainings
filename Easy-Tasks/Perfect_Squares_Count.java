public class Perfect_Squares_Count {

    public static boolean square(int x) {
        boolean flag = false;
        for(int i = 2; i < x / 2; i++) {
            if(i * i == x) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public static int count(int start, int end) {
        int count = 0;
        for(int i = start; i <= end; i++) {
            if(square(i))
                count++;
        }

        return count;
    }


    public static void main(String[] args) {
        int start = 10, end = 40;

        System.out.println(count(start, end));
    }
}
