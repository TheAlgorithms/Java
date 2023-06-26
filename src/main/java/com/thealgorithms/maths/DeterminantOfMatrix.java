package com.thealgorithms.maths;
import java.util.*;
/*
 * Determinant of Matrix :https://textbooks.math.gatech.edu/ila/determinants-cofactors.html
 */
public class DeterminantOfMatrix {
    public static double[][] submatrix(double[][] mat,int order,int c) {
        //creating reference for the sub matrix
        double[][] subMat=new double[order-1][order-1];
        //sub_c stores the column column index of the sub matrix
        int subC;
        for(int i=1;i<order;i++) {	
            //making the column count zero after each row-addition
            subC=0;
            for(int k=0;k<order;k++) {
                if(k==c) {
                    continue;//skipping the column where the mat[0][c] of operation is in
                }
                subMat[i-1][subC]=mat[i][k];
                subC+=1;//increasing the column count
            }
        }
        return(subMat);
    }
    public static double determinant(double[][] mat,int order) {
        /*determinant of the matrix with one element(order 1) :
        is the element itself*/
        if (order==1) {
            return(mat[0][0]);
        }
        return(operation(mat,order));
    }
    public static double operation(double[][] mat,int order) {
        int check;
        double det=0;
        for(int c=0;c<order;++c) {
            if(c%2==0) {
                check=1;
            }
            else {
                check=-1;
            }
            det+=(check*(mat[0][c])*determinant(submatrix(mat,order,c),order-1));
        }
        return(det);
    }
}
/*the above code will work for all square matrix whose order are greater than one.
---to initiate this recursive algorithm pass the matrix and its order to the operation() method
---if the order is 1 return the determinant of the matrix directly
(determinant of the matrix with one element(order 1) :is the element itself)*/
