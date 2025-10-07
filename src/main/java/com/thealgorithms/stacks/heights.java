import java.util.Stack;

public class LargestRectangleHistogram {
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // Use 0 height at end to flush stack
            int h = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width;
                if (stack.isEmpty()) {
                    width = i; // rectangle extends from 0 to i-1
                } else {
                    width = i - stack.peek() - 1; // between previous smaller and i
                }
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int result = largestRectangleArea(heights);
        System.out.println("Largest Rectangle Area: " + result);
    }
}
