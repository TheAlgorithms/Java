
public class compressclient {

	public static void main(String[] args) {

		HEncoder h= new HEncoder("aaaabbbcccccccccccddddddddddd");
		System.out.println(h.compress("aabccdd"));
		System.out.println(h.decompress("10101100011101"));
	}

}
