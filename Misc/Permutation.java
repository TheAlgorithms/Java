/*
 @author: Mrinal Pandey
*/

import java.util.*;
class Permutation{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Permutation permutation = new Permutation();
		
		//number of elements of a set
		System.out.println("Enter cardinality of set : ");
		int N = in.nextInt();
		
		int[] elements = new int[N + 1];
		
		//numbers to N generated 
		for(int i = 1; i <= N; ++i){
			elements[i] = i;
		}
		boolean[] mark = new boolean[N + 1];
		mark[0] = true;
		ArrayList<Integer> soln = new ArrayList<Integer>();
		System.out.println("\nPermutations:");
		permutation.printPermutations(elements, mark, soln, N);
	}
	
	//function to find all permutations of a given set
	void printPermutations(int[] elements, boolean[] mark, ArrayList<Integer> permutation, int N){
		if(allTrue(mark) == true){
			display(permutation);
		}
		for(int i = 1; i <= N; ++i){
			if(mark[i] == false){
				mark[i] = true;
				permutation.add(i);
				printPermutations(elements, mark, permutation, N);
				mark[i] = false;
				permutation.remove(permutation.size() - 1);
			}
		}
	}
	
	//utility function to check if all elements of a set are visited
	boolean allTrue(boolean[] mark){
		for(boolean i : mark){
			if(i == false){
				return false;
			}
		}
		return true;
	}
	
	//display the permutation
	void display(ArrayList<Integer> permutation){
		System.out.println(permutation);
	}
}
