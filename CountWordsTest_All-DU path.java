package Others;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CountWordsTest {

    //Giá trị str là rỗng 
    @test 
    public static isEmpty {  
        String str = "";
        int num = CountWords.secondaryWordCount(str);
        assertEquals(0, num);
    }

    // Str là một từ 
    @test 
    public static Words {  
        String str = "Hello";
        int num = CountWords.secondaryWordCount(str);
        assertEquals(5, num);
    }

    // Str là dãy số
    @test 
    public static Number {  
        String str = "12345678";
        int num = CountWords.secondaryWordCount(str);
        assertEquals(8, num);
    }

    // Str là các ký tự khác đặc biệt
    @test 
    public static other{  
        String str = "#@#?157389";
        int num = CountWords.secondaryWordCount(str);
        assertEquals(9, num);
    }

    // Str là các dấu cách
    @test 
    public static space{  
        String str = "       ";
        int num = CountWords.secondaryWordCount(str);
        assertEquals(7, num);
    }
