Public class Roman_to_Integer {
    public int romanToInt(String s) {
        int result = 0;
        char[] arr1 = s.toCharArray();
        for(char ch: arr1){
            switch (ch) {
                case 'I' -> result += 1;
                case 'V' -> result += 5;
                case 'X' -> result += 10;
                case 'L' -> result += 50;
                case 'C' -> result += 100;
                case 'D' -> result += 500;
                case 'M' -> result += 1000;
                default -> {
                }
            }
        }
        
        for(int i=0; i<arr1.length-1;i++){
            if(arr1[i]==('I')&&(arr1[i+1]==('V')||arr1[i+1]==('X'))){
                result-=2;
            }else if(arr1[i]==('X')&&(arr1[i+1]==('L')||arr1[i+1]==('C'))){
                result-=20;
            }else if(arr1[i]==('C')&&(arr1[i+1]==('D')||arr1[i+1]==('M'))){
                result-=200;
            }
        }
        
         return result;
    }
}
