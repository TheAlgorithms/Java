class ImplementDLL{
    TheNode head;
    private class TheNode {
        int data;
        TheNode next;
        TheNode prev;

        TheNode(int data){
            this.data=data;
            this.next=null;
            this.prev=null;
        }
        
    }
// add first
    public void addFirst(int data){
        TheNode nn = new TheNode(data);
        if(head==null){
            head = nn;
            return;
        }

        nn.next = head;
        head.prev = nn;
        head = nn;
    }
// add last
   /*  public void addLast(int data){
        TheNode nn = new TheNode(data);
        nn.next = null;
        if(head==null){
            head = nn;
            return;
        }
        TheNode current = head;
        while(current != null){
            current = current.next;
        }
        
        current.next = nn;
        nn.prev = current;
        current = nn;
        
    } */

    //find size
    static int listSize(){
        int size = 0;
        TheNode current = head;
        while(current != null){
            size++;
            current = current.next;
           
        }
        return size;
    }
// print list
    public void printList(){
        if(head==null){
            System.out.print("List is Empty");
            return;
        }
        TheNode current = head;
        while(current != null){
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        ImplementDLL dl = new ImplementDLL();
        dl.addFirst(2);
        dl.addFirst(3);
        dl.addFirst(8);
        dl.addFirst(1);

        dl.printList();

        //dl.addLast(10);
        //dl.printList();

        System.out.print(listSize(current));
    }

}