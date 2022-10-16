/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursivedemo;

/**
 *
 * @author HP
 */
public class RecursiveAlgos 
{ 
    int Factorial(int N){
        if (N==0){
            
            return 1 ;} 
        else{ 			
            return N * Factorial ( N - 1 );
        }
       
    }
    int LinearSum(int A[],int n){
	if (n == 1) { 
		return A[0];
        }else{
            return  A[n-1] + LinearSum(A, n-1);
            }
        }
    int EuGCD(int N1,int N2){
        if (N1==0){
            return N2;
        }
        else{
            return EuGCD(N2,N1%N2);
        }
    
    }
    void reverseArray(int A[],int i,int j){
	if (i < j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
		reverseArray(A, i+1, j-1);
	    }

        }
        int BinarySearch(int key, int A[], int LI, int HI){
        int mid=(LI+HI)/2;
	if (LI > HI){ 		
		 return -1;}
	if (key == A[mid]){		
		return mid;}
	else if (key < A[mid]){		
	       return BinarySearch(key, A, LI, mid - 1);} 
					
        return BinarySearch(key, A, mid + 1, HI);
    }

}