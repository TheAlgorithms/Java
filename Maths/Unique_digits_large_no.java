package uniqueDigitsOfLargeNumber.java;    //you can ignore or comment off this line
import java.util.*;
public class UniqueDigitsOfLargeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
        System.out.print("Enter large number: ");
        String n=sc.next();
        String m= n;        
        int len= n.length();
        
  // used  nested for loop to replace duplicate elements with "*" and to get unique digits string
  //containing "*" at indices of duplicate elements
        
        for(int i=0;i<len;i++){
            char c= n.charAt(i);
            String New_string="";
            for(int j=0;j<(i+1);j++){
                New_string+=n.charAt(j);
            }
            for(int k=(i+1);k<len;k++){
                  if(c==n.charAt(k) && n.charAt(k)!='*'){
                      New_string+='*';
                    }
                  else{
                      New_string+=n.charAt(k);
                    }
            }
            n=New_string;
            System.out.println((i+1)+" Iteration :  " + "N  = "+n);
        }
        n=n.replace("*","");         //replaced "*" with "" so that string contains only unique digits
        String u =n;  
        
        String lrg="";              // to store the largest number possible in string lrg
        int len2=n.length();
        
  // used nested for loop to find largest number possible from the unique digits and convert string into 
        
        for(int q=0;q<len2;q++){
            int max=0;
            for(int r=0;r<len2;r++){
                if(n.charAt(r)!='#'){
                   if(max < Integer.parseInt("" + n.charAt(r))){     // checking for max digits 
                        max = Integer.parseInt("" + n.charAt(r));    //converting string lrg into a number using Integer.parseInt()
                    }
                }
            }
            lrg+=max;
            n=n.replace(""+ max, "#");    // replaced the max value digit in the string n with "#"   
            System.out.println((q+1)+"  Iteration :         lrg: "+lrg+"           str = "+n);
        }
        sc.close();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.print("The unique digits present in "+m+" : ");
        for(int s=0;s<u.length();s++){
            if(s==0){
                System.out.print(u.charAt(s));}
            else{
                System.out.print(" , "+u.charAt(s));
            }
             
        }
        System.out.println();
        System.out.println("the largest number possible out of these unique digits is "+lrg);
	}

}
