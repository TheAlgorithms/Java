/**
 *
 * @author Karsey Renfert (https://github.com/karseyr) 
 *
 */

public class BubbleSort {
	
	public static void main(String[] args) {
		Integer[] input = {113,523,1,2,5,3,9,123521,1246313,123912,320,0};
		int len = input.length;
		Integer[] output = new Integer[len];
		output = Sort(input, len);
		for(int i = 0; i < len; i++) {
			System.out.println(output[i]);
		}
	}
	
	public static Integer[] Sort(Integer[] in, int len) {
		int sorted = 0;
		int t = 0;
		while(sorted < (len-1)) {
			sorted = 0;
			for(int i = 0; i < len-1; i++) {
				if(in[i] > in[i+1]) {
					t = in[i];
					in[i] = in[i+1];
					in[i+1] = t;
				} else {
					sorted++;
				}
			}
		}
		return in;
	}
}
