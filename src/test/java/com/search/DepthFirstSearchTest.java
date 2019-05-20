package src.test.java.com.search;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.search.DepthFirstSearch;

public class DepthFirstSearchTest {
    @Test
    public void testDepthFirstSearch() {

        BinaryTree<Integer> tree1 = new BinaryTree<>();
        tree1.add(1,1);
        tree1.add(2,2);
        tree1.add(3,3);
        tree1.add(4,4);
        Assert.assertEquals("Incorrect index", 3,    DepthFirstSearch.find(3, tree1));
        Assert.assertEquals("Incorrect index", 1,    DepthFirstSearch.find(1, tree1));
        Assert.assertEquals("Incorrect index", null, DepthFirstSearch.find(0, tree1));
        Assert.assertEquals("Incorrect index", null, DepthFirstSearch.find(8, tree1));
        Assert.assertEquals("Incorrect index", null, DepthFirstSearch.find(-2, tree1));

        BinaryTree<String> tree2 = new BinaryTree<>();
        tree2.add("1","A");
        tree2.add("2","B");
        tree2.add("3","C");
        tree2.add("4","D");
        
        Assert.assertEquals("Incorrect index", "C",  DepthFirstSearch.find(tree2,"3"));
        Assert.assertEquals("Incorrect index", "B",  DepthFirstSearch.find(tree2,"2"));
        Assert.assertEquals("Incorrect index", null, DepthFirstSearch.find(tree2,"F"));

        BinaryTree<String> tree3 = new BinaryTree<>();
        Assert.assertEquals("Incorrect index", null, DepthFirstSearch.find(tree3, ""));
