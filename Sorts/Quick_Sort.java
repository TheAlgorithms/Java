public class Quick_Sort {
//    public static void quickSort(int[] array){
//        quickSort(array, 0, array.length - 1);
//    }

    public static void quickSort(int[]  array, int left, int right){
        if(left >= right){
            return;
        }

        int pivot = array[(left + right)/2]; //This will take pivot at about an half length of the array
        int index = partition(array, left, right, pivot); //This will make partition using that pivot
        quickSort(array, left, index - 1 ); //This will sort at the left partition
        quickSort(array, index, right); //This will sort at the right partition
    }

    public static int partition(int[] array, int left, int right, int pivot){ //This will divide array in two parts
        while (left <= right){
            while(array[left] < pivot){
                left++;
            }

            while (array[right] > pivot){
                right--;
            }

            if (left <= right){
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    public static void swap(int[] array, int left, int right){ //This function will swap the array elements
        int temp;
        temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args){ //Driver code
        int[] array = {2, 5, 3, 9, 7};
        quickSort(array, 0, array.length - 1);
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

/*
Output :
2
5
3
9
7
*/