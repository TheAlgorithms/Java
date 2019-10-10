// program to check if the entered number is prime 
import java.io.*; 
  
class GFG { 
      
    static boolean isPrime(int n) 
    { 
        // Corner cases 
        if (n <= 1) return false; 
        if (n <= 3) return true; 
      
        // This is checked so that we can skip middle five numbers in below loop 
        if (n % 2 == 0 || n % 3 == 0) 
         return false; 
      
        for (int i = 5; i * i <= n; i = i + 6) 
            if (n % i == 0 || n % (i + 2) == 0) 
             return false; 
      
        return true; 
    } 
  
 
    public static void main(String args[]) 
    { 
        if(isPrime(3)) 
            System.out.println(" true");    
        else
            System.out.println(" false"); 
        if(isPrime(20)) 
            System.out.println(" true"); 
        else
            System.out.println(" false"); 
          
    } 
} 
//Output
//true
//false
  
