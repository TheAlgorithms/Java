//pakage Linkedlist;

class LLimplementation{

    Node head;
    class Node{
        String data;
        Node next;

        Node(String data){
            this.data=data;
            this.next=null;
        }
    }

    // add first node
    public void addFirst(String data){
        Node NewNode = new Node(data);
        if(head==null){
            head=NewNode;
            return;
        }

        NewNode.next=head;
        head=NewNode;
    }

    // add last node
    public void addLast(String data){
        Node NewNode = new Node(data);
        if(head==null){
            head=NewNode;
            return;
        }
       Node current = head;
       while(current.next != null){
        current = current.next;
       }
       current.next=NewNode;
    }

    // print list
    public void printList(){
        if(head==null){
            System.out.print("List is empty.");
            return;
        }
        
        Node current = head;
        while(current.next != null){
            System.out.print(current.data + " ");
         current = current.next;
        }
    }
    

    public static void main(String[] args) {
        LLimplementation list = new LLimplementation();
        list.addFirst("a");
        list.addFirst("b");
        list.addFirst("c");

        list.printList();
    }
}