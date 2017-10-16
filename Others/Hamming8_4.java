
public class Hamming8_4 {
	
	public static void main(String[] args) {
		System.out.println(new String(decode(flipBit(encode("The content of this string is first encoded then a bit each byte is flipped and then decoded to original".getBytes())))));
	}
	public static int[] lookuptable = {
		0,210,85,135,153,75,204,30,225,51,180,102,120,170,45,255 //lookup table for
	};
	
	public static byte[] encode(byte[] input) {
		byte[] output = new byte[input.length*2];
		for(int i=0;i<input.length*2;i++)
			output[i] = (byte) lookuptable[((i%2==0?input[i/2]:input[i/2]>>>4)&0xF)]; //look up the 8bit value for each 4bit of each byte
		return output;
	}
	
	public static byte[] flipBit(byte[] input) {
		byte[] output = new byte[input.length];
		for(int i=0;i<input.length;i++)
			output[i] = (byte)((input[i]&0xFF)^ (1<<(int)(Math.random()*8))); //flip a random bit in each byte
		return output;
	}
	
	public static byte[] decode(byte[] input) {
		byte[] output = new byte[input.length/2];
		for(int i=0;i<input.length;i++) {
			int syn = 0;
			for (int j=1; j<=8; j++) //calculate syndrome
			   if (((input[i]>>(j-1))&1) != 0)
				   syn = syn^j;
			input[i] = (byte) (input[i] ^ (1<<(syn%8)-1)); //correct errors if found
			int v = ((input[i]&32)!=0?8:0)|((input[i]&8)!=0?4:0)|((input[i]&4)!=0?2:0)|((input[i]&2)!=0?1:0); //reduce 8bit to 4
			output[i/2] = (byte) ((i%2==0?v:v<<4|output[i/2])&0xFF); //but 4 bit into byte
		}
		return output;
	}
	

}
