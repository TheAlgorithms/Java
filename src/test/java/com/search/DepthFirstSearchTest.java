package src.test.java.com.search;

import org.junit.Assert;
import org.junit.Test;

import src.main.java.com.search.DepthFirstSearch;
import src.main.java.com.search.BinaryTree;

public class DepthFirstSearchTest {

	@Test
	public void testDepthFirstSearch() {
		
		BinaryTree<Integer> tree1 = new BinaryTree<>();
       		tree1.add(1,1);
        	tree1.add(2,2);
        	tree1.add(3,3);
        	tree1.add(4,4);
		Assert.assertEquals((Integer) 3, DepthFirstSearch.find(3, tree1));
		Assert.assertEquals((Integer) 1, DepthFirstSearch.find(1, tree1));
		Assert.assertEquals(null       , DepthFirstSearch.find(0, tree1));
		Assert.assertEquals(null       , DepthFirstSearch.find(8, tree1));
		Assert.assertEquals(null       , DepthFirstSearch.find(-2, tree1));

		BinaryTree<String> tree2 = new BinaryTree<>();
		tree2.add("1","A");
		tree2.add("2","B");
		tree2.add("3","C");
		tree2.add("4","D");

		Assert.assertEquals("C" , DepthFirstSearch.find("3", tree2));
		Assert.assertEquals("B" , DepthFirstSearch.find("2", tree2));
		Assert.assertEquals(null, DepthFirstSearch.find("F", tree2));

		BinaryTree<String> tree3 = new BinaryTree<>();
		Assert.assertEquals(null, DepthFirstSearch.find("", tree3));
        
	}
}
