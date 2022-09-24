package com.thealgorithms.datastructures.trees;
import java.util.*;
import com.thealgorithms.datastructures.trees.BinaryTree.Node;
 /******************************************************
  * Approach: 
  * Let's assume 
  * INORDER of the tree is: [40,20,50,10,60,30]
  * POSTORDER of the tree is: [40,50,20,60,30,10]
  * We know that INORDER [left-root-right] and POSTORDER [left-right-root] -->(s1).
  * So from the above statement(s1). We can say that the last element in POSTORDER is tree root node.
  * '10' is the root node of the tree because it is the last element in POSTORDER.
  * We also know that INORDER[left-root-right]. So find root(10) node in INORDER and after that left elements of root node are left nodes and right elements are right nodes of tree.
  * [40,20,50] are the left nodes and [60,30] are right nodes.
  * Now see [40,20,50] elements in POSTORDER--> [40,50,20] 
  * We know that POSTORDER [left-right-root]. So we can say that '20' is root node.
  * Now find left and right nodes of '20' in INORDER [40,20,50]
  * So '40' is left node and '50' is right node.
  * Now for [60,30] see in POSTORDER-->[60,30]. From this we can say that '30' is root node and '60' is left node of '30'.
  * You can see that we are doing same thing again and again [Breaking elements into sub elements]. So you can use recursion here.
  * You can also do this by iteration but for understanding purpose I am using recursion here.
  * TIME COMPLEXITY: O(N)* log(N) [O(N) traversal of the tree, log(N) for hashmap]
  * SPACE COMPLEXITY: O(N)+O(N) [stack space and recursion space]
  
  ************************************************************/
  
  
  

  public class TreeNode {
      int val;
      TreeNode left;
     TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
      Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        TreeNode root=bt(inorder,0,inorder.length-1,postorder,0,postorder.length-1,map);
        return root;
    }
    public TreeNode bt(int[] inorder,int instart,int inend,int[] postorder,int postart,int poend,Map<Integer,Integer> map){
        if(instart>inend || postart>poend)
            return null;
        TreeNode root=new TreeNode(postorder[poend]);
        int inroot=map.get(postorder[poend]);
        int end=inroot-instart;
        root.left=bt(inorder,instart,inroot,postorder,postart,postart+end-1,map);
        root.right=bt(inorder,inroot+1,inend,postorder,postart+end,poend-1,map);
        return root;
    }
}
public class Main{
		public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		int inorder={40,20,50,10,60,30};
		int postorder={40,20,50,60,30,10};
		 Solution obj=new Solution();
		TreeNode root=obj.buildTree(inorder,postorder);
	}
}
