
public class JumpSearch {
    public static int min(int a, int b){
        return a<b ? a:b;   //return a if a<b, b if b<a
    }
    public static int jmpSearch(int[] input,int value){
        int idx = 0;
        int step = (int) Math.sqrt(input.length);
        
        while(input[min(step,input.length)-1]<value){
            idx = step;
            step = step+(int) Math.sqrt(input.length);
            if(idx>=input.length){
                return -1;
            }
        }
        while(input[idx]<value){
            idx++;
            if(idx==min(step,input.length)){
                return -1;
                }
            }
            if(input[idx]==value){
                return idx;
            }
        return -1;
    }
     public  static void main(String[]args) {
        int  tab[]=new int[]{1,2,3,5,7,11,13,17,19};
        System.out.println(jmpSearch(tab,13));
    }
}

