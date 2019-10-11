import java.util.*;
class odd_composite{
	public static boolean isodd(int x){ 
        return x%2==0? false:true;
    }
    public static boolean isprime(int x){
        if (x<=1) {return false;}       
        else if(x==2){return true;}
        else if(x>2){
			for (int i = 2; i < (int)(Math.sqrt(x)+1); i++) {if(x%i==0){return false;}                
            } return true;
			}
		else{return false;}
    }
    public static boolean oddcomposite(int x){
        if (isodd(x)&&!isprime(x)) {return true;
                } else {return false;}
    
    }
    public static void main(String args[]){
		Scanner input=new Scanner(System.in);
		System.out.print("Enter an Integer : ");
		int x = input.nextInt();
		
		if(x<0){System.out.print(x+" is negative number");}
		else if(x==0){System.out.print(x+" is neither prime nor composite number");}
		else if (x==1){System.out.print(x+" is neither prime nor composite number");}
		else{
			if(oddcomposite(x)){System.out.print(x+" is positive odd composite number");}
			else{System.out.print(x+" is not positive odd composite number");}
			
			
			}
    
  
    }
    
    }
