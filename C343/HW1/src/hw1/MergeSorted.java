package hw1;

public class MergeSorted {

	private SinglyLinkedList a;
	private SinglyLinkedList b;
	private SinglyLinkedList sort;
	public MergeSorted(SinglyLinkedList a, SinglyLinkedList b)
	{
		this.a = a;
		this.b = b;
	}
	
	private SinglyLinkedList sort(SinglyLinkedList a, SinglyLinkedList b)
	{
		if(a == null && b == null)
		{
			return null;
		}
		else if(b==null)
		{
			sort = new SinglyLinkedList(a.data,sort(a.next,b));
		}
		else if (a==null)
		{
			sort = new SinglyLinkedList(b.data,sort(a,b.next));
		}
		else if(a.data<b.data)
		{
			sort = new SinglyLinkedList(a.data,sort(a.next,b));
		}
		else
		{
			sort = new SinglyLinkedList(b.data,sort(a,b.next));
		}
		
		
		return sort;
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
		
		
		MergeSorted test1 = new MergeSorted(List1,List2);
		SinglyLinkedList output = test1.sort(test1.a,test1.b);
		System.out.println(output);

	}

}
