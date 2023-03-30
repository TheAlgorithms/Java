
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
This class finds the majority element(s) in an array of integers.
A majority element is an element that appears more than n/3 times, where n is the length of the array.
*/
public class MajorityElement {
    /*
    This method finds the majority element(s) in the given array of integers and prints them to console.
    @param nums: an array of integers
    */
    public static void majority(int[] nums){
        HashMap<Integer,Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])){ //true
                map.put(nums[i],map.get(nums[i])+1);
            }else { //false or element appeared first time
                map.put(nums[i],1);
            }
        }
        for (int key: map.keySet()) {
            if (map.get(key) > n/3){
                System.out.println(key);
            }
        }
    }
    /*
    This method tests the majority method of MajorityElement class with an input array.
    */
    @Test
    void testMajority() {
        int[] nums = {1,3,3,3,2,5,1,3,1,5,1};
        int[] expected = {1, 3};
        assertArrayEquals(expected, MajorityElement.majority(nums));
    }
}
