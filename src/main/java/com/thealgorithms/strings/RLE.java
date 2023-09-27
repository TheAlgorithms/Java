package Strings;

public class RLE {

	static void runCode(String s) {
		int n=s.length();
		for(int i=0;i<n;i++) {
			int count=1;
			while(i<n-1&& s.charAt(i)==s.charAt(i+1)) {
				i++;
				count++;
			}
			
			 System.out.print(s.charAt(i));
		        System.out.print(count);
		}
	}
	public static void main(String[] args) {

		String str="aaaabbbccccddd";
		runCode(str);
	}

}
