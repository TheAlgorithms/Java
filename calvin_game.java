import java.util.*;
class calvin_game
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        int k = sc.nextInt()-1;
        int i;
        for(i=0;i<n;i++)
          arr[i] = sc.nextInt();

        int sum = 0;
        int sum1 =0;
        int flag = 0,flag1=0,flag2=0;
        int r=n-1;
        for(i=n-1;i > 0;)
        {
            if(arr[i] <= 0 && flag==0)
            {
              System.out.println("hi");
              i--;
              continue;
            }
            else
            {
                flag=1;
                System.out.print("arr " + i + " = "+arr[i]);
                sum += arr[i];
                if(sum1 < 0)
                {
                  sum1 = 0;
                  flag1++;
                }
                if(arr[i-1]<=0)
                {
                    i = (arr[i-2] > arr[i-1] ? i-1 : i);
                }
                i = i-1;
                if(i >= 0)
                  sum1+=arr[i];
                if(flag1!=flag2)
                {
                    r = i;
                    flag1 = flag2;
                }
            }
            System.out.println(" sum = "+sum + "   " + " sum1 = " + sum1);
        }
        System.out.println("******************************");
        System.out.println("r = "+r);
        for(i=k;i<r;)
        {
            System.out.print("arr " + i + " = "+arr[i]);
            if(arr[i+1] > 0)
            {
              sum += arr[i];
              i++;
              sum1 +=arr[i];
            }
            else
            {
                sum += arr[i];
                i =  arr[i+2] > arr[i+1] ? i+1 : i;
                i++;
                sum1 += arr[i];
            }
            System.out.println(" sum = "+sum + "   " + " sum1 = " + sum1);
        }
    }
}
