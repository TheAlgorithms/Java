import org.junit.Assert;
import org.junit.Test;
public class Testbase2base {


    @Test
    public void Testbase2base0()
    {

        String expected="";
        Assert.assertEquals(expected,AnyBaseToAnyBase.base2base("",4,10));
    }

    @Test
    public void Testbase2base1()
    {

        String expected="A";
        Assert.assertEquals(expected,AnyBaseToAnyBase.base2base("A",4,11));
    }

    @Test
    public void Testbase2base2()
    {
        String expected="10";
        Assert.assertEquals(expected,AnyBaseToAnyBase.base2base("A",4,10));
    }

    @Test
    public void Testbase2base3()
    {
        String expected="9";
        Assert.assertEquals(expected,AnyBaseToAnyBase.base2base("9",4,10));
    }
    
    @Test
    public void Testbase2base4()
    {

        String expected="h";
        Assert.assertEquals(expected,LongestCommonSubsequence.getLCS("a",4,50));
    }

    @Test
    public void Testbase2base4()
    {

        String expected="11";
        Assert.assertEquals(expected,LongestCommonSubsequence.getLCS("A",4,9));
    }


}