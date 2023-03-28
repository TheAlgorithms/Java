import java.util.ArrayList;
import java.util.Arrays;

public class SieveOfEratosthenes {

    /**
     * @param n The number till which we have to check for prime Prints all the
     * prime numbers till n. Should be more than 1.
     * @return ArrayList of all prime numbers between 0 to n
     */
    public static ArrayList<Integer> findPrimesTill(int n) {
        // Create boolean array where index is number and value is flag - is that number a prime or not.
        boolean[] numbers = new boolean[n + 1];

        // Start with assumption that all odd numbers are primes.
        Arrays.fill(numbers, true);
        numbers[0] = numbers[1] = false;
        numbers[2] = true;

        double cap = Math.sqrt(n);
        // Main algorithm: mark all numbers which are multiples of some other primes as not prime
        for (int i = 3; i <= cap; i += 2) {
            if (numbers[i]) {
                for (int j = i * i; j <= n; j += 2 * i) {
                    numbers[j] = false;
                }
            }
        }


        ArrayList<Integer> primes = new ArrayList<>();
        primes.add(2);
        for (int i = 3; i <= n; i += 2) {
            if (numbers[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println("Searching for all primes from zero to " + n);
        ArrayList<Integer> primes = findPrimesTill(n);
        System.out.println("Found: " + primes);
    }
}
