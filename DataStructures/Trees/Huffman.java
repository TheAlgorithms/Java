import java.util.Map;
import java.util.Stack;

/**
 * Create a huffman tree and put the chars' huffman code in a map
 *
 */
public class Huffman {
  /**
   * Create a huffman tree from a priority queue whose element is a huffman tree node
   * @param heap  the priority
   * @return the root of the huffman tree
   */
  public static TreeNode huffmanTree(MinHeap<TreeNode> heap) {
    TreeNode result = null;
    if (! heap.isEmpty()) {
      if (heap.elementNumber() == 1) {
        result = heap.deleteTop();
      } else {
        TreeNode parent;
        while (heap.elementNumber() > 1) {
          //Take two element from the priority queue and link them a tree node
          parent = new TreeNode().combineToNode(heap.deleteTop(), heap.deleteTop());
          //insert the node to the queue
          heap.Insert(parent);
        }
        result = heap.deleteTop();
      }
    }
    
    return result;
  }
  
  /**
   * Traverse a huffman tree and put each char' huffman into a map
   * @param tr  the root of the huffman tree
   * @param stack  a stack to save a code char when traverse the tree.(when pass by the right branch push
   *               1 into the stack and when left push 0, when go back, pop the top element of the stack)
   * @param result a map to save the chars' huffamn code.For each node, the key is a char and the value is
   *              the chars' huffman code. 
   */
  public static void encode(TreeNode tr, Stack<Integer> stack, Map<Character, String> result) {
    if (tr != null) {
      if (tr.isChar()) {
        StringBuilder code = new StringBuilder();
        for (Integer ele : stack) {
          code.append(ele);
        }
       result.put(tr.getChar(), code.toString());
      }else {
        stack.push(0);
        encode(tr.getLeftChild(), stack, result);
        stack.pop();
        stack.push(1);
        encode(tr.getRightChild(), stack, result);
        stack.pop();
      }
    }
  }
}