package lab;

//Lab 4
//
//Ex. 5.22, modify BinarySearchTree to find the Kth smallest
//value in O(log n) time. (The K's start at 0.)
//Hint: each BSTNode should record the size of its left subtree.

import java.util.Stack;
import java.util.HashSet;

class BSTNode {
int key;
BSTNode left;
BSTNode right;
int leftSize;

BSTNode(int k, BSTNode l, BSTNode r) {
 key = k;
 left = l;
 right = r;
 leftSize = 0;
}
}


class BinarySearchTree {
BSTNode root;
BinarySearchTree() {
 root = null;
}
boolean find(int k) {
 return find_helper(root, k) != null;
}
void insert(int key) {
 root = insert_helper(root, key);
}
void remove(int key) {
 root = remove_helper(root, key);
}
int kth_smallest(int k) {
 /* replace the following with your code */
	BSTNode position = kth_smallest_helper(root, k);
	if(position == null )
	{
		return -1;
	}
	else{return position.key;}
	
}
BSTNode kth_smallest_helper(BSTNode n, int k)
{
	if (n == null)
	{
		return null;
	}
	else if(n.leftSize == k)
	{
		return n;
	}
	else if(n.leftSize > k)
	{
		return kth_smallest_helper(n.left,k);
	}
	else
	{
		return kth_smallest_helper(n.right,k -(n.leftSize +1));
	}
}
public void print_tree() { System.out.print(tree_to_string(root)); }

private String tree_to_string(BSTNode n) {
 if (n != null) {
   return String.format("(%s %d %s)",
       tree_to_string(n.left),
       n.key,
       tree_to_string(n.right));
 }
 return "";
}

// Helper Functions

private BSTNode find_helper(BSTNode n, int key) {
 if (n == null) {
   return null;
 } else if (key < n.key) {
   return find_helper(n.left, key);
 } else if (key > n.key) {
   return find_helper(n.right, key);
 } else {
   return n;
 }
}
private BSTNode insert_helper(BSTNode n, int key) {
 if (n == null) {
   return new BSTNode(key, null, null);
 } else if (key < n.key) {
   n.leftSize++;
   n.left = insert_helper(n.left, key);
   return n;
 } else if (key > n.key) {
   n.right = insert_helper(n.right, key);
   return n;
 } else { // no need to insert, already there
   return n;
 }
}
private BSTNode delete_min(BSTNode n) {
 if (n.left == null) {
   return n.right;
 } else {
   n.left = delete_min(n.left);
   return n;
 }
}
private BSTNode get_min(BSTNode n) {
 if (n.left == null) {
   return n;
 }
 else {
   return get_min(n.left);
 }
}
private BSTNode remove_helper(BSTNode n, int key) {
 if (n == null) {
   return null;
 } else if (key < n.key) { // remove in left subtree
   n.left = remove_helper(n.left, key);
   return n;
 } else if (key > n.key) { // remove in right subtree
   n.right = remove_helper(n.right, key);
   return n;
 } else { // remove this node
   if (n.left == null) {
     return n.right;
   } else if (n.right == null) {
     return n.left;
   } else { // two children, replace this with min of right subtree
     BSTNode min = get_min(n.right);
     n.key = min.key;
     n.right = delete_min(n.right);
     return n;
   }
 }
}

public static void main(String[] args) {
 BinarySearchTree T = new BinarySearchTree();
 HashSet<Integer> H = new HashSet<Integer>();
 int[] A = { 5, 2, 4, 1, 5, 9, 8 };
 int[] A_sorted = { 1, 2, 4, 5, 8, 9 };
 
 // Test insert and find
 for (int i = 0; i != A.length; ++i) {
   T.insert(A[i]);
   H.add(A[i]);
 }
 for (Integer k : H) {
   assert T.find(k) == true;
 }
 for (int k : A_sorted) {
   assert T.find(k) == true;
 }
 
 // Test remove
 for (int i = 0; i != A.length; ++i) {
   T.remove(A[i]);
   assert T.find(A[i]) == false;
 }
 
 // Test kth_smallest
 try {
   for (int i = 0; i != A_sorted.length; ++i) {
     int small = T.kth_smallest(i);
     assert small == A_sorted[i];
   }
 } catch (java.lang.Exception e) {
    System.out.println(e.toString());
 }

 System.out.println("tests passed!");
}

}

