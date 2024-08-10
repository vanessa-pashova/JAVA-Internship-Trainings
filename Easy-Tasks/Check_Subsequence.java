public class Check_Subsequence {
    public static void main(String[] args) {
        String str = "Baba Spuza obicha da kopa v gradinata";
        String substr = "Baba Spuza";
        String substr2 = "vuv gradinata";

        System.out.println(checkSubstring(str, substr) ? "True" : "False");
        System.out.println(checkSubstring(str, substr2) ? "True" : "False");
    }

    public static Boolean checkSubstring(String str, String sub) {
        int sizeStr = str.length(), sizeSub = sub.length();
        int j = 0; // Counter for the substring

        for (int i = 0; i < sizeStr; i++) {
            if (j < sizeSub && str.charAt(i) == sub.charAt(j))
                j++;

            if (j == sizeSub)
                return true;
        }

        return false;
    }
}
