import java.util.Scanner;
public class Max_Sum_Subarray{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt(); //window size
        int n = in.nextInt(); //limit of array
        int[] arr = new int[n];
            for(int i=0;i<n;i++){
            arr[i] = in.nextInt();
        }
        int j=0;
        int i=0;
        int sum=0;
        int maxSum = Integer.MIN_VALUE;
        while(j<arr.length){
            sum+=arr[j];
            if(j-i+1<k){
                j++;
            }
            else if(j-i+1==k){
                maxSum=Math.max(maxSum,sum);
                sum-=arr[i];
                i++;
                j++;
            }
        }
        System.out.println(maxSum);
    }
}