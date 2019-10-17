 
import java.util.Vector; 
  
class Test 
{ 
      
    static int MAX  = 10000; 
       
    // array to store all prime less than and equal to 10^6 
    static Vector <Integer>  primes = new Vector<>(); 
      
    // utility function for sieve of sundaram 
    static void sieveSundaram() 
    { 
        // In general Sieve of Sundaram, produces primes smaller 
        // than (2*x + 2) for a number given number x. Since 
        // we want primes smaller than MAX, we reduce MAX to half 
        // This array is used to separate numbers of the form 
        // i+j+2ij from others where 1 <= i <= j 
        boolean marked[] = new boolean[MAX/2 + 100]; 
       
        // Main logic of Sundaram. Mark all numbers which 
        // do not generate prime number by doing 2*i+1 
        for (int i=1; i<=(Math.sqrt(MAX)-1)/2; i++) 
            for (int j=(i*(i+1))<<1; j<=MAX/2; j=j+2*i+1) 
                marked[j] = true; 
       
        // Since 2 is a prime number 
        primes.addElement(2); 
       
        // Print other primes. Remaining primes are of the 
        // form 2*i + 1 such that marked[i] is false. 
        for (int i=1; i<=MAX/2; i++) 
            if (marked[i] == false) 
                primes.addElement(2*i + 1); 
    } 
       
    // Returns true if n is a Smith number, else false. 
    static boolean isSmith(int n) 
    { 
        int original_no = n; 
       
        // Find sum the digits of prime factors of n 
        int pDigitSum = 0; 
        for (int i = 0; primes.get(i) <= n/2; i++) 
        { 
            while (n % primes.get(i) == 0) 
            { 
                // If primes[i] is a prime factor, 
                // add its digits to pDigitSum. 
                int p = primes.get(i); 
                n = n/p; 
                while (p > 0) 
                { 
                    pDigitSum += (p % 10); 
                    p = p/10; 
                } 
            } 
        } 
       
        // If n!=1 then one prime factor still to be 
        // summed up; 
        if (n != 1 && n != original_no) 
        { 
            while (n > 0) 
            { 
                pDigitSum = pDigitSum + n%10; 
                n = n/10; 
            } 
        } 
       
        // All prime factors digits summed up 
        // Now sum the original number digits 
        int sumDigits = 0; 
        while (original_no > 0) 
        { 
            sumDigits = sumDigits + original_no % 10; 
            original_no = original_no/10; 
        } 
       
        // If sum of digits in prime factors and sum 
        // of digits in original number are same, then 
        // return true. Else return false. 
        return (pDigitSum == sumDigits); 
    } 
      
    // Driver method 
    public static void main(String[] args)  
    { 
        // Finding all prime numbers before limit. These 
        // numbers are used to find prime factors. 
        sieveSundaram(); 
           
        System.out.println("Printing first few Smith Numbers" + 
                           " using isSmith()"); 
          
        for (int i=1; i<500; i++) 
           if (isSmith(i)) 
              System.out.print(i + " "); 
    } 
} 
