public class Check_Power_of_Three {
    public static boolean checkOfThirdPower(int x) {
        boolean flag = false;
        for(short i = 1; i < x; i++) {
            if(x == i * i * i) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        int num = 8, num2 = 27;
        System.out.println(checkOfThirdPower(num) ? "true" : "false");
        System.out.println(checkOfThirdPower(num2) ? "true" : "false");
    }
}
