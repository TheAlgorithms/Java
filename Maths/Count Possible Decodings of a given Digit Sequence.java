class TotalDecodingMessages{
    static int findWays(String data,int k){
        if( k== 0)
            return 1;
        int s = data.length() -k;
        if(data.charAt(s) == '0')
            return 0;
        
        int result = findWays(data, k-1);
        if( k>=2 && Integer.parseInt(data.substring(s,s+2)) <=26){
            result+= findWays(data, k-2);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while (tc -- > 0){
            int l = sc.nextInt();
            sc.nextLine();
            String msg = sc.nextLine();
            System.out.println(findWays(msg,msg.length()));
        }
    }
}