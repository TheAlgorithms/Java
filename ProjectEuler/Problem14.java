package ProjectEuler;
/**
 * The following iterative sequence is defined for the set of positive 
 * integers:
 *
 * <p>n → n/2 (n is even)
 * <p>n → 3n + 1 (n is odd)
 *
 * <p>Using the rule above and starting with 13, we generate the following 
 * sequence:
 * 
 * <p>13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 *
 * <p>It can be seen that this sequence (starting at 13 and finishing at 1)
 * contains 10 terms. Although it has not been proved yet (Collatz Problem), 
 * it is thought that all starting numbers finish at 1.
 *
 * <p>Which starting number, under one million, produces the longest chain?
 * 
 * <p>NOTE: Once the chain starts the terms are allowed to go above one
 * million.
 */
public class Problem14 {
  public static void main(String[] args) {
    assert solution1(1000000) == 837799;
  }

  static int getSequenceLength(long num) {
    int length = 1;

    while (num > 1) {
      if (num % 2 == 0) {
        num = num / 2;
      } else {
        num = 3 * num + 1;
      }
      length++;
    }

    return length;
  }

  public static int solution1(int maxNumber) {
    int maxLength = 0;
    int num = 0;
    int len;
    
    for (int i = 1; i <= maxNumber; i++) {
      len = getSequenceLength(i);
      if (len > maxLength) {
        maxLength = len;
        num = i;
      }
    }

    return num;
  }
}
