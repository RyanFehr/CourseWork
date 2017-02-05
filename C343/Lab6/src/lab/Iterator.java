package lab;


interface Iterator<E> {
    E get();
    void set(E e);
    Iterator<E> advance();
    boolean equals(Iterator<E> other);
    Iterator<E> clone();
}