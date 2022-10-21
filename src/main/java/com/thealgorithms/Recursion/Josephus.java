public class Josephus {
    // "n" is the count of the numbers.
    // "k" is the fixed difference between the shots.    
    public static int remaining(int n, int k){
    // The last person remaining is our winner.      
        if(n==1){
            return 0;
        }
    // This is the recursive part.
        return (remaining(n-1,k)+k)%n;
    }
    // Let's understand this with the help of an example:-
    // Consider n = 5 and k = 3.
    // The recursive calls will keep taking place till "n" != 1, once "n" = 1 the call with parameters n = 1 will return 0.
    // Now call with parameter n = 2 will return (0+3) % 2 = 1. 
    // Now call with parameter n = 3 will return (1+3) % 3 = 1. 
    // Now call with parameter n = 4 will return (1+3) % 4 = 0. 
    // Now call with parameter n = 5 will return (0+3) % 5 = 3 (which is our desired answer). 
}
