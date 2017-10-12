import java.util.Scanner;

public class BalancedParan
{
	static class stack
	{
		int top=-1; //top refers to the top most value of the stack
		char items[] = new char[100]; //Stack array

		void push(char x)
		{
			if (top == 99)
			{
				System.out.println("Stack full");
			}
			else
			{
				items[++top] = x;
			}
		}

		char pop()
		{
			if (top == -1)
			{
				System.out.println("Underflow error");
				return '\0';
			}
			else
			{
				char element = items[top];
				top--;
				return element;
			}
		}

		boolean isEmpty()
		{
			return (top == -1) ? true : false;
		}
	}

	/* Returns true if character1 and character2
	are matching left and right Parenthesis */
	static boolean isMatchingPair(char character1, char character2)
	{
	if (character1 == '(' && character2 == ')')
		return true;
	else if (character1 == '{' && character2 == '}')
		return true;
	else if (character1 == '[' && character2 == ']')
		return true;
	else
		return false;
	}

	/* Return true if expression has balanced
	Parenthesis */
	static boolean areParenthesisBalanced(char exp[])
	{
	/* Declare an empty character stack */
	stack st=new stack();

	/* Traverse the given expression to
		check matching parenthesis */
	for(int i=0;i<exp.length;i++)
	{
		/*If the exp[i] is a starting
			parenthesis then push it*/
		if (exp[i] == '{' || exp[i] == '(' || exp[i] == '[')
			st.push(exp[i]);

		/* If exp[i] is an ending parenthesis
			then pop from stack and check if the
			popped parenthesis is a matching pair*/
		if (exp[i] == '}' || exp[i] == ')' || exp[i] == ']')
		{

			/* If we see an ending parenthesis without
				a pair then return false*/
			if (st.isEmpty())
			{
				return false;
			}

			/* Pop the top element from stack, if
				it is not a pair parenthesis of character
				then there is a mismatch. This happens for
				expressions like {(}) */
			else if ( !isMatchingPair(st.pop(), exp[i]) )
			{
				return false;
			}
		}

	}

	/* If there is something left in expression
		then there is a starting parenthesis without
		a closing parenthesis */

	if (st.isEmpty())
		return true; /*balanced*/
	else
		{ /*not balanced*/
			return false;
		}
	}

	/* UTILITY FUNCTIONS */
	public static void main(String[] args)
	{
	    Scanner scanner = new Scanner(System.in);
        String brackets = scanner.nextLine(); // Input eg."{()}["

        char[] exp = brackets.toCharArray();
		if (areParenthesisBalanced(exp))
			System.out.println("Balanced "); // If the Brackets are balanced
		else
			System.out.println("Not Balanced "); // If the Brackets are unbalanced
	}

}
