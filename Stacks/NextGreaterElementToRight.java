import java.io.*;
import java.util.*;

public class Main {
  public static void display(int[] a) {
    StringBuilder sb = new StringBuilder();

    for (int val : a) {
      sb.append(val + "\n");
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    Scanner take = new Scanner(System.in);

    int n = take.nextInt(); // for size of array
    int[] a = new int[n];
    for (int i = 0; i < n; i++) { // enter elements of array
      a[i] = take.nextInt();
    }

    int[] nge = solve(a);
    display(nge);
  }

  public static int[] solve(int[] a) {
    int n = a.length;
    int[] ans = new int[n];
    Stack<Integer> st = new Stack<>();
    ans[n - 1] = -1;
    st.push(a[n - 1]);
    for (int i = n - 2; i >= 0; i--) {
      while (st.size() > 0 && st.peek() <= a[i]) {
        st.pop();
      }
      if (st.size() == 0) {
        ans[i] = -1; // default value is -1 if no greater element if found on right
      } else if (st.peek() > a[i]) {
        ans[i] = st.peek();
      }
      st.push(a[i]);
    }
    return ans;
  }

}