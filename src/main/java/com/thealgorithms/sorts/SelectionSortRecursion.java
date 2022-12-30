import java.util.Arrays;

public class SelectionSortRecursion {
    //start:first indimport java.util.Arrays;
//time:O(n*n)
//space:O(n)
public class SelectionSortRecurrsion {
    //start:first index of array in first call
    //end:last index of array in first call
    //maxIndex:the index of largest element of array
    static void SelectionSort(int[] arr,int start,int end,int maxIndex){
        if(end==0){
            return;
        }
        if(start<end){
            if(arr[maxIndex]<arr[start])
                maxIndex=start;
            SelectionSort(arr,start+1,end,maxIndex);
        }
        else{
            swap(arr,end-1,maxIndex);
            
            SelectionSort(arr,0,end-1,0);//the max element is sorted and we are moving to sort the next max element
        }
    }
    static void swap(int[] arr,int ind1,int ind2){
        int temp=arr[ind1];
        arr[ind1]=arr[ind2];
        arr[ind2]=temp;
    }

    //Driver code
    public static void main(String[] args) {

        int[] arr={4,3,2,1,9,0,6,22};
        SelectionSort(arr,0, arr.length, 0);
        System.out.println(Arrays.toString(arr));
    }
}
