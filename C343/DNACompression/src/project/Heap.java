package project;
public interface Heap<E> {
    
    E minimum();

    void insert(E e);

    E extractMin();

    void minHeapify(int i);

    void buildMinHeap();
    
}

