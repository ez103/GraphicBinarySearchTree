public class BinaryNode {
	private BinaryNode left, right;
	private Comparable myValue;
	
	public BinaryNode(Comparable x) {
		myValue = x;
		left = null;
		right = null;
	}
	
	public void setLeft(BinaryNode bn) {
		left = bn;
	}
	public void setRight(BinaryNode bn) {
		right = bn;
	}
	
	public BinaryNode left() {
		return left;
	}
	public BinaryNode right() {
		return right;
	}
	
	public Comparable getValue() {
		return myValue;
	}
}