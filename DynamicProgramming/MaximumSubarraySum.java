package DynamicProgramming;
import java.util.*;
public class MaximumSubarraySum {
	public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0)
            {
                int n;
                n = sc.nextInt();
                ArrayList<Integer> arr = new ArrayList<Integer>();
                for(int i = 0;i<n;i++)
                    {
                        int p = sc.nextInt();
                        arr.add(p);
                    }
                    
                Sol obj = new Sol();  
                System.out.println(obj.maxSubArray(arr));  
                
            }
    }
}
class Sol
{
    
    public static int maxSubArray(ArrayList<Integer> a)
    {
       int maxsum=a.get(0);
       int currmax=a.get(0);
       for(int i=1;i<a.size();i++){
           currmax=Math.max(a.get(i),currmax+a.get(i));
           maxsum=Math.max(maxsum,currmax);
       }
       return maxsum;
    }
}
