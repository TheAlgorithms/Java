package divideconquer;

/**
 * Time complexity: 
 * Worst-case: O(n^2); Best-case: O(n); Average: O(n)
 */
public class QuickSelect {

    /**
     * This method implements the Generic Quick Select to find the kth smallest element in an unordered array.
     *
     * @param  nums The unordered array
     * @param  k    The kth smallest element
     *
     * @return kth smallest element
     */
    public <T extends Comparable<T>> T Select(T[] nums, int k) {
        int end = nums.length - 1;
        return Partition(nums, 0, end, k);
    }


    /**
     * This method finds the partition element for an array
     *
     * @param  nums  The array
     * @param  start The first index of an array
     * @param  end   The last index of an array
     * @param  k     kth smallest
     *
     * @return kth smallest element
     */
    private <T extends Comparable<T>> T Partition(T[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        
        // Use random position to prevent worse case 
        int length = end - start + 1;
        int randomPosition = (int) (Math.random() * length) + start;
        swap(nums, end, randomPosition);
        
        // Use element at the end as pivot
        T pivot = nums[end];
        int j = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i].compareTo(pivot) < 0) {
                j++;
                swap(nums, i, j);
            }
        }
        j++;
        swap(nums, j, end);
        
        // Find the index of the subarray
        int index = j - start + 1;        
        if (index == k) {
            return nums[j];
        }
        else if (index > k) {
            return Partition(nums, start, j - 1, k);
        }
        else {
            return Partition(nums, j + 1, end, k - index);
        }
    }


    /**
     * Helper method for swapping places in array
     *
     * @param nums The array which elements we want to swap
     * @param i    index of the first element
     * @param j    index of the second element
     */
    public <T extends Comparable<T>> void swap(T[] nums, int i, int j) {
        T temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }


    // Driver Program
    public static void main(String[] args) {
        
        // For integer input
        Integer[] arr = {3, 4, 1, 32, 0, 1, 5, 12, 2, 5, 7, 8, 9, 2, 44, 111, 5};
        
        QuickSelect quickSelect = new QuickSelect();
        
        // Output => 1
        System.out.println(quickSelect.Select(arr, 3));
        
        String[] stringArray = {"c", "a", "e", "b", "d"};
        
        // Output => b
        System.out.println(quickSelect.Select(stringArray, 2));
    }
}
