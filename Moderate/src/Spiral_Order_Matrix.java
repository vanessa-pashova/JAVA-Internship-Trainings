public class Spiral_Order_Matrix {
    public static void printArr(int[][] arr, int r, int c) {
        int left = 0, top = 0, right = r, bottom = c;

        while(top <= bottom && left <= right) {
            for(int i = left; i < right; i++)
                System.out.print(arr[top][i] + " ");

            top++;

            for(int i = top; i < bottom; i++)
                System.out.print(arr[i][right - 1] + " ");

            right--;

            for(int i = right - 1; i >= 0; i--)
                System.out.print(arr[bottom - 1][i] + " ");

            bottom--;

            for(int i = bottom - 1; i >= top; i--)
                System.out.print(arr[i][left] + " ");

            left++;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        printArr(matrix, 3, 3);
    }
}
