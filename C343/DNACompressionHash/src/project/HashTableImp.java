package project;

import java.util.function.BiFunction;
import java.util.Set;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedList;

class Entry<K,V> {
    Entry(K k, V v) { key = k; value = v; }
    final K key;
    V value;
    int hash;
}

@SuppressWarnings("unchecked")
public class HashTableImp<K,V> implements HashTable<K,V> {
    // an array of linked lists to handle chaining
    private Object[] array;
    private int itemCount;
    private BiFunction<K,Integer,Integer> h;

    private int hash(K k){
	return this.h.apply(k,array.length);
    }
    
    public HashTableImp(int initialCapacity, HashMethod hm){
	// This cast violates type safety of the program but we wanted
	// you to implement hash table on top of a data structure that
	// does not do dynamic resizing (arrays instead of ArrayList)
	// so you can do the hash table growing yourself.
	// Do NOT do it in the future.
	this.array = new Object[initialCapacity];
	for(int i = 0;i<initialCapacity;i++)
	{
		array[i]= new LinkedList<Entry<K,V>>();
	}
	
	this.setItemCount(0);
	switch (hm) {
	case Div:
	    // division method
	    h = (k,m) -> {return k.hashCode() % m;};
	    break;
	case MAD:
	    h = (k,m) -> {return mad(k,m);};
	    break;
	}
    }

    // Implement all the following stubs

    // Multiply-Add-and-Divide (MAD) hashing method
    private int mad(K k, int m) {
    	return Math.abs((k.hashCode()* 2) + 2) % m;
    }

    // this method needs to be called appropriately in the put method
    private void growTable() {
    	int newLengthSmall = array.length * 2;
    	BigInteger newLengthBig = BigInteger.valueOf(newLengthSmall);
    	newLengthBig = newLengthBig.nextProbablePrime();
    	int newLength = newLengthBig.intValue();
    	
    	Object[] arrayNew;
    	
    	arrayNew = new Object[newLength];
    	
    	//physically initializing array because of the casting issues
    	for(int i = 0;i<newLength;i++)
    	{
    		arrayNew[i]= new LinkedList<Entry<K,V>>();
    	}
    	
    	//Re hashing of the keys
    	for(int i = 0; i<array.length; i++) 
    	{
    		LinkedList<Entry<K,V>> curr = (LinkedList<Entry<K,V>>) array[i];
    		
    		for(Entry<K,V> entry : curr)
    		{
    			LinkedList<Entry<K,V>> temp = (LinkedList<Entry<K,V>>) arrayNew[this.hash(entry.key)];
    			temp.add(entry);
    		}
    	}
    	
    	array =  new Object[newLength];
    	//physically initializing array because of the casting issues
    	for(int i = 0;i<newLength;i++)
    	{
    		array[i]= new LinkedList<Entry<K,V>>();
    	}
    	
    	//Performs a deep copy
    	for(int i = 0; i<array.length; i++) 
    	{
    		LinkedList<Entry<K,V>> curr = (LinkedList<Entry<K,V>>) arrayNew[i];
    		
    		for(Entry<K,V> entry : curr)
    		{
    			LinkedList<Entry<K,V>> temp = (LinkedList<Entry<K,V>>) array[i];
    			temp.add(entry);
    		}
    	}
    	
    }

    @Override
    public boolean containsKey(K key){
    	LinkedList<Entry<K,V>> index = (LinkedList<Entry<K,V>>) array[this.hash(key)];
    	for(Entry<K,V> entry : index)
		{
			if(entry.key == key){return true;}
		}
    	return false;
    }

    @Override    
    public V get(K key){
    	LinkedList<Entry<K,V>> index = (LinkedList<Entry<K,V>>) array[this.hash(key)];
    	for(Entry<K,V> entry : index)
		{
			if(entry.key == key){return entry.value;}
		}
    	return null;
    }

    @Override    
    public V put(K key, V value){
    	int index = this.hash(key);
    	LinkedList<Entry<K,V>> temp = (LinkedList<Entry<K,V>>) array[index];
    	
    	int i = 0;
    	boolean contains = false;
    	for(Entry<K,V> entry : temp)
		{
			if(entry.key == key)
			{
				contains = true;
				break;
			}
			i++;
		}
    	if(contains)
    	{
    		temp.set(i,new Entry<K,V>(key,value));
    	}
    	else
    	{
    		temp.add(new Entry<K,V>(key,value));
    		this.setItemCount(this.getItemCount() + 1);
    	}
    	
    	//Set the acceptable depth on a indices LL
    	int maxDepth = array.length*3;
    	if(getItemCount() > maxDepth){this.growTable();}
    	
    	return value;
    }

    // not used in this client code so make sure it works correctly by
    // implementing unit tests.
    @Override    
    public boolean isEmpty(){
    	return this.getItemCount() == 0;
    }

    @Override    
    public Set<K> keySet(){
    	Set<K> keys  = new HashSet<K>();
    	for(int i = 0; i<array.length; i++) 
    	{
    		LinkedList<Entry<K,V>> curr = (LinkedList<Entry<K,V>>) array[i];
    		
    		for(Entry<K,V> entry : curr)
    		{
    			keys.add(entry.key);
    		}
    	}
    	
	return keys;
    }
    
    public String toString()
    {
    	String output = "( ";
    	for(K k : this.keySet())
    	{
    		output+= k;
    		output += "=" + get(k) + " ";
    	}
    	
    	return output + ")";
    }
    
    // not used in this client code so make sure it works correctly by
    // implementing unit tests.
    @Override    
    public V remove(K key){
    	if(!this.containsKey(key)){return null;}
    	int index = this.hash(key);
    	V val;
    	
    	LinkedList<Entry<K,V>> temp = (LinkedList<Entry<K,V>>) array[index];
    	
    	int i = 0;
    	for(Entry<K,V> entry : temp)
		{
			if(entry.key == key)
			{
				break;
			}
			i++;
		}
    	val = temp.get(i).value;
    	temp.remove(i);
    	
    	this.setItemCount(this.getItemCount() - 1);
    	
	return val;
    }

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
}
