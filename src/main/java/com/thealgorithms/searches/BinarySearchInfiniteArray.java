package com.thealgorithms.searches;

/*
The idea of infinite array isn't about having an array of infinite elements rather having a large number of elements in
the array and not to use the length function to determine the end of array or the number of elements in the array. But
to apply binary search in an array, length of the array is required to specify the position of end pointer. The array is
sorted, obviously. So, the intuition behind the implementation is not to use the end index of the array rather start with
the smaller part of the array. Let's take the Oth element as the start and 1st element at the end. If the target matches
the elements, return the index, else change the start by (end + 1) and end by (start x 2 + 1) i.e. increasing the search
space by double the size of the previous part.
*/
public class BinarySearchInfiniteArray
{
    public static void main(String[] args)
    {
        int[] arr = {1,5,6,7,9,15,18,20,25,35,48,49,50,54,57,62,73,88,95,100,120,121,123,152,155,169};
        int target = 54;

        int ans = BinarySearch(arr, target);

        if (ans == -1)
            System.out.println("Element not found!!!");
        else
            System.out.println("Element is in index " + ans);
    }

    static int BinarySearch(int[] arr, int target){

        int start = 0, end = 1;

        while (start <= end) {

            if (target >= arr[start] && target <= arr[end]) {

                int mid = start + (end - start) / 2;

                if (target == arr[mid])
                    return mid;

                else if (target > arr[mid])
                    start = mid + 1;

                else
                    end = mid - 1;
            }

            else {
                start = end + 1;
                end = start * 2 + 1;
            }
        }
        return -1;
    }
}
