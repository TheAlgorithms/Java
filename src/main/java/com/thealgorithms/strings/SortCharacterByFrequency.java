// Reference: https://leetcode.com/problems/sort-characters-by-frequency/
import java.util.*;

// Problem: Sort Characters By Frequency
// Pattern: HashMap + Priority Queue (Heap)
// Difficulty: Medium

/*
 Approach:
 1. Count frequency of each character using a HashMap.
 2. Store characters in a max-heap (PriorityQueue) based on frequency.
 3. If frequencies are same, sort by character (lexicographically).
 4. Extract from heap and append characters 'frequency' times.

 Time Complexity: O(n log n)
 Space Complexity: O(n)
*/

class Solution {
    public String frequencySort(String s) {

        // Step 1: Count frequency of each character
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Increment frequency using getOrDefault
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Create max-heap based on frequency
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> {
            // Higher frequency comes first
            int diff = b.getValue() - a.getValue();

            // If frequency same, sort by character
            if (diff == 0) return a.getKey() - b.getKey();

            return diff;
        });

        // Add all entries to heap
        pq.addAll(map.entrySet());

        // Step 3: Build result string
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {

            Map.Entry<Character, Integer> entry = pq.poll();

            char key = entry.getKey(); // character
            int freq = entry.getValue(); // frequency

            // Append character 'freq' times
            for (int i = 0; i < freq; i++) {
                sb.append(key);
            }
        }

        // Step 4: Return final string
        return sb.toString();
    }
}
