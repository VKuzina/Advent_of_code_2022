package advent_of_code.one.utils;

import java.util.ArrayList;
import java.util.List;

public class Element implements Comparable<Element>{
	List<Element> children;
	int value = -1;
	Element parent;
	int current;
	int index;
	
	public Element(Element parent, int value) {
		this.value = value;
		this.parent = parent;
	}
	
	public Element(Element parent) {
		this.parent = parent;
		this.children = new ArrayList<>();
	}
	
	
	public void add_child(String val) {
		children.add(new Element(this, Integer.parseInt(val)));
	}
	
	public Element add_list() {
		Element child = new Element(this);
		children.add(child);
		return child;
	}
	
	public Element get_parent() {
		return parent;
	}
	
	public Element get_child(int i) {
		return children.get(i);
	}
	
	public boolean has_children() {
		if (this.children == null || this.children.size() == 0) return false;
		return true;
	}
	
	public List<Element> get_children() {
		return this.children;
	}
	
	public int get_value() {
		return this.value;
	}

	@Override
	public int compareTo(Element second) {
		List<Element> fc = this.get_children();
		List<Element> sc = second.get_children();

		if (this.has_children() && second.has_children()) {
			for(int i = 0; i < fc.size(); i++) {
				if (sc.size() <= i) return 1;
				int comparison = fc.get(i).compareTo(sc.get(i));
				if (comparison == 0) continue;
				return comparison;
			}
			
			if (fc.size() < sc.size()) return -1;
		} else if (this.has_children() && !second.has_children()) {
			if (second.get_value() != -1) {
				int result = this.get_child(0).compareTo(second);
				if (result != 0) return result;
				if (fc.size() > 1) return 1;
			} else return 1;
		} else if (!this.has_children() && second.has_children()) {
			if (this.get_value() != -1) {
				int result = this.compareTo(second.get_child(0));
				if (result != 0) return result;
				if (sc.size() > 1) return -1;
			} else return -1;
		} else {
			if (this.get_value() < second.get_value()) return -1;
			else if (second.get_value() < this.get_value()) return 1;
			else return 0;
		}
		
		return 0;
	}
	
	public void set_index(int index) {
		this.index = index;
	}
	
	public int get_index() {
		return this.index;
	}
}
