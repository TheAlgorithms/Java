package Maths;

/**
 * Sieve of Eratosthenes is a Math algorithm to find the prime numbers lesser than a given number.
 * The sieve of Eratosthenes is one of the most efficient ways to find all primes smaller than
 * n when n is smaller than 10 million.
 * For example, prime numbers less than 20 include {2,3,5,7,11,13,17,19}
 * This algorithm has O(n*log(log n)) time complexity.
 */
public class SieveOfEratosthenes {

    public static void main(String[] args) {
        findPrimes(20);
        findPrimes(50);
        findPrimes(153);
        findPrimes(1634);
        findPrimes(371);
        findPrimes(200);
    }

    /**
     * Prints all the prime numbers less than the given number
     *
     * @param number
     * @return void
     */
    private static void findPrimes(int number) {
        boolean[] visited = new boolean[number+1];
       for(int i=2;i<number+1;i++){
           if(!visited[i]){
               System.out.println(i);
               visited[i]=true;
               for(int j=i+1; j<number+1; j=j+i){
                   visited[j]=true;
               }
           }
       }
    }
}
