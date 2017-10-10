
/*
* https://en.wikipedia.org/wiki/Cocktail_shaker_sort
*
* Cocktail shaker sort,[1] also known as bidirectional bubble sort,[2] cocktail sort, shaker sort (which can also
* refer to a variant of selection sort), ripple sort, shuffle sort,[3] or shuttle sort, is a variation of bubble sort
 * that is both a stable sorting algorithm and a comparison sort. The algorithm differs from a bubble sort in that
 * it sorts in both directions on each pass through the list. This sorting algorithm is only marginally more
 * difficult to implement than a bubble sort, and solves the problem of turtles in bubble sorts. It provides
 * only marginal performance improvements, and does not improve asymptotic performance; like the bubble sort,
  * it is not of practical interest (insertion sort is preferred for simple sorts), though it finds some use
  * in education.
*
* */
public class ShakerSort {

    public static void main(String[] args) {

        int[] arr = {13,31,4,5,3,44,23,13,23};
        shakerSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void shakerSort(int[] arr_) {
        for (int i = 0; i < arr_.length/2; i++) {
            boolean change = false;
            for (int j = i; j < arr_.length - i - 1; j++) {
                if (arr_[j] < arr_[j+1]) {
                    int tmp = arr_[j];
                    arr_[j] = arr_[j+1];
                    arr_[j+1] = tmp;
                    change = true;
                }
            }
            for (int j = arr_.length - 2 - i; j > i; j--) {
                if (arr_[j] > arr_[j-1]) {
                    int tmp = arr_[j];
                    arr_[j] = arr_[j-1];
                    arr_[j-1] = tmp;
                    change = true;
                }
            }
            if(!change) break;
        }
    }




}
