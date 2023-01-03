package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Eightteenth {
	static boolean[][][] grid = new boolean[20][20][20];

	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/eightteenth")).toArray(String[]::new);
		
		int count = 0;
		for (String line : lines) {
			String[] parts = line.split(",");
			
			int x = Integer.parseInt(parts[0]);
			int y = Integer.parseInt(parts[1]);
			int z = Integer.parseInt(parts[2]);
			
			grid[x][y][z] = true;
		}
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				for (int k = 0; k < 20; k++) {
					if (grid[i][j][k]) {
						count += 6;
						if (k > 0 && grid[i][j][k-1]) count -= 1;
						if (k!= 19 && grid[i][j][k+1]) count -= 1;
						if (j > 0 && grid[i][j-1][k]) count -= 1;
						if (j!= 19 && grid[i][j+1][k]) count -= 1;
						if (i > 0 && grid[i-1][j][k]) count -= 1;
						if (i!= 19 && grid[i+1][j][k]) count -= 1;
					}
				}
			}
		}
		
		grid[0][0][0] = true;
		fill_out_adjacent(0,0,0);
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				for (int k = 0; k < 20; k++) {
					if (!grid[i][j][k]) {
						if (k > 0 && grid[i][j][k-1]) count -= 1;
						if (k!=19 && grid[i][j][k+1]) count -= 1;
						if (j > 0 && grid[i][j-1][k]) count -= 1;
						if (j!=19 && grid[i][j+1][k]) count -= 1;
						if (i > 0 && grid[i-1][j][k]) count -= 1;
						if (i!=19 && grid[i+1][j][k]) count -= 1;
					}
				}
			}
		}
		
		System.out.println(count);
	}

	private static void fill_out_adjacent(int i, int j, int k) {
		if (k != 0 && !grid[i][j][k-1]) {
			grid[i][j][k-1] = true;
			fill_out_adjacent(i, j, k-1);
		}
		if (k != 19 && !grid[i][j][k+1]) {
			grid[i][j][k+1] = true;
			fill_out_adjacent(i, j, k+1);
		}
		if (j != 0 && !grid[i][j-1][k]) {
			grid[i][j-1][k] = true;
			fill_out_adjacent(i, j-1, k);
		}
		if (j != 19 && !grid[i][j+1][k]) {
			grid[i][j+1][k] = true;
			fill_out_adjacent(i, j+1, k);
		}
		if (i != 0 && !grid[i-1][j][k]) {
			grid[i-1][j][k] = true;
			fill_out_adjacent(i-1, j, k);
		}
		if (i != 19 && !grid[i+1][j][k]) {
			grid[i+1][j][k] = true;
			fill_out_adjacent(i+1, j, k);
		}
	}
}
