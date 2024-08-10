package HashMaps_LeetCode;

import java.util.HashMap;

public class Isomorphic_Strings__leetcode {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Character> mapS = new HashMap<>();
        HashMap<Character, Character> mapT = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check if there is already a mapping for charS
            if (mapS.containsKey(charS)) {
                if (mapS.get(charS) != charT)
                    return false;
            }

            else
                mapS.put(charS, charT);

            // Check if there is already a mapping for charT
            if (mapT.containsKey(charT)) {
                if (mapT.get(charT) != charS)
                    return false;
            }

            else
                mapT.put(charT, charS);
        }

        return true;
    }

    public static void main(String[] args) {
        Isomorphic_Strings__leetcode isomorphicStrings = new Isomorphic_Strings__leetcode();
        System.out.println(isomorphicStrings.isIsomorphic("egg", "add")); // Output: true
        System.out.println(isomorphicStrings.isIsomorphic("foo", "bar")); // Output: false
        System.out.println(isomorphicStrings.isIsomorphic("paper", "title")); // Output: true
    }
}
