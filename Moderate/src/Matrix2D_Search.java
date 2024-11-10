public class Matrix2D_Search {
    public static boolean exists(int[][] arr, int r, int l, int value) {
        boolean flag = false;

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < l; j++) {
                if(arr[i][j] == value) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        int target = 0;
        System.out.println(exists(matrix, 5, 5, target) ? "true" : "false");

        target = 30;
        System.out.println(exists(matrix, 5, 5, target) ? "true" : "false");
    }
}
