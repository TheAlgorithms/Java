package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class agnosticBinarySearchTest{

    @Test
    public void Testascending(){
        int arr[] = {1,2,3,4,5};// for ascending order.
        int target = 2;
        int ans=agnosticBinarySearch.agnosticbinarySearch(arr, target);
        int excepted = 1;
        assertEquals(excepted,ans);
    }

    @Test
    public void Testdecending(){
        int arr[] = {5,4,3,2,1};// for descending order.
        int target = 2;
        int ans=agnosticBinarySearch.agnosticbinarySearch(arr, target);
        int excepted = 3;
        assertEquals(excepted,ans );
    }

}