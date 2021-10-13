import java.util.Arrays;
import java.util.Stack;

/*
 * @author Manash Space Complexity : O(n) Time Complexity : O(n)
 */
public class NextGreaterElement<T extends Comparable<T>> {
  /*
   * Description : Given an Array a, for each index i, b[i] = j will represent the smallest index j
   * such that a[j] > a[i]
   * 
   * Algorithm : We use a monotonic stack and pop elements when we find an element greater than it,
   * and assign it's index.
   * 
   * @param a[] : The Input Array
   * 
   * @returns b[] : The described Array
   * 
   * @required : The elements of a must be comparable.
   */
  public int[] nextGreaterElement(T[] a) {
    int n = a.length;
    Stack<Integer> st = new Stack<Integer>();
    int[] b = new int[n];
    Arrays.fill(b, -1);
    for (int i = 0; i < n; ++i) {
      T currentElem = a[i];
      while (!st.empty() && currentElem.compareTo(a[st.peek()]) == 1) {
        int index = st.pop();
        b[index] = i;
      }
      st.push(i);
    }
    return b;
  }
}
