package Misc;

import java.util.*;

public class largestRange {

  // Finds the length of longest occurring consecutive numbers range in an array
  public static int longestRange(int[] nums) {
    int longestRange = 0;
    HashMap<Integer, Boolean> num = new HashMap<>();

    /**
     * Stores a mapping of a number to whether the current number is part of a particular
     * consecutive range or not.
     */
    for (int x : nums) num.put(x, true);
    for (int x : nums) {
      if (!num.get(x)) continue;
      num.replace(x, false);
      int currentRange = 1;
      int left = x - 1;
      int right = x + 1;
      while (num.containsKey(left)) { // Search leftwards for consecutive range
        num.replace(left, false);
        currentRange += 1;
        left--;
      }
      while (num.containsKey(right)) { // Search rightwards for consecutive range
        num.replace(right, false);
        currentRange += 1;
        right++;
      }
      if (currentRange > longestRange)
        longestRange = currentRange; // Store longest range at every interation
    }
    return longestRange;
  }

  public static void main(String[] args) {
    // Testcases
    assert longestRange(new int[] {1, 2, 3, 4, -1, 11, 10}) == 4;
    // The longest consecutive number range is of length 4 i.e. {1, 2, 3, 4}
    assert longestRange(new int[] {-1, 1, 3, 5, 7}) == 1;
    // The longest consecutive number range is of length 1 i.e. any of the element alone
    assert longestRange(new int[] {0, 1, 2, 3, 4, 7, 6, 5}) == 8;
    // The longest consecutive number range is of length 8 i.e. {0, 1, 2, 3, 4, 5, 6, 7}
  }
}
