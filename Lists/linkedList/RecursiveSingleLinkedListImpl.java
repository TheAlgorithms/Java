package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;
	
	public RecursiveSingleLinkedListImpl() {
	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {

		return (this.data == null);
	}

	@Override
	public int size() {

		if(isEmpty())
			return 0;
		
		else
			return 1 + getNext().size();
	}

	@Override
	public T search(T element) {

		if(isEmpty())
			return null;
		
		else {
			
			if(getData().equals(element))
				return getData();
			
			else
				return getNext().search(element);
		}
	
	}

	@Override
	public void insert(T element) {

		if (isEmpty()) {
			setData(element);
			setNext(new RecursiveSingleLinkedListImpl<T>());

		} else
			getNext().insert(element);
	}

	@Override
	public void remove(T element) {

		if(isEmpty())
			return;
		
		else {
			
			if(getData().equals(element)){				
				setData(getNext().getData());
				setNext(getNext().getNext());
			
			} else {
				getNext().remove(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {

		int size = this.size();
		T[] result = (T[]) new Object[size];
		
		this.fullfillArray(result, this, 0);

		return result;
	}

	private void fullfillArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int index){
		
		if(node.isEmpty())
			return;
	
		else if (index < array.length){
			
			array[index] = node.getData();
			fullfillArray(array, node.getNext(), index + 1);
		}
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
}
