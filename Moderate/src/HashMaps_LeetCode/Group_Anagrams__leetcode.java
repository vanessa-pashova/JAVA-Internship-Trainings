package HashMaps_LeetCode;

import java.util.*;

public class Group_Anagrams__leetcode {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Group_Anagrams__leetcode ga = new Group_Anagrams__leetcode();
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(ga.groupAnagrams(strs1));
        // Output: [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]

        String[] strs2 = {""};
        System.out.println(ga.groupAnagrams(strs2));
        // Output: [[""]]

        String[] strs3 = {"a"};
        System.out.println(ga.groupAnagrams(strs3));
        // Output: [["a"]]
    }
}
