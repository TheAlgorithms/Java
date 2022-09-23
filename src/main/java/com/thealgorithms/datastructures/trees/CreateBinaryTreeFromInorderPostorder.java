import java.util.*;
/*
  
   Following is the TreeNode class structure
   
   class TreeNode<T> { 
        T data; 
        TreeNode<T> left;
        TreeNode<T> right;
  
        TreeNode(T data) { 
            this.data = data; 
            left = null; 
            right = null; 
        }
   }
    
 */
 /*
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
  
 */
  
  
  

public class Solution {
	public static TreeNode<Integer> getTreeFromPostorderAndInorder(int[] postOrder, int[] inOrder) {
		Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inOrder.length;i++){
            map.put(inOrder[i],i);
        }
        TreeNode<Integer> root=bt(inOrder,0,inOrder.length-1,postOrder,0,postOrder.length-1,map);
        return root;
	}
    public static TreeNode<Integer> bt(int[] inOrder,int instart,int inend,int[] postOrder,int postart,int poend,Map<Integer,Integer> map){
        if(postart>poend || instart>inend)
            return null;
        TreeNode<Integer> root=new TreeNode<Integer>(postOrder[poend]);
        int inroot=map.get(postOrder[poend]);
        int end=inroot-instart;
        root.left=bt(inOrder,instart,inroot-1,postOrder,postart,postart+end-1,map);
        root.right=bt(inOrder,inroot+1,inend,postOrder,postart+end,poend-1,map);
        return root;
    }
}
