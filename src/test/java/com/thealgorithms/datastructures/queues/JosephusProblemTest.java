import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class JosephusProblemTest {

    @Test
    public void testJosephusWithValidInput() {
        assertEquals(3, JosephusProblem.Josephus(5, 2));
        assertEquals(2, JosephusProblem.Josephus(7, 3));
        assertEquals(4, JosephusProblem.Josephus(8, 4));
        assertEquals(1, JosephusProblem.Josephus(10, 1));
    }

    @Test
    public void testJosephusWithInvalidInput() {
        // Testing with negative values for n and k
        assertThrows(IllegalArgumentException.class, () -> JosephusProblem.Josephus(-5, 2));
        assertThrows(IllegalArgumentException.class, () -> JosephusProblem.Josephus(5, -2));
        
        // Testing with n = 0, which is an invalid input
        assertThrows(IllegalArgumentException.class, () -> JosephusProblem.Josephus(0, 3));
        
        // Add more test cases for invalid input as needed
    }
}
