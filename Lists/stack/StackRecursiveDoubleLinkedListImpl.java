package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;
	protected int elements;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.list = new RecursiveDoubleLinkedListImpl<>();
		this.size = size;
		this.elements = 0;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
		if(element == null)
			return;
		
		else if (elements == size)
			throw new StackOverflowException();
		
		else {
			getList().insertFirst(element);
			setElements(getElements() + 1);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if(elements == 0)
			throw new StackUnderflowException();
		
		else {
			
			T element = ((RecursiveDoubleLinkedListImpl<T>) getList()).getData();
			getList().removeFirst();
			setElements(getElements() - 1);
			
			return element;
		}
	}

	@Override
	public T top() {
		return ((RecursiveDoubleLinkedListImpl<T>) getList()).getData();
	}

	@Override
	public boolean isEmpty() {

		return getList().isEmpty();
	}

	@Override
	public boolean isFull() {

		return (getElements() == getSize());
	}

	// getters and setters
	
	public DoubleLinkedList<T> getList() {
		return list;
	}

	public void setList(DoubleLinkedList<T> list) {
		this.list = list;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getElements() {
		return elements;
	}

	public void setElements(int elements) {
		this.elements = elements;
	}

	
	
}
