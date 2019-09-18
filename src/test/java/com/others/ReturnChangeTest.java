package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ReturnChangeTest {
    @BeforeEach
    public  void setUp() throws Exception {

    }
    @Test
    public void shouldNotAcceptNullArrayOfMoneys() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ReturnChange.calculate(null, 7);
        });
    }
    @Test
    public void shouldNotAcceptEmptyArrayOfMoneys() {
        Assertions.assertThrows( IllegalArgumentException.class, () -> {
            ReturnChange.calculate(new int[0], 7);
        });
    }
    @Test
    public void shouldNotAcceptZeroAsInputValue() {
        Assertions.assertThrows( IllegalArgumentException.class, () -> {
            ReturnChange.calculate(new int[0], 0);
        });
    }
    @Test
    public void shouldNotAcceptNegativeValuesAsInputValue() {
        Assertions.assertThrows( IllegalArgumentException.class, () -> {
            ReturnChange.calculate(new int[0], -7);
        });
    }

    @Test
    public void shouldReturnChangeUsingJustTheBiggestMoney() {
        int[] moneys = { 5, 10, 15, 50 };
        List<Integer> change = ReturnChange.calculate(moneys, 200);
        int[] expectedChange = { 50, 50, 50, 50 };
        assertChangeEqualsTo(expectedChange, change);
    }

    @Test
    public void shouldReturnChangeUsingMoreThanOneMoney() {
        int[] moneys = { 5, 10, 15, 50 };
        List<Integer> change = ReturnChange.calculate(moneys, 80);
        int[] expectedChange = { 50, 15, 15 };
        assertChangeEqualsTo(expectedChange, change);
    }

    private void assertChangeEqualsTo(int[] expectedChange, List<Integer> change) {
        Assertions.assertEquals(expectedChange.length, change.size());
        for (int i = 0; i < expectedChange.length; i++) {
            Integer partialChange = expectedChange[i];
            Assertions.assertEquals(partialChange, change.get(i));
        }
    }
}
