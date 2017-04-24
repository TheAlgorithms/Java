public static void main(String[] args) throws IOException {
	  Reader in=new Reader();
	  int t1=in.nextInt();
        for(int gj=0;gj<t1;gj++)
	{
        int n=in.nextInt();
        int m=in.nextInt();
        long w[][]=new long [n+1][n+1];
        for (long[] row: w)
            Arrays.fill(row, 1000000l);
         for(int i=0;i<m;i++){
             int x=in.nextInt(),y=in.nextInt();
             long cmp=in.nextLong();
             if(w[x][y]>cmp){
            	w[x][y]=cmp; w[y][x]=cmp;
             }
	}
         Stack <Integer> t=new Stack<Integer>();
          int src=in.nextInt();
        for(int i=1;i<=n;i++){
        	if(i!=src){t.push(i);}}
        Stack <Integer> p=new Stack<Integer>();
        p.push(src);
        w[src][src]=0;
         while(!t.isEmpty()){int min=989997979,loc=-1;
        for(int i=0;i<t.size();i++){
        	w[src][t.elementAt(i)]=Math.min(w[src][t.elementAt(i)],w[src][p.peek()]
                +w[p.peek()][t.elementAt(i)]);
            if(w[src][t.elementAt(i)]<=min){
            	min=(int) w[src][t.elementAt(i)];loc=i;}
        }
        p.push(t.elementAt(loc));t.removeElementAt(loc);}
         for(int i=1;i<=n;i++){
        	 if(i!=src && w[src][i]!=1000000l){System.out.print(w[src][i]+" ");}
        	 else if(i!=src){System.out.print("-1"+" ");}
         }System.out.println();
         }
  }
