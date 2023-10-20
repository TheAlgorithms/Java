package com.thealgorithms.others;
/**
 This is a simple two player Tic-Tac-Toe game programmed in Java
*/
class TicTacToe
{
    String user1,user2;
    char us1,us2;
    char [][]a={{'7','8','9'},{'4','5','6'},{'1','2','3'}};
    void userInput()
    {
        System.out.println("USER 1(X) details\n NAME:");
        user1=new java.util.Scanner(System.in).nextLine(); 
        us1='X';
        System.out.println("USER 2(O) details\n NAME:");
        user2=new java.util.Scanner(System.in).nextLine();  
        us2='O';
    }

    char win()
    {
        if(a[0][0]==a[1][1] && a[1][1]==a[2][2])//ltdiagnol
            return a[0][0];
        /*col check*/
        if(a[0][0]==a[1][0] && a[1][0]==a[2][0])
            return a[0][0];
        if(a[0][1]==a[1][1] && a[1][1]==a[2][1])
            return a[0][1];
        if(a[0][2]==a[1][2] && a[1][2]==a[2][2])
            return a[0][2];
        /*row check*/
        if(a[0][0]==a[0][1] && a[0][1]==a[0][2])
            return a[0][0];
        if(a[1][0]==a[1][1] && a[1][1]==a[1][2])
            return a[1][0];
        if( a[2][0]==a[2][1] && a[2][1]==a[2][2])
            return a[2][0];
        /* rt diagnol*/
        if(a[0][2]==a[1][1] && a[1][1]==a[2][0])
            return a[0][2];
        return 0;
    }

    boolean checkInput(char pl)//valid place
    {
        for(int i = 0; i < a.length; i++)
        {
            for(int j = 0; j < a.length; j++)
            {
                if(a[i][j] == pl)
                    return true;
            }
        }
        return false;
    }

    void gotoPlace(char pl, int n)
    {
        for(int i = 0; i < a.length; i++)
        {
            for(int j = 0; j < a.length; j++)
            {
                if(a[i][j] == pl)
                    if((n%2+1) == 1)
                        a[i][j] = 'X';
                    else
                        a[i][j] = 'O';
            }
        }
    }

    void display()
    {
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<a.length;j++)
            {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }

    void play()
    {
        System.out.println("USE NUMPAD FOR PLAYING!\n");
        userInput();
        for(int i=0;i<a.length*a.length;i++)
        {
            display();
            if(win() == 'X')
            {
                System.out.println("\n"+user1+" wins!");
                System.exit(0);
            }
            if(win() == 'O')
            {
                System.out.println("\n"+user2+" wins!");
                System.exit(0);
            }
            System.out.println("\n"+((i%2+1)==1?user1+" : ":user2+" : "));
            char place = new java.util.Scanner(System.in).next().charAt(0);
            if(checkInput(place))
            {
                gotoPlace(place,i);
            }
            else
            {   
                i--;
                continue;
            }
            System.out.println("\f");
        }
        display();
        System.out.println("\nGAME OVER!\nIT'S A TIE!");
        System.exit(0);
    }
    public static void main(String args[])
    {
        TicTacToe ob = new TicTacToe();
        ob.play();
    }
}
