package com.thealgorithms.backtracking;

import java.util.*;

public class LargestValidDateTimefromDigits {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String parts[] = input.split(",");
        List<Integer> digits = new ArrayList<>();
        for (String part : parts) {
            digits.add(Integer.parseInt(part));
        }
        Collections.sort(digits, Collections.reverseOrder());
        String result = findLargestValidDateTime(digits);
        System.out.println(result);
    }

    // Function for latest valid Date Time
    public static String findLargestValidDateTime(List<Integer> digits) {
        List<Integer> month = findValidMonth(digits);
        if (month == null)
            return "0"; // No valid month

        List<Integer> day = findValidDay(digits, month);
        if (day == null)
            return "0"; // No valid day

        List<Integer> hour = findValidHour(digits, month, day);
        if (hour == null)
            return "0"; // No valid hour

        List<Integer> minute = findValidMinute(digits, month, day, hour);
        if (minute == null)
            return "0"; // No valid minute

        // Format the result: MM/DD HH:MM
        return String.format("%02d/%02d %02d:%02d",
                month.get(0) * 10 + month.get(1),
                day.get(0) * 10 + day.get(1),
                hour.get(0) * 10 + hour.get(1),
                minute.get(0) * 10 + minute.get(1));
    }

    // Function for Valid Month
    public static List<Integer> findValidMonth(List<Integer> digits) {
        for (int i = 0; i < digits.size(); i++) {
            for (int j = 0; j < digits.size(); j++) {
                if (i != j) {
                    int month = digits.get(i) * 10 + digits.get(j);
                    if (month >= 1 && month <= 12) {
                        return Arrays.asList(digits.remove(i), digits.remove(j > i ? j - 1 : j));
                    }
                }
            }
        }
        return null; // No valid month found
    }

    // Function for Valid Day
    public static List<Integer> findValidDay(List<Integer> digits, List<Integer> month) {
        int maxDay = getMaxDays(month.get(0) * 10 + month.get(1)); // Get the max days of the selected month
        for (int i = 0; i < digits.size(); i++) {
            for (int j = 0; j < digits.size(); j++) {
                if (i != j) {
                    int day = digits.get(i) * 10 + digits.get(j);
                    if (day >= 1 && day <= maxDay) {
                        return Arrays.asList(digits.remove(i), digits.remove(j > i ? j - 1 : j));
                    }
                }
            }
        }
        return null; // No valid day found
    }

    // Function for Valid Hour
    public static List<Integer> findValidHour(List<Integer> digits, List<Integer> month, List<Integer> day) {
        for (int i = 0; i < digits.size(); i++) {
            for (int j = 0; j < digits.size(); j++) {
                if (i != j) {
                    int hour = digits.get(i) * 10 + digits.get(j);
                    if (hour >= 0 && hour <= 23) {
                        return Arrays.asList(digits.remove(i), digits.remove(j > i ? j - 1 : j));
                    }
                }
            }
        }
        return null; // No valid hour found
    }

    // Function for Valid Minute
    public static List<Integer> findValidMinute(List<Integer> digits, List<Integer> month, List<Integer> day,
            List<Integer> hour) {
        for (int i = 0; i < digits.size(); i++) {
            for (int j = 0; j < digits.size(); j++) {
                if (i != j) {
                    int minute = digits.get(i) * 10 + digits.get(j);
                    if (minute >= 0 && minute <= 59) {
                        return Arrays.asList(digits.get(i), digits.get(j));
                    }
                }
            }
        }
        return null; // No valid minute found
    }

    // Function for maximum days in a month
    public static int getMaxDays(int month) {
        // Max days in each month (index 0 = January)
        int[] maxDaysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        return maxDaysInMonth[month - 1];
    }
}
