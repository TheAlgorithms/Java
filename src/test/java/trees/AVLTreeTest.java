package trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AVLTreeTest {
    private AVLTree avl;
    
    @BeforeEach
    public void setUp() {
        avl = new AVLTree();
    }
    
    @Test
    public void testInsertAndSearch() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        
        assertTrue(avl.search(50));
        assertTrue(avl.search(30));
        assertTrue(avl.search(70));
        assertFalse(avl.search(100));
    }
    
    @Test
    public void testBalancing() {
        // Insert values in ascending order (would create unbalanced BST)
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        
        // AVL should balance - height should be log(n)
        int height = avl.getHeight();
        int n = 5;
        int maxHeight = (int) (Math.log(n + 1) / Math.log(2)) + 1;
        
        assertTrue(height <= maxHeight);
    }
    
    @Test
    public void testDelete() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(20);
        avl.insert(40);
        
        avl.delete(20);
        assertFalse(avl.search(20));
        
        avl.delete(30);
        assertFalse(avl.search(30));
    }
    
    @Test
    public void testRotations() {
        // Test LL rotation case
        AVLTree ll = new AVLTree();
        ll.insert(30);
        ll.insert(20);
        ll.insert(10);
        assertTrue(ll.search(10) && ll.search(20) && ll.search(30));
        
        // Test RR rotation case
        AVLTree rr = new AVLTree();
        rr.insert(10);
        rr.insert(20);
        rr.insert(30);
        assertTrue(rr.search(10) && rr.search(20) && rr.search(30));
    }
    
    @Test
    public void testFindMinMax() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(20);
        avl.insert(80);
        
        assertEquals(20, avl.findMin());
        assertEquals(80, avl.findMax());
    }
}