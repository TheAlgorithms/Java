package Maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/*
 * Find the mode of an array of numbers
 *
 * The mode of an array of numbers is the most frequently occurring number in the array,
 * or the most frequently occurring numbers if there are multiple numbers with the same frequency
 */
public class Mode {

  public static void main(String[] args) {

    /* Test array of integers */
    assert (mode(new int[] {})) == null;
    assert Arrays.equals(mode(new int[] {5}), new int[] {5});
    assert Arrays.equals(mode(new int[] {1, 2, 3, 4, 5}), new int[] {1, 2, 3, 4, 5});
    assert Arrays.equals(mode(new int[] {7, 9, 9, 4, 5, 6, 7, 7, 8}), new int[] {7});
    assert Arrays.equals(mode(new int[] {7, 9, 9, 4, 5, 6, 7, 7, 9}), new int[] {7, 9});
  }

  /*
   * Find the mode of an array of integers
   *
   * @param numbers array of integers
   * @return mode of the array
   */
  public static int[] mode(int[] numbers) {

    if (numbers.length == 0) return null;

    HashMap<Integer, Integer> count = new HashMap<>();

    for (int num : numbers) {
      if (count.containsKey(num)) {

        count.put(num, count.get(num) + 1);

      } else {

        count.put(num, 1);
      }
    }

    int max = Collections.max(count.values());
    ArrayList<Integer> modes = new ArrayList<>();

    for (int num : count.keySet()) {
      if (count.get(num) == max) {
        modes.add(num);
      }
    }
    return modes.stream().mapToInt(n -> n).toArray();
  }
}
