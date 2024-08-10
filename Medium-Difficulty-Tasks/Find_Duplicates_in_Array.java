public class Find_Duplicates_in_Array {
    public static boolean exists(char[] arr, int size, char s) {
        boolean flag = false;
        for(int i = 0; i < size; i++) {
            if(arr[i] == s) {
                flag = true;
                break;
            }
        }

        return flag;
    }
    public static void findDuplicates(char[] arr, int size) {
        char[] duplicates = new char[size];
        int index = 0;

        for(int i = 0; i < size - 1; i++) {
            for(int j = i + 1; j < size; j++) {
                if(arr[i] == arr[j] && !exists(duplicates, size, arr[i]))  {
                    duplicates[index] = arr[i];
                    index++;
                }
            }
        }

        for(int i = 0; i < index; i++)
            System.out.print(duplicates[i] + " ");
    }

    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c', 'd', 'a', 'o', 'e', 'b', 'g', 'h', 'a'};

        findDuplicates(arr, 11);
    }
}
