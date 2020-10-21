/* For detecting cycle in linked list we'll use Floyd's tortoise and hare algorithm. In this algorithm
we set two pointers one slow that is current node and other fast pointer which is two pointers ahead of 
slow pointer. We compare fast and slow pointer if they are same then there is a cycle and return true else
return false.
*/
public class cycleDetection {
    int data;
    cycleDetection next = null;
    public cycleDetection(int d){
        this.data = d;
    }
    public static cycleDetection insert(cycleDetection head, cycleDetection tmp){
        cycleDetection copy = head;
        while(copy.next != null){
            copy = copy.next;
        }
        copy.next = tmp;
        return head;
    }

    public boolean detectCycle(){
        if(this == null){
            return false;
        }
        cycleDetection slow = this;
        cycleDetection fast = this.next;
        while(fast.next.next != null){
            if(slow == fast){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public static void main(String[] args) {
        cycleDetection head = new cycleDetection(1);
        for (int i = 2; i <= 7; i++) {
            cycleDetection tmp = new cycleDetection(i);
            head = insert(head, tmp);
        }
        /*while (head.next != null) {
            System.out.println(head.data);
            head = head.next;
        }*/
        /*How it looks like now:
            1->2->3->4->5->6->null
        
        */
        cycleDetection copy = head;
        cycleDetection randomNode = copy.next.next.next.next;
        cycleDetection endNode = copy.next.next.next.next.next.next;
        endNode.next = randomNode;
        /*Now list looks like this
        1->2->3->4->5->6-
                 ^      |
                 |- - - -
        */
        System.out.println(head.detectCycle());
    }
}
