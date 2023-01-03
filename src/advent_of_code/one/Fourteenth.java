package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import advent_of_code.one.utils.Element;

public class Fourteenth {
	static boolean[][] grid = new boolean[200][1000];
	
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/fourteenth")).toArray(String[]::new);
		
		int lowest = 0;
		boolean[][] grid = new boolean[200][1000];
		for (String line : lines) {
			String[] parts = line.split("->");
			
			String[] start = parts[0].trim().split(",");
			int x1 = Integer.parseInt(start[0]);
			int y1 = Integer.parseInt(start[1]);
			
			
			grid[y1][x1] = true;
			for (int i = 1; i < parts.length; i++) {
				String[] location = parts[i].trim().split(",");
				int x2 = Integer.parseInt(location[0]);
				int y2 = Integer.parseInt(location[1]);
				
				int x_s = Math.min(x1, x2);
				int y_s = Math.min(y1, y2);
				int x_e = Math.max(x1, x2);
				int y_e = Math.max(y1, y2);
				
				for (int n = x_s; n <= x_e; n++) {
					for (int m = y_s; m <= y_e; m++) {
						grid[m][n] = true;
					}
				}
				
				lowest = Math.max(y_e, lowest);
				x1 = x2;
				y1 = y2;
			}
		}
		
		for (int i = 0; i < 1000; i++) {
			grid[lowest + 2][i] = true;
		}
			
		int sum = 0;
		while (true) {
			int x = 500;
			int y = 0;
			
			if (grid[y][x] == true) {
				System.out.println(sum);
				break;
			}
			while (true) {
				if (grid[y+1][x] == false) {
					y++;
				} else if (grid[y+1][x-1] == false) {
					y++;
					x--;
				} else if (grid[y+1][x+1] == false) {
					y++;
					x++;
				} else {
					grid[y][x] = true;
					sum++;
					break;
				}
			}
		}		
	}
}
