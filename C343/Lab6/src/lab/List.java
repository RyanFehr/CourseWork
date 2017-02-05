package lab;

class List<E> {
    Node<E> head;
    List() { head = null; }
    public void addFirst(E d) {
        head = new Node<E>(d, head);
    }
    public Iterator<E> begin() { return new ListIter<E>(head); }
    public Iterator<E> end() { return new ListIter<E>(null); }
} // List
