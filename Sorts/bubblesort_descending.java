import java.util.*;
class bubblesort_descending{
	public static void main(String []args){
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the size of array:");
		int n=sc.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++){
			System.out.print("Enter the element at "+i+" position:");
			a[i]=sc.nextInt();
		}
		int temp=0;
		for(int l=n;l>=0;l++){
			for(int j=0;j<n-1;j++){
				if(a[j]<a[j+1]){
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
		System.out.print("sorted array is:");
		for(int k=0;k<n;k++){
			System.out.print(a[k]+" ");
		}
	}
}
