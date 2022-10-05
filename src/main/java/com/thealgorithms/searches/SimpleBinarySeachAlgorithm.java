package com.thealgorithms.searches;

public class SimpleBinarySeachAlgorithm {
    class Solution {
        public static int findMid(int[] numbers, int target) {
            int  start=0;
            int end=numbers.length-1;
            while(end>=start){
                int mid=start+(end-start)/2;

                if(numbers[mid]==target)
                    return mid;
                else if(numbers[mid]>target)
                    end=mid-1;
                else
                    start=mid+1;

            }
            return -1 ;
        }
        public static void main(String[] args) {
            int[] numArray = new int[]{1, 6, 10, 19, 20, 23}; // sample sorted array
            int foundVal = Solution.findMid(numArray, 1);
            if (foundVal == -1)
                System.out.println("given target element is not present");
            else
                System.out.println("given target element is present as pos " + foundVal);
        }

        }

    }
