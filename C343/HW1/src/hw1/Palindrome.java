package hw1;

import java.util.Stack;

public class Palindrome {

	
	public boolean detect(String word)
	{
		//Three fixed stacks and 4 fixed ints
		
		int characters = word.length();
		int pops = characters/2;
		int skips = characters%2;
		int i;
		Stack<String> firstHalf = new Stack<String>();
		Stack<String> transfer = new Stack<String>();
		Stack<String> secondHalf = new Stack<String>();
		for(i=0;i<pops;i++)
		{
			firstHalf.push(word.substring(word.length()-1-i,word.length()-i));
		}
		for(int j=0;j<skips;j++)
		{
			i++;
		}
		for(int j=0;j<pops;j++)
		{
			transfer.push(word.substring(word.length()-1-i,word.length()-i));
			i++;
		}
		for(i=0;i<pops;i++)
		{
			secondHalf.push(transfer.pop());
		}
		for(i=0;i<pops;i++)
		{
			if(!firstHalf.pop().equals(secondHalf.pop()))
			{
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Palindrome test = new Palindrome();
		
		System.out.println(test.detect("racecar"));
		System.out.println(test.detect("hit"));
		System.out.println(test.detect("some"));
		System.out.println(test.detect("people"));
		System.out.println(test.detect("maam"));

	}

}
