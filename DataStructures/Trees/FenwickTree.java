package DataStructures.Trees;

/** @author Anirudh Buvanesh (https://github.com/anirudhb11)
 * For more information see https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 * */
public class FenwickTree {

    static final int MAX = 100;
    static int[] fenwickTree = new int [MAX];

    /**
     * @param index
     * @return Prefix sum of the array from 0 to index
     */
    public int getSum(int index){
        int sum = 0;
        index = index + 1;
        while(index > 0){
            sum += fenwickTree[index];
            index -= index & (-index);
        }
        return sum;
    }

    /**
     *
     * @param n Number of elements in the array
     * @param index The index of the fenwickTree[] that needs to be modified
     * @param delta The value that should be added to index of fenwickTree[]
     */
    public void updateElement(int n, int index, int delta){
        index = index + 1;
        while(index <= n){
            fenwickTree[index] += delta;
            index += index & (-index);
        }
    }

    /**
     *
     * @param arr Array for which Fenwick Tree is built
     * @param n Number of elements in the array
     */
    public void constructFenwickTree(int[] arr, int n){
        for(int i=0;i<=n;i++){
            fenwickTree[i] = 0;
        }
        for(int i=0;i<n;i++){
            updateElement(n, i, arr[i]);
        }
    }

    public static void main(String[] args){
        int [] arr = {1,2,3,4,5,6,7};
        int n = arr.length;

        FenwickTree tree = new FenwickTree();
        tree.constructFenwickTree(arr, n);

        //Expecting 15
        assert tree.getSum(4) == 15;

        arr[2] += 5;

        //Update Fenwick Tree
        tree.updateElement(n, 2, 5);

        //Expecting 20
        assert tree.getSum(4) == 20;
    }
}
