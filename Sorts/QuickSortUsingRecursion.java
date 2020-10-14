import java.util.*;
class function{
	 private static int partition(int input[], int si, int ei){
        int min=input[si];
        int count=0;
        for(int i=si+1;i<=ei;i++){
            if(input[i]<=min)
                count++;
        }
        //System.out.println(count);
        int pivot=si+count;
        int temp=input[si];
        input[si]=input[pivot];
        input[pivot]=temp;
        // for(int i=si;i<=ei;i++){
        //     System.out.print(input[i]+" ");
        // }
        int i=si;
        int j=ei;
        while(i<pivot && j>pivot){
            if(input[i]<=input[pivot])
        			i++;
        		else if(input[j]>input[pivot])
        			j--;
        		else{
        			int temp1=input[i];
        			input[i]=input[j];
        			input[j]=temp1;
        			i++;
        			j--;
                }
        }
        return pivot;
        
//          int pivot = input[ei];  
//         int count = si-1;
//         for (int j=si; j<ei; j++){
            
//             if (input[j] <= pivot){ 
//                 count++; 
//                 int temp = input[count]; 
//                 input[count] = input[j]; 
//                 input[j] = temp; 
//             } 
//         } 
  

//         int temp = input[count+1]; 
//         input[count+1] = input[ei]; 
//         input[ei] = temp; 
        
//         return count+1;
    }
    public static void quickSort(int input[] , int si, int ei){
        if(si>=ei)
            return;
        int pivot=partition(input,si,ei);
        quickSort(input,si,pivot-1);
        quickSort(input,pivot+1,ei);
        
    }
}

class QuickSortUsingRecursion{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		function f=new  function();
		System.out.println("Enter the size of the Array");
		int size=sc.nextInt();
		int arr[]=new int[size];
		System.out.println("Enter the elements");
		for(int i=0;i<size;i++){
			arr[i]=sc.nextInt();
		}
		System.out.println("***********Entered Array***********");
		for(int i=0;i<size;i++)
			System.out.print(arr[i]+" ");
		f.quickSort(arr,0,arr.length-1);
		System.out.println("\n*************Sorted Array**********");
		for(int i=0;i<size;i++){
			System.out.print(arr[i]+" ");
		}
	}
}
