/*
After Governor's attack on prison, Rick found himself surrounded by walkers. They are coming towards him from all sides. Now, suppose Rick have infinite number of bullets with him. Suppose Rick need 1 bullet to kill each walker (yeah he is good in killing walkers. They need to be shot at head. See, how good he is). Now as soon as he kills 1 walker rest of the walkers move forward by 1 m. There are n walkers each at some distance from Rick. If any walker is able to reach Rick, he dies. Now, you need to tell if he survives or not. If he survives print "Rick now go and save Carl and Judas" else print "Goodbye Rick" followed by no.of walkers he was able to kill before he died in next line. One more thing Rick's gun can fire 6 shots without reload. It takes him 1 sec to reload and during this time walkers move 1 m forward.

[Input]
First line contains an integer t indicating number of test cases.

Next line contains an integer n denoting no.of walkers followed by n space separated integers denoting the distance of walkers from him.

[Output]
For each test case output one line denoting the answer as explained above.

[Constraints]
1<=t<=100
1<=n<=100000
1<=dis[i]<=50000
*/
Sample Input:
2
5
2 4 2 5 6
4
2 2 2 2
Sample Output:
Rick now go and save Carl and Judas
Goodbye Rick
2

SOLUTION 1:
import java.io.*;
import java.util.*;
 
class TestClass {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0){
            int size = Integer.parseInt(br.readLine());
            int[] arr = new int[size];
            String[] str = br.readLine().split(" ");
            for(int i = 0; i<size; i++){
                arr[i] = Integer.parseInt(str[i]);
            }
            Arrays.sort(arr);
            int dis = 1;
            int kills = 0;
            boolean alive = true;
            for(int i = 0; i<size; i++){
                if( dis > arr[i] ){
                    alive = false;
                    break;
                }else{
                    kills++;
                    dis++;
                    if( kills%6==0 ){
                        dis++;
                    }
                }
            }
            if(alive){
                System.out.println("Rick now go and save Carl and Judas");
            }else{
                System.out.println("Goodbye Rick");
                System.out.println(kills);
            }
        }
    }
}


SOLUTION 2:
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
class TestClass {
    public static void main(String args[] ) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine()); 
        for(int i =0; i<testCases; i++)
        {
            TreeMap<Integer,Integer> hash = new TreeMap();
            int n = Integer.parseInt(br.readLine());
            String input[] = br.readLine().split(" ");
            for(int j =0;j<n;j++)
            {
                int temp1 = Integer.parseInt(input[j]);
                if(hash.containsKey(temp1))
                {
                hash.put(temp1,hash.get(temp1)+1);
                }
                else
                hash.put(temp1,1);
            }
            int step = 0;
            int kill = 0;
            boolean flag = true;
            while(hash.size()>0)
            {
                if(kill!=0 && kill%6==0)
                step++;
                int temp = hash.firstKey();
               if(step==temp)
               {
                   flag = false;
                   System.out.println("Goodbye Rick");
                   System.out.println(kill);
                   break;
               }
                hash.put(temp,hash.get(temp)-1);
                step++;
                kill++;
                if(hash.get(temp)==0)
                hash.remove(temp);
            }
            if(flag)
            System.out.println("Rick now go and save Carl and Judas");
 
        }               
 
        
 
    }
}