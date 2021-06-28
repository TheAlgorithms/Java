package Other;
/*.	Algorithm to find if a number is circular prime or not.
2.	Count the number of digits.
3.	Shift the first digit to last and the other digits to the left.
4.	Check if the new number is prime or not.
5.	Shift the digits one by one until the original number is generated.
6.	If all the numbers generated are prime display that the number is circular prime else display number is not circular prime.
7.	Stop algorithm.
*/
class CircularPrime
{   
    boolean isPrime(int m)
    { //to check if a number is prime or not
        int c=0;
        for(int i=1; i<m; i++)
        {
            if(m%i==0)
                c++;
        }
        if(c==1)
            return true;
        else 
            return false;
    }

    int countDigits(int n)
    {//to count number of digits in integer n
        int c=0;
        while(n>0)
        {
            n=n/10;
            c++;
        }
        return c;
    }

    void main(int num)
    {
        int n=num;
        int c=countDigits(num);
        int f=0; int t=0;
        int p=1;
        for(int i=1; i<c; i++)
        {
            p=p*10;
        }
        while(t!=num)
        {
            t=n%p;
            int j=n/p;
            t=t*10 +j;
            boolean r=isPrime(t);
            if(r==false)
                f++;
            n= t;
        }
        if (f==0)
            System.out.println(num +"IS CIRCULAR PRIME");
        else
            System.out.println(num+"IS NOT CIRCULAR PRIME ");
    }
}
