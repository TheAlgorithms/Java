import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class NaturalComparator implements Comparator
{
    private static final List<String> MONTH_LIST_SHORT = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"); // No I18N
    private static final List<String> MONTH_LIST_LONG = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"); // No I18N
    private static final List<String> WEEK_LIST_SHORT = Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"); // No I18N
    private static final List<String> WEEK_LIST_LONG = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"); // No I18N

    private static final Logger LOGGER = Logger.getLogger(NaturalComparator.class.getName());

    private static char charAt(String s, int i)
    {
        return i >= s.length() ? 0 : s.charAt(i);
    }

    private int compareRight(String a, String b)
    {
        int bias = 0, ia = 0, ib = 0;

        // The longest run of digits wins. That aside, the greatest
        // value wins, but we can't know that it will until we've scanned
        // both numbers to know that they have the same magnitude, so we
        // remember it in BIAS.
        for (; ; ia++, ib++)
        {
            char ca = charAt(a, ia);
            char cb = charAt(b, ib);

            if (!Character.isDigit(ca) && !Character.isDigit(cb))
            {
                return bias;
            }
            if (!Character.isDigit(ca))
            {
                return -1;
            }
            if (!Character.isDigit(cb))
            {
                return +1;
            }
            if (ca == 0 && cb == 0)
            {
                return bias;
            }

            if (bias == 0)
            {
                if (ca < cb)
                {
                    bias = -1;
                } else if (ca > cb)
                {
                    bias = +1;
                }
            }
        }
    }

    private Comparator<String> monthCompare = (o1, o2) -> {
        try
        {
            SimpleDateFormat s = new SimpleDateFormat("MMM");

            Date s1 = s.parse(o1.substring(0, Math.min(3, o1.length())));
            Date s2 = s.parse(o2.substring(0, Math.min(3, o2.length())));

            return s1.compareTo(s2);
        } catch (Exception e)
        {
            LOGGER.info("IMPLOG : Exception on Natural Compare for month.");
        }
        return 0;
    };
    private Comparator<String> weekCompare = (o1, o2) -> {
        try
        {
            SimpleDateFormat s = new SimpleDateFormat("EEE");

            Date s1 = s.parse(o1.substring(0, Math.min(3, o1.length())));
            Date s2 = s.parse(o2.substring(0, Math.min(3, o2.length())));

            return s1.compareTo(s2);
        } catch (ParseException e)
        {
            LOGGER.info("IMPLOG : Exception on Natural Compare for week.");
        }
        return 0;
    };

    public int compare(Object o1, Object o2)
    {
        String a = o1.toString();
        String b = o2.toString();

        int ia = 0, ib = 0;
        int nza = 0, nzb = 0;
        char ca, cb;

        while (true)
        {
            // Only count the number of zeroes leading the last number compared
            nza = nzb = 0;

            ca = charAt(a, ia);
            cb = charAt(b, ib);

            // skip over leading spaces or zeros
            while (Character.isSpaceChar(ca) || ca == '0')
            {
                if (ca == '0')
                {
                    nza++;
                } else
                {
                    // Only count consecutive zeroes
                    nza = 0;
                }

                ca = charAt(a, ++ia);
            }

            while (Character.isSpaceChar(cb) || cb == '0')
            {
                if (cb == '0')
                {
                    nzb++;
                } else
                {
                    // Only count consecutive zeroes
                    nzb = 0;
                }

                cb = charAt(b, ++ib);
            }

            // Process run of digits
            if (Character.isDigit(ca) && Character.isDigit(cb))
            {
                int bias = compareRight(a.substring(ia), b.substring(ib));
                if (bias != 0)
                {
                    return bias;
                }
            }

            StringBuilder stringBuilderOfa = new StringBuilder();
            StringBuilder stringBuilderOfb = new StringBuilder();
            while (Character.isAlphabetic(ca) && Character.isAlphabetic(cb))
            {
                stringBuilderOfa.append(ca);
                ca = charAt(a, ++ia);
                stringBuilderOfb.append(cb);
                cb = charAt(b, ++ib);
            }
            String partOfa = stringBuilderOfa.toString();
            String partOfb = stringBuilderOfb.toString();
            if (partOfa.length() > 0 && partOfb.length() > 0)
            {
                if (partOfa.compareTo(partOfb) != 0)
                {
                    if ((WEEK_LIST_SHORT.contains(partOfa) && WEEK_LIST_SHORT.contains(partOfb)) || (WEEK_LIST_LONG.contains(partOfa) && WEEK_LIST_LONG.contains(partOfb)))
                    {
                        return weekCompare.compare(partOfa, partOfb);
                    } else if ((MONTH_LIST_SHORT.contains(partOfa) && MONTH_LIST_SHORT.contains(partOfb)) || (MONTH_LIST_LONG.contains(partOfa) && MONTH_LIST_LONG.contains(partOfb)))
                    {
                        int bias = compare(a.substring(ia), b.substring(ib));
                        if (bias != 0)
                        {
                            return bias;
                        }
                        return monthCompare.compare(partOfa, partOfb);
                    }

                    return stringBuilderOfa.toString().compareTo(stringBuilderOfb.toString());
                }
            }

            if (ca == 0 && cb == 0)
            {
                // The strings compare the same. Perhaps the caller
                // will want to call strcmp to break the tie.
                return nza - nzb;
            }
            if (ca < cb)
            {
                return -1;
            }
            if (ca > cb)
            {
                return +1;
            }

            ++ia;
            ++ib;
        }
    }
}