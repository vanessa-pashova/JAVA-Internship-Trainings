package HashMaps_LeetCode;

import java.util.HashMap;

public class Ransom_Note_leetcode {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> countMap = new HashMap<>();

        // Обхождаме magazine и увеличаваме броя на съответната буква в HashMap
        for (char c : magazine.toCharArray())
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);

        /* ! Методът getOrDefault е метод на класа HashMap в Java, който се използва за извличане на стойност, свързана с даден ключ.
        Ако ключът не съществува в хеш картата, методът връща стойност по подразбиране, която е предоставена като аргумент */

        // Обхождаме ransomNote и намаляваме броя на съответната буква в HashMap
        for (char c : ransomNote.toCharArray()) {
            if (!countMap.containsKey(c) || countMap.get(c) == 0)
                return false; // Ако липсва буква или честотата е 0, връщаме false

            countMap.put(c, countMap.get(c) - 1);
        }

        // Ако успешно обходим всички букви в ransomNote, връщаме true
        return true;
    }

    public static void main(String[] args) {
        Ransom_Note_leetcode rn = new Ransom_Note_leetcode();
        System.out.println(rn.canConstruct("a", "b")); // Output: false
        System.out.println(rn.canConstruct("aa", "ab")); // Output: false
        System.out.println(rn.canConstruct("aa", "aab")); // Output: true
    }
}
