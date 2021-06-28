package DynamicProgramming;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestKandaneAlgorithm {
    @Test
    public void largestContiguousSumT1() {
        int arr[] = new int[]{};
        assertEquals(0,KadaneAlgorithm.largestContiguousSum((arr)));
    }
    @Test
    public void largestContiguousSumT2() {
        int arr[] = new int[]{-1,0};
        assertEquals(0,KadaneAlgorithm.largestContiguousSum((arr)));
    }
    @Test
    public void largestContiguousSumT3() {
        int arr[] = new int[]{-1,-1};
        assertEquals(-1,KadaneAlgorithm.largestContiguousSum((arr)));
    }
    @Test
    public void largestContiguousSumT4() {
        int arr[] = new int[]{-100000,0,100000};
        assertEquals(100000,KadaneAlgorithm.largestContiguousSum((arr)));
    }
    @Test
    public void largestContiguousSumT5() {
        int arr[] = new int[]{1,2,-3,3};
        assertEquals(3,KadaneAlgorithm.largestContiguousSum((arr)));
    }
    @Test
    public void largestContiguousSumT6() {
        int arr[] = new int[]{1,2,3,4,-1,3};
        assertEquals(12,KadaneAlgorithm.largestContiguousSum((arr)));
    }
}
