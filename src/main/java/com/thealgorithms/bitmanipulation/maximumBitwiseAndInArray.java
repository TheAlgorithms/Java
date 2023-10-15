package bits;
import java.util.*;
public class maximumBitwiseAndInArray {

	static int MaxValue(int[] arr) {
		int max=0;
		for(int i=0;i<arr.length;i++) {
			for(int j=i+1;j<arr.length;j++) {
				int temp=arr[i] & arr[j];
				if(max<temp) {
					max=temp;
				}
			}
		}
		return max;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("ENTER SIZE OF ARRAY:");
		int n=sc.nextInt();
		int a[]=new int[100];
		System.out.println("ENTER ELEMENTS OF ARRAY:");
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
		}
		System.out.println("MAXIMUM :"+MaxValue(a));

	}

}
