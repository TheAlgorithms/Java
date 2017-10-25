public class Test{
  public static void main(String[] args) {
    SinglyLinkedList s1 = new SinglyLinkedList();
    s1.insertHead(2);
    s1.insertHead(3);
    s1.insertHead(4);
    s1.insert_At_Tail(5);
    s1.display();
    s1.Delete_At_pos(2);
    s1.display();
  }
}
