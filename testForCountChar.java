package Others;

import Others.CountChar
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
private static int CountCharacters(String str) 
{

        int count = 0;

        if (str == "" || str == null) {
            return 0;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                count++;
            }
        }

        return count;
}
*/
public class testForCountChar
{
    @test
    pubic void test1()
    {
        string inputStr = "";
        string numberOfChar = CountChar.CountCharacters(inputStr);
        
        Assert.assertEquals('0', numberOfChar);
    }
    
    @test
    pubic void test2()
    {
        string inputStr = " ";
        string numberOfChar = CountChar.CountCharacters(inputStr);
        
        Assert.assertEquals('0', numberOfChar);
    }
    
    @test
    pubic void test3()
    {
        string inputStr = null;
        string numberOfChar = CountChar.CountCharacters(inputStr);
        
        Assert.assertEquals('0', numberOfChar);
    }
    
    @test
    pubic void test4()
    {
        string inputStr = "abcxyz";
        string numberOfChar = CountChar.CountCharacters(inputStr);
        
        Assert.assertEquals('6', numberOfChar);
    }
    
    @test
    pubic void test5()
    {
        string inputStr = "abc xyz";
        string numberOfChar = CountChar.CountCharacters(inputStr);
        
        Assert.assertEquals('6', numberOfChar);
    }
}
	