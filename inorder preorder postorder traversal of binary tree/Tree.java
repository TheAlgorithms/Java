//import java.util.Stack;
//import java.util.LinkedList;

public class Tree{
  private Node root = null;
  private static int level = 0;
  
  public void insert(char data){
    if(root == null){
      root = new Node(data);
      return;
    }
    insert(root, new Node(data));
  }
  
  private static void insert(Node node, Node newNode){
    if(node.getData() <= newNode.getData()){
      if(node.right == null) node.right = newNode;
      else insert(node.right,newNode);
    }
    else {
      if(node.left == null) node.left = newNode;
      else insert(node.left,newNode);
    }
  }
  
  public int search(char value){
    System.out.println();
    if(root == null){
      System.out.println("Error, Tree is empty!!");
      return -1;
    }
    
    if(root.getData() == value){
      System.out.println(value+" is foun at level : "+level);
      return searchLevel(root.right,value);
    }
    else{
      if(root.getData() < value) return searchLevel(root.right,value);
      else return searchLevel(root.left,value);
    }
  }
  private static int searchLevel(Node node, char value){
    level++;
    while(node != null){
      if(node.getData() == value) {
        int currLvl = level;
        System.out.println(value+" is found at level : "+currLvl);
        return searchLevel(node.right,value);
      }
      else{
        if(node.getData() < value) return searchLevel(node.right,value);
        else return searchLevel(node.left,value);
      }
    }
    return -1;
  }
  
  public void printPostOrder(){
    if(root == null){
      System.out.println("Error, tree is empty!!");
      return;
    }
    System.out.println("\n*** Print postOrder ***");
    printPostOrder(root);
    System.out.println();
  }
  private static void printPostOrder(Node node){
    if(node == null) return;

    printPostOrder(node.left);
    printPostOrder(node.right);
    System.out.print(node.getData()+" ");
  }
  
  public void printInOrder(){
    if(root == null){
      System.out.println("Error, tree is empty!!");
      return;
    }
    System.out.println("\n*** Print intOrder ***");
    printInOrder(root);
    System.out.println();
  }
  private static void printInOrder(Node root){
    if(root == null) return;

    printInOrder(root.left);
    System.out.print(root.getData()+" ");
    printInOrder(root.right);
  }

  public void printPreOrder(){
    if(root == null){
      System.out.println("Error, tree is empty!!");
      return;
    }
    System.out.println("\n*** Print preOrder ***");
    printPreOrder(root);
    System.out.println();
  }
  private static void printPreOrder(Node root){
    if(root == null) return;

    System.out.print(root.getData()+" ");
    printPreOrder(root.left);
    printPreOrder(root.right);
  }

  
 /* public void DFS(char data){
    if(root == null) return;
    
    Stack<Node> s1 = new Stack<Node>();
    LinkedList<Node> l1 = new LinkedList<Node>();
     
    s1.push(root);
     Node temp = pop();
    s1.push(temp.right);
    s1.push(temp.left);
    
  }*/
}