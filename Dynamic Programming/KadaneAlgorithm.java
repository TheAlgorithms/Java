import java.util.*;
import java.io.*;
import java.lang.*;
class Sun{
static int maxSubArraySum(int a[], int size) 
    {
    int max_so_far = a[0]; 
    int curr_max = a[0]; 
  
    for (int i = 1; i < size; i++) 
    { 
           curr_max = Math.max(a[i], curr_max+a[i]); 
        max_so_far = Math.max(max_so_far, curr_max); 
    } 
    return max_so_far; 
    } 
	public static void main(String[] args) throws Exception{
		try{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] arr=new int[n];
		for(int i=0;i<n;i++){
			arr[i]=sc.nextInt();
		}
		int maxContSum=maxSubArraySum(arr,n);
		System.out.println(maxContSum);
		sc.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
