import java.util.Scanner;

public class A5 {

 private String key = null;
 static final int REG_X_LENGTH = 19;
 static final int REG_Y_LENGTH = 22;
 static final int REG_Z_LENGTH = 23;
 int[] regX = new int[REG_X_LENGTH];
 int[] regY = new int[REG_Y_LENGTH];
 int[] regZ = new int[REG_Z_LENGTH];

 void loadRegisters(String key) {
	for (int i = 0; i < REG_X_LENGTH; i++)
	 regX[i] = Integer.parseInt(key.substring(i, i + 1));
	for (int i = 0; i < REG_Y_LENGTH; i++)
	 regY[i] = Integer.parseInt(key.substring(REG_X_LENGTH + i, REG_X_LENGTH + i + 1));
	for (int i = 0; i < REG_Z_LENGTH; i++)
	 regZ[i] = Integer.parseInt(key.substring(REG_X_LENGTH + REG_Y_LENGTH + i, REG_X_LENGTH +
		REG_Y_LENGTH + i + 1));
 }


 boolean setKey(String key) {
	if (key.length() == 64 && key.matches("[01]+")) {
	 this.key = key;
	 this.loadRegisters(key);
	 return true;
	}
	return false;
 }

 String getKey() {
	return this.key;
 }

 String encrypt(String plaintext) {
	StringBuilder s = new StringBuilder();
	int[] binary = this.toBinary(plaintext);
	int[] keystream = getKeystream(binary.length);
	for (int i = 0; i < binary.length; i++)
	 s.append(binary[i] ^ keystream[i]);
	return s.toString();
 }

 String decrypt(String ciphertext) {
	StringBuilder s = new StringBuilder();
	int[] binary = new int[ciphertext.length()];
	int[] keystream = getKeystream(ciphertext.length());
	for (int i = 0; i < binary.length; i++) {
	 binary[i] = Integer.parseInt(ciphertext.substring(i, i + 1));
	 s.append(binary[i] ^ keystream[i]);
	}
	return this.toStr(s.toString());
 }

 int[] getKeystream(int length) {
	
	int[] regX = this.regX.clone();
	int[] regY = this.regY.clone();
	int[] regZ = this.regZ.clone();
	int[] keystream = new int[length];
	
	for (int i = 0; i < length; i++) {
	 
	 int maj = this.getMajority(regX[8] , regY[10] , regZ[10]);

	 
	 if (regX[8] == maj) {
		int newStart = regX[13] ^ regX[16] ^ regX[17] ^ regX[18];
		int[] temp = regX.clone();
		for (int j = 1; j < regX.length; j++)
		 regX[j] = temp[j - 1];
		regX[0] = newStart;
	 }

	 
	 if (regY[10] == maj) {
		int newStart = regY[20] ^ regY[21];
		int[] temp = regY.clone();
		for (int j = 1; j < regY.length; j++)
		 regY[j] = temp[j - 1];
		regY[0] = newStart;
	 }

	 
	 if (regZ[10] == maj) {
		int newStart = regZ[7] ^ regZ[20] ^ regZ[21] ^ regZ[22];
		int[] temp = regZ.clone();
		for (int j = 1; j < regZ.length; j++)
		 regZ[j] = temp[j - 1];
		regZ[0] = newStart;
	 }
	 keystream[i] = regX[18] ^ regY[21] ^ regZ[22];
	}
	return keystream;
 }

 private int getMajority(int x, int y, int z) {
	return x + y + z > 1 ? 1 : 0;
 }


 public int[] toBinary(String text) {
	StringBuilder s = new StringBuilder();
	for (int i = 0; i < text.length(); i++) {
	 String temp = Integer.toBinaryString(text.charAt(i));
	 for (int j = temp.length(); j < 8; j++)
		temp = "0" + temp;
	 s.append(temp);
	}
	String binaryStr = s.toString();
	int[] binary = new int[binaryStr.length()];
	for (int i = 0; i < binary.length; i++)
	 binary[i] = Integer.parseInt(binaryStr.substring(i, i + 1));
	return binary;
 }


 public String toStr(String binary) {
	StringBuilder s = new StringBuilder();
	for (int i = 0; i <= binary.length() - 8; i += 8)
	 s.append((char) Integer.parseInt(binary.substring(i, i + 8), 2));
	return s.toString();
 }

 static void printOptions() {
	System.out.println("------------------------------");
	System.out.println("* 1: Encrypt *");
	System.out.println("* 2: To decrypt, enter 2 *");
	System.out.println("* 3: Set new key *");
	System.out.println("* 4: Get keystream of length 64 *");
	System.out.println("* q: Quit *");
	System.out.println("------------------------------");
 }

 public static void main(String[] args) {
	
	A51 a51 = new A51();
	Scanner scanner = new Scanner(System.in);
	System.out.println("CS166 A5/1 Stream Cipher by John Tran");
	System.out.println("Input a 64-bit key: ");
	while (a51.getKey() == null) {
	 if (!a51.setKey(scanner.nextLine()))
		System.out.println("Invalid key. Make sure input is a 64-bit binary value");

	}

	String in;
	A51.printOptions();
	final String ANSI_RED = "\u001B[31m";
	final String ANSI_RESET = "\u001B[0m";
	while (!(in = scanner.nextLine()).equals("q")) {
	 if (in.equals("1")) {
		System.out.println("Input plain text");
		in = scanner.nextLine();
		System.out.println(ANSI_RED + "encrypt(" + in + ") = " + a51.encrypt(in) + ANSI_RESET);
	 } else
		if (in.equals("2")) {
		 System.out.println("Input cipher text");
		 in = scanner.nextLine();
		 System.out.println(ANSI_RED + "decrypt(" + in + ") = " + a51.decrypt(in) + ANSI_RESET);
		} else
		 if (in.equals("3")) {
	  	a51.key = null;
			System.out.println("Input a 64-bit key: ");
			while (a51.getKey() == null) {
			 if (!a51.setKey(scanner.nextLine())) {
				System.out.println("Invalid key. Make sure input is a 64-bit binary value");
			 }
			}
		 } else
			if (in.equals("4")) {
			 System.out.print(ANSI_RED + "keystream(64) = " + ANSI_RESET);
			 for (int i : a51.getKeystream(64))
				System.out.print(ANSI_RED + i +  ANSI_RESET);
			 System.out.println();
			}
	 A5.printOptions();
	}
 }
}
