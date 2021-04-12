package ProjectEuler;

public class Problem26 {

  public static void main(String[] args) {
    assert solution() == 983;
  }

  public static int solution() {
    int sequenceSize = 0;
    int d = 0;

    for (int i = 1000; i > 1; i--) {
      if (sequenceSize >= i) {
        break;
      }

      int[] remaindersArray = new int[i];
      int val = 1;
      int pos = 0;

      while (remaindersArray[val] == 0 && val != 0) {
        remaindersArray[val] = pos;
        val *= 10;
        val %= i;
        pos++;
      }

      if (pos - remaindersArray[val] > sequenceSize) {
        d = i;
        sequenceSize = pos - remaindersArray[val];
      }
    }
    return d;
  }
}
