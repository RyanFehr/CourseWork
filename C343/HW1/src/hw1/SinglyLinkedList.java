package hw1;

public class SinglyLinkedList {

	
	
	public int data;
	public SinglyLinkedList next;

	public SinglyLinkedList(int data, SinglyLinkedList next)
	{
		this.data = data;
		this.next = next;
	}
	public String toString()
	{
		String output ="";
		if(this.next != null)
		{
			output += this.data +" "+allElements(this.next);
		}
		else
		{
			output += this.data;
		}
			
		return output;
	}
	public String allElements(SinglyLinkedList list)
	{
		String output = "";
		if(list.next != null)
		{
			output+= list.data + " " + allElements(list.next)+" ";
			
		}
		else 
		{
			output+= list.data;
		}
		return output;
	}
}
