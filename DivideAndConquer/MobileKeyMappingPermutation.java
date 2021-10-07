import java.util.HashMap;
import java.util.Map;
/*
Problem Explanation: https://www.pepcoding.com/resources/online-java-foundation/recursion-with-arraylists/get_kpc/topic
If input is 21--> print all premutations of 21 keys on keypad in old Nokia mobile:  da, db, dc, ea, eb, ec, fa, fb, fc
*/
class MobileKeyMappingPermutation {

  private static Map<String, String> map = new HashMap<>();
	static {
		map.put("1", "a,b,c");
		map.put("2", "d,e,f");
		map.put("3", "g,h,i");
		map.put("4", "j,k,l");
		map.put("5", "m,n,o");
		map.put("6", "p,q,r,s");
		map.put("7", "t,u");
		map.put("8", "v,w,x");
		map.put("9", "y,z");
		map.put("0", ".,;");
	}

	public static void main(String[] args) {
		String str = "12";
		printKeyMapPermutations(str, "");
	}

	public static void printKeyMapPermutations(String str, String ans) {

		if (str == null || str.isEmpty() || str.length() == 0) {
			System.out.println(ans);
			return;
		}
		char c = str.charAt(0);
		String ros = str.substring(1);
		String arr[] = map.get(c + "").split(",");
		for (String item : arr)
			printKeyMapPermutations(ros, ans + item);
	}

}
