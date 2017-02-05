package project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TestClass
{
	ArrayList<Integer> testA =  new ArrayList<Integer>();
	HeapImp<Integer> t = new HeapImp<Integer>(testA, new TestComparator());
	
	@Test
	public void AllTest()
	{
		InitializeTest();
		LeftRighTest();
		ParentTest();
		ExtractTest();
		RemovedEverythingTest();
	}
	
	
	public void InitializeTest()
	{  	
		//Initialize Array [1|2|7|5|4|14|9|16|8|10]
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
    	
    	assertTrue(t.heapCheck());
		
	}
	
    public void LeftRighTest()
    {
    	//Test 1-6(Left,Right Tests)
    	assertTrue(t.left(1) == 3);
    	
    	assertTrue(t.right(1) == 4);
    	
    	assertTrue(t.left(0) == 1);
    	
    	assertTrue(t.right(0) == 2);
    	
    	assertTrue(t.left(5) == 5);
    	
    	assertTrue(t.right(7) == 7);
    	
    }
	
	
	public void ParentTest()
	{  	
		//Test 7-10(Parent Tests)
    	assertTrue(t.parent(1) == 0);
    	
    	assertTrue(t.parent(0) == 0);
    	
    	assertTrue(t.parent(5) == 2);
    	
    	assertTrue(t.parent(9) == 4);
	}
    	
	
	public void ExtractTest()
	{  	
		//Test 11-20(ExtractMin Tests)
    	assertTrue(t.extractMin() == 1);
    	
    	assertTrue(t.extractMin() == 2);
    	
    	assertTrue(t.extractMin() == 4);
    	
    	assertTrue(t.extractMin() == 5);
    	
    	assertTrue(t.extractMin() == 7);
    	
    	assertTrue(t.extractMin() == 8);
    	
    	assertTrue(t.extractMin() == 9);
    	
    	assertTrue(t.extractMin() == 10);
    	
    	assertTrue(t.extractMin() == 14);
    	
    	assertTrue(t.extractMin() == 16);
	}	
    	
	
	public void RemovedEverythingTest()
	{  	
		//Test 21(Empty assertion)
    	assertTrue(t.toString()=="[]");
	}		
}
