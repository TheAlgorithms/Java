//Using Kadane's Algorithm.
import java.util.*;//Import all Classes.

public class kadenes
{
    public static void main(String[] args) {
        Scanner s1=new Scanner(System.in);
        int n=s1.nextInt();
        int arr[]=new int[n];
        
        for (int i=0;i<n;i++)
        {
             arr[i]=s1.nextInt();
        }
        int sum=0;//Initialize variable.
        int maxsum=0; 
        for (int j=0;j<arr.length;j++)
        {
                   sum=sum+arr[j];
                   maxsum=Math.max(maxsum,sum);//Using math function.
                   if(sum<0){
                    sum=0;
                   }
        }
        System.out.println(maxsum);//Output.
    }
}
