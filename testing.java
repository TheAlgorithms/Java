package Others;

import org.junit.Test;
import Others.GCD;
import static org.junit.Assert.assertEquals;

public class GCDTest{

	@Test
	public void test1(){
		int a = 0;
    int b = 56;
	int answer1 = GCD.gcd(a,b);
    assertEquals(answer1, 56);
	}

	@Test
	public void test2(){
		int a = 30;
    int b = 0;
int answer2 = GCD.gcd(a,b);
    assertEquals(answer2, 30);
	}

	@Test
	public void test3(){
		int a = 8;
    int b = 4;
int answer3 = GCD.gcd(a,b);
    assertEquals(answer3,4);
	}

	@Test
	public void test4(){
		int a = 3;
    int b = 13;
int answer4 = GCD.gcd(a,b);
    assertEquals(answer4, 1);
	}

}

@Test
public void test5(){
		int a = 65;
    int b = 1;
int answer5 = GCD.gcd(a,b);
    assertEquals(answer5, 1);
	}

}


