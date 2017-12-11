import java.util.Scanner;

public class encryption {
	private static int size;
	static char[] secret = new char[20]; 
	static int[] change = new int[20];
	public static void main(String[] args) {
		
		//conditions
		//Digit, capital letter, and small letter are allowed for input.
		//the characters are rotated, for example, A is followd by Z.
		//Encryption and decryption should be implemented together.
		
		String temp;
		System.out.println("Let's go encryption");
		Scanner aScanner = new Scanner(System.in);
		temp = aScanner.next();
		size = temp.length();
		for(int i = 0 ; i< size; i++) {
			secret[i]=temp.charAt(i);
		}
		simple_encryption(secret,1);
		medium_encryption(secret,1);
		ultra_encryption(secret,1);	
	}
	private static void ultra_encryption(char[] secret, int n) {
		// TODO Auto-generated method stub
		char[] end = new char[size];
		if(n==1) {
			for(int i = 0; i<size; i++) {
				int temp = (int)secret[i];
				if(temp >=  65 && temp	 <=90) {
					if(temp+(i*i)>90) {
						int rotate = (temp+(i*i))-90;
						int over = 64 + rotate;
						while(true) {
							if(over>90) {
								over = over - 90;
							}
							else if(over<65) {
								over = 64 + over;
							}
							else {
								change[i] = over;
								break;
							}
						}
					}
					else if(temp+(i*i)<=90) {						
						change[i] = temp+(i*i);
					}
				}
				else if(temp >=  97 && temp <=122) {
					if(temp+(i*i)>122) {
						int rotate = (temp+(i*i))-122;
						int over = 96 + rotate;
						while(true) {
							if(over>122) {
								over = over - 122;
							}
							else if(over<97) {
								over = 96 + over;
							}
							else {
								change[i] = over;
								break;
							}
						}
					}
					else if(temp+(i*i)<=122) {						
						change[i] = temp+(i*i);
					}
				}
			}
			System.out.print("(encode) ultra_encryption : ");
			for(int i = 0; i<size ; i++) {
				end[i] = (char)change[i];
				System.out.print(end[i]);
			}

			ultra_encryption(end,2);
		}
		if(n==2) {
			for(int i = 0; i<size; i++) {
				int temp = (int)secret[i];
				
				if(temp >=  65 && temp <=90) {
					if(temp-(i*i)<65) {
						int rotate = 65 - (temp-(i*i));
						int over = 91 - rotate;
						while(true) {
							if(over<65) {
								over = 91 - (65-over);
							}
							else if(over>90) {
								over = 64 +(over-90);
							}
							else {
								change[i] = over;
								break;
							}
						}												
					}
					else if(temp-(i*i)>=65) {						
						change[i] = temp-(i*i);
					}
				}
				else if(temp >=  97 && temp <=122) {
					if(temp-(i*i)<97) {
						int rotate = 97 - (temp-(i*i));
						int over = 123 - rotate;
						while(true) {
							if(over<97) {
								over = 123 - (97-over);
							}
							else if(over>122) {
								over = 96 +(over-122);
							}
							else {
								change[i] = over;
								break;
							}
						}												
					}
					else if(temp-(i*i)>=97) {						
						change[i] = temp-(i*i);
					}
				}
			}
			System.out.print(" -> (decode) ultra_encryption : ");
			for(int i = 0; i<size ; i++) {
				System.out.print((char)change[i]);
			}
		}
		System.out.println();		
	}
	private static void medium_encryption(char[] secret, int n) {
		// TODO Auto-generated method stub
		char[] end = new char[size];
		if(n==1) {
			for(int i = 0; i<size; i++) {
				int temp = (int)secret[i];

				if(temp >=  65 && temp	 <=90) {
					if(temp+i>90) {
						int rotate = (temp+i)-90;
						int over = 64 + rotate;
						while(true) {
							if(over>90) {
								over = over - 90;
							}
							else if(over<65) {
								over = 64 + over;
							}
							else {
								change[i] = over;
								break;
							}
						}
					}
					else if(temp+i<=90) {						
						change[i] = temp+i;
					}
				}
				else if(temp >=  97 && temp <=122) {
					if(temp+i>122) {
						int rotate = (temp+i)-122;
						int over = 96 + rotate;
						while(true) {
							if(over>122) {
								over = over - 122;
							}
							else if(over<97) {
								over = 96 + over;
							}
							else {
								change[i] = over;
								break;
							}
						}
					}
					else if(temp+i<=122) {						
						change[i] = temp+i;
					}
				}
			}
			System.out.print("(encode) medium_encryption : ");
			for(int i = 0; i<size ; i++) {
				end[i] = (char)change[i];
				System.out.print(end[i]);
			}
			medium_encryption(end,2);
		}
		if(n==2) {
			for(int i = 0; i<size; i++) {
				int temp = (int)secret[i];
				if(temp >=  65 && temp <=90) {
					if(temp-i<65) {
						int rotate = 65 - (temp-i);
						int over = 91 - rotate;
						while(true) {
							if(over<65) {
								over = 91 - (65-over);
							}
							else if(over>90) {
								over = 64 +(over-90);
							}
							else {
								change[i] = over;
								break;
							}
						}												
					}
					else if(temp-i>=65) {						
						change[i] = temp-i;
					}
				}
				else if(temp >=  97 && temp <=122) {
					if(temp-i<97) {
						int rotate = 97 - (temp-i);
						int over = 123 - rotate;
						while(true) {
							if(over<97) {
								over = 123 - (97-over);
							}
							else if(over>122) {
								over = 96 +(over-122);
							}
							else {
								change[i] = over;
								break;
							}
						}												
					}
					else if(temp-i>=97) {						
						change[i] = temp-i;
					}
				}
			}
			System.out.print(" -> (decode) medium_encryption : ");
			for(int i = 0; i<size ; i++) {
				System.out.print((char)change[i]);
			}
		}
		System.out.println();		
	}
	private static void simple_encryption(char[] secret, int n) {
		// TODO Auto-generated method stub
		char[] end = new char[20];
		if(n==1) {
			for(int i = 0; i<size; i++) {
				int temp = (int)secret[i];

				if(temp >=  65 && temp	 <=90) {
					if(temp+1>90) {
						int rotate = (temp+1)-90;
						int over = 64 + rotate;
						while(true) {
							if(over>90) {
								over = over - 90;
							}
							else if(over<65) {
								over = 64 + over;
							}
							else {
								change[i] = over;
								break;
							}
						}
					}
					else if(temp+1<=90) {						
						change[i] = temp+1;
					}
				}
				else if(temp >=  97 && temp <=122) {
					if(temp+1>122) {
						int rotate = (temp+1)-122;
						int over = 96 + rotate;
						while(true) {
							if(over>122) {
								over = over - 122;
							}
							else if(over<97) {
								over = 96 + over;
							}
							else {
								change[i] = over;
								break;
							}
						}
//						change[i] = 96 + rotate;
					}
					else if(temp+1<=122) {						
						change[i] = temp+1;
					}
				}
			}
			System.out.print("(encode) simple_encryption : ");
			for(int i = 0; i<size ; i++) {
				end[i] = (char)change[i];
				System.out.print(end[i]);
			}
			simple_encryption(end,2);
		}
		if(n==2) {
			for(int i = 0; i<size; i++) {
				int temp = (int)secret[i];
				if(temp >=  65 && temp <=90) {
					if(temp-1<65) {
						int rotate = 65 - (temp-1);
						int over = 91 - rotate;
						while(true) {
							if(over<65) {
								over = 91 - (65-over);
							}
							else if(over>90) {
								over = 64 +(over-90);
							}
							else {
								change[i] = over;
								break;
							}
						}												
					}
					else if(temp-1>=65) {						
						change[i] = temp-1;
					}
				}
				else if(temp >=  97 && temp <=122) {
					if(temp-1<97) {
						int rotate = 97 - (temp-1);
						int over = 123 - rotate;
						while(true) {
							if(over<97) {
								over = 123 - (97-over);
							}
							else if(over>122) {
								over = 96 +(over-122);
							}
							else {
								change[i] = over;
								break;
							}
						}												
					}
					else if(temp-1>=97) {						
						change[i] = temp-1;
					}
				}
			}
			System.out.print(" -> (decode) simple_encryption : ");
			for(int i = 0; i<size ; i++) {
				System.out.print((char)change[i]);
			}
		}
		System.out.println();
	}
}
