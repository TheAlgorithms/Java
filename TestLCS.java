import org.junit.Assert;
import org.junit.Test;
public class TestLCS {


    @Test
    public void testLCS0(){
        Assert.assertEquals("",LongestCommonSubsequence.getLCS("","adddcv"));
    }
    @Test
    public void testLCS1(){
        Assert.assertEquals("",LongestCommonSubsequence.getLCS("add",""));
    }
    @Test
    public void testLCS2(){
        Assert.assertEquals(null,LongestCommonSubsequence.getLCS(null,"adddcv"));
    }
    @Test
    public void testLCS3(){
        Assert.assertEquals(null,LongestCommonSubsequence.getLCS("adddcv",null));
    }
    @Test
    public void ttestLCS4(){
        Assert.assertEquals("",LongestCommonSubsequence.getLCS("ADDA","adddcv"));
    }
    @Test
    public void testLCS5()
    {
        Assert.assertEquals("a",LongestCommonSubsequence.getLCS("a","adddcv"));
    }
    @Test
    public void testLCS6(){
        Assert.assertEquals("b",LongestCommonSubsequence.getLCS("aaccDcsdxbDvbbb","b"));
    }
    @Test
    public void ttestLCS7(){
        Assert.assertEquals("ADDM",LongestCommonSubsequence.getLCS("ADVSDCSDCSCM","ADJKBMDMMMMMMMMMMMMMMMMMMM14667"));
    }
}