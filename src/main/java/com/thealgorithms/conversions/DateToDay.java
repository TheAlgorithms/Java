package com.thealgorithms.conversions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Converts Date (dd/mm/yyyy) to WeekDay based on Zeller's Congruence
 * Algorithm & Explanation : https://en.wikipedia.org/wiki/Zeller%27s_congruence
 *
 * @author Chetan07j
 */
public class DateToDay {
    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter date in dd/mm/yyyy format");

        String date = sc.nextLine();  // Read user input
        String weekDay = findDayFromDate(date);

        System.out.println("WeekDay is: " + weekDay);  // Output WeekDay for given date
        sc.close();
    }

    /**
     * This method finds and returns name of a day for provided date string.
     * Validate input date format
     * Based on algorithm return name of a day
     *
     * @param date String format date
     * @return Name of day
     */
    public static String findDayFromDate(String date) {
        Map<Integer, String> daysNameList = new HashMap<>();
        daysNameList.put(1, "Sunday");
        daysNameList.put(2, "Monday");
        daysNameList.put(3, "Tuesday");
        daysNameList.put(4, "Wednesday");
        daysNameList.put(5, "Thursday");
        daysNameList.put(6, "Friday");
        daysNameList.put(0, "Saturday");

        String[] arrOfDate = date.split("/");

        if (arrOfDate.length != 3) {
            throw new Error("Invalid date format provided");
        }

        int day = Integer.parseInt(arrOfDate[0]);
        int month = Integer.parseInt(arrOfDate[1]);
        int year = Integer.parseInt(arrOfDate[2]);

        // check the data are valid or not.
        if (day < 1 || day > 31 || month > 12 || month < 1) {
            throw new Error("Date is not valid.");
        }

        // In case of Jan and Feb, we consider it as previous year
        // e.g., 1/1/1987 here year is 1986 (-1)
        if (month < 3) {
            year--;
            month += 12;
        }

        // divide year to century and yearDigit value.
        int yearDigit = (year % 100);
        double century = Math.floor(year / 100);

        // Apply the algorithm shown above
        double weekDay = Math.abs((day + Math.floor((month + 1) * 2.6) + yearDigit + (yearDigit / 4) + (century / 4) - (2 * century)) % 7);

        // return the weekDay name.
        return daysNameList.get((int) Math.floor(weekDay));
    }
}
