package lab;

class ArrayIter<E> implements Iterator<E> {
    Object[] data;
    int pos;
    ArrayIter(Object[] d, int p) { data = d; pos = p; }
    public E get() { return (E)data[pos]; }
    public void set(E e) { data[pos] = e; }
    public Iterator<E> advance() {
        ++pos;
        return this;
    }
    public boolean equals(Iterator<E> other) { 
        return pos == ((ArrayIter<E>)other).pos; 
    }
    public Iterator<E> clone() {
        return new ArrayIter<E>(data, pos);
    }
}
