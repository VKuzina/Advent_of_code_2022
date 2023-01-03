package advent_of_code.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import advent_of_code.one.utils.Element;
import advent_of_code.one.utils.Pair;

public class Fifteenth {
	static int max = 4000000;
	static int max2 = 4000000;
	public static void main(String[] args) throws IOException {
		String[] lines = Files.lines(Paths.get("./src/advent_of_code/inputs/fifteenth")).toArray(String[]::new);
		
		int[][] info = new int[lines.length][5];
		int max_dist = 0;
		int x_min = Integer.MAX_VALUE;
		int x_max = Integer.MIN_VALUE;
		for (int i = 0; i < lines.length; i++) {
			String[] parts = lines[i].split(":");
			String[] sensor_loc = parts[0].trim().split(",");
			String[] beacon_loc = parts[1].trim().split(",");
			
			int s_x = Integer.parseInt(sensor_loc[0].trim().split("=")[1]);
			int s_y = Integer.parseInt(sensor_loc[1].trim().split("=")[1]);
			int b_x = Integer.parseInt(beacon_loc[0].trim().split("=")[1]);
			int b_y = Integer.parseInt(beacon_loc[1].trim().split("=")[1]);
			
			int dist = manhattan(s_x, s_y, b_x, b_y);
			max_dist = Math.max(max_dist, dist);
			x_max = Math.max(x_max, s_x);
			x_min = Math.min(x_min, s_x);
			
			info[i] = new int[] {s_x, s_y, dist, b_x, b_y};
		}
		
//		int js = 2000000;
//		
//		int sum = 0;
//		int all = 0;
//		for (int i = x_min - max_dist; i < x_max + max_dist; i++) {
//			all++;
//			boolean check = false;
//			
//
//			for (int k = 0; k < lines.length; k++) {
//				if (manhattan(i,js,info[k][0],info[k][1]) <= info[k][2]) check = true;
//			}
//
//			for (int k = 0; k < lines.length; k++) {
//				if (info[k][3] == i && info[k][4] == js) {
//					check = false;
//				}
//			}
//			if (check) sum++;
//		}
//		
//		
		int min_dif = Integer.MAX_VALUE;
		for (int j = 0; j < max; j++) {
		
			int mini = -1;
			int maxi = max + 1;
			//System.out.println(j);
			
			List<Pair> pairs = new ArrayList<>();
			for (int k = 0; k < lines.length; k++) {
				int bound = info[k][2] - Math.abs(info[k][1] - j);
				if (bound < 0) continue;
				else if (bound == 0) pairs.add(new Pair(info[k][0], info[k][0]));
				else {
					int[] x_cords = x_cords(info[k][0], info[k][1], info[k][2], j);
					if (manhattan(info[k][0], info[k][1], x_cords[0], j) != info[k][2]) System.out.println("smrt");
					if (manhattan(info[k][0], info[k][1], x_cords[1], j) != info[k][2]) System.out.println("smrt2");
					if (x_cords[1] <= mini + 1 || x_cords[0] >= maxi - 1) continue;
					else if (x_cords[0] <= mini + 1 && x_cords[1] >= mini + 1) mini = x_cords[1];
					else if (x_cords[0] <= maxi - 1 && x_cords[1] >= maxi - 1) maxi = x_cords[0];
					else pairs.add(new Pair(x_cords[0], x_cords[1]));
				}
			}
			
			while(true) {
				List<Pair> next_pairs = new ArrayList<>();
				for (Pair pair : pairs) {
					if (pair.to <= mini + 1 || pair.from >= maxi - 1) continue;
					else if (pair.from <= mini + 1 && pair.to >= mini + 1) mini = pair.to;
					else if (pair.from <= maxi - 1 && pair.to >= maxi - 1) maxi = pair.from;
					else next_pairs.add(pair);
				}
				
				if (next_pairs.size() == pairs.size()) {
					break;
				} else {
					pairs = next_pairs;
				}
			}
			
			min_dif = Math.min(min_dif, mini - maxi);
			if (mini < maxi) {
				System.out.println(mini + " " + j);
				System.out.println((long)(mini + 1) * (long)max + (long)j);
			}
		}
		
		System.out.println(min_dif);
	}

	private static int manhattan(int s_x, int s_y, int b_x, int b_y) {
		return Math.abs(s_x - b_x) + Math.abs(s_y - b_y);
	}
	
	private static int[] x_cords(int x_s, int y_s, int dist, int y) {
		int first = x_s - dist + Math.abs(y_s - y);
		int second = x_s + dist - Math.abs(y_s - y);
		
		return new int[] {Math.min(first, second), Math.max(first, second)};
	}
}
