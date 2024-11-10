public class Find_Peak_Element {
    public static void peakEmenets(int[] arr) {
        int size = arr.length, index = -1;
        int[] peaks = new int[size / 2];

        for(int i = 1; i < size - 1; i++) {
            if(arr[i] >= arr[i - 1] && arr[i] >= arr[i + 1]) {
                index++;
                peaks[index] = arr[i];
            }
        }

        print(peaks);
    }

    public static void print(int[] arr) {
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

    public static void main(String[] args) {
        int[] arr = {0, 3, 2, 1, 6, 4, 8, 7};

        peakEmenets(arr);
    }
}
