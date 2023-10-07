class Trio{
    int r,c,d;
    Trio(int r,int c,int d)
    {
        this.r=r;
        this.c=c;
        this.d=d;
    }
}
class Solution1 {
    public int[][] updateMatrix(int[][] mat) {
        int[][] ans=new int[mat.length][mat[0].length];
        for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[i].length;j++)
            {
                if(mat[i][j]==1)
                {
                    ans[i][j]=function(i,j,mat);
                }
                else
                {
                    ans[i][j]=0;
                }
            }
        }
        return ans;
    }
    public int function(int row,int col,int[][] mat)
    {
        Queue<Trio> q=new LinkedList<>();
        int[][] vis=new int[mat.length][mat[0].length];
        for(int[] v:vis)
        {
            Arrays.fill(v,-1);
        }
        q.offer(new Trio(row,col,0));
        int minDis=Integer.MAX_VALUE;
        while(!q.isEmpty())
        {
            int cd=q.peek().d;
            int r=q.peek().r;
            int c=q.peek().c;
            vis[r][c]=1;
            q.poll();
            if(mat[r][c]==0)
            {
                return cd;
            }
            else
            {
                if(r-1>=0 && vis[r-1][c]==-1)
                {
                    q.offer(new Trio(r-1,c,cd+1));
                }
                if(r+1<mat.length && vis[r+1][c]==-1)
                {
                   q.offer(new Trio(r+1,c,cd+1)); 
                }
                if(c-1>=0 && vis[r][c-1]==-1)
                {
                    q.offer(new Trio(r,c-1,cd+1));
                }
                if(c+1<mat[0].length && vis[r][c+1]==-1)
                {
                    q.offer(new Trio(r,c+1,cd+1));
                }
            }
        }
        return 0;
    }
}
//leetcode problem link : https://leetcode.com/problems/01-matrix/