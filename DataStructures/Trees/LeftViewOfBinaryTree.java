import java.util.*;

class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data = data;
        left=null;
        right=null;
    }
}

public class LeftView {

	static ArrayList<Integer> getLeftView(TreeNode root){
		
		Queue<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        
        if(root==null) return ans;
        
        q.add(root);
        
        while(!q.isEmpty()){
            int count = q.size();
            TreeNode leftmost = q.element();
            ans.add(leftmost.data);
            
            while(count-- > 0){
            	TreeNode temp = q.remove();
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
            }
        }
        return ans;
		
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.right = new TreeNode(3);
	    root.left.left = new TreeNode(4);
	    root.left.right = new TreeNode(5);
	    root.right.left = new TreeNode(6);
	    root.right.right = new TreeNode(7);
	    root.right.left.right = new TreeNode(8);
	    
	    /* 
	     The binary tree is:
	     				1
	     			  /   \
	      			 2     3
	      		    / \   / \
	      		   4   5 6   7
	      		        /
	     			   8
	      */
	    
	    ArrayList<Integer> leftView = getLeftView(root);
	    for(int i: leftView) {
	    	System.out.print(i + " ");
	    }
	}

}
