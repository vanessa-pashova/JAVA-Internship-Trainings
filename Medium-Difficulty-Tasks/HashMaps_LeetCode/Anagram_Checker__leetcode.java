package HashMaps_LeetCode;

import java.util.HashMap;

public class Anagram_Checker__leetcode {
    public boolean isAnagram(String s, String t) {
        // Ако дължините са различни, те не могат да бъдат анаграми
        if (s.length() != t.length())
            return false;

        HashMap<Character, Integer> countMapS = new HashMap<>();
        HashMap<Character, Integer> countMapT = new HashMap<>();

        // Преброяване на символите в s
        for (char c : s.toCharArray())
            countMapS.put(c, countMapS.getOrDefault(c, 0) + 1);

        // Преброяване на символите в t
        for (char c : t.toCharArray())
            countMapT.put(c, countMapT.getOrDefault(c, 0) + 1);

        // Сравняване на двата хеш мапа
        return countMapS.equals(countMapT);
    }

    public static void main(String[] args) {
        Anagram_Checker__leetcode checker = new Anagram_Checker__leetcode();
        System.out.println(checker.isAnagram("anagram", "nagaram")); // Output: true
        System.out.println(checker.isAnagram("rat", "car")); // Output: false
    }
}
