package Strings;
import java.util.ArrayList;

public class Split {

	public static void main(String[] args) {
		String n = "ABC-123";
		String d = "-";
		String actualResult[] = split(n, d);
		String[] expectedResult = {"ABC","123"};
		assert actualResult[0].equals(expectedResult[0]);
		assert actualResult[1].equals(expectedResult[1]);
	}



/**
 * Split one strings into 1 or more substring
 *
 * @param s the string to split
 * @param d the delimiter
 * @return the {@code String}, splitted strings
 * 
 * 
**/

public static String[] split(String s, String d) {
	ArrayList<String> parts = new ArrayList<String>();
	int i,j;

	for (i = s.indexOf(d), j = 0; i != -1;) {
		String tempStr = s.substring(j,i);
		if(tempStr.trim().length()!=0) {
			parts.add(tempStr);
		}
		j = i + d.length();
		i = s.indexOf(d, j);
	}
	String lastpart = s.substring(j);
	parts.add(lastpart);
	return parts.toArray(new String[0]);
}
}
