package project2;

public class BSTNode<Key> implements Node<Key> 
{
	BSTNode<Key> right;
	BSTNode<Key> left;
	BSTNode<Key> after;
	BSTNode<Key> before;
	BSTNode<Key> parent;
	Key key;
	
	public BSTNode(BSTNode rightV, BSTNode leftV, BSTNode parentV, Key keyV)
	{
		this.right = rightV;
		this.left = leftV;
		this.parent = parentV;
		this.key = keyV;
	}
	
	@Override
	public Node<Key> after() {
		return after_helper(this);

	}
	public BSTNode<Key> after_helper(BSTNode<Key> n)
	{
		if(n.right != null)
		{
			return min(n.right);
		}
		BSTNode<Key> p = n.parent;
		while(p != null && n == p.right)
		{
			n = p;
			p = p.parent;
		}
		return p;
	
	}
	
	public BSTNode<Key> min(BSTNode<Key> n)
	{
		if(n.left == null)
		{
			return n;
		}
		else
		{
			return min(n.left);
		}
	}
	@Override
	public Node before() {
		return before_helper(this);
	}

	public Node<Key> before_helper(BSTNode<Key> n)
	{
		if(n.right != null)
		{
			return max(n.right);
		}
		else
		{
			if()
		}
	}
	public BSTNode<Key> max(BSTNode<Key> n)
	{
		if(n.right == null)
		{
			return n;
		}
		else
		{
			return max(n.right);
		}
	}
	@Override
	public Key getKey() {
		return key;
	}
	
	public String toString()
	{
		return key.toString();
	}

}
