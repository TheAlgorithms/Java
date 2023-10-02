package com.thealgorithms.sorts;

import java.util.Arrays;

public class MergeSortUsingRecursion {
    public static void main(String[] args) {
            int[] arr = {5,4,3,2,1};
            arr = mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static int[] mergeSort(int[] arr){

//        breaking point if there is a array having one element there is nothing to sort
//        return it
        if (arr.length==1){
            return arr;
        }

//        else the break the array into two equal halfs
        int mid = arr.length / 2;

//        send the left array for the further recursive calls
        int[] leftPart = mergeSort(Arrays.copyOfRange(arr,0,mid)); // this will return a sorted array

//        after sorting the left half send the right half for the further recursion calls
        int[] rightPart = mergeSort(Arrays.copyOfRange(arr,mid,arr.length));

//        now the leftPart has the sorted array and the right part has the sorted array
//        we have to merge them in order to get the whole array sorted

        return merge(leftPart,rightPart);

    }

    /**
     * this below function compares to arrays and creates a new array where the elements are
     * placed according to their increasing value
     */

    static int[] merge(int[] firstArray , int[] secondArray){

//        the length of the answer array will be the length of the first array and the length
//        of the second array as it contains all the elements of the both the array
        int[] ansArray = new int[firstArray.length + secondArray.length];

//        we need three pointer as we will be dealing with the 3 array
        int i = 0; // for the first array
        int j = 0; // for the second array
        int k = 0; // for the ans array


//        both the pointers of both the array should be in the range because it makes the equal
//        comparison if any of the array gets out of the boundary the comparison must be stopped
        while (i< firstArray.length && j< secondArray.length){
            if (firstArray[i] < secondArray[j]){
                ansArray[k] = firstArray[i];
                i++;
            } else {
                ansArray[k] = secondArray[j];
                j++;
            }
            k++;
        }

//        there might be a case where any of the one array has more elements than the other ones
//        as per the above conditions the remaining elements are not visited and haven't added
//        in the ans array so the below function is used to add the rest of the elements to the ans
//        arr

//        if the first array has more elements
        while (i< firstArray.length){
            ansArray[k] = firstArray[i];
            i++;
            k++;
        }

//        if the second array has more elements
        while (j< secondArray.length){
            ansArray[k] = secondArray[j];
            j++;
            k++;
        }

//        just return the ans array
        return ansArray;

    }

}
