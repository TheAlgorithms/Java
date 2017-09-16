package conversions;

import org.junit.Assert;
import org.junit.Test;

import static conversions.HexaDecimalToBinary.convert;


public class HexaDecimalToBinaryTest {

    @Test
    public void test() {
        String[] hexNums = {"1", "A1", "ef", "BA", "AA", "BB",
            "19", "01", "02", "03", "04"};

        for (String num : hexNums) {
            convert(num);
        }

        Assert.assertEquals("00000001", convert("1"));
        Assert.assertEquals("10100001", convert("A1"));
    }
}