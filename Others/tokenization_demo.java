import java.util.*;

public class Lab3a {
	int a, b;
	void getInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.printf("\nEnter the numbers a and b for (a/b) : ");
		a = sc.nextInt();
		b = sc.nextInt();
	}

	void divide() { 
		int c;
		try {
			c = a/b;
			System.out.println("The answer is : " + c);
		}catch(Exception e) {
				System.out.println("\nEXCEPTION CAUGHT");
				System.out.println("The Exception is :\n" + e.toString());
				System.out.println("The Expection Occured at :\n");
				e.printStackTrace(); 
			}finally {
					System.out.println("THE END of EXCEPTION HANDLING");
				}//end of finally
	}//end of divide method
	public static void main(String[] args) {
		Lab3a obj = new Lab3a();
		obj.getInfo();
		obj.divide();
	}//end og Main Method
}
