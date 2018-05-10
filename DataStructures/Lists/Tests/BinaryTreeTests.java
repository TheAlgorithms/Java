import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTests {
	
	private Tree tree = null;	

	@Before
	public void setUp() throws Exception {
		tree = new Tree();
	}
	
	// Tree = null (no elements) .
	public void resetTree() {
		tree = new Tree();
	}
	
	// Tree with one element .
	public void oneEelement() {
		resetTree();
		tree.put(1);
	}
	
	// Tree with 3 element3.
	public void threeEelement() {
		resetTree();
		tree.put(1);
		tree.put(2);
		tree.put(3);
	}
	

	@Test
	public void testFind() {
		// Tree is null
		assertEquals(null, tree.find(1));
		
		// Tree is not null
		oneEelement();
		assertEquals(1, tree.find(1));
	}

}
