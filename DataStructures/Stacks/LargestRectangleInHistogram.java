package DataStructures.Stacks;

import java.util.*;

/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
Example:
Input: [2,1,5,6,2,3]
Output: 10

Question: Leetcode (https://leetcode.com/problems/largest-rectangle-in-histogram/)
*/

public class LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {
        if(heights.length == 0){
            return 0;
        }
        ArrayList<Integer> list1 = nsl(heights);
        ArrayList<Integer> list2 = nsr(heights);

        //fill left and right array
        Integer[] left = new Integer[list1.size()]; 
        left = list1.toArray(left); 

        Integer[] right = new Integer[list2.size()]; 
        right = list2.toArray(right); 

        //create width array
        int[] width = new int[heights.length];

        //fill width array
        for(int i = 0; i < heights.length; i++){
            width[i] = right[i] - left[i] - 1;
        }

        //area array
        int[] area = new int[heights.length];
        for(int i = 0; i < heights.length; i++){
            area[i] = heights[i] * width[i];
        }

        //Find max in area array
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < area.length; i++){
            if(area[i] > max){
                max = area[i];
            }
        }
        return max;
    }
    
    //Pair class
    private static class Pair{
        int first, second;

        Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
    
    //next smallest to left (nsl)
    public static ArrayList<Integer> nsl(int[] arr){
        ArrayList<Integer> left = new ArrayList<>();
        Stack<Pair> st = new Stack<>();
        int pseudoIndex = -1;

        for(int i = 0; i < arr.length; i++){
            if(st.size() == 0){
                left.add(pseudoIndex);
            }
            else if(st.size() > 0 && st.peek().first < arr[i]){
                left.add(st.peek().second);
            }
            else if(st.size() > 0 && st.peek().first >= arr[i]){
                while(st.size() > 0 && st.peek().first >= arr[i]){
                    st.pop();
                }
                
                if(st.size() == 0){
                    left.add(pseudoIndex);
                }
                else{
                    left.add(st.peek().second);
                }
            }
            st.push(new Pair(arr[i], i));
        }
        return left;
    }

    //next smallest to right (nsr)
    public static ArrayList<Integer> nsr(int[] arr){
        ArrayList<Integer> right = new ArrayList<>();
        Stack<Pair> st = new Stack<>();
        int pseudoIndex = arr.length;

        for(int i = arr.length-1; i >= 0; i--){
            if(st.size() == 0){
                right.add(pseudoIndex);
            }
            else if(st.size() > 0 && st.peek().first < arr[i]){
                right.add(st.peek().second);
            }
            else if(st.size() > 0 && st.peek().first >= arr[i]){
                while(st.size() > 0 && st.peek().first >= arr[i]){
                    st.pop();
                }
                
                if(st.size() == 0){
                    right.add(pseudoIndex);
                }
                else{
                    right.add(st.peek().second);
                }
            }
            st.push(new Pair(arr[i], i));
        }
        Collections.reverse(right);
        return right;
    }

    //main method
    public static void main(String[] args){
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }
}

/*
Output 
10
*/