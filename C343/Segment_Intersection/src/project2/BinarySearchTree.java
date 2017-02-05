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
	{	if(n == null)
		{
			root = new BSTNode<Key>(null,null,null,key);
			return root;
		}
		else if(n.right == null && current.test(n.key, key))//if there is a empty spot and key is nkey is greater than key
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

	public BSTNode<Key> search(Key key) {
		return search_helper(root, key);
	}
	public BSTNode<Key> search_helper(BSTNode<Key> n, Key key)
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
			BSTNode<Key>toDelete = search(key);
			if(toDelete.left == null && toDelete.right == null)//Both leaves are null so just make the link to the parent null
			{
				if(toDelete.parent.left == toDelete)
				{
					toDelete.parent.left = null;
				}
				else
				{
					toDelete.parent.right = null;
				}
			}
			else if(toDelete.left == null)// left node is null so just remove and re-attach to whatever side of parent it comes from
			{
				if(toDelete.parent.left == toDelete)
				{
					toDelete.parent.left = toDelete.right;
					toDelete.right.parent = toDelete.parent;
				}
				else
				{
					toDelete.parent.right = toDelete.right;
					toDelete.right.parent = toDelete.parent;
				}
			}
			else if(toDelete.right == null)// right node is null so just remove and re-attach to whatever side of parent it comes from
			{
				if(toDelete.parent.left == toDelete)
				{
					toDelete.parent.left = toDelete.left;
					toDelete.left.parent = toDelete.parent;
				}
				else
				{
					toDelete.parent.right = toDelete.left;
					toDelete.left.parent = toDelete.parent;
				}
			}
			else//neither is null
			{
				if(toDelete.parent.left == toDelete)
				{
					BSTNode<Key> rightBranch = toDelete.right;
					toDelete.parent.left = toDelete.left;
					BSTNode<Key> maxNode = toDelete.max(toDelete.parent.left);
					maxNode.right = rightBranch;
					rightBranch.parent = maxNode;
				}
				else
				{
					BSTNode<Key> rightBranch = toDelete.right;
					toDelete.parent.right = toDelete.left;
					BSTNode<Key> maxNode = toDelete.max(toDelete.parent.right);
					maxNode.right = rightBranch;
					rightBranch.parent = maxNode;
				}
			}
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
		test.insert(1);
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
		
		System.out.println("\nTest before:");
		System.out.println(test.search(0).before());
		System.out.println(test.search(1).before());
		System.out.println(test.search(2).before());
		System.out.println(test.search(3).before());
		System.out.println(test.search(5).before());
		
		System.out.println("\nTest delete: 0");
		test.delete(0);
		test.inOrder(test.root);
		test.insert(6);
		System.out.println("\nTest delete: 5");
		test.delete(5);
		test.inOrder(test.root);
		System.out.println("\nTest delete: 6");
		test.delete(6);
		test.inOrder(test.root);
		
		test.insert(4);
		test.insert(6);
		test.insert(5);
		System.out.println("\nTest delete: 4");
		test.delete(4);
		test.inOrder(test.root);
		System.out.println("\nTest delete: 6");
		test.delete(6);
		test.inOrder(test.root);
		System.out.println("\nTest delete: 5");
		test.delete(5);
		test.inOrder(test.root);
	}
}

