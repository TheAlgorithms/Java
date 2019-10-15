package Maths;

public class FindMinRecursion {
    public static void main(String[] args) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add("1");
	res.add("2");
	res.add("3");
	res.add("4");
	res.add("5");
        int low = 0;
        int high = res.size() - 1;

        System.out.println("min value is " + min(res, low, high));
    }

    /**
     * Get min of array using divide and conquer algorithm
     *
     * @param array contains elements
     * @param low   the index of the first element
     * @param high  the index of the last element
     * @return min of {@code array}
     */
    public static int min(ArrayList<Interger>, int low, int high) {
        if (low == high) {
            return res.get(high); //or array[high]
        }

        int mid = (low + high) >>> 1;

        int leftMin = min(res, low, mid); //get min in [low, mid]
        int rightMin = min(res, mid + 1, high); //get min in [mid+1, high]

        return leftMin <= rightMin ? leftMin : rightMin;
    }
}
