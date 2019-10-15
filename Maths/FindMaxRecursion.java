package Maths;

public class FindMaxRecursion {
    public static void main(String[] args) {
        ArrayList<Integer> res = new ArrayList<>();
	res.add("2");
	res.add("4");
	res.add("9");
	res.add("7");
	res.add("19");
	res.add("94");
	res.add("5");
        int low = 0;
        int high = array.size() - 1;

        System.out.println("max value is " + max(res, low, high));
    }

    /**
     * Get max of array using divide and conquer algorithm
     *
     * @param array contains elements
     * @param low   the index of the first element
     * @param high  the index of the last element
     * @return max of {@code array}
     */
    public static int max(ArrayList<Integer> res, int low, int high) {
        if (low == high) {
            return res.get(low); //or array[high]
        }

        int mid = (low + high) >>> 1;

        int leftMax = max(res, low, mid); //get max in [low, mid]
        int rightMax = max(res, mid + 1, high); //get max in [mid+1, high]

        return leftMax >= rightMax ? leftMax : rightMax;
    }
}
