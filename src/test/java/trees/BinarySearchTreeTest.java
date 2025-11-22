// File 4: BinarySearchTreeTest.java
package trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Binary Search Tree implementation
 * Reference: https://en.wikipedia.org/wiki/Binary_search_tree
 */
public class BinarySearchTreeTest {
  private BinarySearchTree bst;

  @BeforeEach
  public void setUp() {
    bst = new BinarySearchTree();
  }

  @Test
  public void testInsertAndSearch() {
    bst.insert(50);
    bst.insert(30);
    bst.insert(70);

    assertTrue(bst.search(50));
    assertTrue(bst.search(30));
    assertTrue(bst.search(70));
    assertFalse(bst.search(100));
  }

  @Test
  public void testDuplicateHandling() {
    bst.insert(50);
    bst.insert(50);
    bst.insert(50);

    assertTrue(bst.search(50));
  }

  @Test
  public void testDelete() {
    bst.insert(50);
    bst.insert(30);
    bst.insert(70);
    bst.insert(20);
    bst.insert(40);

    bst.delete(20); // Leaf node
    assertFalse(bst.search(20));

    bst.delete(30); // Node with two children
    assertFalse(bst.search(30));

    bst.delete(50); // Root node
    assertFalse(bst.search(50));
  }

  @Test
  public void testFindMinMax() {
    bst.insert(50);
    bst.insert(30);
    bst.insert(70);
    bst.insert(20);
    bst.insert(80);

    assertEquals(20, bst.findMin());
    assertEquals(80, bst.findMax());
  }

  @Test
  public void testEmptyTreeException() {
    assertThrows(IllegalStateException.class, () -> bst.findMin());
    assertThrows(IllegalStateException.class, () -> bst.findMax());
  }

  @Test
  public void testIsEmpty() {
    assertTrue(bst.isEmpty());

    bst.insert(50);
    assertFalse(bst.isEmpty());

    bst.clear();
    assertTrue(bst.isEmpty());
  }
}