import java.util.Arrays;
public class piglatinGame 
{
    public static void main(String[] args) 
    {
         try 
         {
             if (args[0]  == null )
             ;
         }
         
         catch (ArrayIndexOutOfBoundsException e) 
         {
           System.out.println("Enter A valid string");
           System.exit(1);
            //TODO: handle exception
         }
            
      String s = args[0];
       s = s + "-ay" ;
     char []ch = s.toCharArray();
     int k =ch.length;
       s= null ;
     
     char temp=ch[0];
     int t=  0 ;  
      while(t<k-2)     
        ch[t]=ch[++t];

        ch[k-3] = temp;
       System.out.println(ch);

    }
}
