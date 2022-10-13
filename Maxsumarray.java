//Using Kadane's Algorithm.
import java.util.*;//Import all the libraries.

public class kadenes
{
    public static void main(String[] args) {
        Scanner s1=new Scanner(System.in);
        int n=s1.nextInt();//Input for size of the array.
        int arr[]=new int[n];
        
        for (int i=0;i<n;i++)
        {
             arr[i]=s1.nextInt();//Store the element one by one according to the size of array.
        }
        int sum=0;//Initialize sum and maximum variable .
        int maxsum=0; 
        for (int j=0;j<arr.length;j++)
        {
                   sum=sum+arr[j];
                   maxsum=Math.max(maxsum,sum);//Using math function get the maximum value.
                   if(sum<0){
                    sum=0;
                   }
        }
        System.out.println(maxsum);//Output.
    }
}
