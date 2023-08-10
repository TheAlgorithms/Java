public class ReverseUsingRecursion {
    private class Node{
        private Node next;
        private int data;
        Node(int data){
        this.data = data;
        }

    }
    private Node head;
    private Node tail;
    public void add(int data){
if(head == null){
    head = new Node(data);
    tail = head;
    return;
}
tail.next = new Node(data);
tail = tail.next;

    }
    public void display(){
         Node node = head;
         System.out.print("START->");
         while(node != null){
            System.out.print(node.data + " ->");
            node = node.next;
         }
         System.out.print("END");
    }
    public void reverse(){
          tail = reverse(head);
          tail.next = null;
    }
    private Node reverse(Node node){
        if(node.next == null){
            head = node;
            return node;
        }
        reverse(node.next).next = node;
        return node;
    }

}