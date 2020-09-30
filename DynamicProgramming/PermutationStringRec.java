import java.util.Scanner;
import java.util.TreeSet;

public class PermutationStringRec {
	
	static void print(String ip,String op) {
		
		//base condition
		if(ip.length() == 0) {
			System.out.println(op);
			return;
		}
		
	
		print(ip.substring(1),op); //Not include the decision in output
		print(ip.substring(1),op+ip.charAt(0)); //include the decision in output

	}
	
	static void print(String ip) {
		print(ip,"");
	}  
	
	public static void main(String[] args) {
			String ip = "12345";
			print(ip);
	}
	
	
	
	
}
