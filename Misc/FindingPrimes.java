/**
 * The Sieve of Eratosthenes is an algorithm use to find prime numbers,
 * up to a given value.
 * Illustration: https://upload.wikimedia.org/wikipedia/commons/b/b9/Sieve_of_Eratosthenes_animation.gif
 * (This illustration is also in the github repository)
 * 
 * @author Unknown
 *
 */
public class FindingPrimes{
	/**
	 * The Main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String args[]){
		SOE(20); //Example: Finds all the primes up to 20
	}

	/**
	 * The method implementing the Sieve of Eratosthenes
	 * 
	 * @param n Number to perform SOE on
	 */
	public static void SOE(int n){
		boolean sieve[] = new boolean[n];

		int check = (int)Math.round(Math.sqrt(n)); //No need to check for multiples past the square root of n

		sieve[0] = false;
		sieve[1] = false;
		for(int i = 2; i < n; i++)
			sieve[i] = true; //Set every index to true except index 0 and 1

		for(int i = 2; i< check; i++){
			if(sieve[i]==true)					//If i is a prime
				for(int j = i+i; j < n; j+=i)   //Step through the array in increments of i(the multiples of the prime)
					sieve[j] = false;			//Set every multiple of i to false
		}
		for(int i = 0; i< n; i++){
			if(sieve[i]==true)
				System.out.print(i+" "); //In this example it will print 2 3 5 7 11 13 17 19
		}
	}
}