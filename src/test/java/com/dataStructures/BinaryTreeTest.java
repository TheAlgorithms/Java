package src.main.java.com.dataStructures;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RICARDO
 */
public class BinaryTreeTest {
    
    public BinaryTreeTest() {
    }
    
    /**
     * Test of insert method, of class BinaryTree.
     */
    @Test
    public void testInsert_BinaryTree() {
        System.out.println("insert");
        BinaryTree<String> lowerdata = new BinaryTree<>("1");
        BinaryTree<String> upperdata = new BinaryTree<>("3");
        BinaryTree<String> instance = new BinaryTree<>("2");
        instance.insert(lowerdata);
        instance.insert(upperdata);
        String proof = instance.getLeft().toString()+instance.toString()+instance.getRight().toString();
        System.out.println(proof);
        assertEquals("123", proof);
    }

    /**
     * Test of search method, of class BinaryTree.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        BinaryTree<Integer> instance = new BinaryTree<>(5);
        for (int i = 1; i < 10; i++) {
            instance.insert(new Integer(i));
        }
        BinaryTree result = instance.search(new Integer(1));
        assertEquals(new Integer(1), result.getData());
    }

    /**
     * Test of contains method, of class BinaryTree.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        BinaryTree<Integer> instance = new BinaryTree<>(5);
        for (int i = 1; i < 10; i++) {
            instance.insert(i);
        }
        
        boolean result = instance.contains(2)&&instance.contains(11);
        assertEquals(false, result);
    }

}
