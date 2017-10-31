package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {

		if (element == null)
			return;

		else if (isEmpty()) {
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<T>(null, null, this));

		} else {

			RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>(getData(), getNext(), this);
			setData(element);
			setNext(newNode);
		}
	}

	@Override
	public void removeFirst() {

		if (isEmpty())
			return;

		else {
			setData(getNext().getData());
			setNext(getNext().getNext());
		}
	}

	@Override
	public void removeLast() {

		if (isEmpty())
			return;

		else if (getNext().isEmpty()) {

			getPrevious().setNext(new RecursiveDoubleLinkedListImpl<T>());

		} else {
			RecursiveDoubleLinkedListImpl<T> nextNode = (RecursiveDoubleLinkedListImpl<T>) getNext();
			nextNode.removeLast();
		}
	}

	@Override
	public void insert(T element) {

		if (element == null)
			return;

		else if (isEmpty()) {

			setData(element);
			setNext(new RecursiveDoubleLinkedListImpl<T>(null, null, this));

		} else {

			RecursiveDoubleLinkedListImpl<T> nextNode = (RecursiveDoubleLinkedListImpl<T>) getNext();
			nextNode.insert(element);
		}
	}

	@Override
	public void remove(T element) {

		if (element == null || isEmpty())
			return;

		else {

			RecursiveDoubleLinkedListImpl<T> nextNode = (RecursiveDoubleLinkedListImpl<T>) getNext();

			if (getData().equals(element)) {

				if (getNext().isEmpty()) {
				
					setData(null);
					setNext(null);
				
				} else {

					getPrevious().setNext(nextNode);
					nextNode.setPrevious(getPrevious());
				}

			} else {

				nextNode.remove(element);
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
		
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
