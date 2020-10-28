import java.util.*;

class solution {

    private static long binomial_Coefficient(int n, int k)
    {
        if (k>n-k)
            k=n-k;

        long result = 1;
        for (int i=1, m=n; i<=k; i++, m--)
            result = result*m/i;
        return result;
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the first number(n): ");
        int n = scan.nextInt();
        System.out.print("Input the second number(k): ");
        int k = scan.nextInt();
        if (n>0 && k>0)
        {
            System.out.println("Binomial Coefficient of the said numbers " + binomial_Coefficient(n, k));
        }
    }
}