package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Seventeenth {
	
	static int top = 0;
	static int type = -1;
	static int lowest_falling = 0;
	static int leftest_falling = 0;
	static boolean[][] grid = new boolean[7][10000];
	static int all_top = 0;
	static long count = Long.parseLong("1000000000001");
	static List<List<Integer>> height_diffs = new ArrayList<>();
	static int cycle_height_diff = 0;
	static long finish = -1;
	static long times;

	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/seventeenth")).toArray(String[]::new);
		
		char[] chars = lines[0].toCharArray();
		
		for (int i = 0; i < 7; i++) {
			grid[i][0] = true;
		}

		boolean spawn = true;
		long spawn_count = 0;
		long i = 0;
		while(true) {
			i++;
			boolean check = false;
			if (spawn) {
				spawn_count++;
				if (spawn_count == 5000) {
					int len = find_cycle();
					
					long left = count - 5000;
					
					times = left / len;
					
					finish = 5000 + left % len;
				}
				
				if (spawn_count == finish) {
					break;
				}
				spawn = false;
				type = (int) ((spawn_count - 1) % 5);
				lowest_falling = top + 4;
				leftest_falling = 2;
				if (chars[(int)((i-1) % chars.length)] == '<') {
					if (leftest_falling != 0 && move_if_possible(type, 0)) leftest_falling--;
				} else {
					if (leftest_falling != 6 && move_if_possible(type, 1)) leftest_falling++;
				}
			} else {
				switch(type) {
				case 0:
					check = check_minus();
					break;
				case 1:
					check = check_plus();
					break;
				case 2:
					check = check_l();
					break;
				case 3:
					check = check_i();
					break;
				case 4:
					check = check_cube();
					break;
				}
				
				
				if (check) {
					height_diffs.add(calculate_height_array());
					spawn = true;
					i--;
				}
				else {
					lowest_falling--;
					if (chars[(int)((i-1) % chars.length)] == '<') {
						if (leftest_falling != 0 && move_if_possible(type, 0)) leftest_falling--;
					} else {
						if (leftest_falling != 6 && move_if_possible(type, 1)) leftest_falling++;
					}
				}
			}
		}
		
		long result = top + cycle_height_diff * times;
		System.out.println(result);
	}

	private static int find_cycle() {
		List<Integer> last = height_diffs.get(height_diffs.size() - 1);
		
		for (int i = height_diffs.size() - 2; i >= 0; i--) {
			boolean check = true;
			for (int j = 0; j < 7; j++) {
				if (last.get(j) != height_diffs.get(i).get(j)) check = false;
			}
			
			if (check) {
				cycle_height_diff = last.get(7) - height_diffs.get(i).get(7);
				return height_diffs.size() - 1 - i;
			}
		}
		
		return -1;
	}

	private static List<Integer> calculate_height_array() {
		List<Integer> heights = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		for (int j = 0; j < 7; j++) {
			for (int i = top; i >= 0; i--) {
				if (grid[j][i]) {
					heights.add(i);
					min = Math.min(min, i);
					break;
				}
			}
		}
		
		for (int i = 0; i < 7; i++) {
			heights.set(i, heights.get(i) - min);
		}
		
		heights.add(top);
		
		return heights;
	}

	private static boolean move_if_possible(int type, int direction) {
		boolean check = false;
		if (direction == 0) {
			switch(type) {
			case 0:
				check = grid[leftest_falling-1][lowest_falling];
				break;
			case 1:
				check = grid[leftest_falling-1][lowest_falling+1] || 
						grid[leftest_falling][lowest_falling] ||
						grid[leftest_falling][lowest_falling+2];
				break;
			case 2:
				check = grid[leftest_falling-1][lowest_falling] ||
						grid[leftest_falling+1][lowest_falling+1] ||
						grid[leftest_falling+1][lowest_falling+2];
				break;
			case 3:
				check = grid[leftest_falling-1][lowest_falling] ||
						grid[leftest_falling-1][lowest_falling+1] ||
						grid[leftest_falling-1][lowest_falling+2] ||
						grid[leftest_falling-1][lowest_falling+3];
				break;
			case 4:
				check = grid[leftest_falling-1][lowest_falling] ||
						grid[leftest_falling-1][lowest_falling+1];
				break;
			}
		} else {
			switch(type) {
			case 0:
				if (leftest_falling == 3) return false;
				check = grid[leftest_falling+4][lowest_falling];
				break;
			case 1:
				if (leftest_falling == 4) return false;
				check = grid[leftest_falling+3][lowest_falling+1] || 
						grid[leftest_falling+2][lowest_falling] ||
						grid[leftest_falling+2][lowest_falling+2];
				break;
			case 2:
				if (leftest_falling == 4) return false;
				check = grid[leftest_falling+3][lowest_falling] ||
						grid[leftest_falling+3][lowest_falling+1] ||
						grid[leftest_falling+3][lowest_falling+2];
				break;
			case 3:
				if (leftest_falling == 6) return false;
				check = grid[leftest_falling+1][lowest_falling] ||
						grid[leftest_falling+1][lowest_falling+1] ||
						grid[leftest_falling+1][lowest_falling+2] ||
						grid[leftest_falling+1][lowest_falling+3];
				break;
			case 4:
				if (leftest_falling == 5) return false;
				check = grid[leftest_falling+2][lowest_falling] ||
						grid[leftest_falling+2][lowest_falling+1];
				break;
			}
		}
		
		return !check;
	}

	private static boolean check_cube() {
		for (int i = 0; i < 2; i++) {
			if (grid[leftest_falling + i][lowest_falling - 1]) {
				grid[leftest_falling][lowest_falling] = true;
				grid[leftest_falling][lowest_falling + 1] = true;
				grid[leftest_falling + 1][lowest_falling] = true;
				grid[leftest_falling + 1][lowest_falling + 1] = true;

				top = Math.max(top, lowest_falling+1);
				return true;
			}
		}
		
		return false;
	}

	private static boolean check_i() {
		if (grid[leftest_falling][lowest_falling-1]) {
			grid[leftest_falling][lowest_falling] = true;
			grid[leftest_falling][lowest_falling + 1] = true;
			grid[leftest_falling][lowest_falling + 2] = true;
			grid[leftest_falling][lowest_falling + 3] = true;
			
			top = Math.max(top, lowest_falling+3);
			return true;
		}
		
		return false;
	}

	private static boolean check_l() {
		for (int i = 0; i < 3; i++) {
			if (grid[leftest_falling+i][lowest_falling-1]) {
				grid[leftest_falling][lowest_falling] = true;
				grid[leftest_falling + 1][lowest_falling] = true;
				grid[leftest_falling + 2][lowest_falling] = true;
				grid[leftest_falling + 2][lowest_falling + 1] = true;
				grid[leftest_falling + 2][lowest_falling + 2] = true;
				top = Math.max(top, lowest_falling+2);
				return true;
			}
		}
		
		return false;
	}

	private static boolean check_plus() {
		if (grid[leftest_falling][lowest_falling]  ||
			grid[leftest_falling + 1][lowest_falling - 1] ||
			grid[leftest_falling + 2][lowest_falling]) {
			
			grid[leftest_falling][lowest_falling + 1] = true;
			grid[leftest_falling + 1][lowest_falling] = true;
			grid[leftest_falling + 1][lowest_falling + 1] = true;
			grid[leftest_falling + 1][lowest_falling + 2] = true;
			grid[leftest_falling + 2][lowest_falling + 1] = true;
			
			top = Math.max(top, lowest_falling+2);
			
			return true;
		}
		
		return false;
	}

	private static boolean check_minus() {
		for (int i = 0; i < 4; i++) {
			if (grid[leftest_falling + i][lowest_falling - 1]) {
				for (int j = 0; j < 4; j++) {
					grid[leftest_falling + j][lowest_falling] = true;
				}
				
				top = Math.max(top, lowest_falling);
				return true;
			}
		}
		
		return false;
	}
}
