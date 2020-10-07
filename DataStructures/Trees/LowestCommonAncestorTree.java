package DataStructures.Trees;
class Node
{
    int data;
    Node left,right;
    Node(int d)
    {
        data=d;
    }
}
class Tree
{
    Node lca(Node root,int n1,int n2) // n1 and n2 are the values to be searched
    {
        if(root==null) return null;
        if(root.data==n1 || root.data==n2) return root;
        Node lca1=lca(root.left, n1, n2);
        Node lca2=lca(root.right, n1, n2);
        if(lca1!=null && lca2!=null) return root;
        if(lca1!=null) return lca1;
        else return lca2;
    }
}
class abc
{
    public static void main(String[] args) {
        Node root=new Node(10);
        root.left=new Node(20);
        root.right=new Node(30);
        root.right.left=new Node(40);
        root.right.right=new Node(50);
        System.out.println(new Tree().lca(root, 40, 50).data);
    }
}

/*
It works on the logic of assumption that both n1 and n2 are present
The logic behind this is that there are 4 cases for each node of the graph
1 If it's same as n1 and n2
2 If one of the subtrees conatin n1 and other conatin n2
3 If one of the subtress contain both n1 and n2
4 If none of the subtrees conatin n1 and n2 
*/