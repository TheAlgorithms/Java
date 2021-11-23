package search;

import java.util.*;
class LinearSearchThread{
	public static void main(String args[]){
		int list[] = new int[200];
		for(int j = 0; j < list.length; j++) list[j] = (int)(Math.random()*100);
		for(int y : list) System.out.print(y+" ");
		System.out.println();
		System.out.print("Enter number to search for: ");
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		Searcher t = new Searcher(list,0,50,x);
		Searcher t1 = new Searcher(list,50,100,x);
		Searcher t2 = new Searcher(list,100,150,x);
		Searcher t3 = new Searcher(list,150,200,x);
		t.start(); t1.start(); t2.start(); t3.start();
		try{
			t.join(); t1.join(); t2.join(); t3.join();
		}
		catch(InterruptedException e){}
		boolean found = t.getResult() || t1.getResult() || t2.getResult() || t3.getResult();
		System.out.println("Found = " + found);
	}
}

class Found {
	private boolean found = false;
	public void set(){found = true;}
	public boolean found(){return found;}
	public String toString(){return found+"";}
}

class Searcher extends Thread {
	private int f[];
	private int lb, ub;
	private int x;
	private boolean found;
	Searcher(int f1[], int a, int b, int x) {
		f = f1; lb = a; ub = b; this.x = x;
	}
	public void run() {
		int k = lb; found = false;
		while (k < ub && !found){
			if(f[k] == x) found = true;
			k++;
		}
	}
	boolean getResult() {
		return found;
	}
}

