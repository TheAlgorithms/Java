package Others;

import org.junit.Test;
import Others.GCD;
import static org.junit.Assert.assertEquals;

public class GCDTest{

	@Test
	public void test1(){
		int a = 0;
    int b = 56;
    assertEquals(GCD.gcd(a,b), 56);
	}

	@Test
	public void test2(){
		int a = 30;
    int b = 0;
    assertEquals(GCD.gcd(a,b), 30);
	}

	@Test
	public void test3(){
		int a = 8;
    int b = 4;
    assertEquals(GCD.gcd(a,b), 4);
	}

	@Test
	public void test4(){
		int a = 3;
    int b = 13;
    assertEquals(GCD.gcd(a,b), 1);
	}

}
