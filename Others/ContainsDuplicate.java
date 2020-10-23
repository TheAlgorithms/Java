import java.util.HashSet;
class ContainsDuplicate {
  public static boolean containsDuplicate(int[] nums) {
    HashSet<Integer> set = new HashSet<Integer>(); //Use HashSet because there can't be any duplicates in a HashSet
    for(int i = 0; i < nums.length; i++) { //Iterate through nums array
        if(!set.add(nums[i])) { //Check if element was unable to be added to HashSet (This would only occur if there was a duplicate)
            return true; //Return true if there's a duplicate
        }
    }
    return false; //Return false if there isn't a duplicate
  }
  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 1}; //Values of nums array (You can change values if you want)
    System.out.println(containsDuplicate(nums));//Print method to console
  }
}