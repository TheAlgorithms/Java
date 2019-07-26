
/**
 * A class representing the node of a huffman tree.The keys of the node is a
 * character and it's frequency.
 *
 */
public class TreeNode implements Comparable<TreeNode>{
  private char key;
  private double probablity;
  private TreeNode leftnode;
  private TreeNode rightnode;
  
  public TreeNode(char c, double p) {
    this.key = c;
    this.probablity = p;
    this.leftnode = null;
    this.rightnode = null;
  }

  public TreeNode() {}

  /**
   * link two nodes(or two roots of a huffman tree) to a node.
   * @param n1 a huffman tree node(or a root of a huffman tree)
   * @param n2 a huffman tree node(or a root of a huffman tree)
   * @return  the parent node of two params.
   */
  public TreeNode combineToNode(TreeNode n1, TreeNode n2) {
    this.key = 0; //key need to set as blanck cuz one node can't indicate one character
    this.probablity = n1.probablity + n2.probablity;
    this.leftnode = n1;
    this.rightnode = n2;
    
    return this;
  }
  
  //Determines whether this node represents a character
  public boolean isChar() {
    return this.key != 0;
  }
  
  public char getChar() {
    return this.key;
  }
  
  public TreeNode getLeftChild() {
    return this.leftnode;
  }
  
  public TreeNode getRightChild() {
    return this.rightnode;
  }
  
  @Override
  public int compareTo(TreeNode o) {
    int result;
    if (this.probablity > o.probablity) {
      result = 1;
    }else {
      result = 0;
    }
    return result;
  }
}
