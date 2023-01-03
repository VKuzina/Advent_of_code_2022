package advent_of_code.one.utils;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public int value;
	public List<Node> children;
	public Node parent;
	public String name;
	
	static int maxsize = 70000000;
	static int required_size = 30000000;
	static int free_space;
	
	public Node() {
		this.value = 0;
		this.children = new ArrayList<>();
		this.parent = null;
	}
	
	public Node(int value, Node parent, String name) {
		this.value = value;
		this.children = new ArrayList<>();
		this.parent = parent;
		this.name = name;
	}
	
	public void add_child(Node child) {
		this.children.add(child);
	}
	
	public void add_value(int value) {
		this.value += value;
	}
	
	public int child_value_update() {
		for (Node child : this.children) {
			this.value += child.child_value_update();
		}
		
		return this.value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int find_smalles_cutoff(int cutoff) {
		if (this.value < cutoff) return -1;
		int current = this.value;
		for (Node child : children) {
			int childs = child.find_smalles_cutoff(cutoff);
			if (childs != -1) current = Math.min(current, childs);
		}
		
		return current;
	}
	
	public int sum_bigger_then(int cutoff) {
		int sum = 0;
		if (this.value < cutoff) {
			sum += value;
		}
		
		for (Node child : this.children) {
			sum += child.sum_bigger_then(cutoff);
		}
		
		return sum;
	}
}