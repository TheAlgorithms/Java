
public class QuickSortMedianOfThree {

    public void sort(int[] array, int leftIndex, int rightIndex){

        if (leftIndex < rightIndex) {

            medianOfThree(array, leftIndex, rightIndex);
            changePivot(array, leftIndex, rightIndex);

            int pivotIndex = partition(array, leftIndex, rightIndex);

            sort(array, leftIndex, pivotIndex - 1);
            sort(array, pivotIndex + 1, rightIndex);

        }
    }

    private void medianOfThree(int[] array, int leftIndex, int rightIndex){

        int shortter = leftIndex;
        int mid = leftIndex + (rightIndex - leftIndex) / 2;
        int bigger = rightIndex;

        if (array[mid] < array[shortter]) {
            swap(array, shortter, mid);
        }
        if (array[bigger] < array[shortter]) {
            swap(array, shortter, bigger);
        }
        if (array[bigger] < array[mid]) {
            swap(array, bigger, mid);
        }

    }

    private void changePivot(int[] array, int leftIndex, int rightIndex) {
        int pivotIndex = leftIndex + (rightIndex - leftIndex) / 2;
        if (rightIndex - leftIndex >= 1) {
            swap(array, pivotIndex, rightIndex - 1);
        }

    }

    private int partition(int[] array, int leftIndex, int rightIndex) {

        int i = leftIndex;
        int j = leftIndex + 1;
        int p = leftIndex;

        while (j <= rightIndex) {

            if (array[j] < array[p]) {
                i = i + 1;
                swap(array, i, j);
            }
            j = j + 1;
        }

        swap(array, i, p);
        return i;
    }

    private void swap(int[] array, int i, int j) {
        if(array == null)
            throw new IllegalArgumentException();

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String args[]) {

        int[] array = new int[] {6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36};
        int leftIndex = 0;
        int rightIdex = array.length - 1;

        QuickSortMedianOfThree quick = new QuickSortMedianOfThree();
        quick.sort(array, leftIndex, rightIdex);

        System.out.println("The sorted array is:");
        for (int i=0; i < array.length; i++)
            System.out.print(array[i] + " ");

    }

}
