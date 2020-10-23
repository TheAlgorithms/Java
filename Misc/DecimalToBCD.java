import java.util.Scanner;
public class DecimalToBCD {

static String digitToBcd(int digit)
{
    String str="";
    for(int i=3;i>=0;i--)
        {
        int d=(int) Math.pow(2,i);
        if(digit/d!=0)
            {
                str+="1";
                digit-=d;
            }
        else
            str+="0";
        }
    return str;
}
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int decimal=sc.nextInt();
        int digit;
        String BCD="";
        while(decimal!=0)
            {
            digit=decimal%10;
            BCD=digitToBcd(digit)+" "+BCD;
            decimal/=10;
            }
        System.out.println("BCD is:"+BCD);
        
    }
}
