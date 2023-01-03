package advent_of_code.one.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class State {
	public String temp_id;
	public int id;
	public int flow;
	public List<State> neighbors;
	public int value;
	public Set<Integer> visited;
	
	public State(String temp_id, int flow) {
		this.temp_id = temp_id;
		this.flow = flow;
		visited = new LinkedHashSet<>();
	}
	
	public State(int id, int flow, List<State> neighbors, int value, Set<Integer> visited) {
		this.id = id;
		this.flow = flow;
		this.neighbors = neighbors;
		this.value = value;
		this.visited = visited;
	}

	public void add_neighbors(List<State> neighbors) {
		this.neighbors = neighbors;
	}
	
	public void add_length(int length) {
		this.visited = new LinkedHashSet<>();
	}
	
	public State copy() {
		return new State(this.id, this.flow, this.neighbors, this.value, new LinkedHashSet<>(this.visited));
	}
	
	public String toString() {
		return id + " " + value;
	}
	
	public boolean equal_visited(State o) {
		return this.visited.containsAll(o.visited);
	}
}
