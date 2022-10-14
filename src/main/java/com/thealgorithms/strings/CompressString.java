package com.thealgorithms.strings;

public class CompressString {
    public static String getCompressedString(String str) {
        String s2=new String("");
        int count=0;
   for(int i=0;i<str.length();i++)
   {
       count=1;



       s2=s2+(str.charAt(i));

       while(i+1<str.length() && str.charAt(i+1)==str.charAt(i)  )
       {
           count++;

           i++;

       }
       if(count>1){
             s2=s2.concat(count+"");

       }

    
       }
       return s2;
     
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

    

