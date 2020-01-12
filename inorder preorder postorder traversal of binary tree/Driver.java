public class Driver{
  public static void main(String[] args){
    Tree nt = new Tree();
    
    nt.insert('N');
    nt.insert('O');
    nt.insert('N');
    nt.insert('L');
    nt.insert('I');
    nt.insert('N');
    nt.insert('E');
    nt.insert('A');
    nt.insert('R');
    nt.insert('D');
    nt.insert('A');
    nt.insert('T');
    nt.insert('A');
    nt.insert('T');
    nt.insert('Y');
    nt.insert('P');
    nt.insert('E');
    
    nt.printPostOrder();
    nt.printInOrder();
    nt.printPreOrder();
    
    nt.search('E');
    
  }
}