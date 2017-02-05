package project;
import java.util.Set;

public interface HashTable<K,V> {
    boolean containsKey(K key);
    V get(K key);
    V put(K key, V value);
    boolean isEmpty();
    Set<K> keySet();
    V remove(K key);
}
