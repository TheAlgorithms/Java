package searching;

/**
 * LeetCode Problem 169: Majority Element
 * Boyer-Moore Voting Algorithm
 */
public class MajorityElement {
    public static int findMajority(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Majority element: " + findMajority(nums));
    }
}
