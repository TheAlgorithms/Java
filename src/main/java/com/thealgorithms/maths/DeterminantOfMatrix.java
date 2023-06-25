package com.thealgorithms.maths;
import java.util.*;
/*
 * Determinant of Matrix :https://textbooks.math.gatech.edu/ila/determinants-cofactors.html
 */
public class DeterminantOfMatrix {
	public static double[][] SUB_MAT(double[][] mat,int order,int c) {
		//creating reference for the sub matrix
		double[][] sub_mat=new double[order-1][order-1];
		//sub_c stores the column column index of the sub matrix
		int sub_c;
		for(int i=1;i<order;i++) {	
			//making the column count zero after each row-addition
			sub_c=0;
			for(int k=0;k<order;k++) {
				if(k==c) {
					continue;//skipping the column where the mat[0][c] of OPER is in
				}
				sub_mat[i-1][sub_c]=mat[i][k];
				sub_c+=1;//increasing the column count
			}
		}
		return(sub_mat);
	}
	public static double DET(double[][] mat,int order) {
		/*determinant of the matrix with one element(order 1) :
		 is the element itself*/
		if (order==1) {
			return(mat[0][0]);
		}
		return(OPER(mat,order));
	}
	public static double OPER(double[][] mat,int ord) {
		int check;
		double del=0;
		for(int c=0;c<ord;++c) {
			if(c%2==0) {
				check=-1;
			}
			else {
				check=1;
			}
			del+=(check*(mat[0][c])*(DET(SUB_MAT(mat,ord,c),ord-1)));
		}
		return(del);
	}
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int order=in.nextInt();
		in.nextLine();
		double[][] mat=new double[order][order];
		//getting the input elements of the matrix
		for (int i = 0; i < order; ++i) {
	        String line = in.nextLine().trim();
	        String[] elements = line.split(" ");
	        for (int j = 0; j < order; ++j) {
	            if (elements[j].trim().isEmpty()) {
	                System.out.println("Invalid input. Please enter all elements.");
	                return;
	            }
	            mat[i][j] = Double.parseDouble(elements[j]);
	        }
	    }
		if(order==1) {
			/*determinant of the matrix with one element(order 1) :
			 is the element itself*/
			System.out.println(mat[0][0]);
		}
		else {
		System.out.println(OPER(mat,order));//calling the OPER(operation) function
		}
	}
}
