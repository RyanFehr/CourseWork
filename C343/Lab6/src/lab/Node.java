package lab;

class Node<E> {
    Node(E d, Node<E> n) { data = d; next = n; }
    E data;
    Node<E> next;
}
