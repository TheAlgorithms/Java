package testing;

import static org.junit.Assert.assertEquals;

import javax.annotation.processing.Generated;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
	

	
	@Test
	public void testFindMin(){
		assertEquals(10, FindMin.findMin(new int[] {1,2,3,4,5,6,7,8,9,10}));
	}
}
