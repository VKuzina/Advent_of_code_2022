package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Twentysecond {
	
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/twentysecond2")).toArray(String[]::new);
		
		int max_len = 0;
		for (String line : lines) {
			if (line.equals("")) {
				break;
			}
			
			max_len = Math.max(max_len, line.length());
		}
		
		int[][] grid = new int[lines.length - 2][max_len];
		
		for(int i = 0; i < lines.length - 2; i++) {
			char[] chars = lines[i].toCharArray();
			for (int j = 0; j < chars.length; j++) {
				if (chars[j] == '.') grid[i][j] = 1;
				else if (chars[j] == '#') grid[i][j] = 2;
				else grid[i][j] = 0;
			}
		}
		
		char[] instructions = lines[lines.length-1].toCharArray();
		
		int x = 0;
		int y = 0;
		int dir = 0;
		for (int j = 0; j < max_len; j++) {
			if (grid[0][j] == 1) {
				x = j;
				break;
			}
		}
		
		int cur_ins = 0;
		while (cur_ins < instructions.length) {
			String amount = "";
			if (Character.isAlphabetic(instructions[cur_ins])) {
				if (instructions[cur_ins] == 'R') {
					dir = (dir + 1) % 4;
				} else {
					dir = ((dir - 1) + 4) % 4;
				}
				
				cur_ins++;
				continue;
			}

			while (!Character.isAlphabetic(instructions[cur_ins])) {
				amount += instructions[cur_ins];
				cur_ins++;
				if (cur_ins >= instructions.length - 1) {
					break;
				}
			}
			
			int steps = Integer.parseInt(amount);
			for (int i = 0; i < steps; i++) {
				switch(dir) {
				case 0: 
					if (x == max_len - 1 || grid[y][x+1] == 0) {
						int x_n = x - 1;
						while (x_n != 0 && grid[y][x_n] != 0) {
							x_n--;
						}
						
						if (x_n != 0) x_n++;
						if (grid[y][x_n] == 2) continue;
						else x = x_n;
					} else if (grid[y][x+1] == 1) {
						x++;
					} else {
						continue;
					}
					break;
				case 1:
					if (y == lines.length - 3 || grid[y+1][x] == 0) {
						int y_n = y - 1;
						while (y_n != 0 && grid[y_n][x] != 0) {
							y_n--;
						}
						
						if (y_n != 0) y_n++;
						if (grid[y_n][x] == 2) continue;
						else y = y_n;
					} else if (grid[y+1][x] == 1) {
						y++;
					} else {
						continue;
					}
					break;
				case 2: 
					if (x == 0 || grid[y][x-1] == 0) {
						int x_n = x + 1;
						while (x_n < max_len - 1 && grid[y][x_n] != 0) {
							x_n++;
						}
						
						if (x_n != max_len - 1) x_n--;
						if (grid[y][x_n] == 2) continue;
						else x = x_n;
					} else if (grid[y][x-1] == 1) {
						x--;
					} else {
						continue;
					}
					break;
				case 3:
					if (y == 0 || grid[y-1][x] == 0) {
						int y_n = y + 1;
						while (y_n < lines.length - 3 && grid[y_n][x] != 0) {
							y_n++;
						}
						
						if (y_n != lines.length - 3) y_n--;
						if (grid[y_n][x] == 2) continue;
						else y = y_n;
					}
					else if (grid[y-1][x] == 1) {
						y--;
					} else {
						continue;
					}
					break;
				}
			}
		}
		
		System.out.println((y+1) * 1000 + (x+1) * 4 + dir);
	}
}
