
/**
 * 一个树节点类，关键字为一个字符及它的出现概率。
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
   * 将两个节点(或者说两棵树)连在同一个节点上。
   * @param n1 一个树节点(也可能代表一个树根)
   * @param n2 一个树节点(也可能代表一个树根)
   * @return  两棵树的父亲节点
   */
  public TreeNode combineToNode(TreeNode n1, TreeNode n2) {
    this.key = 0; //因为这样一个节点肯定不会表示一个字符，因此key设置为空字符
    this.probablity = n1.probablity + n2.probablity;
    this.leftnode = n1;
    this.rightnode = n2;
    
    return this;
  }
  
  //判断这个节点是不是代表一个字符
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
