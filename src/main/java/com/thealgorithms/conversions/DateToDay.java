package com.thealgorithms.conversions;

import java.util.*;

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
        // Array holding name of the day: Saturday - Sunday - Friday => 0 - 1 - 6
        List<String> daysNameArr = new ArrayList<>();
        daysNameArr.add("Saturday");
        daysNameArr.add("Sunday");
        daysNameArr.add("Monday");
        daysNameArr.add("Tuesday");
        daysNameArr.add("Wednesday");
        daysNameArr.add("Thursday");
        daysNameArr.add("Friday");

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

        // In case of Jan and Feb:
        // Year: we consider it as previous year
        // e.g., 1/1/1987 here year is 1986 (-1)
        // Month: we consider value as 13 & 14 respectively
        if (month < 3) {
            year--;
            month += 12;
        }

        // divide year to century and yearDigit value.
        int yearDigit = (year % 100);
        double century = Math.floor(year / 100);

        /**
         * Algorithm implementation
         *
         * In the mathematics modulo operations, truncated division is used mostly in most of programming languages.
         * Truncation defines quotient part (integer part) q = trunc(a/n), remainder has same sign as dividend.
         * -2 mod 7 return result of -2 => console.log(-2 % 7) => -2
         *
         * To overcome this problem, to ensure positive numerator, formula is modified by replacing -2J with +5J (J => century)
         *
         * Following example shows issue with modulo division
         * 1. For date 2/3/2014 with old formula - (2 * century) weekDay comes as -6 and wrong day
         * 2. With computer logic + (5 * century) it gives proper valid day as Sunday
         */
        double weekDay = (day + Math.floor((month + 1) * 2.6) + yearDigit + Math.floor(yearDigit / 4) + Math.floor(century / 4) + (5 * century)) % 7;

        // return the weekDay name.
        return daysNameArr.get((int) weekDay);
    }
}
