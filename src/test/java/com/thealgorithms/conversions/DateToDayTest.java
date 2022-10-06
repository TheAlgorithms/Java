package com.thealgorithms.conversions;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateToDayTest {
    public Map<String, String> testScenarios() {
        final Map<String, String> testData= new HashMap<>();
        testData.put("18/02/2001", "Sunday");
        testData.put("18/12/2020", "Friday");
        testData.put("12/12/2012", "Wednesday");
        testData.put("01/01/2001", "Monday");
        testData.put("1/1/2020", "Wednesday");

        return testData;
    }

    @Test
    public void dateToDayCase() {
        for (Map.Entry<String, String> entry : testScenarios().entrySet()) {
            final String input = entry.getKey();
            final String expected = entry.getValue();

            final String actual = com.thealgorithms.conversions.DateToDay.findDayFromDate(input);
            assertEquals(actual, expected, "Wrong day for date: "+input);
        }
    }
}
