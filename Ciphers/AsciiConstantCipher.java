package cipher
/*
	This was created by @AlternateWalls

	This cipher takes in a message and a constant number, and converts the message to ascii values while adding the constant number to the ascii values.
	It's not very complex, but it takes a little while to decrypt and isn't easily recognizable.
	(This is a java version of my first ever Python program I wrote 6-7 years ago.)
*/
public class AsciiConstantCipher
{
	public static void main(String[] args) 
	{
		//Encrypt message
		double[] encryptedArray = AsciiConstantCipher.AsciiScrambleMessage("Testing to see if this works...", 117);

		//Print message array (encrypted)
		System.out.print("["); //Added for looks
		for (double i : encryptedArray)
		{
			System.out.print(i + ", ");
		}
		System.out.println("]"); //Added for looks

		//Decrypt message
		String message = AsciiConstantCipher.AsciiDecryptMessage(encryptedArray, 117);

		//Print out message
		System.out.println(message);
	}

    public static double[] AsciiScrambleMessage(String message, double user_constant)
    {
        double[] encrypted_message = new double[message.length()];
        for (int i = 0; i < message.length(); i++)
        {
			//Get ascii value of every character in message
			int asciiCharacter = message.charAt(i);

			//Add constant to ascii value
			asciiCharacter += user_constant;
					
			//Add character and space to return Strin
			encrypted_message[i] = asciiCharacter;
		}
		//Return array
		return encrypted_message;
    }

    public static String AsciiDecryptMessage(double array[], double constant)
    {
		String decrypted_string = "";
		for (int i = 0; i < array.length; i++)
		{
			//Get rid of constant
			double original_ascii = array[i] - constant;

			//Cast back to char and append to String
			decrypted_string += (char) original_ascii;
		}
		//Return String
		return decrypted_string;
    }
}
