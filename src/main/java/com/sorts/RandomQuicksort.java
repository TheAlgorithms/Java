import java.util.*; 

class RandomQuicksort 
{	 
    static void randomQuick(int arr[],int low,int high){ 
	Random random= new Random(); 
	int pivot = random.nextInt(high-low)+low; 
	int temp1=arr[pivot]; 
	arr[pivot]=arr[high]; 
	arr[high]=temp1; 
    } 
    static int partition(int arr[], int low, int high){ 
	randomQuick(arr,low,high);
	int pivot = arr[high]; 
	int index = (low-1); 
	for (int j = low; j < high; j++){  
	    if (arr[j] < pivot){ 
		index++;  
		int temp = arr[index]; 
		arr[index] = arr[j]; 
		arr[j] = temp; 
	    } 
        }  
	int temp = arr[index+1]; 
	arr[index+1] = arr[high]; 
	arr[high] = temp; 
	return index+1; 
	} 
    static void RQsort(int arr[], int low, int high){ 
	if (low < high){ 
	    int pi = partition(arr, low, high); 
	    RQsort(arr, low, pi-1); 
            RQsort(arr, pi+1, high); 
	} 
    } 
    public static void main(String args[]){ 
	int arr[] = {10, 7, 8, 9, 1, 5}; 
	int n = arr.length; 
        System.out.println("Input array - "); 
        for (int i = 0; i < n; ++i){
             System.out.print(arr[i]+" ");
        } 
	RQsort(arr, 0, n-1); 
	System.out.println("Sorted array - "); 
	for (int i = 0; i < n; ++i){
	     System.out.print(arr[i]+" ");  
	}
	} 
} 