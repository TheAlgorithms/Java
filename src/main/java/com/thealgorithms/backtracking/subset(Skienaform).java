package com.thealgorithms.backtracking;

class Subset{
	static void Backtracking(int a[],int k,int n,int input[]){
		int c[]=new int[n];
		int ncan=0;
		if(is_a_solution(n,k)){
			process_solution(a,n,k,input);
		}
		else{
			k++;
			ncan=construct_candidate(c,k,n);
			for(int i=0;i<ncan;i++) {
				a[k-1]=c[i];
				Backtracking(a,k,n,input);
			}
		}
	} 
	static int construct_candidate(int c[],int k,int n){
		c[0]=0;
		c[1]=1;
		return 2;
	}
	static boolean is_a_solution(int k,int n){
		if(k-1==(n-1)){
			return true;
		}
		else{
			return false;
		}
	}
	static void process_solution(int a[],int n,int k,int input[]){
		for (int i=0;i<n;i++ ) {
			if(a[i]==1){
				System.out.print(input[i]);
			}
		}
		System.out.println();
	}
	public static void main(String[] args) {
		int input[]={2,4,3,1,1};
		int n=input.length;
		int a[]=new int[n];
		Backtracking(a,0,n,input);
	}
}
