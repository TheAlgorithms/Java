import java.util.*;

public class SieveOfEratosthenes {

    /**
     * Finds all prime numbers up to a given limit using the Sieve of Eratosthenes algorithm.
     * 
     * @param limit the upper limit for finding prime numbers
     * @return a list of prime numbers up to the limit
     */
    public static List<Integer> sieveOfEratosthenes(int limit) {
        // Create a boolean array "prime[0..limit]" and initialize all entries as true.
        boolean[] prime = new boolean[limit + 1];
        Arrays.fill(prime, true);

        for (int p = 2; p * p <= limit; p++) {
            // If prime[p] is not changed, then it is a prime.
            if (prime[p]) {
                // Update all multiples of p as non-prime
                for (int i = p * p; i <= limit; i += p) {
                    prime[i] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        // Add all prime numbers to the list
        for (int p = 2; p <= limit; p++) {
            if (prime[p]) {
                primes.add(p);
            }
        }
        return primes;
    }

    // Example usage
    public static void main(String[] args) {
        int limit = 30;
        List<Integer> primes = sieveOfEratosthenes(limit);
        System.out.println("Prime numbers smaller than or equal to " + limit + " are:");
        System.out.println(primes);
    }
}

// Here's the explanation of the concept: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
