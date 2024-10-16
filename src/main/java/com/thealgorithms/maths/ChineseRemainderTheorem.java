//this program is to implement Chinese Remainder Theorem
public class ChineseRemainderTheorem
{

    //function to find the greatest common divisor (GCD) of two numbers using the Extended Euclidean Algorithm
    private static long gcd(long a, long b)
	{
        if(b == 0)
            return a; //base case: if b is 0, a is the GCD

        return gcd(b, a%b);//calling recursively
    }

    //function to find the modular inverse of 'a' under modulo 'm' using the Extended Euclidean Algorithm
    private static long extendedGCD(long a, long b)
	{
        long originalB = b; //keeping original 'b' for later use
        long x1 = 1, x2 = 0; //x1 and x2 are coefficients for 'a'
        long y1 = 0, y2 = 1; //y1 and y2 are coefficients for 'b'

        while(b != 0)
		{
            long q = a/b; //quotient
            long temp = b; //temporary variable to hold value of 'b'
            b = a % b; //remainder
            a = temp; //updating 'a' with previous value of 'b'

            //updating coefficients x1, x2, y1, y2
            temp = x2;
            x2 = x1 - q*x2; //updating x2 with the quotient
            x1 = temp;

            temp = y2;
            y2 = y1 - q*y2; //updating y2 with the quotient
            y1 = temp;
        }
        //ensuring the result is positive
        return (x1 < 0)? (x1+originalB) : x1;
    }

    //function to implement the Chinese Remainder Theorem
    public static long chineseRemainder(int[] n, int[] a)
	{
        long N = 1; //variable to hold the product of all moduli
        for(int ni : n)
            N *= ni; //calculating the product of all moduli

        long result = 0; //variable to accumulate the final result
        for (int i = 0; i < n.length; i++)
		{
            long Ni = N/n[i]; //calculating Ni (the product of all moduli except n[i])
            long mi = extendedGCD(Ni, n[i]); //finding the modular inverse of Ni modulo n[i]
            //updating the result
            result += a[i]*Ni*mi; 
        }

        return result%N; //returning final answer
    }

    public static void main(String[] args)
	{
        //example test case
        int[] n = {3, 5, 7}; //moduli
        int[] a = {2, 3, 2}; //remainders

        //calculating the solution using the Chinese Remainder Theorem
        long solution = chineseRemainder(n, a);
        //output
        System.out.println("The solution is: " + solution); //expected output: 23
    }
}
