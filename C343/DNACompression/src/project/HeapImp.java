package project;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

class TestComparator implements Comparator<Integer>
{
	@Override
    public int compare(Integer x, Integer y)
    {
	if (x < y)
	    {
		return -1;
	    }
	if (x > y)
	    {
		return 1;
	    }
	return 0;
    }
}

public class HeapImp<E> implements Heap<E> {
    private ArrayList<E> data;
    private Comparator<E> compare;
    private int heapSize;

    HeapImp(ArrayList<E> data, Comparator<E> compare) {
	this.data = data;
	this.compare = compare;
	buildMinHeap();
    }

    @Override
    public E minimum() {
    	return data.get(0);
    }

    @Override
    public void insert(E e) {
    	data.add(e);
    	heapSize++;
    	decreaseKey(heapSize-1);
    }

    @Override
    public E extractMin() {
    	E min = data.get(0);
    	data.set(0, data.get(heapSize-1));
    	heapSize--;
    	minHeapify(0);
    	data.remove(heapSize);
    	return min;
    }

    private void decreaseKey(int i) {
    	while(compare.compare(data.get(i),data.get(parent(i)))<0)
    	{
    		swap(data,i,parent(i));
    		i = parent(i);
    	}
    }

    @Override
    public void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;
        if(l<heapSize && compare.compare(data.get(i), data.get(l))<0)
        {smallest = i;}
        else
        {smallest = l;}
        
        if(r < heapSize && compare.compare(data.get(smallest),data.get(r))>0)
        {smallest=r;}
        
        if(smallest != i)
        {
        	swap(data,i,smallest);
        	minHeapify(smallest);
        }
    }

    @Override
    public void buildMinHeap()
    {
    	//heapSize = data.size();
    	int last_parent = heapSize/2-1;
    	for(int i = last_parent;i>-1;i--)
    	{
    		minHeapify(i);
    	}
    }

    private void sortHeap() {
    	buildMinHeap();
    	for(int i = heapSize-1; i>0; i--)
    	{
    		swap(data,0,i);
    		heapSize--;
    		minHeapify(0);
    	}
    }
    
    public int left(int pos)
    {
    	if((2*pos)+1 > heapSize-1)
    	{
    		return pos;
    	}
    	else
    	{
    		return (2*pos)+1;
    	}
    }
    
    public int right(int pos)
    {
    	if((2*pos)+2 > heapSize-1)
    	{
    		return pos;
    	}
    	else
    	{
    		return (2*pos)+2;
    	}
    }
    
    public int parent(int pos)
    {
    	return (pos-1)/2;
    }
    
    public void swap(ArrayList<E> A, int a, int b)
    {
    	E tmp = A.get(a);
    	A.set(a, A.get(b));
    	A.set(b, tmp);
    }
    
    public boolean heapCheck()
    {
    	for(int i = 0; i<heapSize;i++)
    	{
    		if(compare.compare(data.get(i), data.get(parent(i)))<0)
    		{
    			return false;
    		}
    	}
    	return true;
    }
    
    public String toString() {
	return data.toString();
    }
    
    
    
    public static void main(String args[])
    {
    	//Intitalize Array [1|2|7|5|4|14|9|16|8|10]
    	ArrayList<Integer> testA =  new ArrayList<Integer>();
    	
    	HeapImp<Integer> t = new HeapImp<Integer>(testA, new TestComparator());
    	
    	
    	
    	//Initialize Test
    	t.insert(16);
    	t.insert(14);
    	t.insert(10);
    	t.insert(8);
    	t.insert(7);
    	t.insert(9);
    	t.insert(5);
    	t.insert(2);
    	t.insert(4);
    	t.insert(1);
    	System.out.print("Initialized: ");
    	System.out.println(t.heapCheck());
    	
    	//Test Cases
    	
    	//Test 1-6(Left,Right Tests)
    	System.out.print("Test 1 passed: ");
    	System.out.println(t.left(1) == 3);
    	
    	System.out.print("Test 2 passed: ");
    	System.out.println(t.right(1) == 4);
    	
    	System.out.print("Test 3 passed: ");
    	System.out.println(t.left(0) == 1);
    	
    	System.out.print("Test 4 passed: ");
    	System.out.println(t.right(0) == 2);
    	
    	System.out.print("Test 5 passed: ");
    	System.out.println(t.left(5) == 5);
    	
    	System.out.print("Test 6 passed: ");
    	System.out.println(t.right(7) == 7);
    	
    	
    	//Test 7-10(Parent Tests)
    	System.out.print("Test 7 passed: ");
    	System.out.println(t.parent(1) == 0);
    	
    	System.out.print("Test 8 passed: ");
    	System.out.println(t.parent(0) == 0);
    	
    	System.out.print("Test 9 passed: ");
    	System.out.println(t.parent(5) == 2);
    	
    	System.out.print("Test 10 passed: ");
    	System.out.println(t.parent(9) == 4);
    	
    	
    	
    	//Test 11-20(ExtractMin Tests)
    	System.out.print("Test 11 passed: ");
    	System.out.println(t.extractMin() == 1);
    	
    	System.out.print("Test 12 passed: ");
    	System.out.println(t.extractMin() == 2);
    	
    	System.out.print("Test 13 passed: ");
    	System.out.println(t.extractMin() == 4);
    	
    	System.out.print("Test 14 passed: ");
    	System.out.println(t.extractMin() == 5);
    	
    	System.out.print("Test 15 passed: ");
    	System.out.println(t.extractMin() == 7);
    	
    	System.out.print("Test 16 passed: ");
    	System.out.println(t.extractMin() == 8);
    	
    	System.out.print("Test 17 passed: ");
    	System.out.println(t.extractMin() == 9);
    	
    	System.out.print("Test 18 passed: ");
    	System.out.println(t.extractMin() == 10);
    	
    	System.out.print("Test 19 passed: ");
    	System.out.println(t.extractMin() == 14);
    	
    	System.out.print("Test 20 passed: ");
    	System.out.println(t.extractMin() == 16);

    	
    	//Test 21(Empty assertion)
    	System.out.print("Test 21 passed: ");
    	System.out.println(t.toString()=="[]");
    	
    	
    	
    	
    	
    	
    	
    	
    }
}
