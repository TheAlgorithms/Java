import java.math.BigInteger;
import java.util.Scanner;

public class Karatsuba
{
    static String[] divide(String a)
    {
        a.trim();
        int l = a.length();
        String r[] = new String[2];
        if(l>1) {
            String al = a.substring(0, l / 2);
            String ar = a.substring(l / 2, l);
          //  System.out.println(al + "   " + ar);


            r[0] = al;
            r[1] = ar;

                }
        else
        {
            r[0] = "0";
            r[1] = a;

        }
        //System.out.println(r[0] + "   " + r[1]);

        return r;
    }

    static int getLength(long num)
    {
        int ctr = 0;
        while (num != 0)
        {
            ctr++;
            num /= 10;
        }
        return ctr;
    }

    static BigInteger multiply(BigInteger a,BigInteger b)
    {
        int N = Math.max(a.toString().length(),b.toString().length());
        if(N==1)
        return a.multiply(b);

        int P = N/2 + N%2;

        String[] A = divide(a.toString());
        String[] B = divide(b.toString());

        BigInteger al = new BigInteger(A[0]);
        BigInteger ar = new BigInteger(A[1]);
        BigInteger bl = new BigInteger(B[0]);
        BigInteger br = new BigInteger(B[1]);


        BigInteger p1 = multiply(al,bl);
        BigInteger p3 = multiply(ar,br);
        BigInteger p2 = multiply(al.add(ar),bl.add(br)).subtract(p1.add(p3)) ;

        p1 = pow10(p1,2*P);
        p2 = pow10(p2,P);

        BigInteger p = (p1.add(p2)).add(p3);


        return p;




    }
    private static BigInteger pow10(BigInteger a,int p)
    {
        StringBuilder s =new StringBuilder(a.toString());
        for (int i=0;i<p;i++)
            s.append("0");



        return new BigInteger(s.toString());
    }


    public static void main(String[] args)
    {
        System.out.println("Enter two numbers :");
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine();
        String b = sc.nextLine();

        System.out.println(multiply(new BigInteger(a),new BigInteger(b)));
    }
}
