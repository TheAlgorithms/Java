// This is a String matching algorithm.
/* You are given a string str and pattern ptr and you need to find the list of all starting indices where they are found in the main string str.
    
    Sample : str="ababa"
             pat="aba"
    
    output: [0,2]
*/
 
   private static List<Integer> KMP(String str,String pat){


    	int strlen=str.length(),patlen=pat.length();


         /*
         We maintain an array to store that tells that there is a substring in given pattern
         starting from the beginning in which there is a suffix same as prefix
         */

    	int[] preSuffArr=new int[patlen];


    	Arrays.fill(preSuffArr,-1);


    	int ptr1=0,ptr2=1;


        //Traversal through the pattern to fill preSuffArray.

    	while(ptr2<patlen){

    		if(pat.charAt(ptr1)==pat.charAt(ptr2)){
//If both characters are the same,check if next characers are same.
    			preSuffArr[ptr2]=ptr1;

    			ptr1++;

    			ptr2++;

    		}else if(ptr1>0){

                        // if prev character has a prefix then we use it.
    			ptr1=preSuffArr[ptr1-1]+1;

    		}else{

                        //if we are at 1st character,then we can't use previous patterns,so ptr2+=1.
     			ptr2++;

    		}

    	}

        
        // A list to store occurences of the pattern.
    	
List<Integer> occurences=new ArrayList<>();


    	
int strptr=0,patptr=0;


    	while(strptr<strlen){

    		if(str.charAt(strptr)==pat.charAt(patptr)){

                        // if charcter in string equals character in pattern.
    			if(patptr==patlen-1){

                                //if we are at last charcter in pattern => we found a substring in str equals to pattern.
      				occurences.add(strptr-patlen+1);

    				patptr=preSuffArr[patptr-1]+1;

    			}else{

    				patptr++;

    				strptr++;

    			}

    		}else if(patptr>0){

    			patptr=preSuffArr[patptr-1]+1;

    		}else{

    			strptr++;

    		}

    	}

    	
return occurences;

    }