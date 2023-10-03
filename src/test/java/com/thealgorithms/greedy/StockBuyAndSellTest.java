package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StockBuyAndSellTest {

    @Test
    void hasMultipleStocks() {
        int[] arr = {100, 180, 260, 310, 40, 535, 695};
        assertEquals(655, StockBuyAndSell.maxProfit(arr));
    }

    @Test
    void hasSingleStock() {
        int[] arr = {180};
        assertEquals(0, StockBuyAndSell.maxProfit(arr));
    }

    @Test
    void hasNoStock() {
        int[] arr = {};
        assertEquals(0, StockBuyAndSell.maxProfit(arr));
    }

    @Test
    void hasMultipleStocks2() {
        int[] arr = {100, 180, 260, 310, 40, 535, 695, 1868, 2008, 3830, 4808, 5808};
        assertEquals(4993, StockBuyAndSell.maxProfit(arr));
    }
}
