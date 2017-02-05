package hw;

public class QuickSort {

	static int count = 1;
	static <E extends Comparable<? super E>> 
	void qsort(E[] A, int i, int j) { // Quicksort
		//print(A); System.out.print(" start\n");
		
		int pivotindex = findpivot(A, i, j); // Pick a pivot
		DSutil.swap(A, pivotindex, j); // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partition(A, i - 1, j, A[j], j);
		//print(A); System.out.print(" post partition\n");
		DSutil.swap(A, k, j); // Put pivot in place
		//print(A); System.out.print(" post pivot place\n");
		if ((k - i) > 1)
			qsort(A, i, k - 1); // Sort left partition
		if ((j - k) > 1)
			qsort(A, k + 1, j); // Sort right partition
	}
	
	
	static <E extends Comparable<? super E>> 
	void qsortk(E[] A, int i, int j, int k2) { // Quicksort
		if(k2 <= 0)//your left partition is the k smallest elements
		{
			//qsort(A,i,k-1);
			//System.out.println("k left");
			return;
		}
		//print(A); System.out.print(" K start\n");
		
		int pivotindex = findpivot(A, i, j); // Pick a pivot
		DSutil.swap(A, pivotindex, j); // Stick pivot at end 
		// k will be the first position in the right subarray
		int k = partition(A, i - 1, j, A[j], j);
		//print(A); System.out.print(" K post partition\n");
		DSutil.swap(A, k, j); // Put pivot in place
		//print(A); System.out.print(" K post pivot place\n");
		
		
		if(k-1 >= k2)
		{
			if ((k - i) > 1)
				qsortk(A, i, k - 1, k2); // Sort left partition
		}
		else
		{
			if ((k - i) > 1)
				qsort(A, i, k - 1); // Sort left partition
			if ((j - k) > 1 && k2 >0)
				qsortk(A, k + 1, j, k2-k+i-1); // Sort right partition
		}	
		
	}

	static <E extends Comparable<? super E>> 
	int findpivot(E[] A, int i, int j) {
		return (i + j) / 2;
	}
	
	static <E extends Comparable<? super E>> 
	int partition(E[] A, int l, int r, E pivot, int k) {
		do { // Move bounds inward until they meet
			while (A[++l].compareTo(pivot) < 0){;}
				while ((r != 0) && (A[--r].compareTo(pivot) > 0));
				DSutil.swap(A, l, r); // Swap out-of-place values
		} while (l < r); // Stop when they cross
		DSutil.swap(A, l, r); // Reverse last, wasted swap
		return l; // Return first position in right partition
	}
	
	static <E extends Comparable<? super E>> 
	void print(E[] A)
	{
		for(int i = 0;i<A.length;i++)
		{
			System.out.print(A[i]+",");
		}
		System.out.print("   :"+count);
		count++;
	}
	public static void main(String[] args) 
	{
		
		Integer[] testArray = {3,2,9,6,5};
		System.out.println("Test1");
		System.out.println("Original Array:");
		for(int i = 0;i<testArray.length;i++)
		{
			System.out.print(testArray[i]+",");
		}
		System.out.print(" with a k value of 5\n");
		System.out.print("Output: ");
		qsortk(testArray, 0, 4,5);
		Integer[] printArray = testArray;
		for(int i = 0;i<printArray.length;i++)
		{
			System.out.print(printArray[i]+",");
		}
		System.out.println("");
		
		
		
		/////////////////////////////////////
		Integer[] testArray2 = {3,2,9,6,5};
		System.out.println("Test2");
		System.out.println("Original Array:");
		for(int i = 0;i<testArray.length;i++)
		{
			System.out.print(testArray[i]+",");
		}
		System.out.print(" with a k value of 2\n");
		System.out.print("Output: ");
		qsortk(testArray2, 0, 4, 2);
		printArray = testArray2;
		for(int i = 0;i<printArray.length;i++)
		{
			System.out.print(printArray[i]+",");
		}
		System.out.println("");
		
		//////////////////////////////////
		Integer[] testArray3 = {5,4,3,2,1};
		System.out.println("Test3");
		System.out.println("Original Array:");
		for(int i = 0;i<testArray.length;i++)
		{
			System.out.print(testArray[i]+",");
		}
		System.out.print(" with a k value of 1\n");
		System.out.print("Output: ");
		qsortk(testArray3, 0, 4, 1);
		printArray = testArray3;
		for(int i = 0;i<printArray.length;i++)
		{
			System.out.print(printArray[i]+",");
		}
		System.out.println("");
		
		///////////////////////////////////
		Integer[] testArray4 = {1,7,3,10,13, 18, 20, 6,26};
		System.out.println("Test4");
		System.out.println("Original Array:");
		for(int i = 0;i<testArray.length;i++)
		{
			System.out.print(testArray[i]+",");
		}
		System.out.print(" with a k value of 1\n");
		System.out.print("Output: ");
		qsortk(testArray4, 0, 8, 1);
		printArray = testArray4;
		for(int i = 0;i<printArray.length;i++)
		{
			System.out.print(printArray[i]+",");
		}
		System.out.println("");
		
		///////////////////////////////////
		Integer[] testArray5 = {10,10,10,10,1,2,3,10,10,10};
		System.out.println("Test5");
		System.out.println("Original Array:");
		for(int i = 0;i<testArray.length;i++)
		{
			System.out.print(testArray[i]+",");
		}
		System.out.print(" with a k value of 2\n");
		System.out.print("Output: ");
		qsortk(testArray5, 0, 9, 2);
		printArray = testArray5;
		for(int i = 0;i<printArray.length;i++)
		{
			System.out.print(printArray[i]+",");
		}
		System.out.println("");
		
		////////////////////////////////////
		Integer[] testArray6 = {10,9,8,7,6,5,4,3,2,1};
		System.out.println("Test6");
		System.out.println("Original Array:");
		for(int i = 0;i<testArray.length;i++)
		{
			System.out.print(testArray[i]+",");
		}
		System.out.print(" with a k value of 0\n");
		System.out.print("Output: ");
		qsortk(testArray6, 0, 9, 0);
		printArray = testArray6;
		for(int i = 0;i<printArray.length;i++)
		{
			System.out.print(printArray[i]+",");
		}
		
	}
}
 