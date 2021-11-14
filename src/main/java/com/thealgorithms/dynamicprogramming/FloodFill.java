package com.thealgorithms.dynamicprogramming;

/**
 * Java program for Flood fill algorithm.
 * @author Akshay Dubey (https://github.com/itsAkshayDubey)
 */
public class FloodFill {

    /**
     * Get the color at the given co-odrinates of a 2D image
     *
     * @param image The image to be filled
     * @param x_co_ordinate The x co-ordinate of which color is to be obtained
     * @param y_co_ordinate The y co-ordinate of which color is to be obtained
     */
	
	public static int getPixel(int[][] image, int x_co_ordinate, int y_co_ordinate) {
	
		return image[x_co_ordinate][y_co_ordinate];
	
	}
	
    /**
     * Put the color at the given co-odrinates of a 2D image
     *
     * @param image The image to be filed
     * @param x_co_ordinate The x co-ordinate at which color is to be filled
     * @param y_co_ordinate The y co-ordinate at which color is to be filled
     */
	public static void putPixel(int[][] image, int x_co_ordinate, int y_co_ordinate, int new_color) {
		
		image[x_co_ordinate][y_co_ordinate] = new_color;
	
	}
	
	
    /**
     * Fill the 2D image with new color
     *
     * @param image The image to be filed
     * @param x_co_ordinate The x co-ordinate at which color is to be filled
     * @param y_co_ordinate The y co-ordinate at which color is to be filled
     * @param new_color The new color which to be filled in the image
     * @param old_color The old color which is to be replaced in the image
     * @return
     */
	public static void floodFill(int[][] image, int x_co_ordinate, int y_co_ordinate, int new_color, int old_color) {
		if(x_co_ordinate >= 0 && y_co_ordinate >= 0 && getPixel(image, x_co_ordinate, y_co_ordinate) == old_color) {
			
			putPixel(image, x_co_ordinate, y_co_ordinate, new_color);
			floodFill(image, x_co_ordinate + 1, y_co_ordinate, new_color, old_color);
			floodFill(image, x_co_ordinate - 1, y_co_ordinate, new_color, old_color);
			floodFill(image, x_co_ordinate, y_co_ordinate + 1, new_color, old_color);
			floodFill(image, x_co_ordinate, y_co_ordinate - 1, new_color, old_color);
			floodFill(image, x_co_ordinate + 1, y_co_ordinate - 1, new_color, old_color);
			floodFill(image, x_co_ordinate - 1, y_co_ordinate + 1, new_color, old_color);
			floodFill(image, x_co_ordinate + 1, y_co_ordinate + 1, new_color, old_color);
			floodFill(image, x_co_ordinate - 1, y_co_ordinate - 1, new_color, old_color);
			
			
		}
		
	}
	
    /**
     * This method will print the 2D image matrix
     *
     * @param image The image to be printed on the console
     */
	public static void printImageArray(int[][] image) {
		
		for(int i=0 ; i<image.length ; i++) {
			for(int j=0 ; j<image[0].length ; j++) {
				
				System.out.print(image[i][j]+"  ");
			}
			
			System.out.println();
		}
		
	}
	
	// Driver Program
	public static void main(String[] args) {
		
		
		//Input 2D image matrix
		int[][] image = {
				{0,0,0,0,0,0,0},
				{0,3,3,3,3,0,0},
				{0,3,1,1,5,0,0},
				{0,3,1,1,5,5,3},
				{0,3,5,5,1,1,3},
				{0,0,0,5,1,1,3},
				{0,0,0,3,3,3,3}
		};
		
		floodFill(image,2,2,2,1);

		/* Output ==>
		 * 0  0  0  0  0  0  0  
		   0  3  3  3  3  0  0  
		   0  3  2  2  5  0  0  
           0  3  2  2  5  5  3  
           0  3  5  5  2  2  3  
           0  0  0  5  2  2  3  
           0  0  0  3  3  3  3
		 * */
		
		//print 2D image matrix
		printImageArray(image);
	}

}