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

    // ALL-DU paths test
    @Test
    void testInsertAVLTree_FirstPath() {
        // variable "n"
        AVLTree tree = new AVLTree();
        tree.insert(2);

        tree.insert(2);

        String result = tree.toString();
        Asserts.assertEquals("2 ", result);
    }

    @Test
    void testInsertAVLTree_SecondPath() {
        // variable "n"
        AVLTree tree = new AVLTree();
        tree.insert(2);

        tree.insert(3);

        String result = tree.toString();
        Asserts.assertEquals("2 3 ", result);
    }

    @Test
    void testInsertAVLTree_ThirdPath() {
        // variable "parent"
        AVLTree tree = new AVLTree();
        tree.insert(2);

        tree.insert(1);

        String result = tree.toString();
        Asserts.assertEquals("1 2 ", result);
    }

    @Test
    void testInsertAVLTree_FourthPath() {
        // variable "parent"
        AVLTree tree = new AVLTree();
        tree.insert(2);

        tree.insert(3);

        String result = tree.toString();
        Asserts.assertEquals("2 3 ", result);
    }

    @Test
    void testInsertAVLTree_FifthPath() {
        // variable "goLeft"
        AVLTree tree = new AVLTree();
        tree.insert(2);

        tree.insert(4);

        String result = tree.toString();
        Asserts.assertEquals("2 4 ", result);
    }
}