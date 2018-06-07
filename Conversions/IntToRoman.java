public class IntToRoman {
    
    	int value(int toPret){
	    if(toPret>=1000){
	        return 1000;
	    }else if(1000>toPret && toPret>=900){
	        return 900;
	    }else if(900>toPret && toPret>=500){
	        return 500;
	    }else if(500>toPret && toPret>=400){
	        return 400;
	    }else if(400>toPret && toPret>=100){
	        return 100;
	    }else if(100>toPret && toPret>=90){
	        return 90;
	    }else if(90>toPret && toPret>=50){
	        return 50;
	    }else if(50>toPret && toPret>=40){
	        return 40;
	    }else if(40>toPret && toPret>=10){
	        return 10;
	    }else if(10>toPret && toPret>=9){
	        return 9;
	    }else if(9>toPret && toPret>=5){
	        return 5;
	    }else if(5>toPret && toPret>=4){
	        return 4;
	    }else if(4>toPret && toPret>=1){
	        return 1;
	    }else{
	        return 0;
	    }
	}
    
    String valStr(int a){
        if(a==1000){
            return "M";
        }
        if(a==900){
            return "CM";
        }
        if(a==500){
            return "D";
        }
        if(a==400){
            return "CD";
        }
        if(a==100){
            return "C";
        }
        if(a==90){
            return "XC";
        }
        if(a==50){
            return "L";
        }
        if(a==40){
            return "XL";
        }
        if(a==10){
            return "X";
        }
        if(a==9){
            return "IX";
        }
        if(a==5){
            return "V";
        }
        if(a==4){
            return "IV";
        }
        if(a==1){
            return "I";
        }
        
        return "";
    }
    
    StringBuffer str = new StringBuffer();
	public String intToRoman(int a) {
	
    int toRepeat = value(a);
    String corString = valStr(toRepeat);
    for(int i =0;i<(a/toRepeat);i++){
        str.append(corString);
    }
    if((a%toRepeat)>0)
        intToRoman(a%toRepeat);
    return str.toString();
	   
	}
	
	
	public static void main(String[] s){
			
			int intVal =  11128;
			System.out.println(new IntToRoman().intToRoman(intVal));
		
		}
	
}