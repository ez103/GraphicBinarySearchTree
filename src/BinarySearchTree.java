
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
	public void add(BinaryNode parent, BinaryNode x) {
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
	
	public void fillLevels() {
		
	}
}












