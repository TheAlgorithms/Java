package Searches;

import Conversions.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

public class IterativeBinarySearchTest {
    private static IterativeBinarySearch IterativeBinarySearchTest;
    IterativeBinarySearchTest = new IterativeBinarySearch();

    @test
	//mảng rỗng
    public static void testFind1(){
        int[] arr1 = {};
        int a = IterativeBinarySearchTest.find(arr1, 10);
        assetEquals(a, -1);

    }
    @test
	//mảng chưa được sắp xếp
    public static void testFind2(){
        int[] arr1 = {4, 5, 2, 1};
        int a = IterativeBinarySearchTest.find(arr1, 2);
        assetEquals(a, -1);
    }
    @test
	//trường hợp mảng được sắp xếp và key đúng
    public static void testFind3(){
        int[] arr1 = {2, 3, 5, 6};
        int a = IterativeBinarySearchTest.find(arr1, 3);
        assetEquals(a, 1);
    }
    @test
	//mảng được sắp xếp, key sai
    public static void testFind4(){
        int[] arr1 = {1, 2, 3, 4};
        int a = IterativeBinarySearchTest.find(arr1, 5);
        assetEquals(a, -1);
    }

}
