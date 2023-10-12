import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FloydAlgoTest {
    FloydAlgo algo = new FloydAlgo();
    @Test
    public void testForDuplicate(){
        int arr[] = {2,2,1,4,5,6};
        int duplicate =algo.find_duplicate(arr);
        assertEquals(duplicate,2);
    }
    @Test
    public void testForEmptyElement(){
        int duplicate = algo.find_duplicate(new int[0]);
        assertEquals(-1,duplicate);
    }
    public static void main(String[] args) {
        
    }
}
