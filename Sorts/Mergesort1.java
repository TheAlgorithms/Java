import java.util.*;
import java.io.*;
import java.util.Collections;

class MergeSort1{

static int[] sort(int[] ar) throws IOException

{
	int l = ar.length;
	int[] r = new int[l];
	if(l<=1)
		return ar;
	else
	{
		int a1 = l/3;
		int a2 = (l - a1)/2;
		int a3 = (l -a1 - a2);
		int[] A1 = new int[a1];
		int[] A2 = new int[a2];
		int[] A3 = new int[a3];
		for(int i=0;i<l;i++)
		{
			if(i<a1)
				A1[i] = ar[i];
			else if(i>= a1 && i<(a1+a2))
				A2[i - a1] = ar[i];
			else
				A3[i - a1 - a2] = ar[i];
		}
		int x1=0,x2=0,x3=0;
		A1 = sort(A1);
		A2 = sort(A2);
		A3 = sort(A3);
		for(int k=0;k<l;)
		{
			if(x1<a1 && x2<a2 && x3<a3)
			{
				if(A1[x1]<A2[x2] && A1[x1]<A3[x3])
				{
					r[k++] = A1[x1++];
				}
				else if(A2[x2]<A1[x1] && A2[x2]<A3[x3])
				{
					r[k++] = A2[x2++];
				}
				else if(A3[x3]<A2[x2] && A3[x3]<A1[x1])
				{
					r[k++] = A3[x3++];
				}
			}
			else if(x1>=a1 && x2<a2 && x3<a3)
			{
				if(A2[x2]<A3[x3])
					r[k++] = A2[x2++];
				else if(A3[x3]<A2[x2])
					r[k++] = A3[x3++];
			}
			else if(x2>=a2 && x1<a1 && x3<a3)
			{
				if(A1[x1]<A3[x3])
					r[k++] = A1[x1++];
				else if(A3[x3]<A1[x1])
					r[k++] = A3[x3++];
			}
			else if(x3>=a3 && x2<a2 && x1<a1)
			{
				if(A2[x2]<A1[x1])
					r[k++] = A2[x2++];
				else if(A1[x1]<A2[x2])
					r[k++] = A1[x1++];
			}
			else if(x1<a1)
				r[k++] = A1[x1++];
			else if(x2<a2)
				r[k++] = A2[x2++];
			else if(x3<a3)
				r[k++] = A3[x3++];
			else
				break;
		}
	}
	return r;
}
public static void main(String[] args) throws IOException {
	Scanner sc = new Scanner(new File("C:\\Users\\Jinal Adhyaru\\Desktop\\VS Code Java Files\\File1.txt"));

	ArrayList<Integer> al= new ArrayList<>();
	while(sc.hasNextInt()){
		al.add(sc.nextInt());
	}
	int i;
	int[] ar = new int[al.size()];
	for(i=0;i<al.size();i++)
		ar[i] = al.get(i);
	int[] res = new int[al.size()];
	long st,et,t;
	st = System.nanoTime();
	res = sort(ar);
	et = System.nanoTime();
	t = et -st;
	System.out.println("The time taken is : " + t);
	sc.close();

	}
}