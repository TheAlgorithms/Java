package dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;

import static dynamicprogramming.Fibonacci.fibBotUp;
import static dynamicprogramming.Fibonacci.fibMemo;

public class FibonacciTest {
    @Test
    public void test() throws Exception {
        Assert.assertEquals(8, fibMemo(6));
        Assert.assertEquals(8, fibBotUp(6));
    }
}