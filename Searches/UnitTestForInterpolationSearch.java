package Searches;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnitTestForInterpolationSearch {
    InterpolationSearch object = new InterpolationSearch();
    @Test
    public void KeyLessThanFirstElement(){
        int arr[] = {1, 3, 4, 7};
        int actualResult = object.find(arr,0);
        assertEquals(actualResult, -1);
    }
    @Test
    public void KeyAtFirstElement(){
        int arr[] = {1, 3, 4, 7};
        int actualResult = object.find(arr,1);
        assertEquals(actualResult, 0);
    }
    @Test
    public void KeyAtSecondElement(){
        int arr[] = {1, 3, 4, 7};
        int actualResult = object.find(arr,3);
        assertEquals(actualResult, 1);
    }
    @Test
    public void KeyAtLastElement(){
        int arr[] = {1, 3, 4, 7};
        int actualResult = object.find(arr,7);
        assertEquals(actualResult, 3);
    }
}
