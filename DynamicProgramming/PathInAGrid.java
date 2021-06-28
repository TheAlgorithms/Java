/**
 * Our next problem is to find a path from the upper-left corner to the lower-right
 * corner of an n£ n grid, such that we only move down and right. Each square
 * contains a positive integer, and the path should be constructed so that the sum of
 * the values along the path is as large as possible.
 */
public class PathInAGrid {



    static class Grid{
        int[][] grid;

        public Grid(int[][] input){
            this.grid = input;
        }

        /**
         * sum[a, b] tells us the maximum sum from the upper-left corner to the lower-right corner.
         */
        public int[][] biggestPath(){

            int[][] sum = new int[grid.length][grid.length];
            for(int[] temp: sum) Arrays.fill(temp, Integer.MAX_VALUE);

            for (int y = 1; y < grid.length; y++) {
                for (int x = 1; x < grid.length; x++) {
                    sum[y][x] = Integer.max(sum[y][x-1],sum[y-1][x])+value[y][x];
                }
            }

            return sum;
        }

    }

    public static void main(String[] args) {
        Grid g = new Grid({
            {3, 7, 9, 2, 7},
            {9, 8, 3, 5, 5},
            {1, 7, 9, 8, 5},
            {3, 8, 6, 4, 10},
            {6, 6, 9, 7, 8}
        });
        System.out.println(g.biggestPath()[4][4]); // == 67
    }
}