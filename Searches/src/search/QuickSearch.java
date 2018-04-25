package algorithmsjava;

public class QuickSelect {
    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
    private static int partition(int[] input, int left, int right) {
        int pivot = right;
        int stockIdx = left;
        for (int i = left; i <= right-1; i++) {
            if (input[i] < input[pivot]) {
                swap(input, i, stockIdx);
                stockIdx++;
            }
        }
        swap(input, stockIdx, pivot);
        return stockIdx;
    }
    public static int quickSelectRec(int[] input, int left, int right, int rank) {
        if (left == right) {
            return input[left];
        }
        int pivot = partition(input, left, right);
        if (pivot == rank) {
            return input[pivot];
        } else if (rank < pivot) {
            return quickSelectRec(input, left, pivot-1, rank);
        } else {
            return quickSelectRec(input, pivot+1, right, rank);
        }
    }
    public static int quickSelectIte(int[] input, int left, int right, int rank) {
        if (left == right) {
            return input[left];
        }
        while (true) {
            int pivot = partition(input, left, right);
            if (pivot == rank) {
                return input[pivot];
            } else if (rank < pivot) {
                right = pivot-1;
            } else {
                left = pivot+1;
            }
        }
    }
    public static void main(String[] args) {
        int input[] = {9,5,7,10,8,6,1,1,4,15,63,2,1,2};
        System.out.println(quickSelectRec(input, 0,input.length-1 , 0));
        System.out.println(quickSelectIte(input, 0,input.length-1 , 1));
        System.out.println(quickSelectRec(input, 0,input.length-1 , 2));
        System.out.println(quickSelectIte(input, 0,input.length-1 , 3));
        System.out.println(quickSelectRec(input, 0,input.length-1 , 4));
        System.out.println(quickSelectIte(input, 0,input.length-1 , 5));
    }
}
