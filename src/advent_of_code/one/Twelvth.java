package advent_of_code.one;

import java.io.IOException;
import java.lang.Thread.State;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

import advent_of_code.one.utils.LocationState;

public class Twelvth {
	static int[][] grid;
	static boolean[][] visited;
	static int height;
	static int width;
	
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/twelvth")).toArray(String[]::new);
		height = lines.length;
		width = lines[0].length();
		
		grid = new int[height][width];
		visited = new boolean[height][width];
		int x_end = -1;
		int y_end = -1;
		int x_start = -1;
		int y_start = -1;
		
		for(int i = 0; i < height; i++) {
			char[] in = lines[i].toCharArray();
			for (int j = 0; j < width; j++) {
				char c = in[j];
				if (c == 'E') {
					x_end = i;
					y_end = j;
					
					grid[i][j] = 'z'- 97;
				} else if (c == 'S') {
					x_start = i;
					y_start = j;
					
					grid[i][j] = 'a' - 97;
				} else {
					grid[i][j] = c - 97;
				}
			}
		}
		
		Queue<LocationState> states = new LinkedList<>();
		LocationState current_state = new LocationState(x_end, y_end, 0);
		
		while(true) {
			if (visited[current_state.i][current_state.j]) {
				current_state = states.remove();
				continue;
			}
			visited[current_state.i][current_state.j] = true;
			if (grid[current_state.i][current_state.j] == 0) {
				System.out.println(current_state.steps);
				break;
			}
			
			//System.out.println(current_state.i + " " + current_state.j + " " + current_state.steps);
			add_adjacent_states(current_state, states);
			
			current_state = states.remove();
		}
		
		
	}
	
	public static void add_adjacent_states(LocationState current_state, Queue<LocationState> states) {
		for (int i = 0; i < 4; i++) {
			add_state(current_state, states, i);
		}
	}

	public static void add_state(LocationState current_state, Queue<LocationState> states, int dir) {
		int x = current_state.i;
		int y = current_state.j;
		if (dir == 0) {
			x = current_state.i + 1;
			if (x >= height) return;
		} else if (dir == 1) {
			x = current_state.i - 1;
			if (x < 0) return;
		} else if (dir == 2) {
			y = current_state.j + 1;
			if (y >= width) return;
		} else {
			y = current_state.j - 1;
			if (y < 0) return;
		}
		
		if (!visited[x][y] && grid[x][y] - grid[current_state.i][current_state.j] >= -1 ) {
			states.add(new LocationState(x, y, current_state.steps + 1));
			
		}
	}
		

}
