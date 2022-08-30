package com.thealgorithms.backtracking;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Subset_Skiena_Form_Test {
    @Test
	void testForOneElement() 
	{
        int arr[] = {1};
        int len = arr.length;
        int empty_array[] = new int [len];
        List<List<Integer>> nm=new ArrayList<>();
		assertTrue(Subset_Skiena_Form.subset_Skiena_Form(arr,0,len,empty_array,nm));
	}

	@Test
	void testForTwoElements() 
	{
        int arr[] = {1,2};
        int len = arr.length;
        int empty_array[] = new int [len];
        List<List<Integer>> nm=new ArrayList<>();
		assertTrue(Subset_Skiena_Form.subset_Skiena_Form(arr,0,len,empty_array,nm));
	}

	@Test
	void testForThreeElements() 
	{
        int arr[] = {1,2,3};
        int len = arr.length;
        int empty_array[] = new int [len];
        List<List<Integer>> nm=new ArrayList<>();
		assertTrue(Subset_Skiena_Form.subset_Skiena_Form(arr,0,len,empty_array,nm));
	}

	@Test
	void testForFourElements() 
	{
        int arr[] = {1,2,3,4};
        int len = arr.length;
        int empty_array[] = new int [len];
        List<List<Integer>> nm=new ArrayList<>();
		assertTrue(Subset_Skiena_Form.subset_Skiena_Form(arr,0,len,empty_array,nm));
	}

	@Test
	void testForFiveElements() 
	{
        int arr[] = {1,2,3,4,5};
        int len = arr.length;
        int empty_array[] = new int [len];
        List<List<Integer>> nm=new ArrayList<>();
		assertTrue(Subset_Skiena_Form.subset_Skiena_Form(arr,0,len,empty_array,nm));
	}

	// @Test
	// void testForSixElements() 
	// {
    //     int arr[] = {1,2,3,4,5,6};
    //     int len = arr.length;
    //     int empty_array[] = new int [len];
    //     List<List<Integer>> nm=new ArrayList<>();
	// 	assertTrue(Subset_Skiena_Form.subset_Skiena_Form(arr,0,len,empty_array,nm));
	// }

	// @Test
	// void testForSevenElements() 
	// {
    //     int arr[] = {1,2,3,4,5,6,7};
    //     int len = arr.length;
    //     int empty_array[] = new int [len];
    //     List<List<Integer>> nm=new ArrayList<>();
	// 	assertTrue(Subset_Skiena_Form.subset_Skiena_Form(arr,0,len,empty_array,nm));
    // }

	// @Test
	// void testForEightElements() 
	// {
    //     int arr[] = {1,2,3,4,5,6,7,8};
    //     int len = arr.length;
    //     int empty_array[] = new int [len];
    //     List<List<Integer>> nm=new ArrayList<>();
	// 	assertTrue(Subset_Skiena_Form.subset_Skiena_Form(arr,0,len,empty_array,nm));
	// }
}
