public class matrix_multiplication_dc {
    public int[][] add(int[][] a,int[][] b)
    {   int n=a.length;
        int[][] c=new int[n][n];
        for(int k=0;k<n;k++)
        {
            for(int m=0;m<n;m++)
            {
                c[k][m]=a[k][m]+b[k][m];
            }
        }
        return c;
    }
    public int[][] multiply(int[][] a,int[][] b)
    {
        int n=a.length;
        if(n==1)
        {   int e[][]=new int[1][1];
            e[0][0]=a[0][0]*b[0][0];
            return e;
        }
        int[][] d=new int[n][n];
        int[][] a11=new int[n/2][n/2];
        int[][] a12=new int[n/2][n/2];
        int[][] a21=new int[n/2][n/2];
        int[][] a22=new int[n/2][n/2];
        int[][] b11= new int[n/2][n/2];
        int[][] b12=new int[n/2][n/2];
        int[][] b21=new int[n/2][n/2];
        int[][] b22=new int[n/2][n/2];
        for(int u=0;u<n/2;u++)
        {
            for(int y=0;y<n/2;y++)
            {
                a11[u][y]=a[u][y];
                b11[u][y]=b[u][y];
                a12[u][y]=a[u][y+n/2];
                b12[u][y]=b[u][y+n/2];
                a21[u][y]=a[u+n/2][y];
                b21[u][y]=b[u+n/2][y];
                a22[u][y]=a[u+n/2][y+n/2];
                b22[u][y]=b[u+n/2][y+n/2];
            }


        }

        int[][] d11=add(multiply(a11,b11),multiply(a12,b21));
        int[][] d12 =add(multiply(a11,b12),multiply(a12,b22));
        int[][] d21 =add(multiply(a21,b11),multiply(a22,b21));
        int[][] d22=add(multiply(a21,b12),multiply(a22,b22));
        for(int u=0;u<n/2;u++)
        {
            for(int y=0;y<n/2;y++)
            {
                d[u][y]=d11[u][y];
                d[u][y+n/2]=d12[u][y];
                d[u+n/2][y]=d21[u][y];
                d[u+n/2][y+n/2]=d22[u][y];
            }
        }
        return d;
    }

    public static void main(String args[])
    {
        //you can take matrix input of user also by using scanner or buffer reader.
        int a[][] = { {11,2 ,3, 4, -15, 6 ,17, 8},            //initializing matrix 1
                           {3 ,-7 ,16 ,5 ,14 ,3 ,22 ,1},
                    {22 ,3 ,6 ,25, 24 ,23 ,12 ,3},
                    {7 ,6 ,35 ,4 ,23 ,2 ,-11, 22},
                    {25 ,6 ,7 ,28 ,11 ,-12, 3 ,4},
                   {6 ,-15, 8 ,7 ,24, 3 ,23, -4},
                    {-11 ,2 ,2 ,-11 ,5 ,6 ,27 ,18},
                   {12 ,7 ,24 ,15 ,6 ,17 ,8 ,-19}};



            int b[][] = { {5 ,12, 8, -11 ,4 ,18, 6 ,2},        //initializing matrix 2
                       {-23 ,15 ,9 ,7 ,22 ,11 ,14 ,16},
                    {-5 ,-18 ,23 ,28 ,-34 ,24 ,6 ,9},
                    {7 ,14 ,11 ,6 ,9, -4, -7 ,6},
                    {4 ,5 ,-6 ,-7, 8 ,9 ,10 ,-11},
                    {12 ,-13, 14 ,15 ,16 ,-17, 18, 19},
                    {-20 ,21 ,22 ,-23, 24 ,-25, 26, 27},
                    {4 ,3 ,2 ,1 ,9 ,8 ,7 ,6}};
        System.out.println(b.length);
        matrix_multiplication_dc ob=new matrix_multiplication_dc();
        int x[][]=ob.multiply(a,b);
        for(int q=0;q<a.length;q++)
        {     System.out.println(" ");
            for(int w=0;w<a.length;w++)
            {
                System.out.print(x[q][w]+" ");
            }
        }
    }
}