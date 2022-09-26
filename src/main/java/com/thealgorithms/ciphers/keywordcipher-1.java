// Keyword Cipher
// Java

class GFG
{
	static String encoder(char[] key)
	{
		String encoded = "";

		boolean[] arr = new boolean[26];

		for (int i = 0; i < key.length; i++)
		{
			if (key[i] >= 'A' && key[i] <= 'Z')
			{
				if (arr[key[i] - 65] == false)
				{
					encoded += (char) key[i];
					arr[key[i] - 65] = true;
				}
			}
			else if (key[i] >= 'a' && key[i] <= 'z')
			{
				if (arr[key[i] - 97] == false)
				{
					encoded += (char) (key[i] - 32);
					arr[key[i] - 97] = true;
				}
			}
		}


		{
			if (arr[i] == false)
			{
				arr[i] = true;
				encoded += (char) (i + 65);
			}
		}
		return encoded;
	}

	
	static String cipheredIt(String msg, String encoded)
	{
		String cipher = "";

		for (int i = 0; i < msg.length(); i++)
		{
			if (msg.charAt(i) >= 'a' && msg.charAt(i) <= 'z')
			{
				int pos = msg.charAt(i) - 97;
				cipher += encoded.charAt(pos);
			}
			else if (msg.charAt(i) >= 'A' && msg.charAt(i) <= 'Z')
			{
				int pos = msg.charAt(i) - 65;
				cipher += encoded.charAt(pos);
			}
			else
			{
				cipher += msg.charAt(i);
			}
		}
		return cipher;
	}

	// Driver code
	public static void main(String[] args)
	{
		String key;
		key = "Computer";
		System.out.println("Keyword : " + key);
		String encoded = encoder(key.toCharArray());
		String message = "GeeksforGeeks";
		System.out.println("Message before Ciphering : " + message);

		System.out.println("Ciphered Text : " + cipheredIt(message,
				encoded));
	}
}


