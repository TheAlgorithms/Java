import java.util.Arrays;

public class reversearray {
    public static void main(String[] args){
        int[] arr = { 1 ,3, 4, 99, 5, 66 , 78}; // using 2pointers reference
        reverse(arr);
        System.out.println(Arrays.toString(arr));

    }
    static void reverse(int[] arr ){
        int start = 0 ;
        int end = arr.length-1 ;

        while(start < end){ // works for both even and odd  number of elements in arrays
            //keep swapping until start> end
            swap(arr, start , end);
            start ++;
            end -- ;

        }
    }
    static void swap(int[] arr , int index1 , int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp ;
    }
}
