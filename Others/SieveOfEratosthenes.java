package Others;

/** @author Varun Upadhyay (https://github.com/varunu28) */
public class SieveOfEratosthenes {

  /**
   * This method implements the Sieve of Eratosthenes Algorithm
   *
   * @param n The number till which we have to check for prime Prints all the prime numbers till n
   */
  public static void findPrimesTillN(int n) {
    int[] arr = new int[n + 1];

    for (int i = 0; i <= n; i++) {
      arr[i] = 1;
    }

    arr[0] = arr[1] = 0;

    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (arr[i] == 1) {
        for (int j = 2; i * j <= n; j++) {
          arr[i * j] = 0;
        }
      }
    }

    for (int i = 0; i < n + 1; i++) {
      if (arr[i] == 1) {
        System.out.print(i + " ");
      }
    }

    System.out.println();
  }

  // Driver Program
  public static void main(String[] args) {
    int n = 100;

    // Prints 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97
    findPrimesTillN(n);
  }
}
