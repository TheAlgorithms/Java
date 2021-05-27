package LinkedList;

public class deleteFromLast {
    class node{
        int data;
        node next;
        node(int data){
            this.data=data;
        }
    }
    node head;
    void add(int data){
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
            toAdd.next=null;
        }
    }
    Boolean isEmpty(){
        return head==null;
    }
    void print(){
        node temp=head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
    }
    void insertEnd(int data){
        node toAdd=new node(data);
        
        if(head.next==null){
            head.next=toAdd;
            toAdd.next=null;
        }
        else
        {
            node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=toAdd;
        toAdd.next=null;
        }
}
void deleteFromlast(){
    if(head==null){
      head=null;
    }
    else if(head.next==null){
       head=null;
    }
    else
    {
       node temp=head;
       while(temp.next.next!=null){
         temp=temp.next;
       }
       temp.next=null;
    }

}
public static void main(String sp[]){
    deleteFromLast obj=new deleteFromLast();
    obj.add(10);
    obj.add(11);
    obj.add(12);
    obj.print();
    System.out.println();
    obj.deleteFromlast();
    obj.print();
}
}
