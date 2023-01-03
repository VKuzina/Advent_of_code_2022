package advent_of_code.one.utils;

public class Node_int {
	public int value;
	public Node_int next;
	public Node_int previous;
	
	public Node_int(int value, Node_int previous) {
		this.value = value;
		this.previous = previous;
	}
	
	public void setNext(Node_int next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "" + value;
	}
}
