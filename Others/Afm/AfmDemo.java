package Afm;

public class AfmDemo {
	
	public static void main(String[] args) {
		Afm afm= new Afm();
		String str= "012345678";
		
		System.out.format("\nAFM: %s is %s.", str, afm.check(str)?"valid":"wrong");
	}

}
