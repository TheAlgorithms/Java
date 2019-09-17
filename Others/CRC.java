import java.util.*;

class CRC{
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String divisor = "10001000000100001";
		System.out.print("Enter data bits: ");
		String data = in.nextLine();
		System.out.println();
		String modifiedData = "0" + data;
		for(int i = 0; i < 16; ++i){
			modifiedData += "0";
		}
		String checksum = crc(divisor, modifiedData);
		System.out.println("Checksum generated according to data = " + checksum + "\n");
		System.out.print("Enter data to be transmitted: ");
		modifiedData = in.nextLine();
		modifiedData = "0" + modifiedData;
		//modifiedData = "0" + data + checksum;
		//modifiedData = Integer.toBinaryString(Integer.parseInt(modifiedData, 2) + Integer.parseInt(checksum, 2));
		//System.out.println(modifiedData);
		checksum = crc(divisor, modifiedData);
		if(checksum.equals("0000000000000000")){
			System.out.println("\nNo error in transmitted data!\n");
		}else{
			System.out.println("\nError in transmitted data!\n");
		}
		//System.out.println("Checksum = " + checksum);
	}

	static String crc(String divisor, String data){
		//System.out.println(data);
		String subtractedFrom = data.substring(0, 18);
		//System.out.println(subtractedFrom);
		String remainingData = data.substring(18, data.length());
		String afterSubtraction = "";
		//System.out.println(remainingData);
		int k, l = 0;
		for(int i = 0; i <= remainingData.length(); ++i){
			char leftMost = subtractedFrom.charAt(1);
			afterSubtraction = "";
			//System.out.println(leftMost);
			if(leftMost == '1'){
				k = 1;
				for(int j = 0; j < divisor.length(); ++j){
					//System.out.println("k =" + k + "j =" + j + "subFrom = " + subtractedFrom.charAt(k) + "Div = " + divisor.charAt(j));
					afterSubtraction += xor(subtractedFrom.charAt(k), divisor.charAt(j));
					//System.out.println(afterSubtraction);
					++k;
				}
			}
			if(leftMost == '0'){
				k = 1;
				for(int j = 0; j < divisor.length(); ++j){
					//System.out.println("k =" + k + "j =" + j + "subFrom = " + subtractedFrom.charAt(k) + "Div = 0");
					afterSubtraction += xor(subtractedFrom.charAt(k), '0');
					//System.out.println(afterSubtraction);
					++k;
				}
			}
			subtractedFrom = afterSubtraction;
			if(l < remainingData.length()){
				subtractedFrom += remainingData.charAt(l);
			}
			++l;
			//System.out.println(subtractedFrom);
		}
		return subtractedFrom.substring(1, subtractedFrom.length());
	}
	
	static String xor(char A, char B){
		int a = Integer.valueOf(String.valueOf(A));
		int b = Integer.valueOf(String.valueOf(B));
		return String.valueOf(a ^ b);
	}

}
