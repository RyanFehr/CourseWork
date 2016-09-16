package hw1;

public class SinglyLinkedListOperations {
	
	private Integer intList;
	private String stringList;
	public SinglyLinkedListOperations()
	{
		
	}
	
	public Integer addition(SinglyLinkedList a, SinglyLinkedList b)
	{
		int intA = listToInt(a);
		int intB = listToInt(b);
		return intA +intB;
	}
	public Integer subtraction(SinglyLinkedList a, SinglyLinkedList b)
	{
		int intA = listToInt(a);
		int intB = listToInt(b);
		return intA - intB;
	}
	public Integer multiplication(SinglyLinkedList a, SinglyLinkedList b)
	{
		int intA = listToInt(a);
		int intB = listToInt(b);
		return intA * intB;
	}
	
	public Integer listToInt(SinglyLinkedList list)
	{
		stringList = "";
		stringList = listToString(list);
		intList = Integer.parseInt(stringList);
		return intList;
	}
	public String listToString(SinglyLinkedList list)
	{
		if(list != null)
		{
			stringList += list.data + listToString(list.next);
		}
		return stringList;
	}

	public static void main(String[] args) {
		SinglyLinkedList Node5 = new SinglyLinkedList(6,null);
		SinglyLinkedList Node4 = new SinglyLinkedList(5,Node5);
		SinglyLinkedList Node3 = new SinglyLinkedList(3,Node4);
		SinglyLinkedList Node2 = new SinglyLinkedList(2,Node3);
		SinglyLinkedList List1 = new SinglyLinkedList(1,Node2);
		
		SinglyLinkedList Node9 = new SinglyLinkedList(8,null);
		SinglyLinkedList Node8 = new SinglyLinkedList(7,Node9);
		SinglyLinkedList Node7 = new SinglyLinkedList(6,Node8);
		SinglyLinkedList List2 = new SinglyLinkedList(4,Node7);
		SinglyLinkedList Node10 = new SinglyLinkedList(0,null);
		
		
		
		SinglyLinkedList Number10 = new SinglyLinkedList(1,Node10);
		SinglyLinkedList Number5 = new SinglyLinkedList(5,null);
		
		SinglyLinkedListOperations test = new SinglyLinkedListOperations();
		System.out.println(test.addition(Number10, Number5));
		System.out.println(test.subtraction(Number10, Number5));
		System.out.println(test.multiplication(Number10, Number5));
		
		System.out.println(test.addition(List1, List2));
		

	}

}
