import java.util.*;

class CollectionList{
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();

        list.addFirst("a");
        list.addFirst("b");
        System.out.println(list);

        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        System.out.println(list);

        for(int i = 0; i<list.size();i++){
            System.out.print(list.get(i) + "->");
        }
        System.out.println("null");

        //list.removeFirst();
       // System.out.println(list);
        //list.removeLast();
        //System.out.println(list);

        list.remove(3);
        System.out.println(list);    
    }
}