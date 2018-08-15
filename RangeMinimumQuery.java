package SegmentTrees;

import java.util.Scanner;

public class RangeMinimumQuery{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int q = s.nextInt();
	    int[] a = new int[n];
	    for(int i=0;i<n;i++)
	        a[i] = s.nextInt();
	    SegmentTreeRMQ tree = new SegmentTreeRMQ(n);
	    tree.createTree(a);
	    while(q-->0)
	    {
	        char optn = s.next().charAt(0);
	        if(optn=='q')
	        {
	            int l = s.nextInt();
	            int r = s.nextInt();
	            l = l-1;
	            r = r-1;
	            System.out.println(tree.query(l,r));
	        }
	        else
	        {
	            int indx = s.nextInt();
	            int val = s.nextInt();
	            indx = indx-1;
	            tree.update(indx,val,a);
	        }
	    }
	}
}

class SegmentTreeRMQ{
    public int n;
    public int[] st;
    
    private void createTreeHelper(int node,int start,int end,int[] a)
    {
        int mid = (start+end)/2;
        if(start==end)  //Leaf node accessed;
        {
            this.st[node] = a[mid];
            return;
        }
        createTreeHelper(2*node+1,start,mid,a); //Left Subtree.
        createTreeHelper(2*node+2,mid+1,end,a); //Right Subtree.
        this.st[node] = Math.min(this.st[2*node+1],this.st[2*node+2]);
    }
    
    private int queryHelper(int node,int start,int end,int l,int r)
    {
        if(r<start || l>end)
            return Integer.MAX_VALUE;
        if(l<=start && end<=r)
            return this.st[node];
        int mid = (start+end)/2;
        int val1 = queryHelper(2*node+1,start,mid,l,r);
        int val2 = queryHelper(2*node+2,mid+1,end,l,r);
        return Math.min(val1,val2);
    }
    
    private void updateHelper(int node,int start,int end,int[] a,int indx,int val)
    {
        int mid = (start+end)/2;
        if(start==end)  //Leaf node accesses.
        {
            a[indx] = val;
            this.st[node] = val;
            return;
        }
        if(indx<=mid)
            updateHelper(2*node+1,start,mid,a,indx,val);
        else
            updateHelper(2*node+2,mid+1,end,a,indx,val);
        this.st[node] = Math.min(this.st[2*node+1],this.st[2*node+2]);
    }
    
    public SegmentTreeRMQ(int n)
    {
        this.n = n;
        this.st = new int[4*n+1];
    }
    
    public void createTree(int[] a)
    {
        createTreeHelper(0,0,this.n-1,a);
    }
    
    public int query(int l,int r)
    {
        return queryHelper(0,0,this.n-1,l,r);
    }
    
    public void update(int indx,int val,int[] a)
    {
        updateHelper(0,0,this.n-1,a,indx,val);
    }   
}