package hw;

import java.util.Stack;

//Starting with the Java code for Quicksort given in this chapter, write a series of Quicksort implementations 
//to test the following optimizations on a wide range of input data sizes. 
//Try these optimizations in various combinations to try and develop the fastest possible Quicksort implementation that you can. 
//(a) Look at more values when selecting a pivot. 
//(b) Do not make a recursive call to qsort when the list 
//	size falls below a given threshold, and use Insertion 
//	Sort to complete the sorting process. Test various 
//	values for the threshold size. 
//(c) Eliminate recursion by using a stack and inline functions.

//(a)
//I improved by the a method by looking at the beginning element, middle element, and last element and picking the median of the three using a median function

//(b)
// I set the threshold to 5 elements because that is where my insertion sort starts working faster because it has fewer basic operations at values < 5

//(c)
//qsortS is the stack implementation with the other improvements added in


public class QuickSortImprove {

	
	static <E extends Comparable<? super E>> 
	void qsortS(E[] input)
    {
		if(input.length <= 5)
		{
			isort(input);
			return;
		}
        Stack stack = new Stack();
        E pivot;
        int pivotIndex = 0;
        int leftIndex = pivotIndex + 1;
        int rightIndex = input.length - 1;

        stack.push(pivotIndex);
        stack.push(rightIndex);

        int leftIndexOfSubSet, rightIndexOfSubset;

        while (stack.size() > 0)
        {
            rightIndexOfSubset = (int)stack.pop();
            leftIndexOfSubSet = (int)stack.pop();

            leftIndex = leftIndexOfSubSet + 1;
            pivotIndex = leftIndexOfSubSet;
            rightIndex = rightIndexOfSubset;

            pivot = input[pivotIndex];

            if (leftIndex > rightIndex)
                continue;

            while (leftIndex < rightIndex)
            {
                while ((leftIndex <= rightIndex) && (input[leftIndex].compareTo(pivot)<=0))
                    leftIndex++;

                while ((leftIndex <= rightIndex) && (input[rightIndex].compareTo(pivot) >=0))
                    rightIndex--;

                if (rightIndex >= leftIndex) 
                    DSutil.swap(input, leftIndex,rightIndex);
            }

            if (pivotIndex <= rightIndex)
                if( input[pivotIndex].compareTo(input[rightIndex]) >0)
                	DSutil.swap(input, pivotIndex,rightIndex);
           
            if (leftIndexOfSubSet < rightIndex)
            {
                stack.push(leftIndexOfSubSet);
                stack.push(rightIndex - 1);
            }

            if (rightIndexOfSubset > rightIndex)
            {
                stack.push(rightIndex + 1);
                stack.push(rightIndexOfSubset);
            }
        }
    }
	
	static <E extends Comparable<? super E>> 
	void qsort(E[] A, int i, int j) { // Quicksort
		if(A.length <= 5)
		{
			isort(A);
			return;
		}
		int pivotindex = findpivot(A, i, j); // Pick a pivot
		DSutil.swap(A, pivotindex, j); // Stick pivot at end
		// k will be the first position in the right subarray
		int k = partition(A, i - 1, j, A[j]);
		DSutil.swap(A, k, j); // Put pivot in place
		if ((k - i) > 1){
			qsort(A, i, k - 1); // Sort left partition
		}
		if ((j - k) > 1){
			qsort(A, k + 1, j); // Sort right partition
		}
	}
	
	static <E extends Comparable<? super E>> 
	void isort(E[] A)
	{
		E currentSort;
		int j;
			for(int i=0;i<A.length;i++)
			{
				currentSort = A[i];
				for(j = i - 1;(j >= 0) && (A[j].compareTo(currentSort)>0);j--)
		          {
					A[j+1] = A[j];
		          }
				A[j+1] = currentSort;
			}
	}
	
	
	
	static <E extends Comparable<? super E>> 
	int findpivot(E[] A, int i, int j) {
		int middlePivot = (i + j) / 2;
		return median(A, i, middlePivot, j);
	}
	static <E extends Comparable<? super E>>
	int median(E[] A, int i, int mid, int j)
	{
		E l = A[i];
		E m = A[mid];
		E r = A[j];
		if(l.compareTo(r)>0)
		{
			if(l.compareTo(m) < 0)
			{
				return i;
			}
			else
			{
				if(r.compareTo(m)>0)
				{
					return j;
				}
				else 
				{
					return mid;
				}
			}
		}
		else
		{
			if(m.compareTo(r)>0)
			{
				return j;
			}
			else
			{
				if(m.compareTo(l)< 0)
				{
					return i;
				}
				else
				{
					return mid;
				}
			}
		}
	}
	
	static <E extends Comparable<? super E>> 
	int partition(E[] A, int l, int r, E pivot) {
		do { // Move bounds inward until they meet
			while (A[++l].compareTo(pivot) < 0)
				;
			while ((r != 0) && (A[--r].compareTo(pivot) > 0))
				;
			DSutil.swap(A, l, r); // Swap out-of-place values
		} while (l < r); // Stop when they cross
		DSutil.swap(A, l, r); // Reverse last, wasted swap
		return l; // Return first position in right partition
	}
	
	public static void main(String[] args) 
	{		
		Integer[] testArray2 = {3,2,9,6,5};
		qsortS(testArray2);
		Integer[] printArray = testArray2;
		for(int i = 0;i<printArray.length;i++)
		{
			System.out.print(printArray[i]+",");
		}
		
//		System.out.println();
//		Integer[] testArray2 = {9,8,7,6,5,4,3,2,1};
//		qsort(testArray2, 0, 4, 3);
//		printArray = testArray2;
//		for(int i = 0;i<printArray.length;i++)
//		{
//			System.out.print(printArray[i]+",");
//		}
//		System.out.println();
//		Integer[] testArray3 = {9,8,7,6,6,4,3,2,1};
//		qsort(testArray3, 0, 4, 3);
//		printArray = testArray3;
//		for(int i = 0;i<printArray.length;i++)
//		{
//			System.out.print(printArray[i]+",");
//		}
		
	}
}
