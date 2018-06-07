public class RomanToInt {
     int value(String a){
	        if(a.equals("I")){
	            return 1;
	        }else if(a.equals("V")){
	            return 5;
	        }else
	        if(a.equals("X")){
	            return 10;
	        }else
	        if(a.equals("L")){
	            return 50;
	        }else if(a.equals("C")){
	            return 100;
	        }else
	        if(a.equals("D")){
	            return 500;
	        }else
	        if(a.equals("M")){
	            return 1000;
	        }else{
	            return 0;
	        }
	    }
	public int romanToInt(String a1) {
	    int total = 0;
	    for(int i=0;i<a1.length();i++){
	        String s1 = a1.charAt(i)+"";
	        if(i!=a1.length()-1){
	            String s2 = a1.charAt(i+1)+"";
	            if(value(s1)>=value(s2)){
	                total+=value(s1);
	            }else{
	                total = total-value(s1)+value(s2);
	                i++;
	            }
	        }else{
	            total+=value(s1);
	        }
	    }
	    return total;
	}

		public static void main(String[] s){
			
			String romanVal = "XIV";
			System.out.println(new RomanToInt().romanToInt(romanVal));
		
		}
	
	}