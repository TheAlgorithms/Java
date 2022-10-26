//Java Program for Shortest Job First (SJF) Scheduling (Non-Preemptive) algo.

import java.util.*;
 
public class SJF {
public static void main(String args[])
{
Scanner sc = new Scanner(System.in);
System.out.println ("enter no of process:");
int n = sc.nextInt();
int pid[] = new int[n];
int at[] = new int[n]; // at means arrival time
int bt[] = new int[n]; // bt means burst time
int ct[] = new int[n]; // ct means complete time
int ta[] = new int[n]; // ta means turn around time
int wt[] = new int[n];  //wt means waiting time
int f[] = new int[n];  // f means it is flag it checks process is completed or not
int st=0, tot=0;
float avgwt=0, avgta=0;
 
for(int i=0;i<n;i++) //for loop for brust and arrival time
{
System.out.println ("enter process " + (i+1) + " arrival time:");
at[i] = sc.nextInt();
System.out.println ("enter process " + (i+1) + " brust time:");
bt[i] = sc.nextInt();
pid[i] = i+1;
f[i] = 0;
}
  
boolean a = true; // a for all true values
while(true)
{
int c=n, min=999;
if (tot == n) // total no of process = completed process loop will be terminated
break;
for (int i=0; i<n; i++)
{
/*
* If i'th process arrival time <= system time and its flag=0 and burst<min
* That process will be executed first
*/
if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
{
min=bt[i];
c=i;
}
}
/* If c==n means c value can not updated because no process arrival time < system time so we increase the system time */
if (c==n)
st++;
else
{
ct[c]=st+bt[c];
st+=bt[c];
ta[c]=ct[c]-at[c];
wt[c]=ta[c]-bt[c];
f[c]=1;
tot++;
}
}
System.out.println("\npid  arrival brust  complete turn waiting");
for(int i=0;i<n;i++)
{
avgwt+= wt[i];
avgta+= ta[i];
System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+ta[i]+"\t"+wt[i]);
}
System.out.println ("\naverage tat is "+ (float)(avgta/n)); // Final output schow avg tat and avg wt time.
System.out.println ("average wt is "+ (float)(avgwt/n));
sc.close();
}
}
/* output :

enter no of process:
3
enter process 1 arrival time:
0
enter process 1 brust time:
3
enter process 2 arrival time:
0
enter process 2 brust time:
1
enter process 3 arrival time:
0
enter process 3 brust time:
2

pid arrival brust complete turn waiting
1    0       3      6       6     3
2    0       1      1       1     0
3    0       2      3       3     1

average tat is 3.3333333
average wt is 1.333334
*/













