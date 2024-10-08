import java.util.Arrays;

public class MajorityElement {
public static int findMajorityElement(int[] nums) {
int candidate = nums[0];
int count = 1;

    for (int i = 1; i < nums.length; i++) {
    if (count == 0) 
    {
    candidate = nums[i];
    count = 1;
    } else if (nums[i] == candidate) 
    {
    count++;
    } else 
    {
    count--;  

    }
    }

    // Verify if candidate is the majority element
    count = 0;
    for (int num : nums) 
    {
        if (num == candidate) 
        {
            count++;
        }
    }

    if (count > nums.length / 2) 
    {
        return candidate;
    } else 
    {
        return -1; // No majority element found
}
}

public static void main(String[] args) {
    int[] nums = {2, 1, 2, 1, 2, 2, 1};
    int majorityElement = findMajorityElement(nums);

    if (majorityElement != -1) 
    {
        System.out.println("Majority element: " + majorityElement);
    } else 
    {
        System.out.println("No majority element found");  

    }
}
}