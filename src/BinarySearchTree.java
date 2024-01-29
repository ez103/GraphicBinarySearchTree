import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
	public BinaryNode root;
	
	public Comparable[] level0;
	public Comparable[] level1;
	public Comparable[] level2;
	public Comparable[] level3;
	public Comparable[] level4;
	public Comparable[] level5;
	
	public BinarySearchTree() {
		root = null;
		
		level0 = new Comparable[1];
		level1 = new Comparable[2];
		level2 = new Comparable[4];
		level3 = new Comparable[8];
		level4 = new Comparable[16];
		level5 = new Comparable[32];
	}
	
	public void add(BinaryNode x) {
		if (root == null) {
			root = x;
		}
		else {
			add(root, x);
		}
	}
	private void add(BinaryNode parent, BinaryNode x) {
		if(parent == null) return;
		if(x.getValue().compareTo(parent.getValue()) < 0) {
			if(parent.left() == null) {
				parent.setLeft(x);
			}
			else {
				add(parent.left(), x);
			}
		}
		else {
			if(parent.right() == null) {
				parent.setRight(x);
			}
			else {
				add(parent.right(), x);
			}
		}
	}
	
	public Comparable[] level0() {
		return level0;
	}
	public Comparable[] level1() {
		return level1;
	}
	public Comparable[] level2() {
		return level2;
	}
	public Comparable[] level3() {
		return level3;
	}
	public Comparable[] level4() {
		return level4;
	}
	public Comparable[] level5() {
		return level5;
	}
	
	
	public String levelOrder() {
		if (root == null) {
			return "";
		}
		String temp = "";
		Queue<BinaryNode> q = new LinkedList<>();
		q.offer(root);
		
		while (!q.isEmpty()) {
			BinaryNode boob = q.poll();
			temp += boob.getValue() + " ";
			if (boob.left() != null) {
				q.offer(boob.left());
			}
			if (boob.right() != null) {
				q.offer(boob.right());
			}
		}
		
		return temp.trim();
	}
	
	
	private BinaryNode successor(BinaryNode k) {
		BinaryNode temp = k;
		temp = temp.right();
		while(temp.left() != null)
			temp = temp.left();
		return temp;
	}
	private void swap(BinaryNode x, BinaryNode y)
	{
		Comparable k = x.getValue();
		x.setValue(y.getValue());
		y.setValue(k);
	}
	public BinaryNode remove(Comparable target) {
		if (root == null) {
			return null;
		}
		
		BinaryNode temp = root;
		BinaryNode inorderSuccessor;
		
		if (root.getValue().equals(target)) {
			// degree 0; a leaf, no children
			if (root.left() == null && root.right() == null) {
				root = null;
				return temp;
			}
			// has a right child only
			else if (root.left() == null) {
				root = root.right();
				temp.setRight(null);
				return temp;
			}
			// has a left child only
			else if (root.right() == null) {
				root = root.left();
				temp.setLeft(null);
				return temp;
			}
			// has 2 children
			else {
				inorderSuccessor = successor(root);
				swap(root, inorderSuccessor);
				
				if (root.right() == inorderSuccessor) {
					root.setRight(inorderSuccessor.right());
					inorderSuccessor.setRight(null);
					return inorderSuccessor;
				}
				
				return remove(root.right(), target);
			}
		}
		return remove(root, target);
	}
	private BinaryNode remove(BinaryNode startNode, Comparable target) {
		BinaryNode nodeToRemove, inorderSuccessor;
		BinaryNode parent = search(startNode,target);
		if(parent == null) return null;
		//decide if it is a left or right child
		boolean isLeft = parent.left()!=null && parent.left().getValue().equals(target);
		nodeToRemove = isLeft ? parent.left() : parent.right();
		
		if (nodeToRemove.left() == null && nodeToRemove.right() == null) {
			if(isLeft)
				parent.setLeft(null);
			else
				parent.setRight(null);
			return nodeToRemove;
		}
		else if (nodeToRemove.left() == null)
		{
			if(isLeft)
				parent.setLeft(nodeToRemove.right());
			else
				parent.setRight(nodeToRemove.right());
			nodeToRemove.setRight(null);
			return nodeToRemove;
		}
		else if (nodeToRemove.right() == null) {
			if(isLeft)
				parent.setLeft(nodeToRemove.left());
			else
				parent.setRight(nodeToRemove.left());
			nodeToRemove.setLeft(null);
			return nodeToRemove;
		}
		else {
			inorderSuccessor = successor(nodeToRemove);
			swap(inorderSuccessor, nodeToRemove);
			if(nodeToRemove.right()==inorderSuccessor) {
				nodeToRemove.setRight(inorderSuccessor.right());
				inorderSuccessor.setRight(null);
				return inorderSuccessor;
			}
			return remove(nodeToRemove.right(), target);
		}
	}
	private BinaryNode search(BinaryNode parent, Comparable target) {
		if (parent == null) {
			return null;
		}
		if (parent.left()!=null && parent.left().getValue().equals(target) 
				|| parent.right()!=null && parent.right().getValue().equals(target)) {
			return parent;
		}
		else if (target.compareTo(parent.getValue()) < 0) {
			return search(parent.left(), target);
		}
		else {
			return search(parent.right(), target);
		}
	}
	
	
	public void fillLevels() {
		
		if (root == null) {
			level0[0] = -6969911;
			return;
		}
		
		Queue<BinaryNode> q = new LinkedList<>();
		
		if (root.left() != null) {
			q.add(root.left());
		}
		else {
			q.add(new BinaryNode(-6969911));
		}
		if (root.right() != null) {
			q.add(root.right());
		}
		else {
			q.add(new BinaryNode(-6969911));
		}
		
		level0[0] = root.getValue(); // LEVEL 0 fill
		
		int levelWidth = 2; // LEVEL 1 fill
		while (levelWidth > 0) {
			BinaryNode g = q.poll();
			level1[2 - levelWidth] = g.getValue();

			if (g.left() != null) {
				q.add(g.left());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			if (g.right() != null) {
				q.add(g.right());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			levelWidth--;
		}
		
		levelWidth = 4;
		while (levelWidth > 0) { // level 2 fill
			BinaryNode g = q.poll();
			level2[4 - levelWidth] = g.getValue();

			if (g.left() != null) {
				q.add(g.left());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			if (g.right() != null) {
				q.add(g.right());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			levelWidth--;
		}
		
		levelWidth = 8;
		while (levelWidth > 0) {
			BinaryNode g = q.poll();
			level3[8 - levelWidth] = g.getValue(); // LEVEL 3 fill

			if (g.left() != null) {
				q.add(g.left());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			if (g.right() != null) {
				q.add(g.right());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			levelWidth--;
		}
		
		levelWidth = 16;
		while (levelWidth > 0) {
			BinaryNode g = q.poll();
			level4[16 - levelWidth] = g.getValue(); // LEVEL 4 fill

			if (g.left() != null) {
				q.add(g.left());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			if (g.right() != null) {
				q.add(g.right());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			levelWidth--;
		}
		
		levelWidth = 32;
		while (levelWidth > 0) {
			BinaryNode g = q.poll();
			level5[32 - levelWidth] = g.getValue(); // LEVEL 5 fill

			if (g.left() != null) {
				q.add(g.left());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			if (g.right() != null) {
				q.add(g.right());
			}
			else {
				q.add(new BinaryNode(-6969911)); // this means there is no node existing, just a placeholder
			}
			levelWidth--;
		}
	}
}












