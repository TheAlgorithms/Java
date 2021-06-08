package LinkedList;


public class SearchLinkedList {
    class node{
        int data;
        node next;
        node(int data){
           this.data=data;
        }
    }    
    node head;
    boolean isEmpty(){
        return head==null;
    }
    int search(int element){
        if(isEmpty()){
            return -1;
        }
        int count=0;
        
         
            node temp=head;
            while(temp.next!=null){
                if(temp.data==element){
                    count++;
                    break;
                }
                temp=temp.next;
                count++;
            }
           
            if(count==0){
                return -1;
            } 
        return count;
       
    }
    void add(int data){
        node toAdd=new node(data);
        if(head==null){
           head=toAdd;
        }
        else
        {    node temp=head;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=toAdd;
            toAdd.next=null;
        }
    }
    public static void main(String sp[]){
        SearchLinkedList obj=new SearchLinkedList();
        obj.add(12);
        obj.add(13);
        obj.add(14);
        obj.add(15);
        int pos=obj.search(14);
        if(pos==-1){
            System.out.println("The list is either empty or it doesnt contains the entered element");

        }
        else
        {
            System.out.println("The position of the element "+pos);
        }
    }
}
