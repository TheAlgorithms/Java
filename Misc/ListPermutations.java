package Misc;


import java.util.Scanner;

public class ListPermutations {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		printPerms("", str);
	}

	public static void printPerms(String pre, String post) {
		if (post.length() == 0) {
			System.out.println(pre);
			return;
		}
		for (int i = 0; i < post.length(); i++) {
			printPerms(pre + post.charAt(i), post.substring(0, i) + post.substring(i + 1, post.length()));
		}
	}
}
