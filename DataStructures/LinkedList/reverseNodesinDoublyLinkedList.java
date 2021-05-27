package LinkedList;

public class reverseNodesinDoublyLinkedList {
    class node{
        int data;
        node prev;
        node next;
        node(int data){
            this.data=data;
        }
    }
        node head;
        Boolean isEmpty(){
            return head==null;
        }
        void add(int data)
        {
            node toAdd=new node(data);
            if(isEmpty()){
                head=toAdd;
            }
            else
            {
                node temp=head;
                while(temp.next!=null){
                    temp=temp.next;
                }
                temp.next=toAdd;
                toAdd.prev=temp;
                toAdd.next=null;
            }
        }
        void reverseNodes()
        {
            if(isEmpty()){
                System.err.println("Empty list cannot be reversed");
            }
            else if(head.next==null){
                return ;
            }
            else
            {
                node temp=head;
                node val=null;
                while(temp!=null){
                    
                    val=temp.prev;
                    temp.prev=temp.next;
                    temp.next=val;
                    temp=temp.next;
                }
               
            }
        }
        void print(){
           if(isEmpty()){
               System.err.println("Empty list");;
           }
           else
           {
               node temp=head;
               while(temp.prev!=null)
               {
                 System.out.print(temp.data+" ");
                 temp=temp.prev;
               }
           }
        }
    
    public static void main(String s[]){
       reverseNodesinDoublyLinkedList obj=new reverseNodesinDoublyLinkedList();
       obj.add(12);
       obj.add(13);
       obj.add(14);
       obj.add(15);
       obj.print();
       System.out.println();
       obj.reverseNodes();
       obj.print();

    }
}
