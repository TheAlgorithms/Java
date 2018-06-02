package Afm
public class Afm {
	public boolean check(String s) {
		/*
		long t=System.nanoTime();
		boolean v= check1(s);
		t= System.nanoTime()-t;
		System.out.format("duration= %8.3f", (double)t/1_000_000);
		return v;
		*/
		return check1(s);
	}
	
	@SuppressWarnings(value = { "unused" })
	private boolean check1(String s) {
		boolean v= false;
		if(s.length() != 9) return v;
		char[] c= s.toCharArray();
		int sum=0;
		sum+=256*(Character.getNumericValue(c[0]));
		sum+=128*(Character.getNumericValue(c[1]));
		sum+= 64*(Character.getNumericValue(c[2]));
		sum+= 32*(Character.getNumericValue(c[3]));
		sum+= 16*(Character.getNumericValue(c[4]));
		sum+=  8*(Character.getNumericValue(c[5]));
		sum+=  4*(Character.getNumericValue(c[6]));
		sum+=  2*(Character.getNumericValue(c[7]));
		int u= sum%11;
		if(u==10) {
			if(c[8]=='0') v=true;
		} else {
			if(u==Character.getNumericValue(c[8])) v=true;
		}
		return v;
	}
	
	@SuppressWarnings(value = { "unused" })
	private boolean check2(String s) {
		boolean v= false;
		if(s.length() != 9) return v;
		for(int i=0; i<s.length(); i++) {
			if(!Character.isDigit(s.charAt(i))) return v;
		}
		char[] c= s.toCharArray();
		int sum=0;
		int p=256;
		for(int i=0; i<8; i++) {
			sum += p * Character.getNumericValue(c[i]);
			p /= 2;
		}
		int u= sum%11;
		if(u==10) {
			if(c[8]=='0') v=true;
		} else {
			if(u==Character.getNumericValue(c[8])) v=true;
		}
		return v;
	}
}
