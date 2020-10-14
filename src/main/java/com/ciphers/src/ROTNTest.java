import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ROTNTest {
    @Test
    void testROTN() {
        ROTN r = new ROTN();
        Assertions.assertEquals("Attack at Dawn!",r.solveROTN("Nggnpx ng Qnja!",13),"Wrong decryption");
    }
}
