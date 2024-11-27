package CIS_Github;
import java.util.List;

/*A monotonic array is an array that has elements that
are either always increasing or always decreasing.

Example: [2,4,6,8] (Always increasing; Monotonic) {True}
Example: [9,8,6,4] (Always decreasing; Monotonic) {True}
Example: [4,2,8,7] (Neither always increasing nor decreasing) {False}
*/

public class Mono {
    //Function to test if list is monotonic
    public static boolean isMonotonic(List<Integer> nums) {
        //Checks that list is always increasing
        boolean increasing = true;
        //Checks that list is always decreasing
        boolean decreasing = true;

        //Iterates through list to update boolean flag based on +/-
        for (int i = 0; i < nums.size() - 1; i++) {
            //If first number is less than the next, it is not increasing
            if (nums.get(i) < nums.get(i + 1)) {
                decreasing = false;
            }
            ////If first number is more than the next, it is not decreasing
            if (nums.get(i) > nums.get(i + 1)) {
                increasing = false;
            }
        }
        //List will return if monotonic
        return increasing || decreasing;
    }
    //Test case for isMonotonic function
    public static void main(String[] args) {
        System.out.println(isMonotonic(List.of(75, 64, 45, 36)));  // Output: true
        System.out.println(isMonotonic(List.of(35, 45, 65, 85)));  // Output: true
        System.out.println(isMonotonic(List.of(100, 56, 89)));
        System.out.println(isMonotonic(List.of(58394, 134569, 89002)));
    }
}
