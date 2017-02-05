package lab;

class ListIter<E> implements Iterator<E> {
    Node<E> curr;
    ListIter(Node<E> n) { curr = n; }
    public E get() { return curr.data; }
    public void set(E e) { curr.data = e; }
    public Iterator<E> advance() { curr = curr.next; return this; }
    public boolean equals(Iterator<E> other) { 
        return curr == ((ListIter<E>)other).curr; 
    }
    public Iterator<E> clone() {
        return new ListIter<E>(curr);
    }
}
