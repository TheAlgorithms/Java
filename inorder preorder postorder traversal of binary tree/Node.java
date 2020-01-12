public class Node{
  private char data;
  protected Node left;
  protected Node right;
  
  public Node(char data){
    this.data = data;
  }
  
  public char getData(){
    return this.data;
  }
}