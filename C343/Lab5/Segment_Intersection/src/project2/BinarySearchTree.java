package project2;

import java.util.function.BiPredicate;

public class BinarySearchTree<Key> implements SearchTree<Key>
{
	BiPredicate<Key,Key> current;
	BSTNode<Key> root;

	public BinarySearchTree(BiPredicate<Key,Key> bi )
	{
		current = bi;
	}



	public BSTNode<Key> insert(Key key) {
		return insert_helper(root, key);
	}
	
	public BSTNode<Key> insert_helper(BSTNode<Key> n, Key key)
	{
		if(n.right == null && current.test(n.key, key))//if there is a empty spot and key is nkey is greater than key
		{
			n.right = new BSTNode<Key>(null, null, n , key );
			return n.right;
		}
		else if(current.test(n.key,key))
		{
			return insert_helper(n.right,key);
		}
		else if(n.left == null && !current.test(n.key, key))
		{
			n.left = new BSTNode<Key>(null, null, n , key );
			return n.left;
		}
		else
		{
			return insert_helper(n.left,key);
		}
	}

	public Node<Key> search(Key key) {
		return search_helper(root, key);
	}
	public Node<Key> search_helper(BSTNode<Key> n, Key key)
	{
		if(n == null){return null;}
		else if(key.equals(n.key))
		{
			return n;
		}
		else if(current.test(key, n.key))
		{
			return search_helper(n.left, key);
		}
		else
		{
			return search_helper(n.right, key);
		}
	}

	public void delete(Key key) 
	{
				
	}
	public void inOrder(BSTNode<Key>  n)
	{
		  if(n == null){return;}
		  inOrder(n.left);
		  
		  System.out.println(n.key);
		  
		  inOrder(n.right); 
	}
	
	public static void main(String[] args)
	{
		System.out.println("\nTest insert:");
		BiPredicate<Integer, Integer> smaller = (i1,i2) -> i1<i2;
		BinarySearchTree<Integer> test = new BinarySearchTree<Integer>(smaller);
		test.root = new BSTNode<Integer>(null,null,null,1);
		test.insert(2);
		test.insert(5);
		test.insert(3);
		test.insert(0);
		test.inOrder(test.root);
		System.out.println("\nTest search:");
		Integer t = new Integer(3);
		System.out.println(test.search(t));
		t = new Integer(6);
		System.out.println(test.search(t));
		t = new Integer(0);
		System.out.println(test.search(t));
		System.out.println("\nTest after:");
		System.out.println(test.search(0).after());
		System.out.println(test.search(1).after());
		System.out.println(test.search(2).after());
		System.out.println(test.search(3).after());
		System.out.println(test.search(5).after());
	}
}

