public class Count_Words_in_String {
    public static void main(String[] args) {
        String str = "Baba Spuza ima golemi... dini v gradinata\n";
        String str2 = "";

        System.out.printf("Word's count: %d\n", countWords(str));
        System.out.printf("Word's count: %d\n", countWords(str2));
    }

    public static int countWords(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }

        int size = str.length();
        int counter = 0;

        for (int i = 0; i < size; i++) {
            if (str.charAt(i) == ' ' && i != 0 && str.charAt(i - 1) != ' ') {
                counter++;
            }
        }

        return counter + 1; // Add one more to account for the last word
    }
}

//|| str.trim().isEmpty() removes tabulations, white-spaces, etc