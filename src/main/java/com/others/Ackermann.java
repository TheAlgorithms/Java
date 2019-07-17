package src.main.java.com.others;


public class Ackermann {


  /**
   * Ackermann function - simplest and earliest-discovered examples of a total computable function
   * that is not primitive recursive.
   * 
   * Defined only for NONNEGATIVE integers !!!
   * 
   * Time complexity is super-exponential. O(n(^))
   * Any input m higher tahn (3,3) will result in StackOverflow
   * @param m
   * @param n
   * @return
   *
   *
   */
  public long Ack(long m, long n) {

    if (m == 0)
      return n + 1;

    if (n == 0)
      return Ack(m - 1, 1);

    return Ack(m - 1, Ack(m, n - 1));
  }

}


