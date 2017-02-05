package project;

import static org.junit.Assert.*;


import java.util.ArrayList;

import org.junit.Test;

public class TestClass
{
	
	@Test
	public void putTest()
	{
		HashTableImp<Character,Double> ganja = new HashTableImp<Character,Double>(10, HashMethod.MAD);
    	
    	//Insert 11 keys
    	ganja.put('a', 10.0);
    	ganja.put('b', 11.0);
    	ganja.put('c', 12.0);
    	ganja.put('d', 13.0);
    	ganja.put('e', 14.0);
    	ganja.put('f', 15.0);
    	ganja.put('g', 16.0);
    	ganja.put('h', 17.0);
    	ganja.put('i', 18.0);
    	ganja.put('j', 19.0);
    	ganja.put('k', 20.0);
    	
    	assertTrue(ganja.toString().equals("( a=10.0 b=11.0 c=12.0 d=13.0 e=14.0 f=15.0 g=16.0 h=17.0 i=18.0 j=19.0 k=20.0 )"));
	}
	
	@Test
	public void getTest()
	{
		HashTableImp<Character,Double> ganja = new HashTableImp<Character,Double>(10, HashMethod.MAD);
    	
    	//Insert 11 keys
    	ganja.put('a', 10.0);
    	ganja.put('b', 11.0);
    	ganja.put('c', 12.0);
    	ganja.put('d', 13.0);
    	ganja.put('e', 14.0);
    	ganja.put('f', 15.0);
    	ganja.put('g', 16.0);
    	ganja.put('h', 17.0);
    	ganja.put('i', 18.0);
    	ganja.put('j', 19.0);
    	ganja.put('k', 20.0);
    	
    	
    	assertTrue(ganja.get('b').equals(11.0));
    	assertTrue(ganja.get('c').equals(12.0));
    	assertTrue(ganja.get('g').equals(16.0));
    	assertTrue(ganja.get('j').equals(19.0));
    	
	}
	
	@Test
	public void removeTest()
	{
		HashTableImp<Character,Double> ganja = new HashTableImp<Character,Double>(10, HashMethod.MAD);
    	
    	//Insert 11 keys
    	ganja.put('a', 10.0);
    	ganja.put('b', 11.0);
    	ganja.put('c', 12.0);
    	ganja.put('d', 13.0);
    	ganja.put('e', 14.0);
    	ganja.put('f', 15.0);
    	ganja.put('g', 16.0);
    	ganja.put('h', 17.0);
    	ganja.put('i', 18.0);
    	ganja.put('j', 19.0);
    	ganja.put('k', 20.0);
    	
    	
    	ganja.remove('c');
    	assertTrue(ganja.toString().equals("( a=10.0 b=11.0 d=13.0 e=14.0 f=15.0 g=16.0 h=17.0 i=18.0 j=19.0 k=20.0 )"));
    	
    	ganja.remove('a');
    	assertTrue(ganja.toString().equals("( b=11.0 d=13.0 e=14.0 f=15.0 g=16.0 h=17.0 i=18.0 j=19.0 k=20.0 )"));
    	
    	ganja.remove('g');
    	assertTrue(ganja.toString().equals("( b=11.0 d=13.0 e=14.0 f=15.0 h=17.0 i=18.0 j=19.0 k=20.0 )"));
    	
    	ganja.remove('h');
    	assertTrue(ganja.toString().equals("( b=11.0 d=13.0 e=14.0 f=15.0 i=18.0 j=19.0 k=20.0 )"));
    	
    	ganja.remove('j');
    	assertTrue(ganja.toString().equals("( b=11.0 d=13.0 e=14.0 f=15.0 i=18.0 k=20.0 )"));
    	
    	ganja.remove('k');
    	assertTrue(ganja.toString().equals("( b=11.0 d=13.0 e=14.0 f=15.0 i=18.0 )"));
    	
    	ganja.remove('i');
    	assertTrue(ganja.toString().equals("( b=11.0 d=13.0 e=14.0 f=15.0 )"));
    	
    	ganja.remove('b');
    	assertTrue(ganja.toString().equals("( d=13.0 e=14.0 f=15.0 )"));
    	
	}
	
	
	
