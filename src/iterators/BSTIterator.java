package iterators;

import java.util.Iterator;
import java.util.ArrayList;

public class BSTIterator<E> implements Iterator<E> {
	
	protected ArrayList<E> travList;
	protected int size;
	protected int counter;

	public BSTIterator(ArrayList<E> travList) {
		this.travList = travList;
		this.size = travList.size();
		counter = 0;
	}

	@Override
	public boolean hasNext() {
		return counter < size;
	}

	@Override
	public E next() {
		return travList.get(counter++);
	}

}






