import java.util.Scanner;

public class leftmostNonRepeating {
    public static void main(String s[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string");
        String a=sc.next();
        check(a);
        sc.close();

    }
    public static void check(String a)
    {
        int n[]=new int[256];
        for(int i=0 ; i<a.length() ; i++)
        {
            n[a.charAt(i)]++;
        }
        for(int i=0 ; i<a.length() ; i++)
        {
            if(n[a.charAt(i)]==1)
            {System.out.println(i);
                break;}
        }
    }
}
