package com.thealgorithms.sorts;

import java.util.Arrays;

class MergeSortDivideAndConquer{
    public static void main(String[]args){
        //taking the sample array input
        int []array={12,32,1,5,9,7,25};

        //calling the recursive fumction which will divide and sort the array
        array=MergeSort(array);
        //print the sorted array
        System.out.println(Arrays.toString(array));
    }

    //implementing divide and sort method

    public static int[] MergeSort(int [] array){
        //base condition
        //if only 1 element present in the array then we return the array
        if(array.length==1){
            return array;
        }

        //if the array length is greater than 1 than we will split it into two arrays until the length becomes 1.

        int mid=array.length/2;
        //splitting the array into 2 equal lengths i.e left array is from 1st element to middle element and right array is from middle to last element.

        int [] left=MergeSort(Arrays.copyOfRange(array,0,mid));
        int [] right=MergeSort(Arrays.copyOfRange(array,mid,array.length));
        //after dividing the arrays until a single element exists in them we will combine them.
        return Merge(left,right);




    }

    //implementing the merge function which will combine left and right arrays.
    public static int [] Merge(int[]left,int[]right){
        //creating a array which contain the result of merged array after sorting;
        int [] result=new int[left.length+right.length];
        //intializing the indexes
        int i=0;    //to iterate the left array
        int j=0;    //to iterate the  right array
        int k=0;    //to iterate the resultant array

        //appending the elements into resultant by comparing the left and right arrays
        while(i!=left.length && j!=right.length){
            if(left[i]<right[j]){
                result[k]=left[i];
                i++;
            }
            else{
                result[k]=right[j];
                j++;
            }
            k++;
        }
        //this condition checks and appends whether the right array is being appended and there still exists elements in the left array.
        while(i!=left.length){
            result[k]=left[i];
            i++;
            k++;
        }
        //this checks when the all elements in the left array are appended and there still remains elements in right array
        while(j!=right.length){
            result[k]=right[j];
            j++;
            k++;

        }
        return result;
    }
}
