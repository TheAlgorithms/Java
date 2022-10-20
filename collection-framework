package javacollectionsframeworkexamples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.ListIterator;


/**
 *
 * @author Laszlo Harri Nemeth
 */
public class JavaCollectionsFrameworkExamples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayListVsLinkedList();
        HashMapLinkedHashMapTreeMapTest();
        SetsTest();
        QueueTest();
        IteratorTest();
    }
    
    private static final void ArrayListVsLinkedList() {
        List<Integer> arrayList = new ArrayList();
        List<Integer> linkedList = new LinkedList();
        System.out.println("-------------------------------------------");
        System.out.println("Comparison of LinkedList and ArrayList performance");
        System.out.println("-------------------------------------------");
        doTimings("ArrayList", arrayList);
        doTimings("LinkedList", linkedList);
        System.out.println("-------------------------------------------");
    }
    
    private static void doTimings(String type, List<Integer> list) {
        for (int i = 0; i < 1E5; i++)
            list.add(i);
        
        long start = System.currentTimeMillis();
        
        // add items to the end of the list
        for (int i = 0; i < 1E5; i++)
            list.add(i);
        
        long end = System.currentTimeMillis();
        
        System.out.println("Time taken for adding items to the end: " + (end - start) + "ms for " + type);
    
        /* --------------------------------------------------------------*/
        
        list.clear();
        for (int i = 0; i < 1E5; i++)
            list.add(i);
        
        start = System.currentTimeMillis();
        
        // add items to the beginning of the list
        for (int i = 0; i < 1E5; i++)
            list.add(0, i);
        
        end = System.currentTimeMillis();
        
        System.out.println("Time taken for adding items to the beginning: " + (end - start) + "ms for " + type);

        /* --------------------------------------------------------------*/
        
        list.clear();
        for (int i = 0; i < 1E5; i++)
            list.add(i);
        
        start = System.currentTimeMillis();
        
        // add items to the beginning of the list
        for (int i = 0; i < 1E5; i++)
            list.add(list.size()-100, i);
        
        end = System.currentTimeMillis();
        
        System.out.println("Time taken for adding items near the end: " + (end - start) + "ms for " + type);

    }
    
    private static void HashMapLinkedHashMapTreeMapTest() {
        Map<Integer, String> hashMap = new HashMap<Integer, String>();
        Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
        Map<Integer, String> treeMap = new TreeMap<Integer, String>();
        
        // order not guaranteed
        System.out.println("-------------------------------------------");
        System.out.println("HashMap: order not guaranteed");
        testMap(hashMap);
        System.out.println("-------------------------------------------");
        System.out.println("LinkedHashMap: insertion order");
        testMap(linkedHashMap);
        System.out.println("-------------------------------------------");
        System.out.println("TreeMap: natural order");
        testMap(treeMap);
        System.out.println("-------------------------------------------");
    }
    
    private static void testMap(Map<Integer, String> map) {
        map.put(9, "Fox");
        map.put(4, "Cat");
        map.put(8, "Dog");
        map.put(1, "Giraffe");
        map.put(0, "Swan");
        map.put(15, "Bear");
        map.put(6, "Snake");
        
        for (Integer key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
    
    private static void SetsTest() {
        System.out.println("HashSet does not retain order");
        Set<String> set = new HashSet<String>();
        set.add("dog");
        set.add("cat");
        set.add("mouse");
        set.add("snake");
        set.add("bear");
        // add duplicate item doesn't add
        set.add("mouse");
        System.out.println(set);
        System.out.println("-------------------------------------------");

        System.out.println("LinkedHashSet remembers the order of insertion");
        Set<String> linkedHashSet = new LinkedHashSet<String>();
        linkedHashSet.add("dog");
        linkedHashSet.add("cat");
        linkedHashSet.add("mouse");
        linkedHashSet.add("snake");
        linkedHashSet.add("bear");
        // add duplicate item doesn't add
        linkedHashSet.add("mouse");
        System.out.println(linkedHashSet);
        System.out.println("-------------------------------------------");
        
        System.out.println("TreeSet sorts in natural order");
        Set<String> treeSet = new TreeSet<String>();
        treeSet.add("dog");
        treeSet.add("cat");
        treeSet.add("mouse");
        treeSet.add("snake");
        treeSet.add("bear");
        // add duplicate item doesn't add
        treeSet.add("mouse");
        System.out.println(treeSet);
        System.out.println("-------------------------------------------");
        
        System.out.println("Intersection:");
        Set<String> treeSet2 = new TreeSet<String>();
        // some common elements and some new elements
        treeSet2.add("dog");
        treeSet2.add("cat");
        treeSet2.add("giraffe");
        treeSet2.add("monkey");
        treeSet2.add("ant");
        System.out.println("Set1: " + treeSet);
        System.out.println("Set2: " + treeSet2);
        // copy treeSet
        Set<String> intersectionHashSet = new HashSet<String>(treeSet);
        // keep only the elements which are treeSet2 - therefore this will be the intersection
        intersectionHashSet.retainAll(treeSet2);
        System.out.println("Intersection: " + intersectionHashSet);
        System.out.println("-------------------------------------------");

        System.out.println("Difference:");
        System.out.println("Set1: " + treeSet);
        System.out.println("Set2: " + treeSet2);
        // copy treeSet
        Set<String> differenceHashSet = new HashSet<String>(treeSet);
        // remove all those elements which are in treeSet2. So the difference will be in differenceHashSet.
        differenceHashSet.removeAll(treeSet2);
        System.out.println("Difference (Set1 \\ Set2): " + differenceHashSet);
        System.out.println("-------------------------------------------");

    }
    
    private static void QueueTest() {
        Queue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(3);
        try {
            arrayBlockingQueue.add(10);
            arrayBlockingQueue.add(20);
            arrayBlockingQueue.add(30);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        
        System.out.println(arrayBlockingQueue);
        Integer val = arrayBlockingQueue.remove();
        System.out.println(val);
        System.out.println(arrayBlockingQueue);
        val = arrayBlockingQueue.remove();
        System.out.println(val);
        System.out.println(arrayBlockingQueue);
        val = arrayBlockingQueue.remove();
        System.out.println(val);
    }

    private static void IteratorTest() {
        LinkedList<String> animals = new LinkedList<String>();
        animals.add("fox");
        animals.add("meerkat");
        animals.add("guinea pig");
        animals.add("rabbit");
        animals.add("turtle");
        
        System.out.println("\"Old\", classical way of iteration, removing \"guinea pig\":");
        System.out.println("-------------------------------------------");
        Iterator<String> it = animals.iterator();
        while (it.hasNext()) {
            String animal = it.next();
            System.out.println(animal);
            if (animal.equals("guinea pig")) {
                it.remove();
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println("Modern way of iteration (>=Java5) - cannot remove element during iteration:");
        System.out.println("(animals.remove() would throw an exception)");
        System.out.println("-------------------------------------------");
        for (String animal : animals)  {
            System.out.println(animal);
        }
        System.out.println("-------------------------------------------");
        System.out.println("Adding items to a list during iteration: ListIterator");
        System.out.println("-------------------------------------------");
        ListIterator<String> lit = animals.listIterator();
        while (lit.hasNext()) {
            String animal = lit.next();
            System.out.println(animal);
            if (animal.equals("turtle")) {
                lit.add("giraffe");
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println("After adding \"giraffe\"");
        System.out.println("-------------------------------------------");
        lit = animals.listIterator();
        while (lit.hasNext()) {
            System.out.println(lit.next());
        }
        System.out.println("-------------------------------------------");
    }
}
