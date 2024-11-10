public class Count_Vowels_in_a_String {
    public static void main(String[] args) {
        String str = "Hello, Mama Mia!!!\n";
        System.out.printf("Count of Vowels: %d", countVowels(str));
    }

    public static int countVowels(String str) {
        int count = 0;
        for(short i = 0; i < str.length(); i++) {
            if(str.charAt(i) == 'a' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'e' || str.charAt(i) == 'i')
                count++;
        }

        return count;
    }
}
