package project;
import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueueImp<E> implements PriorityQueue<E> {
    private Heap<E> heap;

    PriorityQueueImp(Comparator<E> comparator) {
	this.heap = new HeapImp<E>(new ArrayList<E>(), comparator);
    }

    @Override
    public void push(E e) {
	heap.insert(e);
    }

    @Override
    public E pop() {
	return heap.extractMin();
    }

    public String toString() {
	return heap.toString();
    }
    
}
