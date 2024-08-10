package HashMaps_LeetCode;

import java.util.HashMap;

public class Pattern_Matching__leetcode {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        // Ако броят на символите в шаблона не е равен на броя на думите в низа, връщаме false
        if (pattern.length() != words.length)
            return false;

        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // Проверяваме дали символът вече има съответствие и дали съответствието е коректно
            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(word))
                    return false;
            }

            else
                charToWord.put(c, word);

            // Проверяваме дали думата вече има съответствие и дали съответствието е коректно
            if (wordToChar.containsKey(word)) {
                if (!wordToChar.get(word).equals(c))
                    return false;
            }

            else
                wordToChar.put(word, c);
        }

        return true;
    }

    public static void main(String[] args) {
        Pattern_Matching__leetcode pm = new Pattern_Matching__leetcode();
        System.out.println(pm.wordPattern("abba", "dog cat cat dog")); // Output: true
        System.out.println(pm.wordPattern("abba", "dog cat cat fish")); // Output: false
        System.out.println(pm.wordPattern("aaaa", "dog cat cat dog")); // Output: false
    }
}