	@Test
	public void containsTest()
	{
		HashTableImp<Character,Double> ganja = new HashTableImp<Character,Double>(10, HashMethod.MAD);
    	
    	//Insert 11 keys
    	ganja.put('a', 10.0);
    	ganja.put('b', 11.0);
    	ganja.put('c', 12.0);
    	ganja.put('d', 13.0);
    	ganja.put('e', 14.0);
    	ganja.put('f', 15.0);
    	ganja.put('g', 16.0);
    	ganja.put('h', 17.0);
    	ganja.put('i', 18.0);
    	ganja.put('j', 19.0);
    	ganja.put('k', 20.0);
    	
    	assertTrue(ganja.containsKey('a'));
    	assertTrue(ganja.containsKey('b'));
    	assertTrue(ganja.containsKey('c'));
    	assertTrue(ganja.containsKey('d'));
    	assertTrue(ganja.containsKey('e'));
    	assertTrue(ganja.containsKey('f'));
    	assertTrue(ganja.containsKey('g'));
    	assertTrue(ganja.containsKey('h'));
    	assertTrue(ganja.containsKey('i'));
    	assertTrue(ganja.containsKey('j'));
    	assertTrue(ganja.containsKey('k'));
    	
    	
    	ganja.remove('c');
    	assertTrue(ganja.containsKey('a'));
    	assertTrue(ganja.containsKey('b'));
    	assertFalse(ganja.containsKey('c'));
    	assertTrue(ganja.containsKey('d'));
    	assertTrue(ganja.containsKey('e'));
    	assertTrue(ganja.containsKey('f'));
    	assertTrue(ganja.containsKey('g'));
    	assertTrue(ganja.containsKey('h'));
    	assertTrue(ganja.containsKey('i'));
    	assertTrue(ganja.containsKey('j'));
    	assertTrue(ganja.containsKey('k'));
    	
    	ganja.remove('a');
    	assertFalse(ganja.containsKey('a'));
    	assertTrue(ganja.containsKey('b'));
    	assertFalse(ganja.containsKey('c'));
    	assertTrue(ganja.containsKey('d'));
    	assertTrue(ganja.containsKey('e'));
    	assertTrue(ganja.containsKey('f'));
    	assertTrue(ganja.containsKey('g'));
    	assertTrue(ganja.containsKey('h'));
    	assertTrue(ganja.containsKey('i'));
    	assertTrue(ganja.containsKey('j'));
    	assertTrue(ganja.containsKey('k'));
    	
    	ganja.remove('g');
    	assertFalse(ganja.containsKey('a'));
    	assertTrue(ganja.containsKey('b'));
    	assertFalse(ganja.containsKey('c'));
    	assertTrue(ganja.containsKey('d'));
    	assertTrue(ganja.containsKey('e'));
    	assertTrue(ganja.containsKey('f'));
    	assertFalse(ganja.containsKey('g'));
    	assertTrue(ganja.containsKey('h'));
    	assertTrue(ganja.containsKey('i'));
    	assertTrue(ganja.containsKey('j'));
    	assertTrue(ganja.containsKey('k'));
    	
    	ganja.remove('h');
    	assertFalse(ganja.containsKey('a'));
    	assertTrue(ganja.containsKey('b'));
    	assertFalse(ganja.containsKey('c'));
    	assertTrue(ganja.containsKey('d'));
    	assertTrue(ganja.containsKey('e'));
    	assertTrue(ganja.containsKey('f'));
    	assertFalse(ganja.containsKey('g'));
    	assertFalse(ganja.containsKey('h'));
    	assertTrue(ganja.containsKey('i'));
    	assertTrue(ganja.containsKey('j'));
    	assertTrue(ganja.containsKey('k'));
    	
    	ganja.remove('j');
    	assertFalse(ganja.containsKey('a'));
    	assertTrue(ganja.containsKey('b'));
    	assertFalse(ganja.containsKey('c'));
    	assertTrue(ganja.containsKey('d'));
    	assertTrue(ganja.containsKey('e'));
    	assertTrue(ganja.containsKey('f'));
    	assertFalse(ganja.containsKey('g'));
    	assertFalse(ganja.containsKey('h'));
    	assertTrue(ganja.containsKey('i'));
    	assertFalse(ganja.containsKey('j'));
    	assertTrue(ganja.containsKey('k'));
    	
    	ganja.remove('k');
    	assertFalse(ganja.containsKey('a'));
    	assertTrue(ganja.containsKey('b'));
    	assertFalse(ganja.containsKey('c'));
    	assertTrue(ganja.containsKey('d'));
    	assertTrue(ganja.containsKey('e'));
    	assertTrue(ganja.containsKey('f'));
    	assertFalse(ganja.containsKey('g'));
    	assertFalse(ganja.containsKey('h'));
    	assertTrue(ganja.containsKey('i'));
    	assertFalse(ganja.containsKey('j'));
    	assertFalse(ganja.containsKey('k'));
    	
    	ganja.remove('i');
    	assertFalse(ganja.containsKey('a'));
    	assertTrue(ganja.containsKey('b'));
    	assertFalse(ganja.containsKey('c'));
    	assertTrue(ganja.containsKey('d'));
    	assertTrue(ganja.containsKey('e'));
    	assertTrue(ganja.containsKey('f'));
    	assertFalse(ganja.containsKey('g'));
    	assertFalse(ganja.containsKey('h'));
    	assertFalse(ganja.containsKey('i'));
    	assertFalse(ganja.containsKey('j'));
    	assertFalse(ganja.containsKey('k'));
    	
    	
    	ganja.remove('b');
    	assertFalse(ganja.containsKey('a'));
    	assertFalse(ganja.containsKey('b'));
    	assertFalse(ganja.containsKey('c'));
    	assertTrue(ganja.containsKey('d'));
    	assertTrue(ganja.containsKey('e'));
    	assertTrue(ganja.containsKey('f'));
    	assertFalse(ganja.containsKey('g'));
    	assertFalse(ganja.containsKey('h'));
    	assertFalse(ganja.containsKey('i'));
    	assertFalse(ganja.containsKey('j'));
    	assertFalse(ganja.containsKey('k'));
    	
    	
	}
	
	
	@Test
	public void emptyTest()
	{
		HashTableImp<Character,Double> ganja = new HashTableImp<Character,Double>(10, HashMethod.MAD);
    	
		assertTrue(ganja.isEmpty());
		
    	//Insert 11 keys
    	ganja.put('a', 10.0);
    	ganja.put('b', 11.0);
    	ganja.put('c', 12.0);
    	ganja.put('d', 13.0);
    	ganja.put('e', 14.0);
    	ganja.put('f', 15.0);
    	ganja.put('g', 16.0);
    	ganja.put('h', 17.0);
    	ganja.put('i', 18.0);
    	ganja.put('j', 19.0);
    	ganja.put('k', 20.0);
    	
    	ganja.remove('a');
    	ganja.remove('b');
    	ganja.remove('c');
    	ganja.remove('d');
    	ganja.remove('e');
    	ganja.remove('f');
    	ganja.remove('g');
    	ganja.remove('h');
    	ganja.remove('i');
    	ganja.remove('j');
    	ganja.remove('k');
    	
    	
    	assertTrue(ganja.isEmpty());
	}
	
