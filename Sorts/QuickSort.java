/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

class QuickSort {

    /**
     * This method implements the Generic Quick Sort
     *
     * @param array The array to be sorted
     * @param start The first index of an array
     * @param end The last index of an array
     * Sorts the array in increasing order
     **/

    public static <T extends Comparable<T>> void QS(List<T> list, int left, int right) {
        if (left>=right) { return }
        else
        {
            int pivot = partition(array, left,right);
            QS(list, left, pivot- 1);
            QS(list, pivot + 1, right);
        }
    }

    /**
     * This method finds the partition index for an array
     *
     * @param array The array to be sorted
     * @param start The first index of an array
     * @param end The last index of an array
     * Finds the partition index of an array
     **/

    public static <T extends Comparable<T>> int partition(List<T> list, int left, int right) {
        int mid=(left+right)/2;
        T pivot=list.get(mid);
        swap(list,mid,right);
        while(left<right)
        {
            while(left<right && pivot.compareTo(list.get(left))>=0)
            {
                ++left;
            }
            if(left<right)
            {
                swap(list,left,right);
                --right;
            }
            while(left<right && list.get(right).compareTo(pivot)>=0)
            {
                --right;
            }
            if(left<right)
            {
                swap(list,left,right);
                ++left;
            }
        }
        return left;
    }

    /**
     * This method swaps two elements of an array
     *
     * @param array The array to be sorted
     * @param initial The first element
     * @param fin The second element
     * Swaps initial and fin element
     **/

    public static <T extends Comparable<T>> void swap(List<E> list, int initial, int fin) {
        E temp= list.get(initial);
        list.set(initial,list.get(fin));
        list.set(fin,temp);
    }

    // Driver Program
    public static void main(String[] args) {

        // For integer input
        ArrayList<Integer> array = new ArrayList<Integer>(9);
         array = {3,4,1,32,0,2,44,111,5};

        QS(array, 0, array.size()-1);

        //Output => 0 1 2 3 4 5 32 44 111
        for (int i=0;i<array.size();i++) {
            System.out.print(array.get(i) + " ");
        }
        System.out.println();

        ArrayList<String> array1=new ArrayList<String>(5);
        array1 = {"c", "a", "e", "b","d"};

        QS(array1, 0,array1.size()-1);

        //Output => a	b	c	d	e
        for(int i=0; i<array1.size(); i++)
        {
            System.out.print(array1.get(i)+"\t");
        }
    }
}

