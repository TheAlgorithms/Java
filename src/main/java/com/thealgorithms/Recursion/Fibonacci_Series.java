package Practice;
public class Fibonacci_Series{
//    "a", "b", "c" are the three consecutive numbers in a fibonacci series.
//    "n" is a number in the series till which we want our series to be printed.
    public static void fibbonacci_series(int a,int b,int c,int n){
//        This is printing our series during each recursive call.
        System.out.print(a+",");
//        This is the base case in which when "c" will be equal to the number till which we want the series i.e "n".
        if(c==n){
            System.out.print(b+","+c);
            return;
        }
        //   Now comes the fun part, the following recursion has taken place in which "b" has taken a's place and "c" has taken b's place.

        fibbonacci_series(b, c, c+b,n);
    }
    //   Let's understand this with an example:-

    //   Consider a = 0, b = 1, c = 1 and n = 5.
    // 1.) "0" will get printed due the command at line 6.
    // 2.) Then as c!=n the recursive call will be done and the same method will be run but with the parameters a=1(previously b =1),b=1(previously c=1),c=2(previous "c"+ previous "b")
    // 3.) Then again "1" will get printed due to the print command.
    // 4.) Again c!=n second recursive call will take place with parameters a=1,b=2,c=3.
}
