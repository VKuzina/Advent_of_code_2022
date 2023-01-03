package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import advent_of_code.one.utils.State;

public class Sixteenth {
	
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/sixteenth")).toArray(String[]::new);
		
		State start = null;
		List<State> states = new ArrayList<>();
		for (String line : lines) {
			String[] parts = line.split(";");
			
			State cur = new State(parts[0].split(" ")[1], Integer.parseInt(parts[0].split("=")[1]));
			states.add(cur);
			
			if (cur.temp_id.equals("AA")) start = cur;
		}
		
		for (int i = 0; i < states.size(); i++) {
			states.get(i).id = i;
			states.get(i).add_length(states.size());
		}
		
		for (String line : lines) {
			String[] parts = line.split(";");
			
			String temp_id = parts[0].split(" ")[1];
			State cur = null;
			for (State s : states) {
				if (s.temp_id.equals(temp_id)) cur = s;
			}

			List<State> connected = new ArrayList<>();
			
			String[] sec_parts = parts[1].trim().split(",");
			String id = sec_parts[0].split(" ")[4];
			for (State state : states) {
				if (state.temp_id.equals(id)) connected.add(state);
			}
			
			for (int i = 1; i < sec_parts.length; i++) {
				id = sec_parts[i].trim();
				for (State state : states) {
					if (state.temp_id.equals(id)) connected.add(state);
				}
			}
			
			cur.add_neighbors(connected);
		}
		
		List<State> current_states = new ArrayList<>();
		current_states.add(start);
		for (int i = 0; i < 30; i++) {
			System.out.println(i + " " + current_states.size());
			List<State> next_states = new ArrayList<>();
			for (State s : current_states) {
				for (State nei : s.neighbors) {
					boolean check = true;
					List<Integer> to_delete = new ArrayList<>();
					for (int j = 0; j < next_states.size(); j++) {
						State existing = next_states.get(j);
						if (existing.id == nei.id) {
							if (existing.value > s.value && existing.equal_visited(s)) {
								check = false;
								break;
							}
							if (s.value > existing.value && s.equal_visited(existing)) to_delete.add(j);
						}
					}
					Collections.reverse(to_delete);
					for (int d : to_delete) {
						next_states.remove(d);
					}
					
					if (check) {
						State next_state = nei.copy();
						next_state.value = s.value;
						next_state.visited = new LinkedHashSet<>(s.visited);
						next_states.add(next_state);
					}
				}
				
				if (s.flow != 0 && !s.visited.contains(s.id)) {
					boolean check = true;
					List<Integer> to_delete = new ArrayList<>();
					for (int j = 0; j < next_states.size(); j++) {
						State existing = next_states.get(j);
						
						if (existing.id == s.id) {
							if (existing.value > s.value && existing.equal_visited(s)) {
								check = false;
								break;
							}
							if (s.value > existing.value && s.equal_visited(s)) to_delete.add(j); 
						}
					}
					
					Collections.reverse(to_delete);
					for (int d : to_delete) {
						next_states.remove(d);
					}
					
					if (check) {
						State copy = s.copy();
						copy.value = s.value + (30 -(i+1)) * s.flow;
						copy.visited.add(copy.id);
						next_states.add(copy);
					}
				}
			}
			current_states = next_states;
		}
		
		int max = 0;
		for (State s : current_states) {
			System.out.println(s.value);
			max = Math.max(max, s.value);
		}
		System.out.println(max);
	}
}
