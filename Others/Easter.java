//You can read more about the reason behind the algorithm itself here: https://en.wikipedia.org/wiki/Date_of_Easter

  /* Testing data:
 * 2003 - 20th of April (4/20) - Passed
 * 1830 - 11th of April (4/11) - Passed
 * 2020 - 12th of April (4/12) - Passed
 * 1910 - 27th of March (3/27) - Passed
 */

public class Easter
{
	public static void FindEaster(int y) 
	{
		if (y > 1582)
		{
            //Year of Metonic Cycle
            int a = y % 19;

            int b = y / 100;
            int c = y % 100;
            int d = b / 4;
            int e = b % 4;
            int f = (b + 8) / 25;
            int g = (b - f + 1) / 3;
            int h = (19 * a + b - d - g + 15) % 30;
            int i = c / 4;
            int k = c % 4;

            //The number of days from Paschal full moon to next Sunday
            int r = (32 + 2 * e + 2 * i - h - k) % 7;

            int m = (a + 11 * h + 22 * r) / 451;
            int n = (h + r - 7 * m + 114) / 31;
            int p = (h + r - 7 * m + 114) % 31;
			
			//Print date
			System.out.println("Easter in " + y + " falls on " + n + "/" + (p + 1) + ".");
		}
		else
		{
		    return;
		}		
	}
}
