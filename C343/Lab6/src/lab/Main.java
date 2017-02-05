package lab;

public class Main {
    
    // create extra helper functions as needed

    static <E extends Comparable<? super E>>
    void quicksort(Iterator<E> begin, Iterator<E> end) {
        // put your solution here
    	if(!begin.equals(end)) {
    		Iterator<E> result = partition(begin, end);
    		quicksort(begin, result);
    		quicksort(result.advance(), end);
    	}
    	else {
    		return;
    	}

    }
    
    
    static <E extends Comparable<? super E>>
    Iterator<E> partition(Iterator<E> begin, Iterator<E> end) {
		
		Iterator<E> a = begin.clone();
		Iterator<E> b = begin.clone();
		Iterator<E> c = begin.clone(); //the pivot value
		Iterator<E> d = begin.clone().advance();
		
		while(!d.equals(end)) {
			c.advance();
			d.advance();
		}
		
		while(!b.equals(end)) {
			if(b.get().compareTo(c.get()) < 0) {
				swap(a, b);
				a.advance();
			}
			b.advance();
		}
		swap(a, c);
		return a;
		
	}

    //Swaps two iterators pointer location
    static <E extends Comparable<? super E>>
	void swap(Iterator<E> i, Iterator<E> j) {
		
		E temp = i.get();
		i.set(j.get());
		j.set(temp);
		
		
	}

    public static void main(String[] args) {
    	
    	System.out.println("\n Array Integers");
    	//array test for Integers
    	Array<Integer> test1 = new Array<Integer>();
    	test1.add(1);
    	test1.add(10);
    	test1.add(30);
    	test1.add(17);
    	test1.add(15);
    	test1.add(13);
    	test1.add(19);
    	test1.add(11);
    	test1.add(54);
    	test1.add(65);

    	quicksort(test1.begin(), test1.end());
    	
    	for(Iterator<Integer> iter = test1.begin(); !iter.equals(test1.end()); iter.advance()) {
    		System.out.println(iter.get());
    	}
    	
    	System.out.println("\n LL Integers");
    	
    	//linked lists for Integers
    	List<Integer> test2 = new List<Integer>();
    	test2.addFirst(1);
    	test2.addFirst(10);
    	test2.addFirst(30);
    	test2.addFirst(17);
    	test2.addFirst(15);
    	test2.addFirst(13);
    	test2.addFirst(19);
    	test2.addFirst(11);
    	test2.addFirst(54);
    	test2.addFirst(65);
    	
    	quicksort(test2.begin(), test2.end());
    	
    	for(Iterator<Integer> iter = test2.begin(); !iter.equals(test2.end()); iter.advance()) {
    		System.out.println(iter.get());
    	}
    	
    	System.out.println("\n Array Strings:");
    	
    	//array test for strings
    	//sorts them into alphabetical order
    	Array<String> word1 = new Array<String>();
    	word1.add("a first");
    	word1.add("c third");
    	word1.add("z fifth");
    	word1.add("b second");
    	word1.add("d fourth");
    	
    	quicksort(word1.begin(), word1.end());
    	
    	for(Iterator<String> iter = word1.begin(); !iter.equals(word1.end()); iter.advance()) {
    		System.out.println(iter.get());
    	}
    	
    	System.out.println("\n LL Strings:");
    	
    	//linked list test for strings
    	//sorts them into alphabetical order
    	List<String> word2 = new List<String>();
    	
    	word2.addFirst("a first");
    	word2.addFirst("c third");
    	word2.addFirst("z sixth");
    	word2.addFirst("e fifth");
    	word2.addFirst("b second");
    	word2.addFirst("d fourth");
    	
    	quicksort(word2.begin(), word2.end());
    	
    	for(Iterator<String> iter = word2.begin(); !iter.equals(word2.end()); iter.advance()) {
    		System.out.println(iter.get());
    	}
    }
}
