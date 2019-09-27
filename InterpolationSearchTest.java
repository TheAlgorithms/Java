package Searches;

import Conversions.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

public class InterpolationSearchTest {
    private static InterpolationSearch InterpolationSearchTest;
    InterpolationSearchTest = new InterpolationSearch();

    @test
    public static void testFind1(){
        int[] arr1 = {1, 2, 3, 4};
        int a = InterpolationSearchTest.find(arr1, 2);
        assetEquals(a, 2);

    }
    @test
    public static void testFind2(){
        int[] arr1 = {4, 3, 2, 1};
        int a = InterpolationSearchTest.find(arr1, 2);
        assetEquals(a, -1);
    }
    @test
    public static void testFind3(){
        int[] arr1 = {2, 3, 5, 6};
        int a = InterpolationSearchTest.find(arr1, 4);
        assetEquals(a, -1);
    }
    @test
    public static void testFind4(){
        int[] arr1 = {};
        int a = InterpolationSearchTest.find(arr1, 0);
        assetEquals(a, -1);
    }

}
