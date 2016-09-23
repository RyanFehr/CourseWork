package project2;
public interface SearchTree<Key> {

    Node<Key> insert(Key key);

    Node<Key> search(Key key);

    void delete(Key key);
}
