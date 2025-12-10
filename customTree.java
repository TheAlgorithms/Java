import java.util.*;
class TREE{
   static Node root;

   public TREE(){ // constructor initializes the root 
      this.root=null;
    }
   
   static  class Node{  //Node class
      Node left;
      Node right;
      int data;
      int height;

      public Node(int data){
           this.data=data;
	   this.left=null;
	   this.right=null;
	   this.height=1;
      }
   }

   public static int height(Node n){
      return (n==null)? 0:n.height;
   }

   private static int bf(Node n){ // BALANCE FACTOR
       return height(n.left)-height(n.right);
   }
   
   public static void insert(int data){
        root=insert(data,root);
   }
   
   private static Node insert(int data,Node root){
        if(root==null){
            return new Node(data);
	}
	if(root.data <= data){
           root.right=insert(data,root.right);
	}
	else{
           root.left=insert(data,root.left);
	}

	root.height=Math.max(height(root.right),height(root.left))+1;

	return rotate(root);
   }

   private static Node rotate(Node root){
       if(bf(root)>1){ //LEFT-HEAVY TREE
           if(bf(root.left)>0){
              //LEFT-LEFT
	      return rightrotate(root);
	   }
	   else{
              //LEFT-RIGHT
	      root.left=leftrotate(root.left);
	      return rightrotate(root);
	   }
       }
       else if(bf(root)<-1){ //RIGHT-HEAVY TREE
           if(bf(root.right)<0){
               //RIGHT-RIGHT
	       return leftrotate(root);
	   }
	   else{
              //RIGHT-LEFT
	      root.right=rightrotate(root.right);
	      return leftrotate(root);
	   }
       }
       return root;
   }

   private static Node rightrotate(Node p){   //RIGHT-ROTATION
       Node c=p.left;
       Node t=c.right;
       c.right=p;
       p.left=t;
       
       p.height=Math.max(height(p.right),height(p.left))+1;
       c.height=Math.max(height(c.right),height(c.left))+1;
       return c;
   }
   private static Node leftrotate(Node p){    //LEFT-ROTATION
      Node c=p.right;
      Node t=c.left;
      c.left=p;
      p.right=t;

      p.height=Math.max(height(p.right),height(p.left))+1;
      c.height=Math.max(height(c.right),height(c.left))+1;
      return c;
   }

   public static void inorder(Node root){   //INORDER TRAVERSAL
       if(root==null) return;

       inorder(root.left);
       System.out.print(root.data+" ");
       inorder(root.right);
   }

   public  static void preorder(Node root){  //PREORDER TRAVERSAL
       if(root==null) return;

       System.out.print(root.data+" ");
       preorder(root.left);
       preorder(root.right);
   }

   public static void postorder(Node root){  //POSTORDER TRAVERSAL
       if(root==null) return;

       postorder(root.left);
       postorder(root.right);
       System.out.print(root.data+" ");
   }
   
   public static void Levelorder(Node root){  //LEVELORDER TRAVERSAL
       if(root==null) return;

       Queue<Node> q=new LinkedList<>();
       q.offer(root);
       q.offer(null);

       while(!q.isEmpty()){
          Node curr=q.poll();
	  if(curr==null){
             System.out.println();
	     
	     if(q.isEmpty()) break;
	     else{
                q.offer(null);
	     }
	  }
	  else{
	     System.out.print(curr.data+" ");

	   if(curr.left!=null){
             q.offer(curr.left);
	   }
	   if(curr.right!=null){
             q.offer(curr.right);
	   }
	  }
       }
   }
}

class customTree{
  public static void main(String[] args){
      TREE tree=new TREE();
      tree.insert(2);
      tree.insert(3);
      tree.insert(1);
      tree.insert(5);
      tree.insert(10);
      tree.insert(6);
      tree.insert(11);
      
      tree.inorder(tree.root);
      System.out.println();
      
      tree.preorder(tree.root);
      System.out.println();
      
      tree.postorder(tree.root);
      System.out.println();

      tree.Levelorder(tree.root);
  }
}

