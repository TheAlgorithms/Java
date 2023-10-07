class Pair{
      int row,col,tm;
      Pair(int row,int col,int tm)
      {
          this.row=row;
          this.col=col;
          this.tm=tm;
      }
}
class Solution2 {
    public int orangesRotting(int[][] grid) {
        int[][] vis=new int[grid.length][grid[0].length];
        int cntFresh=0;
         Queue<Pair> q=new LinkedList<>();
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[i].length;j++)
            {
                if(grid[i][j]==2)
                {
                    q.offer(new Pair(i,j,0));
                    vis[i][j]=2;
                }
                else
                {
                    vis[i][j]=0;
                }
                 if(grid[i][j]==1)
                {
                    cntFresh++;
                }
            }
        }
        int cnt=0;
        int t=0;
        int[] dr={-1,0,+1,0};
        int[] dc={0,+1,0,-1};
        while(!q.isEmpty())
        {
            int r=q.peek().row;
            int c=q.peek().col;
            int tm=q.peek().tm;
            q.poll();
            if(t!=tm)
            {
                t=tm;
            }
            for(int i=0;i<4;i++)
            {
                int nrow=r+dr[i];
                int ncol=c+dc[i];
                if(nrow>=0 && nrow<grid.length && ncol>=0 && ncol<grid[0].length && vis[nrow][ncol]!=2 && grid[nrow][ncol]==1)
                {
                    vis[nrow][ncol]=2;
                    q.offer(new Pair(nrow,ncol,t+1));
                    cnt++;
                }
            }
        }
        if(cnt!=cntFresh)
        {
            return -1;
        }
        return t;
    }
}
//leetcode problem link : https://leetcode.com/problems/rotting-oranges/