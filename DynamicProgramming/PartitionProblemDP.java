import java.util.*;
import java.util.Arrays;
 
public class PartitionProblemDP
{
    
    public static boolean subsetSum(int[] num, int n, int sum)
    {
        if (sum == 0) {
            return true;
        }
        
        if (n < 0 || sum < 0) {
            return false;
        }

        boolean include = subsetSum(num, n - 1, sum - num[n]);
        
        if (include) {
            return true;
        }
        
        boolean exclude = subsetSum(num, n - 1, sum);
        return exclude;
    }
 
    
    public static boolean partition(int[] nums)
    {
        int sum = Arrays.stream(nums).sum();
        return (sum & 1) == 0 && subsetSum(nums, nums.length - 1, sum/2);
    }
 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num[] = new int[n];
        for(int i=0; i<n; i++){
            num[i] = sc.nextInt();
        }
 
        if (partition(num)) {
            System.out.println("Set is partitioned");
        }
        else {
            System.out.println("Set is not partitioned");
        }
    }
}