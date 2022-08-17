package com.thealgorithms.others;

/**
 * Sieve of Eratosthenes is an ancient algorithm for finding all prime numbers
 * up to any given limit. It does so by iteratively marking as composite (i.e.,
 * not prime) the multiples of each prime, starting with the first prime number,
 * 2. The multiples of a given prime are generated as a sequence of numbers
 * starting from that prime, with constant difference between them that is equal
 * to that prime. This is the sieve's key distinction from using trial division
 * to sequentially test each candidate number for divisibility by each prime.
 * Once all the multiples of each discovered prime have been marked as
 * composites, the remaining unmarked numbers are primes.
 * <p>
 * Poetry about Sieve of Eratosthenes:
 * <p>
 * <i>Sift the Two's and Sift the Three's:</i></p>
 * <p>
 * <i>The Sieve of Eratosthenes.</i></p>
 * <p>
 * <i>When the multiples sublime,</i></p>
 * <p>
 * <i>The numbers that remain are Prime.</i></p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">Wiki</a>
 */
public class SieveOfEratosthenes {

    /**
     * @param n The number till which we have to check for prime Prints all the
     * prime numbers till n. Should be more than 1.
     * @return array of all prime numbers between 0 to n
     */
    public static void findPrimesTill(int n, boolean[] notPrime) {
        
        //Initializing indexes 0 & 1 to be true as they are neither prime nor composite.
        notPrime[0] = true;
        notPrime[1] = true;

        for(int i = 2; i*i <= n; i++) {  

            if (!notPrime[i]) {  //As if the index is false means that the index value is a prime, so taking the negation of false i.e. !false = true

                for (int j = i*2; j <= n; j += i) {  //making the multiples of i to be true i.e. they aren't prime
                    notPrime[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println("Searching for all primes from zero to " + n);

        boolean[] notPrime = new boolean[n+1];  //java indexes starts from 0, so taking length to be n+1 to include the n'th index also.
        /*
        The boolean array notPrime will be initialized with default value false, so let's take that if the index is false then the
        index value is a prime number, else the index value is not prime.
        */
        findPrimesTill(n+1, notPrime);           
        
        for (int i = 2; i <= n; i++) {
            if (!notPrime[i]) {

                System.out.print(i + " ");
            }
        }
    }
}
