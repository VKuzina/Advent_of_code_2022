package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ninth {
	static int[][] commands;
	static boolean[][] visited;
	static int[][] locations;
	
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/ninth")).toArray(String[]::new);
		
		commands = new int[lines.length][2];
		for (int i = 0; i < lines.length; i++) {
			String[] parts = lines[i].split(" ");
			commands[i][0] = parse_direction(parts[0]);
			commands[i][1] = Integer.parseInt(parts[1]);
		}
		
		int[] dimensions = get_dimensions();
		
		visited = new boolean[dimensions[1]][dimensions[0]];
		
		locations = new int[10][2];
		for (int i = 0; i < 10; i++) {
			locations[i][0] = dimensions[2];
			locations[i][1] = dimensions[3];
		}
		visited[locations[9][1]][locations[9][0]] = true;
		
		for (int i = 0; i < commands.length; i++) {
			for (int j = 0; j < commands[i][1]; j++) {
				simulate_single(commands[i][0]);
			}
		}
		
		int sum = 0;
		
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				if (visited[i][j]) sum += 1;
			}
		}
		
		System.out.println(sum);
	}
	

	private static void simulate_single(int dir) {
		switch(dir) {
		case 1:
			locations[0][0] -= 1;
			break;
		case 2:
			locations[0][0] += 1;
			break;
		case 3:
			locations[0][1] -= 1;
			break;
		case 4:
			locations[0][1] += 1;
			break;
		}
		
		move_tails();
	}
	
	


	private static void move_tails() {
		for (int i = 0; i < 9; i++) {
			move_tail(i);
		}
		
		visited[locations[9][1]][locations[9][0]] = true;
	}


	private static void move_tail(int i) {
		if (locations[i][0] == locations[i+1][0]) {
			if (Math.abs(locations[i][1]-locations[i+1][1]) <= 1) return;
			else if (locations[i][1] > locations[i+1][1]) locations[i+1][1]++;
			else locations[i+1][1]--;
		} else if(locations[i][1] == locations[i+1][1]) {
			if (Math.abs(locations[i][0]-locations[i+1][0]) <= 1) return;
			else if (locations[i][0] > locations[i+1][0]) locations[i+1][0]++;
			else locations[i+1][0]--; 
		} else {
			if (locations[i][0] - locations[i+1][0] > 1) {
				locations[i+1][0]++;
				if (locations[i][1] > locations[i+1][1]) locations[i+1][1]++;
				else locations[i+1][1]--;
			} else if (locations[i][0] - locations[i+1][0] < -1) {
				locations[i+1][0]--;
				if (locations[i][1] > locations[i+1][1]) locations[i+1][1]++;
				else locations[i+1][1]--;
			} else if (locations[i][1] - locations[i+1][1] > 1) {
				locations[i+1][1]++;
				if (locations[i][0] > locations[i+1][0]) locations[i+1][0]++;
				else locations[i+1][0]--;
			} else if (locations[i][1] - locations[i+1][1] < -1) {
				locations[i+1][1]--;
				if (locations[i][0] > locations[i+1][0]) locations[i+1][0]++;
				else locations[i+1][0]--;
 			} else return;
		}
	}


	private static int[] get_dimensions() {
		int left_size = 0;
		int right_size = 0;
		int up_size = 0;
		int down_size = 0;
		int cur_lr = 0;
		int cur_ud = 0;
		for (int i = 0; i < commands.length; i++) {
			switch(commands[i][0]) {
			case 1:
				cur_lr -= commands[i][1];
				left_size = Math.min(cur_lr, left_size);
				break;
			case 2:
				cur_lr += commands[i][1];
				right_size = Math.max(cur_lr, right_size);
				break;
			case 3:
				cur_ud -= commands[i][1];
				up_size = Math.min(cur_ud, up_size);
				break;
			default:
				cur_ud += commands[i][1];
				down_size = Math.max(cur_ud, down_size);
			}
		}
		
		return new int[]{right_size - left_size + 1, down_size - up_size + 1, -left_size, -up_size};
	}

	public static int parse_direction(String s) {
		if(s.equals("L")) return 1;
		else if (s.equals("R")) return 2;
		else if (s.equals("U")) return 3;
		else return 4;
	}
}
