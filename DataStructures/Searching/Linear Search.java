/*
Code for LINEAR SEARCH
 */

public class LinearSearch {

    public static void main(String[] args) {
        int[] arr = {1,5,6,7};
        int target = 45;
        System.out.println("Elements is present at:" + linearSearch(arr,target));       //this will give -1
        target = 5;
        System.out.println("Elements is present at:" + linearSearch(arr,target));       //this will give index value 1


    }

    //search in the array: return the index if item found
    //otherwise if item is not found return -1
    static int linearSearch(int[] arr,int target){
        if(arr.length == 0){
            return -1;
        }
        //run for loop
        for (int index = 0; index < arr.length; index++) {
            //check for the element index if it is = target
            if(arr[index] == target){
                return index;
            }
        }
//this line will execute when item is not present in array
        return -1;
    }
}
