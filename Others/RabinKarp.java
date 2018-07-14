
import java.io.IOException;
import java.util.Random;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Implementation of Rabin Karp Algorithm for pattern matching.
 * Program accepts the text and the pattern and prints the position of the pattern int the text.
 * @author https://github.com/ReemaIsrani
 */
public class RabinKarp
{
    private String pattern; 
    private long patternHash;  
 /** Large prime **/         
    private long prime; 
    /** radix **/         
    private int radix;   
    /** radix^(len-1) % prime **/        
    private long rm;   	
    /** pattern length **/
    private int len;         
 
    public void RabinKarpAlgo(String text, String pattern) 
    {
        this.pattern = pattern;      
        radix = 256;
        len = pattern.length();
        prime = longRandomPrime();
        /** precompute radix^(len-1) % prime for use in removing leading digit **/
        rm = 1;
        for (int i = 1; i <= len-1; i++)
           rm = (radix * rm) % prime;
        patternHash = hash(pattern, len);
        int pos = search(text);
        if (pos == -1)
            System.out.println("\nPattern not Found\n");
        else
            System.out.println("Pattern found at position : "+ pos);
    } 
    /** Compute hash **/
    private long hash(String key, int len)
    { 
        long h = 0; 
        for (int j = 0; j < len; j++) 
            h = (radix * h + key.charAt(j)) % prime; 
        return h; 
    } 
    /** Funtion check **/
    private boolean check(String text, int i) 
    {
        for (int j = 0; j < len; j++) 
            if (pattern.charAt(j) != text.charAt(i + j)) 
                return false; 
        return true;
    }
    /** Funtion to check for exact match**/
    private int search(String text) 
    {
        int N = text.length(); 
        if (N < len) return N;
        long textHash = hash(text, len); 
        /** check for match at start **/
        if ((patternHash == textHash) && check(text, 0))
            return 0;
        /** check for hash match. if hash match then check for exact match**/
        for (int i = len; i < N; i++) 
        {
            // Remove leading digit, add trailing digit, check for match. 
            textHash = (textHash + prime - rm * text.charAt(i - len) % prime) % prime; 
            textHash = (textHash * radix + text.charAt(i)) % prime; 
            // match
            int offset = i - len + 1;
            if ((patternHash == textHash) && check(text, offset))
                return offset;
        }
        /** no match **/
        return -1;
    }
    /** generate a random 31 bit prime **/
    private static long longRandomPrime() 
    {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
    /** Main Function **/
    public static void main(String[] args) throws IOException
    {    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Rabin Karp Algorithm");
        System.out.println("Enter Text\n");
        String text = br.readLine();
        System.out.println("Enter Pattern\n");
        String pattern = br.readLine();
        RabinKarp r = new RabinKarp();
		r.RabinKarpAlgo(text, pattern);
    }
}