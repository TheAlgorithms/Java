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
// add at position
    public void InsetInd(String data, int pos){
        Node NewNode = new Node(data);
        if(head == null){
            NewNode=head;
            return;
        }

        Node temp,curr;
        temp=head;
        curr=null;

        for(int i=0;i<pos;i++){
            curr = temp;
            temp = temp.next;
        }

        curr.next = NewNode;
        NewNode.next = temp;
    }

    // print list
    public void printList(){
        if(head==null){
            System.out.print("List is empty.");
            return;
        }
        
        Node current = head;
        while(current != null){
            System.out.print(current.data + "->");
         current = current.next;
        }
    }

    //delete first

    public void DeleteFirst(){
        if(head == null){
            System.out.print("List is empty.");
            return;
        }
        head=head.next;
    }
    
    //delete last

    public void DeleteLast(){
        if(head == null){
            System.out.print("List is empty.");
            return;
        }

        Node secondLast = head;
        Node lastNode = head.next;
        while(lastNode.next != null){
            lastNode = lastNode.next;
            secondLast = secondLast.next;
        }

        secondLast=null;
        //System.out.println("\n");
    }
    

    public static void main(String[] args) {
        LLimplementation list = new LLimplementation();
        list.addFirst("a");
        list.addFirst("b");
        list.addFirst("c");

        list.printList();

        list.addLast("2");
        list.addLast("3");
        list.addLast("4");

        list.printList();

        //list.DeleteFirst();
        //list.printList();

        //list.DeleteLast();
        //list.printList();

        list.InsetInd("Hello",1);

        list.printList();
    }
}