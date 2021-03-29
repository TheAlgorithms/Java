package ProjectEuler;

import java.util.Arrays;

public class Problem19 {
  public static void main(String[] args) {
    assert solution() == 171;
  }

  public static int solution() {
    int year = 1901;
    boolean leapYear = false;
    int totalSundays = 0;
    int currentDay = 2; // start on a monday

    while (year <= 2000) {
      leapYear = false;
      if (year % 4 == 0) {
        if (year % 100 == 0 && year % 400 == 0) {
          leapYear = true;
        } else leapYear = year % 100 != 0 || year % 400 == 0;
      }

      for (int month = 1; month <= 12; month++) {
        if (currentDay == 7) {
          totalSundays++;
        }
        if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month)) {
          currentDay += 3;
        } else if (Arrays.asList(4, 6, 9, 11).contains(month)) {
          currentDay += 2;
        } else if (leapYear) {
          currentDay += 1;
        }

        if (currentDay > 7) {
          currentDay -= 7;
        }
      }

      year++;
    }

    return totalSundays;
  }
}
