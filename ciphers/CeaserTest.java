
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;


public class CaesarTest {




    @Test
    public void testDecode1() {
        assertEquals(decode("ABCDE",1),"ZABCD"," simple test 1");
        assertEquals(encode(decode("banana",1),1), "banana", "test encode and decode 1");
    }
	
    @Test
    public void testDecode2() {
        assertEquals(decode("YSMBYZWMIYREQ",4),"ZYSMBYZWMIYRE"," simple test 2");
        assertEquals(encode(decode("TOIYEUVIETNAM",4),4), "TOIYEUVIETNAM", "test encode and decode 2");
    }
    @Test
    public void testDecode3() {
        assertEquals(decode("ABC",3),"ZAB"," simple test 3");
        assertEquals(encode(decode("CDE",3),3), "CDE", "test encode and decode 3");
    }
    @Test
    public void testDecode4() {
        assertEquals(decode("ZUOXGZJKVZXGO",6),"ZZUOXGZJKVZXG"," simple test 4");
        assertEquals(encode(decode("TOIRATDEPTRAI",6),6), "TOIRATDEPTRAI", "test encode and decode 4");
    }
    
     @Test
    public void testDecode5() {
        assertEquals(decode("WtaeLtPgtQtxcvPiiprzts",15),"ZWtaeLtPgtQtxcvPiiprzt"," simple test 5");
        assertEquals(encode(decode("Helpwearebeingattacked",15),15), "Help we are being attacked", "test encode and decode 5");
    }




}
