class StringManipulation {
	public static void main (String [] args)
	{
		String target = "Java is Fun to learn";
		String s1 ="JAVA";
		String s2 ="Java";
		String s3 ="Hello Java";

		System.out.println("Char at position:" +target.charAt(2));
		System.out.println("Concat:" +target.concat(" yes"));
		System.out.println("Equal ignore:" +s2.equalsIgnoreCase(s1));
		System.out.println("equal case :" +s2.equals(s1));
		System.out.println("len:" +target.length());
		System.out.println("replace:" +target.replace("fun","easy"));
		System.out.println("substring:" +target.substring(8,12));
		System.out.println("lower:" +target.toLowerCase());
		System.out.println("upper:" +target.toUpperCase());
		System.out.println("trim:" +s3.trim());
		System.out.println("contains:" +target.contains(s2));
		char[] charArray = s2.toCharArray();
		System.out.println("size of char array:" +charArray.length);
		
	}
}