package DateAndTime;

// An algorithm to count the number of days BETWEEN two dates
public class DaysBetween2Date {

    public static void main(String[] args) {
        // count number of days BETWEEN Jan 1st 2019 and Dec 31st 2019
        int numberOfDays = daysBetweenTwoDate(2019, 1, 1, 2019, 12, 31);
        System.out.println(numberOfDays);
    }

    public static int daysBetweenTwoDate(int startYear, int startMonth, int startDay, int y, int m, int d) {
        return Math.abs(thisIsMagic(startYear, startMonth, startDay) - thisIsMagic(y, m, d));
    }

    public static int thisIsMagic(int y, int m, int d) {
        if (m < 3) {
            y--;
            m += 12;
        }
        return 365*y + y/4 - y/100 + y/400 + (153*m - 457)/5 + d - 306;
    }
}
