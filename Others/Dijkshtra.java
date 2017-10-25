/*
@author  : Mayank K Jha

*/


public class Solution {

public static void main(String[] args) throws IOException {
	  Scanner in =new Scanner(System.in);
	
	int n=in.nextInt();             //n = Number of nodes or vertices
        int m=in.nextInt();             //m = Number of Edges
        long w[][]=new long [n+1][n+1]; //Adjacency Matrix  
        
	//Initializing Matrix with Certain Maximum Value for path b/w any two vertices 
	for (long[] row: w)
            Arrays.fill(row, 1000000l);
	//From above,we Have assumed that,initially path b/w any two Pair of vertices is Infinite such that Infinite = 1000000l
	//For simplicity , We can also take path Value = Long.MAX_VALUE , but i have taken Max Value = 1000000l .
	
	//Taking Input as Edge Location b/w a pair of vertices
         for(int i=0;i<m;i++){
             int x=in.nextInt(),y=in.nextInt();
             long cmp=in.nextLong();
             if(w[x][y]>cmp){                      //Comparing previous edge value with current value  -  Cycle Case
            	w[x][y]=cmp; w[y][x]=cmp;        
             }
	}
	
	//Implementing Dijkshtra's Algorithm 
	
         Stack <Integer> t=new Stack<Integer>();
          int src=in.nextInt();
        for(int i=1;i<=n;i++){
        	if(i!=src){t.push(i);}}
        Stack <Integer> p=new Stack<Integer>();
        p.push(src);
        w[src][src]=0;
         while(!t.isEmpty()){int min=989997979,loc=-1;
        for(int i=0;i<t.size();i++){
        	w[src][t.elementAt(i)]=Math.min(w[src][t.elementAt(i)],w[src][p.peek()]
                +w[p.peek()][t.elementAt(i)]);
            if(w[src][t.elementAt(i)]<=min){
            	min=(int) w[src][t.elementAt(i)];loc=i;}
        }
        p.push(t.elementAt(loc));t.removeElementAt(loc);}
	
	//Printing shortest path from the given source src
         for(int i=1;i<=n;i++){
        	 if(i!=src && w[src][i]!=1000000l){System.out.print(w[src][i]+" ");}
        	 else if(i!=src){System.out.print("-1"+" ");}    //Printing -1 if there is no path b/w given pair of edges
         }
         
  }
}
