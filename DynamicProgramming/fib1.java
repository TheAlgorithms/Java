import java.io.*;
import java.util.*;
public class fib1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter n");
        String n = sc.nextLine();
        String result = fib(n);
        System.out.println(result);

        //String a = subtract(n,"1");
        //System.out.println(a);
    }

    public static String fib(String s)
    {
        //if(s.charAt(0)-'0'<=1) {
          //  return s;
        //}
        if(s.equals("0") || s.equals("1")){
            //et = System.nanoTime();
            return s;
        }
        else{
        String s1=subtract(s,"1");
        String s2=subtract(s,"2");
        String s3 = fib(s1);
        //System.out.println(s3);
        String s4 = fib(s2);

                return add(s3,s4);}
    }

    public static String add(String a , String b)
    {
        int n=0;
        int l1 = a.length();
        int l2 = b.length();
        if(l1>l2)
        {
            for(int i=0;i<l1-l2;i++)
            {
                b = '0' + b;
            }
            n=l1;
        }
        else
        {
            for(int i=0;i<l2-l1;i++)
            {
                a = '0' + a;
            }
            n=l2;
        }
        int sum=0,carry=0;
        String s = "";
        for(int i=n-1;i>=0;i--)
        {
            sum = (a.charAt(i)-'0') + (b.charAt(i)-'0')+carry;
            carry = sum/10;
            sum = sum%10;
            s = s + (char)(sum +'0');
        }
        if(carry >0)
        {
            s += (char)(carry + '0');
        }
        s = new StringBuilder(s).reverse().toString();
        return s;
    }

 /*   public static String subtract(String a , String b)
    {
        int n =0;
        int l1 = a.length();
        int l2 = b.length();
        if(l1>l2)
        {
            for(int i=0;i<l1-l2;i++)
            {
                b = '0' + b;
            }
            n=l1;
        }
        else
        {
            for(int i=0;i<l2-l1;i++)
            {
                a = '0' + a;
            }
            n=l2;
        }
        int sub=0 , carry=0;
        String subs = "";
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();
        for(int i =0 ; i<n;i++)
        {
            sub = (a.charAt(i)-'0') - (b.charAt(i)-'0')-carry;
            if (sub < 0)
            {
                sub = sub + 10;
                carry = 1;
            }
            else
                carry = 0;

            subs += (char)(sub + '0');
        }
        return new StringBuilder(subs).reverse().toString();
    }*/

    public static String subtract(String a, String b){
        if(a.equals(b))
            return "0";
        int al = a.length();
        int bl = b.length();
        if(al != bl){
            if(al>bl){
                for(int i = 0; i < (al - bl); i++){
                    b = "0" + b;
                }
            }
            else if(bl>al){
                for(int i = 0; i < (bl - al); i++){
                    a = "0" + a;
                }
            }
        }
        al = a.length();
        bl = b.length();
        int x, y, carry = 0, difference = 0;
        String res = "";
        for(int i = (al - 1); i >= 0; i--){
            x = Character.getNumericValue(a.charAt(i));
            y = Character.getNumericValue(b.charAt(i));
            difference = x - y - carry;
            carry = 0;
            if(difference < 0){
                carry = 1;
                difference = difference + 10;
            }
            res = String.valueOf(difference) + res;
        }
        while(res.startsWith("0"))
            res = res.substring(1);
        return res;
    }
}
