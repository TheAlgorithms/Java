package com.interview.string;

import java.util.*;

/**
 * https://leetcode.com/problems/anagrams/
 */
public class GroupAnagramsTogether {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<List<String>>();
        
        int listIndex = 0;
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> anagramGroup = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (anagramGroup.containsKey(sorted)) {
                int index = anagramGroup.get(sorted);
                List<String> listResult = result.get(index);    
                listResult.add(str);
            } else {
                List<String> resultList = new ArrayList<>();
                resultList.add(str);
                result.add(listIndex, resultList);
                anagramGroup.put(sorted, listIndex);
                listIndex++;
            }
        }
        return result;
    }
}
