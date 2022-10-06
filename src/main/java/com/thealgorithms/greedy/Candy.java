/*
    There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

    You are giving candies to these children subjected to the following requirements:
        1.Each child must have at least one candy.
        2.Children with a higher rating get more candies than their neighbors.

    Return the minimum number of candies you need to have to distribute the candies to the children.
    
 */



import java.util.*;
public class Candy {
    public static int candyFun(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int sum = candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            sum += candies[i];
        }
        return sum;
    }

    public static void main(String[] arg) {
        int[] ratings = {1,0,2};
        System.out.println( candyFun(ratings));
    }
}

