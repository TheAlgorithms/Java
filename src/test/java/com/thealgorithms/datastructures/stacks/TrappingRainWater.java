/*Problem Statement:
We are given an array of non-negative integers representing an elevation map, where each integer corresponds to the height of a bar and the width of each bar is 1.
The objective is to compute how much water can be trapped between these bars after it rains.
For example, given the input:
height = [2, 1, 0, 1, 3, 2]
The total amount of water trapped between the bars is 4 units.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Approach:
To solve this problem efficiently, I have implemented a two-pointer approach that achieves O(n) time complexity and O(1) space complexity.
This method minimizes memory usage while maintaining optimal performance by calculating water trapped in a single pass through the array. Here’s a breakdown of the strategy:

Two Pointers (Left and Right):

We maintain two pointers: left starting at the beginning of the array and right starting at the end.
The key observation is that water trapped at any position is limited by the shorter of the bars to its left and right.
Tracking Maximum Heights:

We also maintain two variables: leftMax and rightMax, which track the highest bars encountered so far from the left and right pointers, respectively.
Decision to Move Pointers:

At each step, we compare leftMax and rightMax. If leftMax is smaller, it means the amount of water that can be trapped depends on leftMax. So, we move the left pointer to the right, 
update leftMax if needed, and calculate the water trapped at that position.
If rightMax is smaller, we move the right pointer to the left, update rightMax if needed, and calculate the water trapped at that position.
Why This Works:

By always moving the pointer with the smaller max, we ensure that we only consider the side where water can be trapped, as the larger max on the other side guarantees the current position
is bounded by a taller bar.

Water Calculation:
For each position, the water trapped is calculated as:
water = min(leftMax, rightMax) - current height
This formula works because the trapped water is the difference between the height of the shortest boundary (leftMax or rightMax) and the current bar’s height. 
If the current bar is taller than the shortest boundary, no water is trapped at that position.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Algorithm in Action:
Let’s consider an example to see how this approach works:

Input: height = [2, 1, 0, 1, 3, 2]

Initial state:

left = 0, right = 5
leftMax = 2, rightMax = 2
Water trapped = 0

1.We compare leftMax and rightMax, and since they are equal, we move right to 4. Update rightMax to 3. No water trapped at this step.

2.Now, leftMax (2) is less than rightMax (3), so we move left to 1. Water trapped at index 1 is:
leftMax (2) - height[1] (1) = 1 unit of water

3.Again, leftMax (2) is less than rightMax (3), so we move left to 2. Water trapped at index 2 is:
leftMax (2) - height[2] (0) = 2 units of water

4.We move left to 3. Water trapped at index 3 is:
leftMax (2) - height[3] (1) = 1 unit of water

5.Finally, we move left to 4 where leftMax is updated to 3. At this point, the iteration stops as left and right pointers meet.
Total water trapped = 1 + 2 + 1 = 4 units.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Code:*/
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = height[0], rightMax = height[height.length - 1];
        int water = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                if (leftMax < height[left]) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
            } else {
                right--;
                if (rightMax < height[right]) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
            }
        }
        return water;
    }
}
/*
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Complexity:
Time Complexity: O(n) — We only traverse the array once with two pointers.
Space Complexity: O(1) — Only a few extra variables are used, regardless of the input size.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Test Cases:
Example 1:

Input: height = [2, 1, 0, 1, 3, 2]
Output: 4
Example 2:

Input: height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
Output: 6
Example 3:

Input: height = [4, 2, 0, 3, 2, 5]
Output: 9
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
This approach efficiently solves the problem using minimal space while providing clear and understandable logic. It handles all edge cases, including arrays with no trapping capacity or a single bar.
*/
