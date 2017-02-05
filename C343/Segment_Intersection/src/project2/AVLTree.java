package project2;

import java.util.function.BiPredicate;

public class AVLTree<Key> implements SearchTree<Key>{


	BiPredicate<Key,Key> current;
	BSTNode<Key> root;

	public AVLTree(BiPredicate<Key,Key> bi )
	{
		current = bi;
	}


	@Override
	public BSTNode<Key> insert(Key key) {
		return insert_helper(root, key);
	}
	
	public BSTNode<Key> insert_helper(BSTNode<Key> n, Key key)
	{	
		if(n == null)
		{
			root = new BSTNode<Key>(null,null,n,key);
			return root;
		}
		else if(n.right == null && current.test(n.key, key))//if there is a empty spot and key is nkey is greater than key
		{
			n.right = new BSTNode<Key>(null, null, n , key );
			//rebalance
			if (n.left == null)
			{
				updateHeights(n);
			}
			
			BSTNode<Key> unbalanced = climb(n);
			if(unbalanced != null)
			{
				BSTNode<Key> balanced = rebalance(unbalanced,key);
			}
			return n.right;
		}
		else if(current.test(n.key,key))
		{
			return insert_helper(n.right,key);
		}
		else if(n.left == null && !current.test(n.key, key))
		{
			n.left = new BSTNode<Key>(null, null, n , key );
			if(n.right == null)
			{
				updateHeights(n);
			}
			BSTNode<Key> unbalanced = climb(n);
			if(unbalanced != null)
			{
				BSTNode<Key> balanced = rebalance(unbalanced,key);
			}
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
			if(toDelete.parent == null){}
			if(toDelete.left == null && toDelete.right == null)//Both leaves are null so just make the link to the parent null
			{
				if(toDelete.parent == null)
				{
					BSTNode<Key> rightBranch = toDelete.right;
					BSTNode<Key> swapNode = toDelete.max(toDelete.left);
					BSTNode<Key> leftBranch = toDelete.left;
					
					swapNode.left = deletePivotRight(leftBranch, swapNode.key);
					swapNode.right = rightBranch;
					if(swapNode != null){swapNode.parent = toDelete.parent;}
					if(swapNode != null){swapNode.parent.right = swapNode;}
					rightBranch.parent = swapNode;
					updateAfterDelete(swapNode);
					BSTNode<Key> unbalanced = climb(swapNode);
					if(unbalanced != null)
					{
						BSTNode<Key> balanced = rebalance(unbalanced,key);
					}
					root = swapNode;
				}
				else if(toDelete.parent.left == toDelete)
				{
					if(toDelete.parent.parent == null)
					{
						root.left = null;
					}
					toDelete.parent.left = null;
					
				}
				else
				{
					if(toDelete.parent.parent == null)
					{
						root.right = null;
					}
					toDelete.parent.right = null;
				}
			}
			else if(toDelete.left == null)// left node is null so just remove and re-attach to whatever side of parent it comes from
			{
				if(toDelete.parent == null)
				{
					toDelete.right.parent = null;
					root = toDelete.right;
				}
				else if(toDelete.parent.left == toDelete)
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
				if(toDelete.parent == null)
				{
					toDelete.left.parent = null;
					root = toDelete.left;
				}
				else if(toDelete.parent.left == toDelete)
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
				if(toDelete.parent == null)
				{
					BSTNode<Key> rightBranch = toDelete.right;
					BSTNode<Key> swapNode = toDelete.max(toDelete.left);
					BSTNode<Key> leftBranch = toDelete.left;
					
					swapNode.left = deletePivotRight(leftBranch, swapNode.key);
					swapNode.right = rightBranch;
					if(swapNode != null){swapNode.parent = toDelete.parent;}
					if(swapNode != null){swapNode.parent = null;}
					if(swapNode.left != null){
						swapNode.left.parent = swapNode;}
					rightBranch.parent = swapNode;
					if(swapNode.left != null){updateAfterDelete(swapNode.left);}
					else{updateAfterDelete(swapNode);}
					
					BSTNode<Key> unbalanced = climb(swapNode);
					if(unbalanced != null)
					{
						BSTNode<Key> balanced = rebalance(unbalanced,key);
					}
					root = swapNode;
				}
				else if(toDelete.parent.left == toDelete)
				{
					BSTNode<Key> rightBranch = toDelete.right;
					BSTNode<Key> swapNode = toDelete.max(toDelete.left);
					BSTNode<Key> leftBranch = toDelete.left;
					
					swapNode.left = deletePivotRight(leftBranch, swapNode.key);
					swapNode.right = rightBranch;
					if(swapNode != null){swapNode.parent = toDelete.parent;}
					if(swapNode != null){swapNode.parent.left = swapNode;}
					rightBranch.parent = swapNode;
					updateAfterDelete(swapNode);
					BSTNode<Key> unbalanced = climb(swapNode);
					if(unbalanced != null)
					{
						BSTNode<Key> balanced = rebalance(unbalanced,key);
					}
					
				}
				else
				{
					BSTNode<Key> rightBranch = toDelete.right;
					BSTNode<Key> swapNode = toDelete.max(toDelete.left);
					BSTNode<Key> leftBranch = toDelete.left;
					
					swapNode.left = deletePivotRight(leftBranch, swapNode.key);
					swapNode.right = rightBranch;
					if(swapNode != null){swapNode.parent = toDelete.parent;}
					if(swapNode != null){swapNode.parent.right = swapNode;}
					rightBranch.parent = swapNode;
					updateAfterDelete(swapNode);
					BSTNode<Key> unbalanced = climb(swapNode);
					if(unbalanced != null)
					{
						BSTNode<Key> balanced = rebalance(unbalanced,key);
					}
				}
			}
			updateAfterDelete(toDelete.parent);
			BSTNode<Key> unbalanced = climb(toDelete.parent);
			if(unbalanced != null)
			{
				//unbalanced.height = unbalanced.height +1;
				BSTNode<Key> balanced = rebalance(unbalanced,key);
			}
	}
	BSTNode<Key> deletePivotRight(BSTNode<Key> n, Key k)
	{
		if(n.key.equals(k))
		{
			return null;
		}
		else
		{
			return new BSTNode<Key>(deletePivotRight(n.right,k),n.left,n.parent, n.key);//deletePivotRight(n.right, k);
		}
	}
	
	int getBalance(BSTNode<Key> N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }
	
	public BSTNode<Key> rebalance(BSTNode<Key> n, Key key)
	{
		int balance = getBalance(n);
		
		if (balance > 1 && current.test(key,n.left.key)) {
            return leftLeft(n);
        }
 
        // Right Right Case
        if (balance < -1 && current.test(n.right.key,key)) {
            return rightRight(n);
        }
 
        // Left Right Case
        if (balance > 1 && current.test(n.left.key,key)) {
            return leftRight(n);
        }
 
        // Right Left Case
        if (balance < -1 && current.test(key,n.right.key)) {
            return rightLeft(n);
        }
        else{
        /* return the (unchanged) node pointer */
        return n;}
	}
	
	
	//climbs up the tree to the first unbalanced node unless it hits the root
	BSTNode<Key> climb(BSTNode<Key> n)
	{
		if(n == null){return null;}
		else if(getBalance(n) >1 || getBalance(n) < -1)
		{
			return n;
		}
		else
		{
			return climb(n.parent);
		}
	}
	
	void updateHeights(BSTNode<Key> n)
	{
		if(n == null){return;}
		else
		{
			if(getBalance(n)!= 0)
			{
				n.height = n.height+1;
			}
			updateHeights(n.parent);
		}
	}
	void updateAfterDelete(BSTNode<Key> n)
	{
		if(n == null){return;}
		else
		{
			n.height = greater(height(n.left),height(n.right))+1;
			updateAfterDelete(n.parent);
		}
	}
	
	BSTNode<Key> rightRight(BSTNode<Key> n)
	{
		boolean switchRoot = isRoot(n);
		BSTNode<Key> k2 = n.right;
		BSTNode<Key> b = k2.left;
		BSTNode<Key> superParent = n.parent;
		
		k2.left = n;
		n.right =b;
		
		if(b != null){b.parent = n;}
		
		n.parent = k2;
		k2.parent = superParent;
		if(superParent != null)
		{
			if(superParent.left == n)
			{
				superParent.left = k2;
			}
			else superParent.right = k2;
		}
		n.height = greater(height(n.left),height(n.right))+1;
		if(switchRoot)
		{
			root = k2;
			return k2;
		}
		else
		{
			k2.parent.height = k2.parent.height -1;
			root = findRoot(k2);
			return root;
		}
	}
	
	BSTNode<Key> leftLeft(BSTNode<Key> n)
	{
		boolean switchRoot = isRoot(n);
		BSTNode<Key> k2 = n.left;
		BSTNode<Key> b = k2.right;
		BSTNode<Key> superParent = n.parent;
		
		
		k2.right = n;
		n.left =b;
		
		
		if(b != null){b.parent = n;}
		n.parent = k2;
		k2.parent = superParent;
		if(superParent != null)
		{
			if(superParent.left == n)
			{
				superParent.left = k2;
			}
			else superParent.right = k2;
		}
		
		n.height = greater(height(n.left),height(n.right))+1;
		if(switchRoot)
		{
			root = k2;
			return k2;
		}
		else
		{
			k2.parent.height = k2.parent.height -1;
			root = findRoot(k2);
			return root;
		}
	}
	
	BSTNode<Key> rightLeft(BSTNode<Key> n)
	{
		boolean switchRoot = isRoot(n);
		BSTNode<Key> k2 = n.right;
		BSTNode<Key> k3 = n.right.left;
		if(k3 == null){return rightRight(k2.parent);}
		BSTNode<Key> b = k3.left;
		BSTNode<Key> c = k3.right;
		BSTNode<Key> superParent = n.parent;
		
		k3.left = n;
		k3.right = k2;
		n.right = b;
		k2.left = c;
		
		
		if(b != null){b.parent = n;}
		if(c != null){c.parent = k2;}
		n.parent = k3;
		k2.parent = k3;
		k3.parent = superParent;
		if(superParent != null)
		{
			if(superParent.left == n)
			{
				superParent.left = k3;
			}
			else superParent.right = k3;
		}
		
		
		k3.height = k3.height +1;
		k2.height = k2.height -1;
		n.height = n.height -2;
		
		if(switchRoot)
		{
			root = k3;
			return k3;
		}
		else
		{			
			k3.parent.height = k3.parent.height -1;
			root = findRoot(k3);
			return root;
		}
	}
	
	BSTNode<Key> leftRight(BSTNode<Key> n)
	{
		boolean switchRoot = isRoot(n);
		BSTNode<Key> k2 = n.left;
		BSTNode<Key> k3 = n.left.right;
		if(k3 == null){return leftLeft(k2.parent);}
		BSTNode<Key> b = k3.left;	
		
		BSTNode<Key> c = k3.right;
		BSTNode<Key> superParent = n.parent;
		
		k3.right = n;
		k3.left = k2;
		n.left = b;
		k2.right = c;
		
		if(b != null){b.parent = k2;}
		if(c != null){c.parent = n;}
		n.parent = k3;
		k2.parent = k3;
		k3.parent = superParent;
		if(superParent != null)
		{
			if(superParent.left == n)
			{
				superParent.left = k3;
			}
			else superParent.right = k3;
		}
		
		k3.height = k3.height +1;
		k2.height = k2.height -1;
		n.height = n.height -2;
		
		if(switchRoot)
		{
			root = k3;
			return k3;
		}
		else
		{
			k3.parent.height = k3.parent.height -1;
			root = findRoot(k3);
			return root;
		}
	}
	
	
	
    BSTNode<Key> rightRotate(BSTNode<Key> y) {
    	BSTNode<Key> x = y.left;
    	BSTNode<Key> T2 = x.right;
 
        // Perform rotation
        x.right = y;
        y.left = T2;
 
        // Update heights
        y.height = greater(height(y.left), height(y.right)) + 1;
        x.height = greater(height(x.left), height(x.right)) + 1;
 
        // Return new root
        return x;
    }
 
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    BSTNode<Key> leftRotate(BSTNode<Key> x) {
    	BSTNode<Key> y = x.right;
    	BSTNode<Key> T2 = y.left;
 
        // Perform rotation
        y.left = x;
        x.right = T2;
 
        //  Update heights
        x.height = greater(height(x.left), height(x.right)) + 1;
        y.height = greater(height(y.left), height(y.right)) + 1;
 
        // Return new root
        return y;
    }
    
    BSTNode<Key> findRoot(BSTNode<Key> n)
    {
    	if(n.parent == null)
    	{
    		return n;
    	}
    	else{return findRoot(n.parent);}
    }
    boolean isRoot(BSTNode<Key> n)
    {
    	return root.equals(n);
    }
	public int height (BSTNode<Key> n)
	{
		if(n==null){return 0;}
		return n.height;
	}
	public int greater(int x, int y)
	{
		if(x>y){return x;}
		return y;
	}
	public void inOrder(BSTNode<Key>  n)
	{
		  if(n == null){return;}
		  inOrder(n.left);
		  
		  System.out.println(n.key);
		  
		  inOrder(n.right); 
	}
	
	public void inOrderHeight(BSTNode<Key>  n)
	{
		  if(n == null){return;}
		  inOrderHeight(n.left);
		  
		  System.out.println(n.key +" height: "+n.height);
		  
		  inOrderHeight(n.right); 
	}

	public static void main(String[] args) {
		System.out.println("\nTest insert:");
		BiPredicate<Integer, Integer> smaller = (i1,i2) -> i1<i2;
		AVLTree<Integer> test = new AVLTree<Integer>(smaller);
		test.insert(1);
		test.insert(2);
		test.insert(5);
		test.insert(3);
		test.insert(0);
		test.inOrder(test.root);
		test.inOrderHeight(test.root);
		
		System.out.println("");
		AVLTree<Integer> test2 = new AVLTree<Integer>(smaller);
		test2.insert(5);
		test2.insert(8);
		test2.insert(6);
		test2.insert(3);
		test2.insert(4);
		test2.inOrder(test2.root);
		test2.inOrderHeight(test2.root);
		
		System.out.println("");
		AVLTree<Integer> test3 = new AVLTree<Integer>(smaller);
		test3.insert(25);
		test3.insert(27);
		test3.insert(14);
		test3.insert(17);
		test3.insert(15);
		test3.insert(20);
		test3.insert(24);
		test3.inOrder(test3.root);
		test3.inOrderHeight(test3.root);
		
		System.out.println("");
		AVLTree<Integer> test4 = new AVLTree<Integer>(smaller);
		test4.insert(30);
		test4.insert(45);
		test4.insert(47);
		test4.insert(49);
		test4.insert(39);
		test4.insert(36);
		test4.insert(35);
		test4.insert(8);
		test4.insert(10);
		test4.insert(9);
		test4.insert(7);
		test4.inOrder(test4.root);
		test4.inOrderHeight(test4.root);
		
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
		test.inOrderHeight(test.root);
		System.out.println("\nTest insert: 6");
		test.insert(6);
		test.inOrderHeight(test.root);
		System.out.println("\nTest delete: 5");
		test.delete(5);
		test.inOrder(test.root);
		test.inOrderHeight(test.root);
		System.out.println("\nTest delete: 6");
		test.delete(6);
		test.inOrder(test.root);
		
		test.insert(4);
		test.insert(6);
		test.insert(5);
		System.out.println("\nTest insert: 4,6,5");
		test.inOrder(test.root);
		System.out.println("\nTest height:");
		test.inOrderHeight(test.root);
		System.out.println("\nTest delete: 4");
		test.delete(4);
		test.inOrder(test.root);
		test.inOrderHeight(test.root);
		System.out.println("\nTest delete: 6");
		test.delete(6);
		test.inOrder(test.root);
		test.inOrderHeight(test.root);
		System.out.println("\nTest delete: 5");
		test.delete(5);
		test.inOrder(test.root);
		
		
		test.inOrderHeight(test.root);
		
		System.out.println("\nDeleted all nodes\n");
		System.out.println("________________");
		
		
		
		
	}

}
