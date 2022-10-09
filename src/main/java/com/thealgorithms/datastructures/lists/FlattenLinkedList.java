// Node class
public class Node
{
	int data;
	Node next;
	Node bottom;

	Node(int d)
	{
		data = d;
		next = null;
		bottom = null;
	}
}
/*  Function which returns the  root of 
    the flattened linked list. */
class FlattenLinkedList
{   Node merge(Node a,Node b){
    Node temp=new Node(0);
    Node res=temp;
    while(a!=null && b!=null){
        if(a.data<b.data){
            temp.bottom=a;
            temp=temp.bottom;
            a=a.bottom;
        }
        else{
            temp.bottom=b;
            temp=temp.bottom;
            b=b.bottom;
        }
    }
    if(a!=null){
        temp.bottom=a;
    }
    else{
        temp.bottom = b;
    }
    return res.bottom;
}
Node flatten(Node root)
{

if(root==null || root.next==null){
    return root;
}
root.next=flatten(root.next);
root=merge(root,root.next);
return root;
}
}  
