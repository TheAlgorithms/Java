import java.io.*;
import java.util.*;

class cqueue
{
	int f=-1,r=-1; //here f is front pointer and r is rear pointer.
	Scanner x=new Scanner(System.in);
	int queue[]=new int[5]; //maximum size of queue is 5.
	public void insert()
	{
		int e;
		if((f==0 && r==4)||((r+1)==f))
			System.out.print("\nThe queue is full");
		else if (f==-1 && r==-1)
		{
			f=0;
			r++;
			System.out.print("\nInsert element:");
			e=x.nextInt();
			queue[r]=e;
		}
		else if(f!=0 && r==4)
		{
			r=0;
			System.out.print("\nInsert element:");
			e=x.nextInt();
			queue[r]=e;
		}
		else
		{
			r++;
			System.out.print("\nInsert element:");
			e=x.nextInt();
			queue[r]=e;
		}
	}
	public void display()
	{
		System.out.print("\nThe queue is: ");
		if((f==0)||(f<r))
		{
			for(int i=f;i<=r;i++)
				System.out.print(" "+queue[i]);
		}
		else if(f>r)
		{
			for(int k=f;k<=4;k++)
				System.out.print(" "+queue[k]);
			for(int l=0;l<=r;l++)
				System.out.print(" "+queue[l]);
		}
		else
			System.out.println();
	}
	public void delet()
	{
		if(f==-1 && r==-1)
			System.out.print("\nThe queue is empty.");
		else if(queue[f]=='\0' && queue[r]=='\0')
			System.out.print("\nThe queue is empty.");
		else if(f>=0)
		{
			queue[f]='\0';
			f++;
		}
		else if(f==4)
		{
			queue[f]='\0';
			f=	0;
		}
	}
}
class circularqueue
{
	public static void main(String args[])
	{
		cqueue cq=new cqueue();
		Scanner x=new Scanner(System.in);
		int choice;
		do{
			System.out.print("\n1. Insert\t2. Delete\n3. Display\t4. Exit\n");
			System.out.print("\nEnter your choice: ");
		    choice=x.nextInt();
			switch(choice)
			{
				case 1:	cq.insert(); break;
			
				case 2:	cq.delet(); break;
			
				case 3:	cq.display(); break;
			
				case 4: break;
			
				default : System.out.print("Invalid choice.");
				break;
			}
		}while(choice!=4);
	}
}