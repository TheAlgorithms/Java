

import java.util.ArrayList;
import java.util.Comparator;

public class genericheap<T> { // create a generic heap class <T> , where T can be of any type.

	private ArrayList<T> data = new ArrayList<>();
	private Comparator<T> ctor;

	public genericheap(Comparator<T> ctor) { // constructor to initialize the generic comparator 
		this.ctor=ctor;
	}	

	public int size() { // returns the size of the arraylist data
		return data.size();
	}

	public boolean isEmpty() { // checks whether the list is empty or not :: return true or false for the same
		return data.isEmpty();
	}

	public void display() { //displays the list
		System.out.println(this.data);
	}

	public void add(T integer) { // in this function we have added the <t> type object into the arraylist and called upheapify 
		data.add(integer);
		upheapify(data.size() - 1);
	}

	private void upheapify(int ci) {
		if (ci == 0) {
			return;
		}
		int pi = (ci - 1) / 2;
		if (isLarger(ci,pi) == true) {
			swap(ci, pi);
			upheapify(pi);
		}
	}

	private boolean isLarger(int i, int j) {
		T ith = data.get(i);
		T jth = data.get(j);
		if(ctor.compare(ith,jth)>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void swap(int ci, int pi) { // swap function  written like this because of the generic property
		T ith = data.get(ci);
		T jth=data.get(pi);
		data.set(ci, jth);
		data.set(pi, ith);
	}

	public T getHP() {
		return data.get(0);
	}

	public T removeHP() {
	
		swap(0, data.size() - 1);
		T rv=data.remove(data.size()-1);
		downheapify(0);
		return rv;
	}

	private void downheapify(int pi) {
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;

		int max = pi;

		if (lci < data.size() && isLarger(lci, max) == true) {
			max = lci;
		}
		if (rci < data.size() && isLarger(rci, max) == true) {
			max = rci;
		}
		if (max != pi) {
			swap(pi, max);
			downheapify(max);
		}
	}

}
