import java.util.*;
class PlayFair {
	public static void main(String args[]) {		
		Scanner sc = new Scanner(System.in);
		int i,j,k;
		char secret_key[][] = {
			{'l','g','d','b','a'},
			{'q','m','h','e','c'},
			{'u','r','n','i','f'},
			{'x','v','s','o','k'},
			{'z','y','w','t','p'}
		};
		String xyz;
		System.out.println("Enter the plain text:- ");
		xyz = sc.nextLine();
		StringBuffer buffer = new StringBuffer(xyz);
		for( i = 0 ; i < buffer.length() ; i = i + 2) {
			if(i == buffer.length()-1 ) {
			}
			else if(buffer.charAt(i) == buffer.charAt(i+1)) {
				buffer.setCharAt(i+1,'-');
				int m = buffer.length()-1;
				char temp = buffer.charAt(buffer.length()-1);
				while(m >= i + 3 ) {
					buffer.setCharAt(m,buffer.charAt(m-1));
					m--;
				}
				buffer.append(temp);
				buffer.setCharAt(i+2,buffer.charAt(i));
			}
		}
		if(buffer.length()%2 == 1)
			buffer.append('-');
		for(i = 0 ; i < buffer.length() ; i = i + 2) {
			if(buffer.charAt(i)=='-') {
				buffer.setCharAt(i,'x');
			}
			if(buffer.charAt(i+1)=='-') {
				buffer.setCharAt(i+1,'x');
			}
			if(buffer.charAt(i)=='j') {
				buffer.setCharAt(i,'i');
			}
			if(buffer.charAt(i+1)=='j') {
				buffer.setCharAt(i+1,'i');
			}
			boolean firstFound=false,secondFound=false;
			int firstRow=0,firstColumn=0,secondRow=0,secondColumn=0;
			for(j=0;j<5;j++) {
				for(k=0;k<5;k++) {
					if(secret_key[j][k] == buffer.charAt(i)) {
						firstRow = j;
						firstColumn = k;
						firstFound = true;
					}
					else if(secret_key[j][k] == buffer.charAt(i+1)) {
						secondRow = j;
						secondColumn = k;
						secondFound = true;
					}
					if(firstFound == true && secondFound == true) {
						break;
					}
				}
			}
			if(firstRow == secondRow) {
				buffer.setCharAt(i,secret_key[firstRow][(firstColumn+1)%5]);
				buffer.setCharAt(i+1,secret_key[secondRow][(secondColumn+1)%5]);
			}
			else if(firstColumn == secondColumn) {
				buffer.setCharAt(i,secret_key[(firstRow+1)%5][firstColumn]);
				buffer.setCharAt(i+1,secret_key[(secondRow+1)%5][secondColumn]);
			}
			else {
				buffer.setCharAt(i,secret_key[firstRow][secondColumn]);
				buffer.setCharAt(i+1,secret_key[secondRow][firstColumn]);
			}
		}
		System.out.println("Generated Cipher Text is:- ");
		System.out.println(buffer.toString());
	}
}

/*
OUTPUT:-
vips@vips-HP-Pavilion-x360-Convertible ~/Downloads $ javac PlayFair.java 
vips@vips-HP-Pavilion-x360-Convertible ~/Downloads $ java PlayFair 
Enter the plain text:- 
thehouseisbeingsoldtonight
Generated Cipher Text is:- 
wecexiohnoeifidvxbbwsirbew
vips@vips-HP-Pavilion-x360-Convertible ~/Downloads $ 
*/