class Solution {
    public static int StackPer(int n, int[] ip, int[] op) {
          
            Stack<Integer> st = new Stack<Integer>();
            int i;
            int j = 0;
            for(i = 0; i < n; i++){
                if(ip[i] != op[j]){
                    st.push(ip[i]);
                }
                else{
                    st.push(op[j]);
                    while(!st.isEmpty() && st.peek() == op[j]){
                        st.pop();
                        j++;
                    }
                    
                }
            }
            
            if(st.isEmpty()){
              return 0;
            }
            else{
              return 1;
            }
    }
}
public static void main(String args[])
    {
        int A[] = {1, 2, 3};
 
        int B[] = {2, 1, 3};
 
        int n = 3;
 
        if (StackPer(input, output, n))
            System.out.println("YES");
        else
            System.out.println("NO");
    }
