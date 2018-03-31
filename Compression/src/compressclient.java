
public class compressclient {

	public static void main(String[] args) {

		HEncoder h= new HEncoder("aaaabbbcccccccccccdddd");
		System.out.println(h.compress("aabccd"));
		System.out.println(h.decompress("101011000111"));
	}

}
