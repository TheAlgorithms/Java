import java.util.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/*
	@author - Kanika Saini ( https://github.com/kanikasaini )
*/ 
class rightView
{
static int visited;
public static void main(String[] args) throws Exception
{
    node root = null;
    root = insert(root, 3);
    root = insert(root, 1);
    root = insert(root, 2);
    root = insert(root, 4);
    root = insert(root, 7);
    root = insert(root, 5);
	visited=0;
	System.out.print("Right view of the tree: ");
	printRightView(root, 1); // prints 3 4 7 5
	System.out.println();
}	

public static node insert(node root, int d) //function to insert a node in a BST
{
	if(root==null)
		root= new node(d);
	else
	{
		if (d<root.data)
			root.left=insert(root.left, d);
		else
			root.right=insert(root.right, d);
	}
		return root;
}

public static void printRightView(node root, int current) //function to print the right view of a tree
{
	if (root!=null)
	{
		if(visited<current)
		{
			System.out.print(root.data +" ");
			visited=current;
		}
	printRightView(root.right, current+1);
	printRightView(root.left, current+1);
	}
	else 
		return;
}
}
class node // node class for a binary tree
{
	int data;
	node right;
	node left;
	public node()
	{
		data=0;
		left=right=null;
	}
	public node(int d)
	{
		data=d;
		right=left=null;
	}
}

