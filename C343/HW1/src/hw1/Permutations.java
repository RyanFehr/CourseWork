package hw1;

public class Permutations {
	
	int[] data;
	int swapIndex=0;
	
	Permutations(int[] data)
	{
		this.data = data;
	}

	public void printPermutations(int[] array, int perms)
	{
		
		//swap two elements, move swapIndex up and print until you arrive at the front
		int outerIndex = this.numberOfPermutations();
		outerIndex = outerIndex/array.length;
		outerIndex = outerIndex/(array.length-2);
		int innerIndex = array.length-2;
		int index = array.length-1;
		if(perms >1)
		{
			
			while(outerIndex >0)
			{
				while(innerIndex>0)
				{
					swap(array, index-1,index);
					printArray(array);
					perms--;
					index--;
					innerIndex--;
				}
				//do this perms/array.length/array.length-2
				//do it length - 2 times and then reset index
				outerIndex--;
				index = array.length-1;
				innerIndex = array.length-2;
			}
			//once at front recur
			if(perms > 0)
			{
				swapIndex++;
				swap(array,0,swapIndex);				
			}
			printPermutations(array, perms);
		}
		
		
	}
	public int numberOfPermutations()
	{
		int permutations =1;
		int i= 1;
		for(int item: data)
		{
			permutations *= i;
			i++;
		}
		return permutations;
	}
	
	public void swap(int[] array, int a, int b)
	{
		int temp;
		temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public void printArray(int[] array)
	{
		String output = "{";
		for(int i = 0;i<array.length;i++)
		{
			output += array[i] +",";
		}
		System.out.println(output.substring(0,output.length()-1) +"}");
	}
	public static void main(String[] args) 
	{
		int[] test1 = {1,2,3};
		int[] test2 = {1,2,3,4};
		int[] test3 = {1,2,3,4,5};
		Permutations Perm1 = new Permutations(test1);
		Perm1.printPermutations(Perm1.data, Perm1.numberOfPermutations());
		
		System.out.println("");
		Perm1 = new Permutations(test2);
		Perm1.printPermutations(Perm1.data, Perm1.numberOfPermutations());
		
		System.out.println("");
		Perm1 = new Permutations(test3);
		Perm1.printPermutations(Perm1.data, Perm1.numberOfPermutations());
	}

}
