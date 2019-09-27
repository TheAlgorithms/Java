package DataStructures.Trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AVLTreeTest {
    AVLTreeTest() {}

    @Test
    void testInsertAVLTree() {
        // Test Condition Converge

        AVLTree tree = new AVLTree();
        // A=T
        tree.insert(2);
        // A=F, B=T
        tree.insert(2);
        // A=F, B=F, C=T, D=T
        tree.insert(1);
        // A=F, B=F, C=T, D=F
        tree.insert(3);
        // A=F, B=F, C=F
        tree.insert(3);

        String result = tree.printBalance();
        Asserts.assertEquals("1 2 3 3 ", result);
    }
}