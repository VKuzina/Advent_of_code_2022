package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Eighth {
	static int constant = 14;
	static int height;
	static int width;
	static int[][][] grid;

	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/eighth")).toArray(String[]::new);
		
		height = lines.length;
		width = lines[0].length();
		grid = new int [height][width][1];
		
		for(int i = 0; i < height; i++) {
			char[] chars = lines[i].toCharArray();
			for(int j = 0; j < width; j++) {
				grid[i][j][0] = chars[j]-48;
				//for (int k = 1; k < 6; k++) grid[i][j][k] = -1;
			}
		}
		
		System.out.println(calculate_best_view());
 	}

	private static int calculate() {
		int sum = 0;
		
		for (int i = 0; i < width; i++) {
			grid[0][i][5] = 1;
			grid[0][i][1] = grid[0][i][0];
			grid[0][i][6] = 0;
			grid[height-1][i][5] = 1;
			grid[height-1][i][2] = grid[height-1][i][0];
			grid[height-1][i][7] = 0;
			grid[i][0][5] = 1;
			grid[i][0][3] = grid[i][0][0];
			grid[i][0][8] = 0;
			grid[i][width-1][5] = 1;
			grid[i][width-1][4] = grid[i][width-1][0];
			grid[i][width-1][9] = 0;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				sum += is_visible(i, j);
			}
		}
		
		return calculate_best_view();
	}
	
	private static int calculate_best_view() {
		int max = 0;
		for(int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				max = Math.max(calculate_view(i,j), max);
			}
		}
		
		return max;
	}
	
	private static int calculate_view(int h, int w) {
		int l = 0;
		int r = 0;
		int d = 0;
		int u = 0;
		
		for (int i = 1; i <= h; i++) {
			l++;
			if (grid[h-i][w][0] >= grid[h][w][0]) break;
		}
		
		for (int i = 1; i < height-h; i++) {
			r++;
			if (grid[h+i][w][0] >= grid[h][w][0]) break;
		}
		
		for (int i = 1; i <= w; i++) {
			u++;
			if (grid[h][w-i][0] >= grid[h][w][0]) break;
		}
		
		for (int i = 1; i < width-w; i++) {
			d++;
			if (grid[h][w+i][0] >= grid[h][w][0]) break;
		}
		
		return l * r * d * u;
	}
	
	private static int is_visible(int i, int j) {
		if (grid[i][j][5] != -1) return grid[i][j][5];
		
		grid[i][j][1] = check_left(i,j, 1);
		grid[i][j][2] = check_right(i,j, 2);
		grid[i][j][3] = check_up(i,j, 3);
		grid[i][j][4] = check_down(i,j, 4);
		
		int ret_value = 0;
		
		if(grid[i][j][0] > grid[i][j][1] || grid[i][j][0] > grid[i][j][2] || grid[i][j][0] > grid[i][j][3] || grid[i][j][0] > grid[i][j][4]) {
			ret_value = 1;
		}
		
		grid[i][j][1] = Math.max(grid[i][j][1], grid[i][j][0]);
		grid[i][j][2] = Math.max(grid[i][j][2], grid[i][j][0]);
		grid[i][j][3] = Math.max(grid[i][j][3], grid[i][j][0]);
		grid[i][j][4] = Math.max(grid[i][j][4], grid[i][j][0]);
		
		return ret_value;
	}

	private static int check_left(int i, int j, int dim) {
		if(grid[i-1][j][dim] != -1) return grid[i-1][j][dim];
		
		grid[i-1][j][dim] = Math.max(check_left(i-1, j, dim), grid[i-1][j][0]);
		
		return grid[i-1][j][dim];		
	}
	
	private static int check_right(int i, int j, int dim) {
		if(grid[i+1][j][dim] != -1) return grid[i+1][j][dim];
		
		grid[i+1][j][dim] = Math.max(check_right(i+1, j, dim), grid[i+1][j][0]);
		
		return grid[i+1][j][dim];		
	}
	
	private static int check_up(int i, int j, int dim) {
		if(grid[i][j-1][dim] != -1) return grid[i][j-1][dim];
		
		grid[i][j-1][dim] = Math.max(check_up(i, j-1, dim), grid[i][j-1][0]);
		
		return grid[i][j-1][dim];		
	}
	
	private static int check_down(int i, int j, int dim) {
		if(grid[i][j+1][dim] != -1) return grid[i][j+1][dim];
		
		grid[i][j+1][dim] = Math.max(check_down(i, j+1, dim), grid[i][j+1][0]);
		
		return grid[i][j+1][dim];		
	}
}
