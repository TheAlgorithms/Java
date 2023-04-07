import java.util.HashSet;

public class IntersectionOfArray {

 // This method takes two integer arrays, 'a' and 'b', as input and finds the count of common elements.
 // It uses a HashSet to keep track of elements in array 'a' and uses it to compare with elements in array 'b'.
  public static int intersection(int[] a, int[] b) {
            HashSet<Integer> set = new HashSet<>();
            int count = 0;
            for (int i = 0; i < a.length; i++) {
                set.add(a[i]);
            }

            for (int j = 0; j < b.length; j++) {
                if (set.contains(b[j])) {
                    count++;
                    set.remove(b[j]);
                }
            }
            return count; // give count of common elements
        }
}
