// Definition for singly-linked list.
public class Node {
    int val;
    Node next;
    Node(int x) {
        val = x;
        next = null;
    }
 }
 
public class Main {
    public Node getIntersectionNode(Node headA, Node headB) {
        while(headB!=null){
            Node temp= headA;
            while (temp!=null){
                if(temp==headB)
                    return headB;
                temp=temp.next;
            }
            headB=headB.next;
        }
        return null;
    }
}
