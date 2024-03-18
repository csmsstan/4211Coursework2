package pizzafactory.classes;

// Heavily WIP, will most likely replace with a PriorityBlockingQueue, see https://www.baeldung.com/java-priority-blocking-queue

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

public class OrderQueue<T> extends AbstractQueue<T> {
	
	private LinkedList<T> elements;
	
	@Override
	public boolean offer(T queuedOrder) {
		if (queuedOrder == null) return false;
		elements.add(queuedOrder);
		return false;
	}

	@Override
	public T poll() {
		
	    Iterator<T> iter = elements.iterator();
	    T queuedOrder = iter.next();
	    if(queuedOrder != null){
	        iter.remove();
	        return queuedOrder;
	    }
	    return null;
	}

	@Override
	public T peek() {
		
		return elements.getFirst();
	}

	@Override
	public Iterator<T> iterator() {
		return elements.iterator();
	}

	@Override
	public int size() {
		return elements.size();
	}
	

}
