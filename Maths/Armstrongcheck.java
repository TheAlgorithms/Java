import java.io.*;
public class Armstrong
{
    public static void main(String args[])throws IOException
    {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number");
        int n=Integer.parseInt(br.readLine());
        int sum=0;
        int r=0;
        int s=n;
        while(s!=0)
        {
            r=s%10;
            sum=sum+(int)(Math.pow(r,3));
            s=s/10;
        }
        if(sum==n)
        System.out.println("It is an Armstrong number");
        else
        System.out.println("It is not an Armstrong number");
    }
}