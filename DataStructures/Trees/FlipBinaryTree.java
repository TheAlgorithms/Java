import java.util.Queue; 
import java.util.LinkedList; 
public class FlipTree { 
  
    // method to flip the binary tree  
    public static Node flipBinaryTree(Node root) 
    { 
        if (root == null)  
            return root;  
        if (root.left == null && root.right ==null)  
            return root; 
  
        //  recursively call the same method  
        Node flippedRoot=flipBinaryTree(root.left); 
  
        //  rearranging main root Node after returning  
        // from recursive call  
        root.left.left=root.right; 
        root.left.right=root; 
        root.left=root.right=null; 
        return flippedRoot; 
    } 
  
    // Iterative method to do level order traversal  
    // line by line 
    public static void printLevelOrder(Node root) 
    { 
        // Base Case 
        if(root==null) 
            return ; 
          
        // Create an empty queue for level order traversal  
        Queue<Node> q=new LinkedList<>(); 
        // Enqueue Root and initialize height  
        q.add(root); 
        while(true) 
        { 
            // nodeCount (queue size) indicates number  
            // of nodes at current lelvel.  
            int nodeCount = q.size();  
            if (nodeCount == 0)  
                break; 
              
            // Dequeue all nodes of current level and  
            // Enqueue all nodes of next level  
            while (nodeCount > 0)  
            {  
                Node node = q.remove();  
                System.out.print(node.data+" "); 
                if (node.left != null)  
                    q.add(node.left);  
                if (node.right != null)  
                    q.add(node.right);  
                nodeCount--;  
            }  
            System.out.println(); 
        } 
    } 
  
    public static void main(String args[]) { 
        Node root=new Node(1); 
        root.left=new Node(2); 
        root.right=new Node(1); 
        root.right.left = new Node(4);  
        root.right.right = new Node(5);  
        System.out.println("Level order traversal of given tree"); 
        printLevelOrder(root);  
    
        root = flipBinaryTree(root);  
        System.out.println("Level order traversal of flipped tree"); 
        printLevelOrder(root); 
    } 
} 
  
/* A binary tree node structure */
class Node  
{  
    int data;  
    Node left, right;  
    Node(int data) 
    { 
        this.data=data; 
    } 
}; 