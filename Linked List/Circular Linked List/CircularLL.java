public class CircularLL {  
    //Represents the node of list.  
    public class Node{  
        int data;  
        Node next;  
        public Node(int data) {  
            this.data = data;  
        }  
    }  
  
    //Declaring head and tail pointer as null.  
    public Node head = null;  
    public Node tail = null;  
  
    //This function will add the new node at the end of the list.  
    public void add(int data){  
        //Create new node  
        Node newNode = new Node(data);  
        //Checks if the list is empty.  
        if(head == null) {  
             //If list is empty, both head and tail would point to new node.  
            head = newNode;  
            tail = newNode;  
            newNode.next = head;  
        }  
        else {  
            //tail will point to new node.  
            tail.next = newNode;  
            //New node will become new tail.  
            tail = newNode;  
            //Since, it is circular linked list tail will point to head.  
            tail.next = head;  
        }  
    } 
    public void InsetInd(int data, int pos){
        Node newNode = new Node(data);
        if(head == null){
            newNode=head;
            return;
        }

        Node temp,curr;
        temp=head;
        curr=null;

        for(int i=0;i<pos;i++){
            curr = temp;
            temp = temp.next;
        }

        curr.next = newNode;
        newNode.next = temp; 
    }
    //Displays all the nodes in the list  
    public void display() {  
        Node current = head;  
        if(head == null) {  
            System.out.println("List is empty");  
        }  
        else {  
            System.out.println("Nodes of the circular linked list: ");  
             do{  
                //Prints each node by incrementing pointer.  
                System.out.print(" "+ current.data);  
                current = current.next;  
            }while(current != head);  
            System.out.println();  
        }  
    }  
  
    public static void main(String[] args) {  
        CircularLL cl = new CircularLL();  
        //Adds data to the list  
        cl.add(1);  
        cl.add(2);  
        cl.add(3);  
        cl.add(4);  
        //Displays all the nodes present in the list  
        cl.display();  

        cl.InsetInd(9,2);
        cl.InsetInd(15,5);
        cl.display();
    }  
}  