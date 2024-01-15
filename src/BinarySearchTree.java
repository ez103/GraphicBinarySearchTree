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
	
	public String levelOrder() {
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
	
	public void fillLevels() {
		
	}
}












