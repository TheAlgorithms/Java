//considering the arrival time of all processes are 0
import java.util.Scanner; 
public class ProcessScheduling
{
	public static void main(String args[])
	{
		int i, n, option;	
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of processes:");
		n = sc.nextInt();
		int burst[] = new int[n];
		for(i=0; i<n; i++)
		{
			System.out.print("Enter burst time of process "+(i+1)+": ");
			burst[i] = sc.nextInt();
		}
		do
		{
			System.out.println("Non preemptive process scheduling algorithms:");
			System.out.print("1.FCFS\n2.SJF,SRTF\n3.Round Robin\n4.Priority\n5.Exit\nEnter your choice:");
			option = sc.nextInt();
			Algorithms algo = new Algorithms(n);		
			switch(option)
			{
				case 1:algo.fcfs(n, burst);
						break;
				case 2:algo.sjf(n, burst);
						break;
				case 3:algo.roundRobin(n, burst);
						break;
				case 4:algo.priority(n, burst);
						break;
				case 5:break;
				default:System.out.println("Invalid choice");
			}
		}while(option!=5);
	}
}
class Algorithms
{
	float total_waiting_time, total_turn_time, avgwait, avgturn;
	int turnaround[], waiting[];
	Scanner sc = new Scanner(System.in);
	public Algorithms(int n)
	{
		total_waiting_time = 0;
		total_turn_time = 0;
		turnaround = new int[n];
		waiting = new int[n];
	}
	public void fcfs(int n, int burst[])
	{
		int i;
		waiting[0] = 0;
		for(i=1; i<n; i++)
		{
			waiting[i] = burst[i-1] + waiting[i-1];
		}
		for(i=0; i<n; i++)
		{
			turnaround[i] = waiting[i] + burst[i];
			total_waiting_time = total_waiting_time + waiting[i];
			total_turn_time = total_turn_time + turnaround[i];
		}
		avgwait = total_waiting_time/n;
		avgturn = total_turn_time/n;
		System.out.println("Average waiting time is "+avgwait);
		System.out.println("Average turnaround time is "+avgturn);
	}
	public void sjf(int n, int burst[])
	{
		int i, j, temp;
		int temp_burst[] = new int[n];
		for(i=0; i<n; i++)
		{
			temp_burst[i] = burst[i];
		}
		for(i=0; i<n-1; i++)
		{
			for(j=0; j<n-i-1; j++)
			{
				if(burst[j] > burst[j+1])
				{
					temp=temp_burst[j];		 
					temp_burst[j]=temp_burst[j+1];	 
					temp_burst[j+1]=temp;
				}
			}			
		}
		waiting[0] = 0;
		for(i=1; i<n; i++)
		{
			waiting[i] = temp_burst[i-1] + waiting[i-1];
		}
		for(i=0; i<n; i++)
		{
			turnaround[i] = waiting[i] + temp_burst[i];
			total_waiting_time = total_waiting_time + waiting[i];
			total_turn_time = total_turn_time + turnaround[i];
		}
		avgwait = total_waiting_time/n;
		avgturn = total_turn_time/n;
		System.out.println("Average waiting time is "+avgwait);
		System.out.println("Average turnaround time is "+avgturn);
	}

	public void roundRobin(int n, int burst[])
	{
		int i, quantam, count, temp, tempwait = 0;
		int remaining_burst[] = new int[n];
		System.out.print("Enter quantam time: ");
		quantam = sc.nextInt();
		for(i=0; i<n; i++)
		{
			remaining_burst[i] = burst[i];
		}
		while(true)
		{
			for (i=0,count=0;i<n;i++)
      		{
        		temp = quantam;
        		if(remaining_burst[i] == 0)
        		{
          			count++;
          			continue;
        		}
        		if(remaining_burst[i] >= quantam)
				{
					remaining_burst[i]= remaining_burst[i] - quantam;
				}
        		else if(remaining_burst[i]>=0 && remaining_burst[i] < quantam)
          		{
            		temp = remaining_burst[i];
            		remaining_burst[i] = 0;
          		}
          		tempwait = tempwait + temp;
          		turnaround[i] = tempwait;
			}
      		if(n == count)
      		break;
		}
		for(i=0; i<n; i++)
        {
            waiting[i] = turnaround[i] - burst[i];
            total_waiting_time = total_waiting_time + waiting[i];
			total_turn_time = total_turn_time + turnaround[i];
		}
		avgwait = total_waiting_time/n;
		avgturn = total_turn_time/n;
		System.out.println("Average waiting time is "+avgwait);
		System.out.println("Average turnaround time is "+avgturn);
	}
	public void priority(int n, int burst[])
	{
		int i,j,temp;
		int priorities[][] = new int[n][2];
		int temp_burst[] = new int[n];
		for(i=0; i<n; i++)
		{
			System.out.print("Enter priority of process "+(i+1)+": ");
			priorities[i][1] = sc.nextInt();
			priorities[i][0] = i+1;
			temp_burst[i] = burst[i];
		}
		for(i=0; i<n-1; i++)
		{
			for(j=0; j<n-i-1; j++)
			{
				if(priorities[j][1] > priorities[j+1][1])
				{
					temp = priorities[j][1];
					priorities[j][1] = priorities[j+1][1];
					priorities[j+1][1] = temp;
					
					temp=temp_burst[j];		 
					temp_burst[j]=temp_burst[j+1];	 
					temp_burst[j+1]=temp;
					
					temp = priorities[j][0];
					priorities[j][0] = priorities[j+1][0];
					priorities[j+1][0] = temp;
				}
			}			
		}
		waiting[0] = 0;
		for(i=1; i<n; i++)
		{
			waiting[i] = temp_burst[i-1] + waiting[i-1];
		}
		for(i=0; i<n; i++)
		{
			turnaround[i] = waiting[i] + temp_burst[i];
			total_waiting_time = total_waiting_time + waiting[i];
			total_turn_time = total_turn_time + turnaround[i];
		}
		avgwait = total_waiting_time/n;
		avgturn = total_turn_time/n;
		System.out.println("Average waiting time is "+avgwait);
		System.out.println("Average turnaround time is "+avgturn);
	}
}