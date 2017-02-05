package hw;

import java.util.HashMap;

public class Hash<E> {

	public HashMap<E,E> data = new HashMap<E,E>();
	public E tombstone;
	
	Hash(E tomb, HashMap<E,E> map)
	{
		this.tombstone = tomb;
		this.data = map;
	}
	
	

	void hashDelete(Key k, E e) { 
		int home; // Home position for r 
		int pos = home = h(k); 		// Initial posit on probe sequence 
		for (int i=1; HT[pos] != k && HT[pos] != null; i++) { 
			pos = (home + p(k, i)) % M; // Next on probe sequence 
			} 
		if(k == HT[pos]) //Found it
		{
			e = HT[pos];
			HT[pos] = tombstone;
		} 
	}
	
	//Search does not need to be adjusted
	/** Insert record r with key k into HT */
	void hashInsert(Key k, E r) { 
		int home; // Home position for r 
		int pos = home = h(k); 		// Initial position 
		for (int i=1; HT[pos] != null && HT[pos] != tombstone; i++) { 
			pos = (home + p(k, i)) % M; // Next probe slot 
			assert HT[pos].key().compareTo(k) != 0 : 
				"Duplicates not allowed"; 
			} 
			HT[pos] = new KVpair<Key,E>(k, r); // Insert R 
	}
}
