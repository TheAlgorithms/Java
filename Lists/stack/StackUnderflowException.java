package adt.stack;

public class StackUnderflowException extends Exception {

	public StackUnderflowException() {
		super("Stack is empty");
	}
}
