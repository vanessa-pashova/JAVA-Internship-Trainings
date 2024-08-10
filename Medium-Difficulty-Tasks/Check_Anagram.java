import java.util.Arrays;

public class Check_Anagram {
    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSort(char[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(char[] arr, int low, int high) {
        char pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        return i + 1;
    }

    public static boolean checkAnagram(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;


        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        quickSort(charArray1, 0, charArray1.length - 1);
        quickSort(charArray2, 0, charArray2.length - 1);

        return Arrays.equals(charArray1, charArray2);
    }

    public static void main(String[] args) {
        String anagram1 = "cinema";
        String anagram2 = "iceman";
        String anagram3 = "baba";

        System.out.println(checkAnagram(anagram1, anagram2) ? "true" : "false");
        System.out.println(checkAnagram(anagram1, anagram3) ? "true" : "false");
    }
}
