import java.util.HashMap;

public class Longest_Substring_Without_Repeating_Characters {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Хеш карта за запазване на последната позиция на всеки символ
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Ако символът вече е в хеш картата, местим левия указател
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // Актуализираме последната позиция на текущия символ
            map.put(currentChar, right);

            // Изчисляваме текущата дължина на подниза и я сравняваме с максималната
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String test1 = "abcabcbb";
        String test2 = "bbbbb";
        String test3 = "pwwkew";
        String test4 = "";

        System.out.println("Length of longest substring without repeating characters:");
        System.out.println("Test 1: " + lengthOfLongestSubstring(test1)); // Output: 3
        System.out.println("Test 2: " + lengthOfLongestSubstring(test2)); // Output: 1
        System.out.println("Test 3: " + lengthOfLongestSubstring(test3)); // Output: 3
        System.out.println("Test 4: " + lengthOfLongestSubstring(test4)); // Output: 0
    }
}

