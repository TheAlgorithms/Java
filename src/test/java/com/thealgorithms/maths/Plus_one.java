
//        You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. 
//        The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
//        Increment the large integer by one and return the resulting array of digits.

class Plus_one {
    public int[] plusOne(int[] digits) {
        String number = "";
        boolean leading_one = false;
        for(int i=digits.length-1; i>=0 ;i--){
            int current_digit = digits[i];
            int sum = 0;
            if(i== digits.length-1){
                if(current_digit==9){
                    number += 0+"";
                    leading_one = true;
                }else{
                    sum = current_digit+1;
                    number += sum+"";
                    leading_one = false;
                }
            }else{
                if(leading_one&&(current_digit==9)){
                    number += 0+"";
                    leading_one = true;
                }else if(leading_one&&(current_digit<9)){
                    sum = current_digit+1;
                    number += sum+"";
                    leading_one = false;
                }else{
                    number += current_digit+"";
                    leading_one = false;
                }
            }
            
            if(i==0&&leading_one){
                number+=1+"";
            }
        }
        
        int num[] = new int[number.length()];
        int j = number.length()-1;
        for(int i=0; i<number.length();i++){
            num[i] = number.charAt(j)-48;
            j--;
        }
        
        return num;
        
    }
}
