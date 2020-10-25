package DateAndTime;

// An algorithm to determine how many days in a month
public class DaysInMonth {

    public static void main(String[] args) {
        getDaysInMonth(2020, 2); // return 29
        getDaysInMonth(2019, 2); // return 28
        getDaysInMonth(2020, 1); // return 31
        getDaysInMonth(2020, 9); // return 30

        int result = getDaysInMonth(2020, 2);
        System.out.println(result); // 29
    }

    // return the number of days of a month
    private static int getDaysInMonth(int year, int month) {
        // April, June, September, November have 30 days
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        // February has 28 or 29 (29 in leap year)
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            }
            return 28;
        }
        // other months have 31 days
        return 31; 
    }

    /*
    A leap year is exactly divisible by 4 except for century years (years ending with 00).
    The century year is a leap year only if it is perfectly divisible by 400.
    * */
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }
}
