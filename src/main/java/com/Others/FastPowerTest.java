package src.main.java.com.Others;

import org.junit.*;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class FastPowerTest {

    @Test
    public void test() {
        System.out.println("Long Type:");
        long result;
        result = FastPower.calculate(2, 2, 10);
        assertEquals(result, 4);
        System.out.println("The result of power(2,2) mod 10 is " + result);

        result = FastPower.calculate(100, 1000, 20);
        assertEquals(result, 0);
        System.out.println("The result of power(100, 1000) mod 20 is " + result);

        result = FastPower.calculate(123456, 123456789, 234);
        System.out.println("The result of power(123456, 123456789) mod 234 is " + result);


        System.out.println("BigInteger Type:");
        BigInteger bigResult;
        bigResult = FastPower.calculate(BigInteger.TEN, BigInteger.TEN, new BigInteger("4"));
        assertEquals(bigResult, BigInteger.ZERO);
        System.out.println("The bigResult of power(10, 10) mod 4 is " + bigResult);

        bigResult = FastPower.calculate(new BigInteger("123456"), new BigInteger("123456789"), new BigInteger("234"));
        System.out.println("The bigResult of power(123456, 123456789) mod 234 is " + bigResult);

        bigResult = FastPower.calculate(new BigInteger("123456789101112"), new BigInteger("12345678910111213"), new BigInteger("567890"));
        System.out.println("The bigResult of power(123456789101112, 12345678910111213) mod 567890 is " + bigResult);

    }
}