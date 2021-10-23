package org.supriyo.macos.binarytree;
 
public class BinaryTreeLCA {
    public static class TreeNode
    {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int data)
        {
            this.data=data;
        }
    }
 
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
        if(root == null)
            return null;
        if(root.data == a.data || root.data == b.data )
            return root;
 
        TreeNode left=lowestCommonAncestor(root.left,a,b);
        TreeNode right=lowestCommonAncestor(root.right,a,b);
 
        // If we get left and right not null , it is lca for a and b
        if(left!=null && right!=null)
            return root;
        if(left== null)
            return right;
        else
            return left;
 
    }
    public static void main(String[] args)
    {
        // Creating a binary tree
        TreeNode rootNode=createBinaryTree();
        System.out.println("Lowest common ancestor for node 5 and 30:");
        TreeNode node5=new TreeNode(5);
        TreeNode node30=new TreeNode(30);
        System.out.println(lowestCommonAncestor(rootNode,node5,node30).data);
 
    }
 
    public static TreeNode createBinaryTree()
    {
 
        TreeNode rootNode =new TreeNode(40);
        TreeNode node20=new TreeNode(20);
        TreeNode node10=new TreeNode(10);
        TreeNode node30=new TreeNode(30);
        TreeNode node60=new TreeNode(60);
        TreeNode node50=new TreeNode(50);
        TreeNode node70=new TreeNode(70);
        TreeNode node5=new TreeNode(5);
        TreeNode node45=new TreeNode(45);
        TreeNode node55=new TreeNode(55);
 
        rootNode.left=node20;
        rootNode.right=node60;
 
        node20.left=node10;
        node20.right=node30;
 
        node60.left=node50;
        node60.right=node70;
 
        node10.left=node5;
        node50.right=node55;
        return rootNode;
    }
}


//output

//   Lowest common ancestor for node 5 and 30:
//   20