	@Test
	public void countTest()
	{
		HashTableImp<Character,Double> ganja = new HashTableImp<Character,Double>(10, HashMethod.MAD);
    	
		assertTrue(ganja.isEmpty());
		
    	//Insert 11 keys
    	ganja.put('a', 10.0);
    	ganja.put('b', 11.0);
    	ganja.put('c', 12.0);
    	ganja.put('d', 13.0);
    	ganja.put('e', 14.0);
    	ganja.put('f', 15.0);
    	ganja.put('g', 16.0);
    	ganja.put('h', 17.0);
    	ganja.put('i', 18.0);
    	ganja.put('j', 19.0);
    	ganja.put('k', 20.0);
    	ganja.put('k', 21.0);
    	ganja.put('k', 22.0);
    	ganja.put('k', 23.0);
    	ganja.put('k', 24.0);
    	
    	assertTrue(ganja.getItemCount() == 11);
    	
    	ganja.remove('a');
    	ganja.remove('b');
    	ganja.remove('c');
    	ganja.remove('d');
    	ganja.remove('e');
    	ganja.remove('f');
    	ganja.remove('g');
    	ganja.remove('h');
    	ganja.remove('i');
    	ganja.remove('j');
    	ganja.remove('k');
    	
    	
    	assertTrue(ganja.getItemCount() == 0);
	}
	
}
