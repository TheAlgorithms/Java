import java.util.Scanner;
import java.util.Stack;

class StackSorting{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Stack size : ");
        Stack<Integer> s=new Stack<>();
        int n=sc.nextInt();
        System.out.println("Enter the elements in the Stack : ");
        while(n-->0)
            s.push(sc.nextInt());
            StackSorting g = new StackSorting();
            System.out.println("Sorted Stack : ");
            Stack<Integer> a = g.sort(s);       //Sorted elements inserted in new Stack
            while (!a.empty()) {
                System.out.print(a.peek() + " ");
                a.pop();
            }
}

    public void sortedInsert(Stack<Integer> S , int element)  // This method inserts the sorted element
    {
        if((S.empty()) || (S.peek() < element))
            S.push(element);
        else
        {
            int temp = S.pop();
            sortedInsert(S , element);
            S.push(temp);
        }
    }
    public Stack<Integer> sort(Stack<Integer> s)    //Sorts the stack and then returns it
    {
        if(!s.empty())
        {
            int temp = s.pop();
            sort(s);
            sortedInsert(s , temp);
        }
        return s;
    }
}