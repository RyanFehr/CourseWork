package hw2;

public class BinarySearchTree {
	
	public static int smallcount(BSTnode root, int key)
	{
		if(root == null)
		{
			return 0;
		}
		else if(root.key > key)
		{
			return 0 + smallcount(root.left, key);
		}
		else if(root.key < key)
		{
			return 1 + smallcount(root.left,key) + smallcount(root.right, key);
		}
		else
		{
			return 1 + smallcount(root.left,key);
		}
	}

	public static void main(String args[])
	{
		BSTnode seven = new BSTnode(7,null,null);
		BSTnode four  = new BSTnode(4,null,null);
		BSTnode thirteen = new BSTnode(13,null,null);
		BSTnode fourteen = new BSTnode(14,thirteen,null);
		BSTnode six = new BSTnode(6,four,seven);
		BSTnode one = new BSTnode(1,null,null);
		BSTnode ten = new BSTnode(10,null,fourteen);
		BSTnode three = new BSTnode(3,one,six);
		BSTnode eight = new BSTnode(8,three,ten);
		System.out.println(smallcount(eight,5));
	}
}
